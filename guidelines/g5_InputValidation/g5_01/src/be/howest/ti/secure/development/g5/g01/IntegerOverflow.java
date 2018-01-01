package be.howest.ti.secure.development.g5.g01;


import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Emmanouil Perselis
 */
public class IntegerOverflow {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int number1 = 0;
        int number2 = 0;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("please enter first integer");
            String string = sc.nextLine();              //give 1231231233 as input
            System.out.println("please enter second integer");
            String string1 = sc.nextLine();             //give 1231231233 as input
            number1 = Integer.parseInt(string);
            number2 = Integer.parseInt(string1);
        }catch(NumberFormatException ex){
            System.out.println("Input is not an integer. Exiting...");
            System.exit(1);
        }
        System.out.println("output without proper handling...");
        System.out.println(number2*number1);
        BigInteger bi = BigInteger.valueOf(number1);
        BigInteger bi2 =BigInteger.valueOf(number2);
        System.out.println("output with proper handling...");
        System.out.println(bi2.multiply(bi).floatValue());
    }

}

