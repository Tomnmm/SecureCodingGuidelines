package be.howest.ti.secure.development.g0.g06;

import be.howest.ti.secure.development.g0.g06.domain.Carpass;

import java.math.BigDecimal;

/**
 * Created by Bart Devriendt on 1/10/2017.
 */
public class SimpleAttackG0G6 {


    public static void main(String[] args) {
        new SimpleAttackG0G6().run();
    }

    public void run() {

        Carpass carpass = new Carpass("VIN1", new BigDecimal(100000));

        System.out.println(carpass);
        System.out.printf("Car %s has %s kms\n", carpass.getVinNumber(), carpass.getMileage().toString());

        carpass.mileage = new BigDecimal(20000);

        System.out.println(carpass);
        System.out.printf("Car %s has %s kms\n", carpass.getVinNumber(), carpass.getMileage().toString());

    }
}
