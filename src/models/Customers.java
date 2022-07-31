package models;


/**
 * Customers class contains customerName, customerAddress, customerPostalCode, customerPhoneNumber, customerState, divisionID.
 * These values are necessary for the customer information and this class also contains setters and getters for functionality.
 */

public class Customers {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private int divisionID;
    private int countryID;


    public Customers(int customerID, String customerName, String customerAddress, String customerPostalCode,
                     String customerPhoneNumber, int divisionID, int countryID) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionID = divisionID;
        this.countryID = countryID;


    }


    /**
     *
     * @return customerID
     */
    public int getCustomerID() {

        return customerID;
    }


    /**
     *
     * @return customerName
     */
    public String getCustomerName() {

        return customerName;
    }



    /**
     *
     * @return customerAddress
     */
    public String getCustomerAddress() {

        return customerAddress;
    }



    /**
     *
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {

        return customerPostalCode;
    }


    /**
     *
     * @return customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {

        return customerPhoneNumber;
    }



    /**
     *
     * @return divisionID
     */
    public Integer getDivisionID() {

        return divisionID;
    }

    /**
     *
     * @return countryID
     */
    public Integer getCountryID(){

        return countryID;
    }


    @Override
    public String toString(){

        return customerID + "-" + customerName;
    }
}
