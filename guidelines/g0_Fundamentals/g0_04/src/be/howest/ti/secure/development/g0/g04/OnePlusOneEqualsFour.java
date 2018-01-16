package be.howest.ti.secure.development.g0.g04;

import java.lang.reflect.Field;

/**
 * Shows how private fields are not safe when you're using untrusted third-party code and your JVM
 * does not limit the use of reflection.
 */
public class OnePlusOneEqualsFour {

    public static void main(String[] args) throws Exception {
        // Added by Jürgen
        // Like Manu hinted Setting the securitymanager gives, java.security.AccessControlException: access denied ("java.lang.RuntimePermission" "accessDeclaredMembers")
        // System.setSecurityManager(new SecurityManager());

        Integer one = 1;

        new MaliciousThirdPartyClass().naughtyCall();

        // 1 + 1 equals 2, right?
        System.out.println("1 + 1 = " + (one + one));
    }

    private static class MaliciousThirdPartyClass {
        private void naughtyCall() {
            Integer i = 1; // JVM applies autoboxing and will reuse the Integer object assigned to variable "one"

            Class clazz = i.getClass();
            Field field;

            // the naughty stuff: change the internal value of the Integer object...
            try {
                field = clazz.getDeclaredField("value");
                field.setAccessible(true);
                field.set(i, 2); // let's give it the value 2
               	System.out.println("Ouch, Integer private field \"value\" was updated from value 1 to 2. "
                            + "You're vulnerable.");
            } catch (NoSuchFieldException e) {
                System.out.println("Oops, Integer private field \"value\" not found. You're safe.");
            } catch (IllegalAccessException e) {
                System.out.println("Oops, access to Integer private field \"value\" not allowed. You're safe.");
            }
        }
    }
}
