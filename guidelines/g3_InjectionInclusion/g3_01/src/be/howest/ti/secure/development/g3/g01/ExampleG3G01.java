package be.howest.ti.secure.development.g3.g01;
//created by Jürgen on 14/01/2018

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ExampleG3G01 {
    public static void main(String[] args)
    {
        new ExampleG3G01().run();
    }

    public void run() {
        //System.out.printf("Boolean return:" + unsafeRead());
        System.out.printf("Boolean return:" + safeRead());
    }

    public boolean unsafeRead(){

        Scanner reader = new Scanner(System.in);
        try {
            System.out.println("Enter True or False: ");
            boolean n = reader.nextBoolean();
            return n;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            reader.close();
        }
        return false; //can you return null or nothing?
    }

    public boolean safeRead(){
        while(true){ //is this acceptable (creating an endless loop, or bad programming etiquette?
            System.out.println("Enter True or False: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String input = br.readLine();
                if(input.equalsIgnoreCase("true")){
                    return true;
                }else if (input.equalsIgnoreCase("false")){
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
