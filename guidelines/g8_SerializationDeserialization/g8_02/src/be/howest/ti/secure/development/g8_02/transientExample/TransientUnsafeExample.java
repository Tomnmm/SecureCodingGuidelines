package be.howest.ti.secure.development.g8_02.transientExample;

import java.io.*;

public class TransientUnsafeExample implements Serializable {
    private String nonSensitiveData;
    private String sensitiveData;
    private int nonSensitiveNumber;

    // Default constructor
    public TransientUnsafeExample(String nonSensitiveData, String sensitiveData, int nonSensitiveNumber ) {
        this.nonSensitiveData = nonSensitiveData;
        this.sensitiveData = sensitiveData;
        this.nonSensitiveNumber = nonSensitiveNumber;
    }

    @Override
    public String toString() {
        return "Non Sensitive Data = " + nonSensitiveData +  " \nSensitive Data = " + sensitiveData +  " \nNon Sensitive Number = " + nonSensitiveNumber + "\n";
    }

    public static void main(String[] args) {
        TransientUnsafeExample objToSerialize = new TransientUnsafeExample("I wanna be serialized and transferred", "Please don't serialize me",42);

        //Print the content of the object
        System.out.println("Object before serialization");
        System.out.println(objToSerialize.toString());

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
            System.out.println(objToDeserialize.toString());

        } catch(IOException | ClassNotFoundException ex){
            System.out.println("Deserialization exception is caught");
        }

    }
}
