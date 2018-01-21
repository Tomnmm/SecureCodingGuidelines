package be.howest.ti.secure.development.g8.g04;

public class ExampleG8G04 implements java.io.Serializable{

    public static void main(String[] args){ new ExampleG8G04().run(); }

    public void run() {
        System.setSecurityManager(new SecurityManager());
        //check permissions at start of class
        securityManagerCheck();
    }

    private void readObject(java.io.ObjectInputStream inputStream){
        // duplicate check from constructor at deserialization
        securityManagerCheck();
    }

    private void securityManagerCheck(){
        SecurityManager secMgr = System.getSecurityManager();
        if(secMgr == null){
            //no security manager present
            System.setSecurityManager(new SecurityManager());
            secMgr = System.getSecurityManager();
        }
        //example= check read access demo file
        secMgr.checkRead("C:/temp/file.json");
    }

}