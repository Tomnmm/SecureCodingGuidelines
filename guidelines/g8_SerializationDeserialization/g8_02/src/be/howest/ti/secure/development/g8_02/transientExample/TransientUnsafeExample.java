package guidelines.g8_SerializationDeserialization.g8_02.src.be.howest.ti.secure.development.g8_02.transientExample;

import java.io.*;

public class TransientUnsafeExample implements Serializable {
    private String nonSensitiveData;
    private String sensitiveData;

    // Default constructor
    public TransientUnsafeExample(String nonSensitiveData, String sensitiveData) {
        this.nonSensitiveData = nonSensitiveData;
        this.sensitiveData = sensitiveData;
    }

    public static void main(String[] args) {
        TransientUnsafeExample objToSerialize = new TransientUnsafeExample("I wanna be serialized and transferred", "Please don't serialize me");

        // Serialization
        try {
            //Save object in a file
            FileOutputStream file = new FileOutputStream("serialFile.dat");
            ObjectOutputStream out = new ObjectOutputStream(file);

            //Print the content of the object
            System.out.println("Object before serialization");
            System.out.println("Non Sensitive Data = " + objToSerialize.nonSensitiveData);
            System.out.println("Sensitive Data = " + objToSerialize.sensitiveData);

            //Serialization of object
            out.writeObject(objToSerialize);

            out.close();
            file.close();

            System.out.println("Object has been serialized \n");

        } catch (IOException  ex) {
            System.out.println("Serialization exception is caught \n");
        }


        TransientUnsafeExample objToDeserialize = null;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("SerialFile.dat");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            objToDeserialize = (TransientUnsafeExample) in.readObject();

            in.close();
            file.close();

            //Print the content of the deserialized object
            System.out.println("Object has been deserialized ");
            System.out.println("Non Sensitive Data = " + objToDeserialize.nonSensitiveData);
            System.out.println("Sensitive Data = " + objToDeserialize.sensitiveData);

        } catch(IOException | ClassNotFoundException ex){
            System.out.println("Deserialization exception is caught");
        }

    }
}
