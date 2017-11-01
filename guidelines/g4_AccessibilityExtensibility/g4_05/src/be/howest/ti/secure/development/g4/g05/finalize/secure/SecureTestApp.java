package be.howest.ti.secure.development.g4.g05.finalize.secure;

public class SecureTestApp {
    public static void main(String[] args) {
        try {
            FutileUntrustedClass untrustedClass = new FutileUntrustedClass();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Requesting garbage collection...");

        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore
        }

        // As opposed to InsecureTestApp, the finalize() method of the FutileUntrustedClass instance "untrustedClass"
        // will NOT run and so the method executeUnreachableMethod(), will indeed not be executed!

        System.out.println(String.format("The finalize() method of %s should NOT have been called.",
                FutileUntrustedClass.class));
    }
}
