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
            String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.location, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID, a.Contact_ID " +
                    "FROM appointments AS a " +
                    "INNER JOIN customers AS c ON a.Customer_ID = c.Customer_ID " +
                    "INNER JOIN users AS u ON a.User_ID = u.User_ID " +
                    "INNER JOIN contacts AS con ON a.Contact_ID = con.Contact_ID";
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

    /**
     * Create appointment SQL query
     *
     *
     * @throws //SQLException
     */
    public static void createAppointment(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        try {
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?,?,?,?,?,?, ?,?,?) ";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, contactID);
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update appointment SQL query
     *
     *
     * @throws //SQLException
     */
    public static void updateAppointment(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID, int appointmentID) {
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ? ,  Location = ?, Type = ?," +
                    " Start = ?, End = ? , Customer_ID = ? , User_ID = ? , Contact_ID = ? WHERE Appointment_ID = ? ";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, contactID);
            ps.setInt(10, appointmentID);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete appointment SQL query
     *
     *
     * @throws //SQLException
     */
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
