package be.howest.ti.secure.development.g0.g06.domain;

import java.math.BigDecimal;

/**
 * Created by Jurgen Taverniers on 12/10/2017.
 */

public class SafeCarpass {
    private String vinNumber;
    private BigDecimal mileage;

    public SafeCarpass(String vinNumber, BigDecimal mileage){
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
