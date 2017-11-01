package be.howest.ti.secure.development.g4.g05.finalize.insecure;

public class InsecureTrustedClass {

    public InsecureTrustedClass() {
        System.out.println(String.format("Entered %s constructor", InsecureTrustedClass.class));

        // THIS IS THE INSECURE PART: an exception is thrown after super() (implicitly the first line in this
        // constructor) so the finalize() method WILL be called during garbage collection (finalize() can and IS
        // overridden by the malicious subclass UntrustedClass)
        throw new SecurityException(String.format("Security exception thrown in constructor: you are not allowed to "
                + "create an instance of %s", InsecureTrustedClass.class));
    }

    // you should not be able to call this method since you cannot even create an instance of this class:
    // the constructor throws an exception at all times
    void executeUnreachableMethod() {
        System.out.println("---> Oh oh, unreachable method has been reached!!!");
    }
}
