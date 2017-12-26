/*
 * @author Robin Peiremans <robin.peiremans@student.howest.be>
 */

package be.howest.ti.secure.development.g6.g04;

import java.util.Calendar;

public class Example {
    public static void main(String[] args) {
        new Example().run();
    }

    public void run() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(1970, Calendar.JANUARY, 1);

        // Create the original person
        Person p1 = new Person("Person1", birthday.getTime());

        // Now create a copy using the copy constructor
        Person p1CopyConstructor = new Person(p1);

        // And using the copy method
        Person p1CopyMethod = Person.copy(p1);

        // Alternative copy method
        Person p1CopyMethod2 = p1.copy();


        // Now change some fields and print the results
        System.out.println("Name after copy:");
        System.out.printf("p1.name: %s\n", p1.getName());
        System.out.printf("p1CopyConstructor.name: %s\n", p1CopyConstructor.getName());
        System.out.printf("p1CopyMethod.name: %s\n", p1CopyMethod.getName());
        System.out.printf("p1CopyMethod2.name: %s\n", p1CopyMethod2.getName());

        System.out.println("\nChange name of p1CopyConstructor:");
        p1CopyConstructor.name = "Person2";
        System.out.printf("p1.name: %s\n", p1.getName());
        System.out.printf("p1CopyConstructor.name: %s\n", p1CopyConstructor.getName());
        System.out.printf("p1CopyMethod.name: %s\n", p1CopyMethod.getName());
        System.out.printf("p1CopyMethod2.name: %s\n", p1CopyMethod2.getName());

        System.out.println("\nChange name of p1CopyMethod:");
        p1CopyMethod.name = "Person3";
        System.out.printf("p1.name: %s\n", p1.getName());
        System.out.printf("p1CopyConstructor.name: %s\n", p1CopyConstructor.getName());
        System.out.printf("p1CopyMethod.name: %s\n", p1CopyMethod.getName());
        System.out.printf("p1CopyMethod2.name: %s\n", p1CopyMethod2.getName());

        System.out.println("\nChange name of p1CopyMethod2:");
        p1CopyMethod2.name = "Person4";
        System.out.printf("p1.name: %s\n", p1.getName());
        System.out.printf("p1CopyConstructor.name: %s\n", p1CopyConstructor.getName());
        System.out.printf("p1CopyMethod.name: %s\n", p1CopyMethod.getName());
        System.out.printf("p1CopyMethod2.name: %s\n", p1CopyMethod2.getName());
    }
}
