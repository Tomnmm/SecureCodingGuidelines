package be.howest.ti.secure.development.g4.g05.finalize.insecure;

public class InsecureTestApp {
    public static void main(String[] args) {
        try {
            UntrustedClass untrustedClass = new UntrustedClass();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Do whatever you need to do here. At one point the garbage collector would kick in and you would see the
        // finalize() method being called on the mc instance (because it is not being used anymore). Since there's
        // nothing more to do for now, this program would finish and the gc might not even run. Therefore we kindly ask
        // the garbage collector to run (note there is no guarantee it will - at least not right away). You would
        // usually not do the following stuff programmatically:
        System.out.println("Requesting garbage collection...");

        System.gc();

        try {
            // give the gc some time to do its job. We assume it will run within this time frame.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore
        }

        // The finalize() method of untrustedClass will run and it will have access to the parent method
        // executeUnreachableMethod(). Its trusted parent class thought it was not reachable!
    }
}
