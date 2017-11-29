package guidelines.g2_ConfidentialInformation.g2_03.src.be.howest.ti.secure.development.g2.g03.Example01;

public class G2_03Example01 {

    public static void main(String[] args) throws InterruptedException {
        new G2_03Example01().run();
    }

    public void run() throws InterruptedException {

        safeMemory();
        unsafeMemory();

        // This put the process a sleep, so that you can make a memory dump and analyze it.
        Thread.sleep(1000 * 3600);
    }

    private void safeMemory()  {

        char[] mySafeSecret ={ 'S','A','F','E','S','E','C','R','E','T','_','T','H','A','T','_','S','H','O','U','L','D','_','N','O','T','_','S','T','A','Y','_','I','N','_','M','E','M','O','R','Y'};

        System.out.printf("Safe mode: My secret has %d characters %n" , mySafeSecret.length);

        clearSafeSecret(mySafeSecret);

    }

    private void unsafeMemory() throws InterruptedException {

        String myUnsafeSecret = "UNSAFE_SECRET_THAT_SHOULD_NOT_STAY_IN_MEMORY";

        System.out.printf("Unsafe mode: My secret has %d characters %n" , myUnsafeSecret.length());

        clearUnsafeSecret(myUnsafeSecret);
    }

    private void clearSafeSecret(char[] pass){
        for (int i=0;i<pass.length;i++) {
            if(i % 2 == 0)
                pass[i] ='a';
            else
                pass[i] ='b';
        }
        System.out.println("Safe wiping: I hope that my safe secret is wiped");
    }

    private void clearUnsafeSecret( String myUnsafeSecret) throws InterruptedException {
        myUnsafeSecret = null;

        while( myUnsafeSecret != null) {
            System.out.print("Password not null");
            Thread.sleep(100);
        }
        System.gc();
        System.out.println("Unsafe wiping: Garbage collection started");
    }

}
