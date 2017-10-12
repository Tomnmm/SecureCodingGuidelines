package be.howest.ti.secure.development.g1.g02;

import java.io.FileInputStream;
import java.io.InputStream;
//import be.howest.ti.secure.development.g1.g02.domain.

/*
* Created By Jurgen Taverniers on 12/10/2017
 */

public class SimpleExampleG1G2 {
    public static void main(String[] args){
        new SimpleAttackG1G2().run();
    }
    public void run(){}

    public static void safeFunction1(){
        InputStream in = new FileInputStream(fileName);
        try{
            doSomethingWithStream(in);
        }finally{
            in.close();
        }
    }
    public static void badFunction1(){
        InputStream in = new FileInputStream(fileName);
        doSomethingWithStream(in);
    }
}