package be.howest.ti.secure.development.g1.g03;

public class SimpleAttack {
    public static void main(String[] args) {
        new SimpleAttack().run();
    }

    public void run() {
        System.out.println(this.getClass().toString());

        System.out.println("Unsafe version with input " + Integer.MAX_VALUE);
        this.checkGrowByUnsafe(Integer.MAX_VALUE);

        System.out.println("Safe version with input " + Integer.MAX_VALUE);
        this.checkGrowBySafe(Integer.MAX_VALUE);
    }

    private void checkGrowBySafe(int extra) {
        int current = 10, max = 1024;

        System.out.println("max (" + max + ") - extra (" + extra + ") = " + (max - extra));

        if (extra < 0 || current > max - extra) {
            System.out.println("Too much");
        } else {
            System.out.println("Go ahead");
        }
    }

    private void checkGrowByUnsafe(int extra) {
        int current = 10, max = 1024;

        System.out.println("current (" + current + ") + extra (" + extra + ") = " + (current + extra));

        if (extra > 0 && current + extra < max) {
            System.out.println("Go ahead");
        } else {
            System.out.println("Too much");
        }
    }
}
