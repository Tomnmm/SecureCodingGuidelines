package guidelines.summary.src.be.howest.ti.secure.development.summary;

import java.io.Serializable;
import java.sql.*;
import java.util.Date;

/**
 * G4.5: you might want to limit the extensibility of classses and method
 * To do so, make the class final
 *
 * G8.1: Do you really want this class to be serializable?
 */
public class BankAccount implements Serializable {

    /**
     * G8.2: balance might be sensitive data if this class is serialized
     * use keyword "transient" to avoid exposure
     */
    private double balance;

    private Date lastTrxDate;

    /**
     * G6.9: make public static fields final, so no one can modify them
     */
    public static String bankID = "479";
    public static String separator = "-";

    /**
     * G0.6 + G4.1: encapsulate, limit accessability (make it private)
     */
    String fullAccountNo;

    public BankAccount(int accountNo) {
        //calculate check digit
        int checkDigit = accountNo % 97;
        this.fullAccountNo = bankID + separator + String.valueOf(accountNo) + separator + checkDigit;
        /**
         * G1.2: better to use try with resources:
         * try(Connection conn = ..., Statement stmt = ... , ResultSet rs = ...) {}
         */
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb");
            Statement stmt = conn.createStatement();
            /**
             * G3.2: SQL injection is possible here! Use prepared statements!
             */
            String query = "select amount, date from transactions where account_no = %s order by date desc";
            ResultSet rs = stmt.executeQuery(String.format(query,accountNo));
            this.balance = 0;
            this.lastTrxDate = null;
            while(rs.next()) {
                this.balance += rs.getDouble("amount");
                if(this.lastTrxDate == null) {
                    this.lastTrxDate = rs.getDate("date");
                }
            }
        } catch(SQLException ex) {
            /**
             * G2.1: sensitive data in the exception!
             */
            throw new RuntimeException("failed to calculate balance for accountNo " + accountNo,ex);
        }
    }


    public void payWithCreditCard(String cardNo, double amount) {
        /**
         * G2.2: don't log sensitive data!
         */
        System.out.println("paymant with card: " + cardNo);
        /**
         * G3.9+G5.1: check on valid input: Double.isNaN(), Double.isFinite()
         * In case of integer: beware of integer overflows
         */
        balance -= amount;
    }

    public void changeAccountNo(int accountNo) {
        /**
         * G0.2: avoid code duplication; same logic is done in the constructor
         * better to provide a private method getFullAccountNo(int)
         */
        int checkDigit = accountNo % 97;
        this.fullAccountNo = bankID + separator + String.valueOf(accountNo) + separator + checkDigit;
    }

    private Date getLastTrxDate() {
        /**
         * //G6.2: Date is a mutable object! Better to return a clone!
         */
        return this.lastTrxDate;
    }

}