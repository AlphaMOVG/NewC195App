package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



/**
 * Class JDBC is the connection class to connect the application to the database.
 * */
public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface


    public static void  startConnection() {

        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            System.out.println("Connection successful!");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }


    }



    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }






}
