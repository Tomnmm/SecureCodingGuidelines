package be.howest.ti.secure.development.g4.g05.finalize.secure;

public class SecureTrustedClass {

    // secure: make sure an Exception is thrown before super() finishes
    public SecureTrustedClass() {
        this(securityCheck());
    }

    private SecureTrustedClass(Void securityCheck) {
        System.out.println(String.format("Entered %s constructor", SecureTrustedClass.class));

        throw new SecurityException("Security exception thrown in constructor");
        //System.out.println("Entered " + SecureTrustedClass.class + " constructor");
    }

    private static Void securityCheck() {
        System.out.println(String.format("Entered %s security check", SecureTrustedClass.class));

        throw new SecurityException(String.format("Security exception thrown during construction: you are not allowed "
                + "to create an instance of %s", SecureTrustedClass.class));
    }

    void unreachableMethod() {
        System.out.println("---> Oh oh, unreachable method has been reached!!!");
    }
}
