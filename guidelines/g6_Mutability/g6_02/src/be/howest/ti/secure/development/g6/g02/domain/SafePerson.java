package be.howest.ti.secure.development.g6.g02.domain;

import java.util.Date;

public class SafePerson extends Person {

    public SafePerson(String name, Date birthday){
        super(name, birthday);
    }

    @Override
    public Date getBirthday(){
        return (Date) super.getBirthday().clone();
    }

}
