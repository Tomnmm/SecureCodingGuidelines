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
 
## Examples
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
