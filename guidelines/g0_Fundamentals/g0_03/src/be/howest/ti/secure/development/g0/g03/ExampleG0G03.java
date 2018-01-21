package be.howest.ti.secure.development.g0.g03;

import be.howest.ti.secure.development.g0.g03.priviledged.privilegedAccess;

import java.security.AccessControlException;

/**
 * Created by Jürgen Taverniers on 13/01/2018.
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
grant codeBase "file:${user.home}${/}IdeaProjects/SecureCodingGuidelines/out/production/SecureCodingGuidelines/be/howest/ti/secure/development/g0/g03/priviledged/" {
    permission java.util.PropertyPermission "java.home", "read";
};
            *
            *   However this walks the stack and everything needs to have this permission,
            *
            *   For some reason I'm unable to fine tune the policy file and need to give file:${user.home}${/}IdeaProjects/SecureCodingGuidelines/out/production/SecureCodingGuidelines the permissions, this however means that not only the priviledged class but also the Example Class has sufficient privileges?
            *   Any suggestions?
            *
            *
            * */

            System.out.println(System.getProperty("java.home"));
        }catch (AccessControlException e){
            e.printStackTrace();
        }

        /*
        *   If not all elements in the stack have permissions you can use the following,
        *   However the class itslef still needs the permissions of course.

        AccessController.doPrivileged((PrivilegedAction)() -> {
            try{
                System.out.println(System.getProperty("java.home"));
            }catch (AccessControlException e) {
                e.printStackTrace();
            }finally {
                return null;
            }
        });*/

        System.out.println(privilegedAccess.readJavaHome());
    }



}
