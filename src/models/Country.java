package models;

public class Country {

    private String countryName;
    private int countryID;

    /**
     *
     *
     * @param countryName
     */
    public Country(int countryID,String countryName ) {

        this.countryName = countryName;
        this.countryID = countryID;
    }

    /**
     *
     * @return countryName
     */
    public String getCountryName() {

        return countryName;
    }

    public int getCountryID(){

        return countryID;
    }
@Override
    public String toString() {
        return countryID + " - " + countryName;
    }

}
