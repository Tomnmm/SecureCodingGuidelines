package be.howest.ti.secure.development.g6.g12;

import java.util.Arrays;
import java.util.List;

public class ExampleAttackG6G12 {
    public static void main(String[] args){
        printLists("Starting..");

        List lstTampered = Arrays.asList("tamper1","tamper2","...");
        ExampleG6G12.lstAttackable = lstTampered;
        printLists("Tampered lstAttackable 1");

        ExampleG6G12.lstAttackable.set(0,"Tampered!!");
        printLists("Tampered lstAttackable 2");

        ExampleG6G12.lstBetter.set(0,"PROBLEM ?!");
        printLists("Tampered lstBetter 1");

        //ExampleG6G12.lstBetter = lstTampered; // ==> Error: Cannot assign a value to final variable lstBetter

    }

    private static void printLists(String comment){
        System.out.println(comment);
        System.out.println("lstAttackable: " + ExampleG6G12.lstAttackable.toString());
        System.out.println("lstBetter: " + ExampleG6G12.lstBetter.toString());
        System.out.println();
    }
}
