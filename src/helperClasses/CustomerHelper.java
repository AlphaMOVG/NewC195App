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
            String query = "SELECT c.Customer_ID, c.Customer_Name, c.Address, c.Postal_code, c.Phone, c.Division_ID, cn.Country_ID "+
                    " FROM customers AS C " +
                    " INNER JOIN first_level_divisions AS F ON c.Division_ID = f.Division_ID" +
                    " INNER JOIN countries AS CN ON CN.Country_ID = F.Country_ID";

            PreparedStatement ps = JDBC.connection.prepareStatement(query);
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


    public static void createCustomer(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int divisionID, int countryID) {
        try {
            String sql = "INSERT INTO customers VALUES(NULL,?,?,?,?,?,?)";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        String sql  = "UPDATE customers set Customer_ID = ?, Customer_Name = ?, Address = ?, Postal_Code = ? ,  Phone = ?, Division_ID = ?, Country_ID = ? ";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
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

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ps.execute();

            String sqlTwo = "DELETE from customers WHERE Customer_ID = ?";
            PreparedStatement psTwo = JDBC.connection.prepareStatement(sqlTwo);
            ps.setInt(1, customerID);
            ps.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
