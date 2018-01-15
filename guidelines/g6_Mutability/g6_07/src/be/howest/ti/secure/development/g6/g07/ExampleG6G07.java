package be.howest.ti.secure.development.g6.g07;

import java.util.Date;

public class ExampleG6G07 {
    public static void main(String[] args)
    {
        new ExampleG6G07().run();
    }

    public void run() {
        System.out.println("Starting...");
        endWith(new Date(2017-1900,11,24));
        System.out.println(end.toString());
        endWith(new Date(2017-1900,0,15));
        System.out.println(end.toString());
        System.out.println("End");
    }

    private final Date start = new Date(2017-1900,9,31);
    private Date end;

    public void endWith(Date input){
        //getting data from an untrusted object should be considered input,
        if(input.before(start)){
            throw new IllegalArgumentException("Illegal input");
        }
        this.end = input;
    }
}
