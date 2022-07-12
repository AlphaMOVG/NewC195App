package models;

public class Divisions {
    private int divisionID;
    private static String divisionName;
    public static int countryID;


    /**
     *
     * @param divisionID
     * @param countryID
     * @param divisionName
     */
    public Divisions(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    /**
     *
     * @return divisionID
     */
    public int getDivisionID() {

        return divisionID;
    }

    /**
     *
     * @return divisionName
     */
    public static String getDivisionName() {

        return divisionName;
    }

    /**
     *
     * @return country_ID
     */
    public static int getCountryID() {

        return countryID;
    }

}
