package DAO;

import Main.JDBC;
import Models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDBA extends Country {

    public CountryDBA(int countryID, String countryName) {
        super(countryID, countryName);
    }

    /**
     * ObservableList that queries Country_ID and Country from the country's database table.
     *
     * @return countriesObservableList
     * @throws SQLException
     */
    public static ObservableList<CountryDBA> getCountries() throws SQLException {
        ObservableList<CountryDBA> countriesObservableList = FXCollections.observableArrayList();
        String sql = "SELECT Country_ID, Country from countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            CountryDBA country = new CountryDBA(countryID, countryName);
            countriesObservableList.add(country);
        }
        return countriesObservableList;
    }

}