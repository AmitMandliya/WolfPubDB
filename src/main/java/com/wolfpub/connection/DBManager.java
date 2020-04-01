package com.wolfpub.connection;
import java.sql.*;

public class DBManager {
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/bpatel24"; // Using SERVICE_NAME
    private static final String user = "bpatel24";
    private static final String password = "bin@123";
    public Connection connection=null;
    public Connection getConnection() {
        try {
            //Loading the driver
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                //Get a connection instance
                connection = DriverManager.getConnection(jdbcURL,user,password);
            } catch(SQLException e){
                System.out.println("Error while creating a JDBC connection");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found");
            e.printStackTrace();
        }
        return connection;
    }
}
