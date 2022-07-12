package helperClasses;

import main.JDBC;
import models.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;



public class CustomerHelper {

    /**
     * Observablelist that takes all customer data.
     *
     * @return customersObservableList
     * @throws SQLException
     */
    public static ObservableList<Customers> getAllCustomers(Connection connection) throws SQLException {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM customers, Division_ID WHERE customers.Division_ID = first_level_divisions.Division_ID, countries WHERE customers.Country_ID = countries.Country_ID";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                int countryID = rs.getInt("Country_ID");
                Customers customer = new Customers(customerID, customerName, customerAddress, customerPostalCode, customerPhone, divisionID, countryID);
                customersObservableList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersObservableList;
    }

    public CustomerHelper() {
    }

    public static void createCustomer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int divisionID, int countryID) {
        try {
            String sql = "INSERT INTO customers VALUES(NULL,?,?,?,?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerID);
            ps.setString(2, customerName);
            ps.setString(3, customerAddress);
            ps.setString(4, customerPostalCode);
            ps.setString(5, customerPhone);
            ps.setInt(6, divisionID);
            ps.setInt(7, countryID);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int custID = rs.getInt("1");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void updateCustomer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int divisionID, int countryID){
        try {
        String sql  = "UPDATE customers set Customer_ID = ?, Customer_Name = ?, Address = ?, Postal_Code = ? ,  Phone = ?, WHERE Division_ID = ? AND Country_ID = ? ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerID);
            ps.setString(2, customerName);
            ps.setString(3, customerAddress);
            ps.setString(4, customerPostalCode);
            ps.setString(5, customerPhone);
            ps.setInt(6, divisionID);
            ps.setInt(7, countryID);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void deleteCustomer(int appointmentID, int customerID){
        try {

            String sql = "DELETE from appointments WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ps.execute();

            String sqlTwo = "DELETE from customers WHERE Customer_ID = ?";
            PreparedStatement psTwo = JDBC.getConnection().prepareStatement(sqlTwo);
            ps.setInt(1, customerID);
            ps.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
