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
    public static ObservableList<Customers> getAllCustomers() throws SQLException {

        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT c.Customer_ID, c.Customer_Name, c.Address, c.Postal_code, c.Phone, c.Division_ID, cn.Country_ID " +
                    " FROM customers AS c " +
                    " INNER JOIN first_level_divisions AS f ON c.Division_ID = f.Division_ID " +
                    " INNER JOIN countries AS cn ON cn.Country_ID = f.Country_ID";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
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


    /**
     * Create customer SQL query
     *
     *
     *
     */
    public static void createCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, Integer divisionID) {
        try {
            String sql = "INSERT INTO customers VALUES(NULL,?,?,?,?,NOW(),'JF', NOW(),'JF',?)";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
           // ps.setInt(1, customerID);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostalCode);
            ps.setString(4, customerPhone);
            ps.setInt(5, divisionID);

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Update customer SQL query
     *
     *
     *
     */
    public static void updateCustomer(String customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int divisionID){
        try {
        String sql  = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ? , Phone = ?, Division_ID = ? WHERE Customer_ID = ? ";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostalCode);
            ps.setString(4, customerPhone);
            ps.setInt(5, divisionID);
            ps.setString(6, customerID);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     * Delete customer SQL query
     *
     *
     *
     */
    public static void deleteCustomer(int customerID){
        try {
            String sql = "DELETE from customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
