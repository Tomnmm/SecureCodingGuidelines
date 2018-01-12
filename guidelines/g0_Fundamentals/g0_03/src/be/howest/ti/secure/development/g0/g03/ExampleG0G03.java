package be.howest.ti.secure.development.g0.g03;

import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by Jürgen Taverniers on 12/01/2018.
 */

public class ExampleG0G03 {
    public static void main(String[] args){ new ExampleG0G03().run(); }

    public void run(){
        //using my own policy here
        System.setProperty("java.security.policy","file:/C:/temp/java.policy");

        System.setSecurityManager(new SecurityManager());

        try{
            /*
            *  Needs following in the policy file
            *
            *   grant codeBase "file:${user.home}${/}IdeaProjects/SecureCodingGuidelines/out/production/SecureCodingGuidelines/be/howest/ti/secure/development/g0/g03" {
            *       permission java.util.PropertyPermission "java.home", "read";
            *   };
            *
            *   However this walks the stack and everything needs to have this permission,
            * */

            System.out.println(System.getProperty("java.home"));
        }catch (AccessControlException e){
            e.printStackTrace();
        }

        /*
        *   If elements in the stack do NOT have permissions you can use the following,
        *   However the class still needs the permissions of course.
         */
        AccessController.doPrivileged((PrivilegedAction)() -> {
            try{
                System.out.println(System.getProperty("java.home"));
            }catch (AccessControlException e) {
                e.printStackTrace();
            }finally {
                return null;
            }
        });
    }



}
