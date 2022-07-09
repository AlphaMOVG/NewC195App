package DAO;

import Main.JDBC;
import Models.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDBA {

    /**
     * Create observablelist from all contacts.
     *
     * @return contactsObservableList
     * @throws SQLException
     */
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> contactsObservableList = FXCollections.observableArrayList();

        String sql = "SELECT * from contacts";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Contacts contact = new Contacts(contactID, contactName);
            contactsObservableList.add(contact);
        }
        return contactsObservableList;
    }

    /**
     * Retrieves contact ID given contact name.
     *
     * @param contactID
     * @return contactID
     * @throws SQLException
     */
    public static String retrieveContactID(String contactID) throws SQLException {
        PreparedStatement ps = JDBC.getConnection().prepareStatement("SELECT * FROM contacts WHERE Contact_Name = ?");
        ps.setString(1, contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contactID = rs.getString("Contact_ID");
        }
        return contactID;
    }
}