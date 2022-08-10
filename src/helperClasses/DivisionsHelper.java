package helperClasses;

import main.JDBC;
import models.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionsHelper extends Divisions {

    public DivisionsHelper(int divisionID, String divisionName, int country_ID) {
        super(divisionID, divisionName, country_ID);
    }

    /**
     * ObservableList that takes entire first_level_divisions table.
     *
     * @return firstLevelDivisionsObservableList
     * @throws SQLException
     *
     */
    public static ObservableList<Divisions> getAllDivisions( ) throws SQLException {
        ObservableList<Divisions> DivisionsObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int country_ID = rs.getInt("Country_ID");
                Divisions divisions = new Divisions(divisionID, divisionName, country_ID);
                DivisionsObservableList.add(divisions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DivisionsObservableList;
    }

    /**
     * ObservableList that gets a list of filtered divisions based on their Country ID.
     *
     * @return filteredDivisionsObservableList
     * @throws SQLException
     *
     */
    public static ObservableList<Divisions> getAllFilteredDivisions(int countryID) throws SQLException {
        ObservableList<Divisions> filteredDivisionsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from first_level_divisions WHERE Country_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, countryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int country_ID = rs.getInt("Country_ID");
                Divisions divisions = new Divisions(divisionID, divisionName, country_ID);
                filteredDivisionsObservableList.add(divisions);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return filteredDivisionsObservableList;
    }
}
