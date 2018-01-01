package be.howest.ti.secure.development.g8_02.serialPersistentFields;

import java.io.*;

class SerialPersistentFieldsClass implements Serializable {

    private String nonSensitiveData;
    private String sensitiveData;
    private int nonSensitiveNumber;

    public String getNonSensitiveData() { return nonSensitiveData; }
    public String getSensitiveData() { return sensitiveData; }
    public int getNonSensitiveNumber() { return nonSensitiveNumber; }

    // Default constructor
    public SerialPersistentFieldsClass(String nonSensitiveData, String sensitiveData, int nonSensitiveNumber) {
        this.nonSensitiveData = nonSensitiveData;
        this.sensitiveData = sensitiveData;
        this.nonSensitiveNumber = nonSensitiveNumber;
    }
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("nonSensitiveData", String.class),
            new ObjectStreamField("nonSensitiveNumber", Integer.TYPE)
    };

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        // get the field and assign it
        ObjectInputStream.GetField fields = in.readFields();

         nonSensitiveData =(String) fields.get("nonSensitiveData", "");
        nonSensitiveNumber = fields.get("nonSensitiveNumber", 0);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        // write into the ObjectStreamField array the variable
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("nonSensitiveData", nonSensitiveData);
        fields.put("nonSensitiveNumber", nonSensitiveNumber);
        out.writeFields();
    }

    @Override
    public String toString() {
        return "Non Sensitive Data = " + this.getNonSensitiveData() +  " \nSensitive Data = " + this.getSensitiveData() +  " \nNon Sensitive Number = " + this.getNonSensitiveNumber()+ "\n";
    }
}
