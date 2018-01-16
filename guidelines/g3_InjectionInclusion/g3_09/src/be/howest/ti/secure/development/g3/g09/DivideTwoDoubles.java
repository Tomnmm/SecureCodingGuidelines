package be.howest.ti.secure.development.g3.g09;

import java.util.Scanner;

public class DivideTwoDoubles {

    private double number1;
    private double number2;

    public static void main(String[] args) {
        new DivideTwoDoubles().run();
    }

    private void run() {
        startInformation();

        getTwoNumbers();

        //Divide the two numbers
        Double result = number1 / number2;

        //What is the result
        System.out.println(String.format("What will the result be if we divide %.2f by %.2f \n\t => %s", number1, number2, String.valueOf(result)));


        // As an alternative result.isNaN() can be used.
        if (Double.isNaN(result)) {
            System.out.println("Result is NaN \nExiting ...");
            System.exit(1);
        }

        // As an alternative result.isInfinite() can be used.
        if (Double.isInfinite(result)) {
            System.out.println("Value is isInfinite \nExiting ...");
            System.exit(1);
        }

        System.out.println("The normal processing starts here, but we are exiting ...");
        System.exit(0);
    }

    private void startInformation() {
        System.out.println("Useful examples to test.");
        System.out.println("\t Number 1 = 0.0 & Number 2 = 0. The result will be NaN");
        System.out.println("\t Number 1 = 2.0 & Number 2 = 0. The result will be Infinity");
    }

    private void getTwoNumbers() {
        Scanner scanner = new Scanner(System.in);

        //  Ask for Number 1:
        System.out.print("Enter your number 1: ");

        // get input as a Double
        number1 = Double.parseDouble(scanner.next());

        // prompt for Number 2:
        System.out.print("Enter your number 2: ");

        // get input as a Double
        number2 = Double.parseDouble(scanner.next());

    }
}