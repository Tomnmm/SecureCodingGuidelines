/*
 * @author Robin Peiremans <robin.peiremans@student.howest.be>
 */

package be.howest.ti.secure.development.g6.g09;

public class Example {
    public static void main(String[] args){
        new Example().run();
    }

    public void run() {
        // Print and the change the separator in our unsafe version
        System.out.println("Original separator: " + UnsafeFiles.separator);
        UnsafeFiles files1 = new UnsafeFiles();

        System.out.println("files1.getPath: " + files1.getPath());
        System.out.println();

        System.out.println("Change separator");
        UnsafeFiles.separator = "/../";

        System.out.println("Changed separator: " + UnsafeFiles.separator);

        UnsafeFiles files2 = new UnsafeFiles();

        System.out.println();
        System.out.println("files1.getPath: " + files1.getPath());
        System.out.println("files2.getPath: " + files2.getPath());
    }
}
