package be.howest.ti.secure.development.g6.g04;

import java.util.Calendar;
import java.util.GregorianCalendar;

// In this example there is no problem with the date, because you replace it with a new Date object.
// Every birthday will have a different date.
public class Example_Only_One_Date_Changes {
    public static void main(String[] args) {
        new Example_Only_One_Date_Changes().run();
    }

    public void run() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(2000, Calendar.JANUARY, 1);

        // Create the original person
       Person p1 = new Person("Person1", birthday);

        // Now create a copy using the copy constructor
       Person p1CopyConstructor = new Person(p1);

        // And using the copy method
       Person p1CopyMethod =Person.copy(p1);

        // Alternative copy method
        Person p1CopyMethod2 = p1.copy();


        // Now change some fields and print the results
        System.out.println("Birthday after copy:");
        System.out.printf("p1.getBirthday: %s\n", p1.getBirthday().getTime());
        System.out.printf("p1CopyConstructor.getBirthday: %s\n", p1CopyConstructor.getBirthday().getTime());
        System.out.printf("p1CopyMethod.getBirthday: %s\n", p1CopyMethod.getBirthday().getTime());
        System.out.printf("p1CopyMethod2.getBirthday: %s\n", p1CopyMethod2.getBirthday().getTime());

        System.out.println("\nChange Birthday of p1CopyConstructor:");
        p1CopyConstructor.birthday = new GregorianCalendar(2222, 2, 2);
        System.out.printf("p1.getBirthday: %s\n", p1.getBirthday().getTime());
        System.out.printf("-> p1CopyConstructor.getBirthday: %s\n", p1CopyConstructor.getBirthday().getTime());
        System.out.printf("p1CopyMethod.getBirthday: %s\n", p1CopyMethod.getBirthday().getTime());
        System.out.printf("p1CopyMethod2.getBirthday: %s\n", p1CopyMethod2.getBirthday().getTime());

        System.out.println("\nChange Birthday of p1CopyMethod:");
        p1CopyMethod.birthday = new GregorianCalendar(3333, 3, 3);
        System.out.printf("p1.getBirthday: %s\n", p1.getBirthday().getTime());
        System.out.printf("p1CopyConstructor.getBirthday: %s\n", p1CopyConstructor.getBirthday().getTime());
        System.out.printf("-> p1CopyMethod.getBirthday: %s\n", p1CopyMethod.getBirthday().getTime());
        System.out.printf("p1CopyMethod2.getBirthday: %s\n", p1CopyMethod2.getBirthday().getTime());

        System.out.println("\nChange Birthday of p1CopyMethod2:");
        p1CopyMethod2.birthday = new GregorianCalendar(4444, 4, 4);
        System.out.printf("p1.getBirthday: %s\n", p1.getBirthday().getTime());
        System.out.printf("p1CopyConstructor.getBirthday: %s\n", p1CopyConstructor.getBirthday().getTime());
        System.out.printf("p1CopyMethod.getBirthday: %s\n", p1CopyMethod.getBirthday().getTime());
        System.out.printf("-> p1CopyMethod2.getBirthday: %s\n", p1CopyMethod2.getBirthday().getTime());
    }
}
