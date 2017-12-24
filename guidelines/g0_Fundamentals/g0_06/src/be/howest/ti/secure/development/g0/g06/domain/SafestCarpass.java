package be.howest.ti.secure.development.g0.g06.domain;

import java.math.BigDecimal;

/**
 * Created by Sven Meuleman on 24/12/2017.
 */

public class SafestCarpass {
    private final String vinNumber;
    private final BigDecimal mileage;

    public SafestCarpass(String vinNumber, BigDecimal mileage){
        this.vinNumber = vinNumber;
        this.mileage = mileage;
    }

    public String getVinNumber(){
        return  vinNumber;
    }

    public BigDecimal getMileage(){
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
