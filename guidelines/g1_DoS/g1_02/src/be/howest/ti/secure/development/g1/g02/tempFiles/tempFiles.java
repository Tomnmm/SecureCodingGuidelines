package be.howest.ti.secure.development.g1.g02.tempFiles;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class tempFiles {
    public static void main(String[] args){
    }
    private void worstCode(){
        // Creates the file but does not do any attempt in deleting once finished.
        File f = new File("temp.tmp");
        if(f.exists()){
            System.out.println("file exists");
            return;
        }

        FileOutputStream fop = null;
        try {
            fop = new FileOutputStream(f);
            String str = "Some data";
            fop.write(str.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fop != null){
                try {
                    fop.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private void betterCode(){
        File f = null;
        try {
            f = File.createTempFile("temp",".tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fop = null;
        try{
            fop = new FileOutputStream(f);
            String str = "some data";
            fop.write(str.getBytes());
            fop.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            f.deleteOnExit();
            if(fop != null) {
                try {
                    fop.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void bestCode(){
        Path tempFile = null;
        try{
            tempFile = Files.createTempFile("temp",".tmp");
            try (BufferedWriter writer = Files.newBufferedWriter(tempFile, Charset.forName("UTF8"), StandardOpenOption.DELETE_ON_CLOSE)){
                // write to file
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Write is done, so file is deleted on close.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

