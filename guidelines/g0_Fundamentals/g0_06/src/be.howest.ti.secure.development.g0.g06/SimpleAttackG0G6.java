package be.howest.ti.secure.development.g0.g06;

import be.howest.ti.secure.development.g0.g06.domain.Carpass;
import be.howest.ti.secure.development.g0.g06.domain.SafeCarpass;

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

        /**
         * Added by Jurgen Taverniers on 12/10/2017
         */
        SafeCarpass safecarpass = new SafeCarpass("VIN2", new BigDecimal(123456));

        System.out.println(safecarpass);
        System.out.printf("Car %s has %s kms\n", safecarpass.getVinNumber(), safecarpass.getMileage().toString());

        /*
            safecarpass.mileage = new BigDecimal(20000);
            results in error: Error:(38, 20) java: mileage has private access in be.howest.ti.secure.development.g0.g06.domain.SafeCarpass
         */

        System.out.println(safecarpass);
        System.out.printf("Car %s has %s kms\n", safecarpass.getVinNumber(), safecarpass.getMileage().toString());

    }
}
