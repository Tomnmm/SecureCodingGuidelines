package be.howest.ti.secure.development.g3.g04;

import java.io.*;
import java.util.regex.Pattern;

public class Example {
    public static void main(String[] args) {
        new Example().run();
    }

    public void run() {
        String command = "cat ";
        String validFile = "some_valid_file";

        // Create the file first
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(validFile), "utf-8"))) {
            writer.write("something");
        } catch (UnsupportedEncodingException e) {
            System.out.println (e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println (e.getMessage());
        } catch (IOException e) {
            System.out.println (e.getMessage());
        }

        System.out.println("Unsafe:");
        execCmd(command, validFile);
        execCmd(command, "/etc/passwd");

        System.out.println("");
        System.out.println("Safe:");
        safeExecCmd(command, validFile);
        safeExecCmd(command, "/etc/passwd");
    }

    private void execCmd (String command, String arguments) {
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(command + arguments);
            p.waitFor();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line = "";

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
        catch(IOException e1) {
            System.out.println (e1.getMessage());
        }
        catch(InterruptedException e2) {
            System.out.println (e2.getMessage());
        }
    }

    private void safeExecCmd (String command, String arguments) {
        String regex = "^[a-zA-Z0-9_\\\\.-]+$";

        if (Pattern.matches(regex, arguments)) {
            execCmd(command, arguments);
        } else {
            System.out.println("illegal argument");
        }
    }
}