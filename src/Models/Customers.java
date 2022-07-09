package Models;


/**
 * Customers class contains customerName, customerAddress, customerPostalCode, customerPhoneNumber, customerState, divisionID.
 * These values are necessary for the customer information and this class also contains setters and getters for functionality.
 */

public class Customers {

    private String divisionName;
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private int divisionID;


    public Customers(int customerID, String customerName, String customerAddress, String customerPostalCode,
                     String customerPhoneNumber, int divisionID) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionID = divisionID;


    }



    /**
     *
     * @return customerID
     */
    public Integer getCustomerID() {

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
    public String getCustomerPhone() {

        return customerPhoneNumber;
    }



    /**
     *
     * @return divisionID
     */
    public Integer getCustomerDivisionID() {

        return divisionID;
    }



    public String toString(){
        return "#"+ Integer.toString(customerID) + "" + "["+ customerName + "]";
    }
}
