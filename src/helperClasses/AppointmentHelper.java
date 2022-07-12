package helperClasses;

import main.JDBC;
import models.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentHelper {
    /**
     * Observablelist for all appointments in database.
     *
     * @return appointmentsObservableList
     * @throws //SQLException
     */
    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        String sql = "SELECT * from appointments, Customer_ID WHERE appointments.Customer_ID = customers.Customer_ID, User_ID WHERE appointments.User_ID = users.User_ID, Contact_ID WHERE appointments.Contact_ID = contacts.Contact_ID";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, start, end, customerID, userID, contactID);
            appointmentsList.add(appointment);
        }

        return appointmentsList;
    }


    public static void createAppointment(int appointmentID, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp start, Timestamp end, int customerID, int userID, int contactID) {
        try {
            String sql = "INSERT INTO appointments VALUES(NULL,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, appointmentID);
            ps.setString(2, appointmentTitle);
            ps.setString(3, appointmentDescription);
            ps.setString(4, appointmentLocation);
            ps.setString(5, appointmentType);
            ps.setTimestamp(6, start);
            ps.setTimestamp(7, end);
            ps.setInt(8, customerID);
            ps.setInt(9, userID);
            ps.setInt(10, contactID);


            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int appID = rs.getInt("1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAppointment(int appointmentID, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp start, Timestamp end, int customerID, int userID, int contactID) {
        try {
            String sql  = "UPDATE appointments set Appointment_ID = ?, Title = ?, Address = ?, Description = ? ,  Location = ?, Type = ?, Start = ?, End = ? WHERE Customer_ID = ? AND User_ID = ? AND Contact_ID = ? ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ps.setString(2, appointmentTitle);
            ps.setString(3, appointmentDescription);
            ps.setString(4, appointmentLocation);
            ps.setString(5, appointmentType);
            ps.setTimestamp(6, start);
            ps.setTimestamp(7, end);
            ps.setInt(8, customerID);
            ps.setInt(9, userID);
            ps.setInt(10, contactID);

            ps.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that deletes appointment
     * based on appointment ID.
     *
     * @param //customer
     * @param
     * @return result
     * @throws //SQLException
     */
    public static void deleteAppointment(int appointmentID, int customerID) throws SQLException {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ps.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
