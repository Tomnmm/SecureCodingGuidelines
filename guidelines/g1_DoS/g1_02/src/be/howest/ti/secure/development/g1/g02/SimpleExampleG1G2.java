package be.howest.ti.secure.development.g1.g02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import be.howest.ti.secure.development.g1.g02.domain.

/*
* Created By Jurgen Taverniers on 12/10/2017
* Updated By Mattias De Wael on 20/10/2017
 */

public class SimpleExampleG1G2 {
    public static void main(String[] args){
        new SimpleExampleG1G2().run();
    }

    public void run() {
        System.out.println("starting");
        try {
            new File("test.txt").createNewFile();
            unsafeFunction1("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("done");
        }
    }

    private long doSomethingWithStream(InputStream in) throws IOException {
        long current = 0;
        for (;;) {
            int b = in.read();
            if (b == -1) {
                return current;
            }
            current += b;
        }
    }

    public void unsafeFunction1(String fileName) throws IOException {
        InputStream in = new FileInputStream(fileName);
        doSomethingWithStream(in);
        // unsafe because the file (stream) is not closed !
    }

    public void unsafeFunction2(String fileName) throws IOException {
        InputStream in = new FileInputStream(fileName);
        doSomethingWithStream(in);
        in.close();
        /*
         * unsafe because the file (stream) is not closed
         * if something goes wrong during doSomethingWithStream(in)!
         */
    }

    public void unsafeFunction3(String fileName) throws IOException {
        InputStream in = new FileInputStream(fileName);
        try {
            doSomethingWithStream(in);
        } finally {
            in.close();
        }
        /*
         * unsafe because the file (stream) is not closed
         * if something goes wrong during in.close() itself,
         * this happens silently
         */
    }

    public void safeFunction1(String fileName) throws IOException {
        InputStream in = new FileInputStream(fileName);
        try {
            doSomethingWithStream(in);
        } finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException ex) {
                    // handle exception
                }
            }

        }
        /*
         * safe because the file (stream) is always closed, and
         * if something goes wrong during in.close() itself the exception
         * can be handled.
         */
    }

    public void safeFunction2(String fileName) throws IOException {
        try (InputStream in = new FileInputStream(fileName)) {
            doSomethingWithStream(in);
        }
        /*
         * safe because the file (stream) is always closed.
         * This is possible since Java 8, and the new construct is called
         * try-with-resources
         */
    }

}