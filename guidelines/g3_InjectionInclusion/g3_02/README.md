# INJECT-2: Avoid dynamic SQL
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)

``
It is well known that dynamically created SQL statements including untrusted input are subject to command injection. This often takes the form of supplying an input containing a quote character (') followed by SQL. Avoid dynamic SQL.

For parameterised SQL statements using Java Database Connectivity (JDBC), use java.sql.PreparedStatement or java.sql.CallableStatement instead of java.sql.Statement. In general, it is better to use a well-written, higher-level library to insulate application code from SQL. When using such a library, it is not necessary to limit characters such as quote ('). If text destined for XML/HTML is handled correctly during output (Guideline 3-3), then it is unnecessary to disallow characters such as less than (<) in inputs to SQL.

An example of using PreparedStatement correctly:

        String sql = "SELECT * FROM User WHERE userId = ?"; 
        PreparedStatement prepStmt = con.prepareStatement(sql); 
        prepStmt.setString(1, userId); 
        ResultSet rs = prepStmt.executeQuery();

## Simple example

![Author](https://img.shields.io/badge/Author-Robin.Peiremans-blue.svg)
![Date](https://img.shields.io/badge/Date-20171129-lightgrey.svg)
![CHECKED BY LECTOR](https://img.shields.io/badge/CHECKED_BY_LECTOR-YES-green.svg)


This example uses an sqlite database to show one of the effects of improper input validation. The example is written on a linux system, so you will need to adjust the path to the database when running the code on other OS'es.

The ```Example``` class first creates a database connection, then creates the tables (so no existing database is needed) and then show the result of bad and good code.

The bad example creates a dynamic select statement, based on user input (simulated with a simple variable). The query as sent to the database query parser is printed.
Once executed, the example basically simulates an authentication bypass attack as often seen on website login pages some years ago.

The good example uses prepared statements with bind variables to mitigate this vulnerability.  