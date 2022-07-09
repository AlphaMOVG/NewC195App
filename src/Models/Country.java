package Models;

public class Country {

    private String countryName;
    private static int countryID;

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

    public static int getCountryID(){
        return countryID;
    }

    public String toString(){
        return "#"+ Integer.toString(Integer.parseInt(countryName));
    }
}
