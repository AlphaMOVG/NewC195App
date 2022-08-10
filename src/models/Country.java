package models;


/**
 * Country class.
 * */
public class Country {

    private String countryName;
    private int countryID;

    /**
     *
     * @param countryID
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
    /**
     *
     * @return countryID
     */
    public int getCountryID(){

        return countryID;
    }

    /**
     * To string method for countryID and countryName.
     * */
@Override
    public String toString() {
        return countryID + " - " + countryName;
    }

}
