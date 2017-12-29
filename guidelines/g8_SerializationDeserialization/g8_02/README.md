# SERIAL-2: Guard sensitive data during serialization
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Once an object has been serialized the Java language's access controls can no longer be enforced and attackers can access private fields in an object by analyzing its serialized byte stream. Therefore, do not serialize sensitive data in a serializable class.

Approaches for handling sensitive fields in serializable classes are:

 - Declare sensitive fields transient
 - Define the serialPersistentFields array field appropriately
 - Implement writeObject and use ObjectOutputStream.putField selectively
 - Implement writeReplace to replace the instance with a serial proxy
 - Implement the Externalizable interface

## Declare sensitive fields transient
![Author](https://img.shields.io/badge/Author-Ben-blue.svg)
![Date](https://img.shields.io/badge/Date-20171228-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

### Information regarding transient variables
Variables may be marked transient to indicate that they are not part of the persistent state of an object. [[1]](https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.3.1.3)  
`transient` is a Java keyword which marks a member variable not to be serialized when it is persisted to streams of bytes.  
 When an object is transferred through the network, the object needs to be 'serialized'. Serialization converts the object state to serial bytes. Those bytes are sent over the network and the object is recreated from those bytes. 
 Member variables marked by the **java `transient` keyword are not transferred; they are lost intentionally**. [[2]](https://en.wikibooks.org/wiki/Java_Programming/Keywords/transient) 
 
Example:  
```
public class TransientSaveExample implements Serializable {  
      private transient String sensitiveData;  
}
```
 
### Examples
**TransientUnsafeExample.java**  
In the unsafe example the variable containing the sensitive data is not declared `transient`. Because of this, the file *SerialFile.dat* contains this sensitive data.   
Output:   
```
Object before serialization
Non Sensitive Data = I wanna be serialized and transferred
Sensitive Data = Please don't serialize me
Object has been serialized 

Object has been deserialized 
Non Sensitive Data = I wanna be serialized and transferred
Sensitive Data = Please don't serialize me
```   

**TransientSaveExample.java**  
In the safe example the variable containing the sensitive data is declared `transient`. Because of this, the file *SerialFile.dat* does not contains the sensitive data.   
Output:
```
Object before serialization
Non Sensitive Data = I wanna be serialized and transferred
Sensitive Data = Please don't serialize me
Object has been serialized 

Object has been deserialized 
Non Sensitive Data = I wanna be serialized and transferred
sensitiveData = null
```   

## Define the serialPersistentFields array field appropriately & Implement writeObject and use ObjectOutputStream.putField selectively
![Author](https://img.shields.io/badge/Author-Ben-blue.svg)
![Date](https://img.shields.io/badge/Date-20171229-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

### Information
The text below is an almost copy/paste from: <https://docs.oracle.com/javase/7/docs/platform/serialization/spec/serial-arch.html#6250>

**Defining Serializable Fields for a Class**    
Default serializable fields of a class are defined to be the non-transient and non-static fields. 
This default computation can be overridden by declaring a special field in the Serializable class, `serialPersistentFields`. 
This field must be initialized with an array of `ObjectStreamField` objects that list the names and types of the serializable fields. 
The modifiers for the field are required to be **private**, **static**, and **final**. 
If the field's value is null or is otherwise not an instance of `ObjectStreamField[]`, 
or if the field does not have the required modifiers, then the behavior is as if the field were not declared at all. 

```
class SerialPersistentFieldsClass implements Serializable {
    private String nonSensitiveData;

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("nonSensitiveData", String.class)
    };
}
```
By using `serialPersistentFields` to define the Serializable fields for a class, there no longer is a limitation that a serializable field must be a field within the current definition of the Serializable class.  
The `writeObject` and `readObject` methods of the Serializable class overrides the default mechanism and can map the current implementation of the class to the serializable fields of the class using the interface that is described in [Oracle Docs - Section 1.7, "Accessing Serializable Fields of a Class"](https://docs.oracle.com/javase/7/docs/platform/serialization/spec/serial-arch.html#525) (see also below).
Therefore, the fields for a Serializable class can change in a later release, 
as long as it maintains the mapping back to its Serializable fields that must remain compatible across release boundaries. 

```
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        // get the field and assign it
        ObjectInputStream.GetField fields = in.readFields();

         nonSensitiveData =(String) fields.get("nonSensitiveData", "");
    }
```
```
    private void writeObject(ObjectOutputStream out) throws IOException {

        // write into the ObjectStreamField array the variable
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("nonSensitiveData", nonSensitiveData);
        out.writeFields();
    }
```
> There is a limitation to the use of this mechanism to specify serializable fields for **inner classes**. 
Inner classes can only contain final static fields that are initialized to constants or expressions built up from constants. 
Consequently, it is not possible to set serialPersistentFields for an inner class (though it is possible to set it for static member classes)

**Accessing Serializable Fields of a Class**  
When the `writeObject` and `readObject` methods are implemented, 
the class has an opportunity to modify the serializable field values before they are written or after they are read. 
When the default mechanism cannot be used, 
the serializable class can use the putFields method of `ObjectOutputStream` to put the values for the serializable fields into the stream. 
The `writeFields` method of `ObjectOutputStream` puts the values in the correct order, 
then writes them to the stream using the existing protocol for serialization. 
Correspondingly, the `readFields` method of `ObjectInputStream` reads the values from the stream and makes them available to the class by name in any order. 

> If the class provides `writeObject` and `readObject` methods, the default mechanism can be invoked by calling `defaultWriteObject` and `defaultReadObject`.  

> See [Oracle Docs - Section 2.2, "The ObjectOutputStream.PutField Class"](https://docs.oracle.com/javase/7/docs/platform/serialization/spec/output.html#5068) 
and [Oracle Docs - Section 3.2, "The ObjectInputStream.GetField Class"](https://docs.oracle.com/javase/7/docs/platform/serialization/spec/input.html#4936) for a detailed description of the Serializable Fields API.

### Example
Note:
- The string `sensitiveData` in the class `SerialPersistentFieldsClass` is not declared `transient`.  
- The `main` method from the classes `RunSerialPersistentFields` and `TransientSaveExample` are the same.

**Run** `RunSerialPersistentFields` to see the example in action.  
Output:  
```
Object before serialization
Non Sensitive Data = I wanna be serialized and transferred
Sensitive Data = Please don't serialize me
Object has been serialized 

Object has been deserialized 
Non Sensitive Data = I wanna be serialized and transferred
Sensitive Data = null
```
> Reference: [docs.oracle.com](https://docs.oracle.com/javase/8/docs/platform/serialization/spec/examples.html)
