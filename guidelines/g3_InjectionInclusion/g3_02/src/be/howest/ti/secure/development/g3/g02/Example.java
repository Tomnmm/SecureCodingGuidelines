package be.howest.ti.secure.development.g3.g02;

import java.sql.*;


public class Example {
    public static void main(String[] args) {
        new Example().run();
    }

    public void run() {
        Connection db = null;
        String user_input = "' or 1=1--";
        String dbFile = System.getProperty("user.dir")+"/example.sqlite";          // Sven Meuleman: Modfied it so it creates the file in the directory where the code is run.
        try {
            System.out.println("Connect to the database");
            String url = "jdbc:sqlite:" + dbFile;
            db = DriverManager.getConnection(url);
            Statement statement = db.createStatement();

            // Set up the database
            System.out.println("Set up tables");
            statement.executeUpdate("drop table if exists t1");
            statement.executeUpdate("create table t1 (id integer, user string, password string)");
            statement.executeUpdate("insert into t1 values(1, 'test', 'test')");

            // Bad example
            System.out.println("");
            System.out.println("Bad example");

            String sql = "select * from t1 where password = '" + user_input + "'";
            System.out.println(sql);
            showResults(statement.executeQuery(sql));
            
            
            // Good example
            System.out.println("");
            System.out.println("Good example");

            sql = "select * from t1 where password = ?";
            System.out.println(sql);
            PreparedStatement stmt = db.prepareStatement(sql);
            stmt.setString(1, user_input);
            showResults(stmt.executeQuery());
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /***
     * Added by Sven Meuleman on 24/12/2017
     * Best practice don't duplicate code, write once use many
     * 
     */
    private void showResults(ResultSet rs)
    {
    	try
    	{
    		/**
    		 * Check if we have a result
    		 */
    		if (!rs.isBeforeFirst() ) {    
    			System.out.println("No data"); 
    		} 
    		while(rs.next()) {
    			System.out.println("name = " + rs.getString("user"));
    			System.out.println("id = " + rs.getInt("id"));
    		}
    	 } catch (SQLException e) {
             System.out.println(e.getMessage());
    	 }
     }
}

