package be.howest.ti.secure.development.g1.g02;

import java.sql.*;

public class SQLconnection {
    public static void main(String[] args){

    }

    private void BadGetResults(String sqlQuery){
        try{
            Connection conn = DriverManager.getConnection("demo url");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            processResults(rs);
            stmt.close();
            //if there is an error the conn will not be closed
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void BetterBadGetResults(String sqlQuery){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("demo url");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            processResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            rs.close();
            stmt.close();
            // if rs or stmt equals null, connection will not be closed
            conn.close();
        }
    }
    private void BestBadGetResults(String sqlQuery){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("demo url");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            processResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null) {
                rs.close();
            }
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null){
                //if something, anything goes wrong in the closing of rs or stmt. conn will not be closed.
                conn.close();
            }
        }
    }

    private void GoodGetResult(String sqlQuery){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("demo url");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            processResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs != null) { rs.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (stmt != null) { stmt.close(); }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (conn != null) { conn.close(); }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void GoodGetResult2(String sqlQuery){
        //since java SE 7
        try(Connection conn = DriverManager.getConnection("demo url");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery)){
            processResults(rs);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void processResults(ResultSet rs) {
        //do something;
    }
}
