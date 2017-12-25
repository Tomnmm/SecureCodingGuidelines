package be.howest.ti.secure.development.g0.g06.domain;

import java.math.BigDecimal;

/**
 * Created by Bart Devriendt on 1/10/2017.
 */
public class Carpass {

    public String vinNumber;
    public BigDecimal mileage;


    public Carpass(String vinNumber, BigDecimal mileage) {
        this.vinNumber = vinNumber;
        this.mileage = mileage;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Carpass{" +
                "vinNumber='" + vinNumber + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
