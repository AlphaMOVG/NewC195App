package helperClasses;

import main.JDBC;
import models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHelper {
    public UserHelper(int userID, String userName, String userPassword) {
        super();
    }

    /**
     * This method validates the user for the login form.
     *
     * @param password
     * @param username
     */
    public static int validateUser(String username, String password) {
        try {
            String sqlQuery = "SELECT * FROM users ";

            PreparedStatement ps = JDBC.connection.prepareStatement(sqlQuery);

            ResultSet rs = ps.executeQuery();

            rs.next();

            if (rs.getString("User_Name").equals(username)) {
                if (rs.getString("Password").equals(password)) {
                    return rs.getInt("User_ID");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Method to pull in all user data to getAllUsers observablelist.
     *
     * @return usersObservableList
     * @throws SQLException
     */
    public static ObservableList<Users> getAllUsers() throws SQLException {
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();

        String sql = "SELECT * from users, ";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String userPassword = rs.getString("Password");
            Users user = new Users(userID, userName, userPassword);
            usersObservableList.add(user);
        }
        return usersObservableList;
    }
}
