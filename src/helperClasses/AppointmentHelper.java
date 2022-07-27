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
try {


    String sql = "SELECT * FROM appointments";
    System.out.println(sql);
    PreparedStatement ps = JDBC.connection.prepareStatement(sql);
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
} catch (SQLException e) {
    e.printStackTrace();
}

        return appointmentsList;
    }


    public static void createAppointment(int appointmentID, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp start, Timestamp end, int customerID, int userID, int contactID) {
        try {
            String sql = "INSERT INTO appointments VALUES(NULL,?,?,?,?,?,?, NOW(),'JF', NOW(),'JF',?,?,?";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, contactID);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void updateAppointment(int appointmentID, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, Timestamp start, Timestamp end, int customerID, int userID, int contactID) {
        try {
            String sql  = "UPDATE appointments SET Title = ?, Address = ?, Description = ? ,  Location = ?, Type = ?, Start = ?, End = ? WHERE Customer_ID = ? AND User_ID = ? AND Contact_ID = ? ";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, contactID);

            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteAppointment(int appointmentID) throws SQLException {
        try {

            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, appointmentID);
            ps.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
