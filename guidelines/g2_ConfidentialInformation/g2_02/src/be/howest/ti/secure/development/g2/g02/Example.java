package be.howest.ti.secure.development.g2.g02;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Example {
    public static void main(String[] args){
        new Example().run();
    }

    private class PaymentInformation {
        private LocalDate date;
        private BigDecimal amount;
        private String visaCardNumber;

        public PaymentInformation(LocalDate date, BigDecimal amount, String visaCardNumber) {
            this.date = date;
            this.amount = amount;
            this.visaCardNumber = visaCardNumber;
        }

        @Override
        public String toString() {
            return safeToString();
        }

        public String unsafeToString() {
            return "PaymentInformation{" +
                    "date=" + date +
                    ", amount=" + amount +
                    ", visaCardNumber='" + visaCardNumber + '\'' +
                    '}';
        }

        public String safeToString() {
            return "PaymentInformation{" +
                    "date=" + date +
                    ", amount=" + amount +
                    ", visaCardNumber='__FILTERED__" +
                    '}';
        }

    }

    public void run() {
        PaymentInformation payment = new PaymentInformation(LocalDate.now(), new BigDecimal(100), "123-456789-01");
        Logger.getLogger("G02_G02").log(Level.SEVERE, payment.toString());
        Logger.getLogger("G02_G02").log(Level.SEVERE, payment.unsafeToString());
    }

}