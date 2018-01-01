package be.howest.ti.secure.development.g8_02.serialPersistentFields;

import java.io.*;

public class RunSerialPersistentFields {

    public static void main(String[] args) {

        SerialPersistentFieldsClass objToSerialize = new SerialPersistentFieldsClass("I wanna be serialized and transferred", "Please don't serialize me",42);

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

        } catch (IOException ex) {
            System.out.println("Serialization exception is caught \n");
        }


        SerialPersistentFieldsClass objToDeserialize = null;

         // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("SerialFile.dat");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            objToDeserialize = (SerialPersistentFieldsClass) in.readObject();

            in.close();
            file.close();

            //Print the content of the deserialized object
            System.out.println("Object has been deserialized ");
            System.out.println(objToDeserialize.toString());

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Deserialization exception is caught");
        }

    }
}


