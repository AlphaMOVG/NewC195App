package helperClasses;

import main.JDBC;
import models.Appointments;
import models.Customers;
import models.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportsHelper  {
//take a look at this code to edit
    public ReportsHelper() throws SQLException {
        ObservableList<Appointments> appointments = AppointmentHelper.getAllAppointments();
    }

    /**
     * SQL Query that pulls the exact data needed: Countries and total appointment per country.
     * @throws SQLException
     * @return countriesObservableList
     */
    public static ObservableList<Reports> getCountries() throws SQLException {
        ObservableList<Reports> countriesObservableList = FXCollections.observableArrayList();
        String sql = "select countries.Country, count(customers.Customer_ID) as countryCount from customers inner join first_level_divisions on customers.Division_ID = first_level_divisions.Division_ID inner join countries on countries.Country_ID = first_level_divisions.Country_ID group by countries.Country, count(customers.Customer_ID) order by count(customers.Customer_ID) desc";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            String countryName = rs.getString("Country");
            int countryCount = rs.getInt("countryCount");
            Reports report = new Reports(countryCount, countryName);
            countriesObservableList.add(report);

        }

        return countriesObservableList;
    }

}