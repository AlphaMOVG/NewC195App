package helperClasses;

import main.JDBC;
import models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryHelper extends Country {

    public CountryHelper(int countryID, String countryName) {
        super(countryID, countryName);
    }

    /**
     * ObservableList that queries Country_ID and Country from the country's database table.
     *
     * @return countriesObservableList
     * @throws SQLException
     */
    public static ObservableList<CountryHelper> getCountries() throws SQLException {
        ObservableList<CountryHelper> countriesObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            CountryHelper country = new CountryHelper(countryID, countryName);
            countriesObservableList.add(country);
        }
        return countriesObservableList;
    }

}