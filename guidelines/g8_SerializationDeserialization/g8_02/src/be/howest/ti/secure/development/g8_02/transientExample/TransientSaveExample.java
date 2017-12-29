package be.howest.ti.secure.development.g8_02.transientExample;

import java.io.*;

public class TransientSaveExample implements Serializable {
    private String nonSensitiveData;
    private transient String sensitiveData;

    // Default constructor
    public TransientSaveExample(String nonSensitiveData, String sensitiveData) {
        this.nonSensitiveData = nonSensitiveData;
        this.sensitiveData = sensitiveData;
    }

    public static void main(String[] args) {
        TransientSaveExample objToSerialize = new TransientSaveExample("I wanna be serialized and transferred", "Please don't serialize me");

        //Print the content of the object
        System.out.println("Object before serialization");
        System.out.println("Non Sensitive Data = " + objToSerialize.nonSensitiveData);
        System.out.println("Sensitive Data = " + objToSerialize.sensitiveData);

        // Serialization
        try {
            //Save object in a file
            FileOutputStream file = new FileOutputStream("serialFile.dat");
            ObjectOutputStream out = new ObjectOutputStream(file);

            //Serialization of object
            out.writeObject(objToSerialize);

            out.close();
            file.close();

            System.out.println("Object has been serialized \n");

        } catch (IOException  ex) {
            System.out.println("Serialization exception is caught \n");
        }


        TransientSaveExample objToDeserialize = null;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("SerialFile.dat");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            objToDeserialize = (TransientSaveExample) in.readObject();

            in.close();
            file.close();

            //Print the content of the deserialized object
            System.out.println("Object has been deserialized ");
            System.out.println("Non Sensitive Data = " + objToDeserialize.nonSensitiveData);
            System.out.println("sensitiveData = " + objToDeserialize.sensitiveData);

        } catch(IOException | ClassNotFoundException ex){
            System.out.println("Deserialization exception is caught");
        }

    }
}
