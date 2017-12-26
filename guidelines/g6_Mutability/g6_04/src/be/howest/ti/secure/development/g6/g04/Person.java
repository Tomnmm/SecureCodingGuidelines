package be.howest.ti.secure.development.g6.g04;

import java.util.Date;

public final class Person {
    public String name;
    public Date birthday;

    // Regular constructor
    public Person(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    // Copy constructor
    public Person(Person origPerson) {
        this.name = origPerson.name;
        this.birthday = origPerson.birthday;
    }

    // Public copy method
    public static Person copy(Person origPerson) {
        return new Person(origPerson);
    }

    // Alternative copy method
    public Person copy() {
        return new Person(this);
    }

    // Getters
    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }
}
