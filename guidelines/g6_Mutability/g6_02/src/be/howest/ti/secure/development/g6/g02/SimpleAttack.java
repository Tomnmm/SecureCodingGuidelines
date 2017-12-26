package be.howest.ti.secure.development.g6.g02;

import be.howest.ti.secure.development.g6.g02.domain.Person;
import be.howest.ti.secure.development.g6.g02.domain.SafePerson;

import java.util.Calendar;
import java.util.Date;

public class SimpleAttack {

    public static void main(String[] args) {
        new SimpleAttack().run();
    }

    public void run() {
        System.out.println(this.getClass().toString());

        Calendar birthday = Calendar.getInstance();
        birthday.set(1986, Calendar.OCTOBER, 21);
        Person p = new Person("Mattias De Wael", birthday.getTime());
        Person p1 = new SafePerson("Mattias De Wael", birthday.getTime());
        print(p);
        attackUsingVerySimpleJavaCode(p);
        print(p1);
        attackUsingVerySimpleJavaCode(p1);

    }
    private void print(Person p)
    {
    	 System.out.println(p);
         System.out.printf("%s is %s\n", p.getName(), p.isOfAge()?"of age":"underage");
    }

    private void attackUsingVerySimpleJavaCode(Person p) {
        // Nothing fancy ...
        p.getBirthday().setTime(new Date().getTime());
        print(p);
    }

}