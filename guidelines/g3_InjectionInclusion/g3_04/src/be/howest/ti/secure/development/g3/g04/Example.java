package be.howest.ti.secure.development.g3.g04;

import java.io.*;
import java.util.regex.Pattern;

public class Example {
    public static void main(String[] args) {
        new Example().run();
    }

    public void run() {
        String command = "cat ";
        String validFile = "C:\\temp\\g3_04.txt";       //"some_valid_file";

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
        //---Linux---
        //execCmd(command, validFile);
        //execCmd(command, "/etc/passwd");
        //---Windows---
        command = "type ";
        execWinCmd(command, validFile);
        execWinCmd(command, "C:\\Windows\\System32\\drivers\\etc\\hosts");

        System.out.println("");
        System.out.println("Safe:");
        //---Linux---
        //safeExecCmd(command, validFile);
        //safeExecCmd(command, "/etc/passwd");
        //---Windows---
        safeExecWinCmd(command, 1);
        safeExecWinCmd(command, 3);
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
    private void execWinCmd(String cmd, String args){
        Runtime rt = Runtime.getRuntime();
        Process proc = null;
        try {
            proc = rt.exec("cmd.exe /C " + cmd + " " + args);
            int result = proc.waitFor();
            if(result != 0){
                System.out.println("Error: " + result);
            }
            InputStream in = (result == 0) ? proc.getInputStream() : proc.getErrorStream();
            int c;
            while((c = in.read()) != -1){
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private void safeExecCmd (String command, String arguments) {
        String regex = "^[a-zA-Z0-9_\\\\.-]+$";

        if (Pattern.matches(regex, arguments)) {
            execCmd(command, arguments);
        } else {
            System.out.println("illegal argument");
        }
    }
    private void safeExecWinCmd(String cmd, int input){
        // you can sanitize the arguments, just the same way as Robin.
        // I just wanted to do an alternative
        String args = null;
        switch (input){
            case 1:
                args = "C:\\temp\\g3_04.txt";
                break;
            case 2:
                args = "C:\\temp\\g3_04_bis.txt";
                break;
            default:
                break;
        }
        if(args == null){
            System.out.printf("Illegal argument");
        }else{
            execWinCmd(cmd, args);
        }
    }
}