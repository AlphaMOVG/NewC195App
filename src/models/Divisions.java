package models;

public class Divisions {
    private int divisionID;
    private String divisionName;
    public int countryID;


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
    public String getDivisionName() {

        return divisionName;
    }

    /**
     *
     * @return country_ID
     */
    public int getCountryID() {

        return countryID;
    }


    @Override
    public String toString(){

        return divisionID + "-" + divisionName;
}
}
