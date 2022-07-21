package models;


/**
 * Customers class contains customerName, customerAddress, customerPostalCode, customerPhoneNumber, customerState, divisionID.
 * These values are necessary for the customer information and this class also contains setters and getters for functionality.
 */

public class Customers {

    private String divisionName;
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private String divisionID;
    private String countryID;


    public Customers(String customerID, String customerName, String customerAddress, String customerPostalCode,
                     String customerPhoneNumber, String divisionID, String countryID) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionID = divisionID;
        this.countryID = countryID;


    }


    /**
     * @return customerID
     */
    public String getCustomerID() {

        return customerID;
    }


    /**
     * @return customerName
     */
    public String getCustomerName() {

        return customerName;
    }


    /**
     * @return customerAddress
     */
    public String getCustomerAddress() {

        return customerAddress;
    }


    /**
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {

        return customerPostalCode;
    }


    /**
     * @return customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {

        return customerPhoneNumber;
    }


    /**
     * @return divisionID
     */
    public String getDivisionID() {

        return divisionID;
    }

    /**
     * @return countryID
     */
    public String getCountryID() {

        return countryID;
    }
}




