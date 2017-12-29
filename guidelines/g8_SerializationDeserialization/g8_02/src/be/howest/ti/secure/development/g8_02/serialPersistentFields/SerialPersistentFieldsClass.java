package src.be.howest.ti.secure.development.g8_02.serialPersistentFields;

import java.io.*;

class SerialPersistentFieldsClass implements Serializable {

    private String nonSensitiveData;
    private String sensitiveData;

    public String getNonSensitiveData() { return nonSensitiveData; }
    public String getSensitiveData() { return sensitiveData; }

    // Default constructor
    public SerialPersistentFieldsClass(String nonSensitiveData, String sensitiveData) {
        this.nonSensitiveData = nonSensitiveData;
        this.sensitiveData = sensitiveData;
    }
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("nonSensitiveData", String.class)
    };

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        // get the field and assign it
        ObjectInputStream.GetField fields = in.readFields();

         nonSensitiveData =(String) fields.get("nonSensitiveData", "");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        // write into the ObjectStreamField array the variable
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("nonSensitiveData", nonSensitiveData);
        out.writeFields();
    }
}
