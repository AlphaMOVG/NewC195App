package DAO;

import Main.JDBC;
import Models.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;



public class CustomerDBA {

    /**
     * Observablelist that takes all customer data.
     *
     * @return customersObservableList
     * @throws SQLException
     */
    public static ObservableList<Customers> getAllCustomers(Connection connection) throws SQLException {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

        String query = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division from customers, Division_ID WHERE Customers.Division_ID = first_level_divisions.Division_ID";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();



        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPostalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            int divisionID = rs.getInt("Division_ID");
            Customers customer = new Customers(customerID, customerName, customerAddress, customerPostalCode, customerPhone, divisionID);
            customersObservableList.add(customer);
        }
        return customersObservableList;
    }

    public CustomerDBA() {
    }



}
