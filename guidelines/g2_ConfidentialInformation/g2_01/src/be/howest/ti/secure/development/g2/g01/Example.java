package be.howest.ti.secure.development.g2.g01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Example {
	public static void main(String[] args){
        new Example().run();
    }

    public void run() {
        System.out.println("Unsafe example:");
        try {
            unsafe();
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        System.out.println("Safe example:");
        try {
            safe();
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        System.out.println();
        System.out.println("Bad Exception Example:");
        try {
            BadExceptionExample();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("EmptyTryBlock")
    public void unsafe() throws Exception{
        try (InputStream input = new FileInputStream("non-existing.file")) {
            // ...
        }
    }

    @SuppressWarnings("EmptyTryBlock")
    public void safe() throws Exception{
        try (InputStream input = new FileInputStream("non-existing.file")) {
            // ...
        }
        catch(FileNotFoundException e) {
            throw new Exception("Something went wrong.");
        }
    }

    @SuppressWarnings("EmptyTryBlock")
    private void BadExceptionExample() throws Exception {
        try (FileInputStream fis = new FileInputStream(System.getenv("APPDATA"))) {
            //Linux (not tested)
            //FileInputStream fis = new FileInputStream(System.getenv("HOME"));
            // ...
        } catch (FileNotFoundException e) {
            throw new Exception("Unable to retrieve file ",  e);
        }
    }
}
