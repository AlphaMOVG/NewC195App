package models;
/**
 * Reports Class.
 * */
public class Reports {
    private int countryCount;
    private String countryName;
    public String appointmentMonth;
    public int appointmentTotal;



    /**
     * Reports Constructor
     * @param countryName
     * @param countryCount
     */
    public Reports(int countryCount, String countryName) {
        this.countryCount = countryCount;
        this.countryName = countryName;
    }

    /**
     * Returns country name for custom report.
     * @return countryName
     */
    public String getCountryName() {

        return countryName;
    }

    /**
     * Total for each country.
     * @return countryCount
     */
    public int getCountryCount() {

        return countryCount;
    }

    public String getAppointmentMonth() {
        return appointmentMonth;
    }

    public int getAppointmentTotal() {
        return appointmentTotal;
    }


}
