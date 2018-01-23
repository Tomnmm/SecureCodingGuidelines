package be.howest.ti.secure.development.g3.g08;

public class ExampleG3G08 {
    public static void main(String[] args){ new ExampleG3G08().run();}
    public void run(){
        ExampleSecurityManager secMgr = new ExampleSecurityManager();
        System.setSecurityManager(secMgr);
        try{
            untrustedCode();
        }catch (Exception e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        secMgr.setExitAllowed(true);
        untrustedCode();
    }

    private void untrustedCode(){
        System.out.println("I shall have my revenge...");
        System.exit(1);
        System.out.println("You will never see me...");
    }

}

class ExampleSecurityManager extends SecurityManager {
    private boolean isExitAllowed;

    public ExampleSecurityManager(){
        super();
        isExitAllowed = false;
    }

    public boolean isExitAllowed(){
        return isExitAllowed;
    }
    public void setExitAllowed(boolean flag){
        isExitAllowed = flag;
    }

    @Override
    public void checkExit(int status){
        if(!isExitAllowed()){
            throw new SecurityException();
        }
        super.checkExit(status);
    }
}
