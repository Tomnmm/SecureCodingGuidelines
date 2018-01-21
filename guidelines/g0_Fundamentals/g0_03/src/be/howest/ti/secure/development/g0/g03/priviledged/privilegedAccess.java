package be.howest.ti.secure.development.g0.g03.priviledged;

import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class privilegedAccess {
   public static String readJavaHome(){
       /*
       *   If not all elements in the stack have permissions you can use the following,
       *   However the class itself still needs the permissions of course.
       */

       return AccessController.doPrivileged(
               new PrivilegedAction<String>() {
                   public String run(){
                       try{
                           return System.getProperty("java.home");
                       }catch (AccessControlException e) {
                           e.printStackTrace();
                           return null;
                       }
                   }
               }
       );
    }
}
