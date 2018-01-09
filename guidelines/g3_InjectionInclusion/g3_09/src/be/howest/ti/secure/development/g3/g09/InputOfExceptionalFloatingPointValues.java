package be.howest.ti.secure.development.g3.g09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Asks the user for a numeric value. If a numeric value is entered, the program doubles the input and prints the
 * result. For input that cannot be parsed to a double, no calculation is done. Try also with the values NaN, Infinity
 * or -Infinity.
 */
public class InputOfExceptionalFloatingPointValues {

    public static void main(String[] args) {
        new InputOfExceptionalFloatingPointValues().execute();
    }

    private void execute() {
        System.out.println("Enter a numeric value or q to quit. You can also try other values. Notice that NaN, "
                + "Infinity, -Infinity will be accepted by the JVM.");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String untrustedInput;

            while (!"q".equals(untrustedInput = br.readLine())) {
                System.out.println(String.format("You entered %s", untrustedInput));

                try {
                    System.out.println(String.format("Twice that value equals %s", 2 * Double.valueOf(untrustedInput)));
                } catch (NumberFormatException e) {
                    System.out.println("Oops, was that really a numeric value? Enter q to quit.");
                }
            }
        } catch (IOException e) {
            System.out.println(String.format("O come on... %s", e.getMessage()));
        }
    }
}
