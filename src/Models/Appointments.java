package Models;

import java.time.LocalDateTime;

public class Appointments {

    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime start;
    private LocalDateTime end;
    public int customerID;
    public int userID;
    public int contactID;


    public Appointments(int appointmentID, String appointmentTitle, String appointmentDescription,
                        String appointmentLocation, String appointmentType, LocalDateTime start, LocalDateTime end, int customerID,
                        int userID, int contactID) {

        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     *
     * @return appointmentID
     */
    public int getAppointmentID() {

        return appointmentID;
    }

    /**
     *
     * @return appointmentTitle
     */
    public String getAppointmentTitle() {

        return appointmentTitle;
    }

    /**
     *
     * @return appointmentDescription
     */
    public String getAppointmentDescription() {

        return appointmentDescription;
    }

    /**
     *
     * @return appointmentLocation
     */
    public String getAppointmentLocation() {

        return appointmentLocation;
    }

    /**
     *
     * @return appointmentType
     */
    public String getAppointmentType() {

        return appointmentType;
    }


    /**
     *
     * @return start
     */
    public LocalDateTime getStart() {
        System.out.println("start " + start);

        return start;
    }



    /**
     *
     * @return end
     */
    public LocalDateTime getEnd() {

        return end;
    }

    /**
     *
     * @return customerID
     */
    public int getCustomerID () {

        return customerID;
    }

    /**
     *
     * @return userID
     */
    public int getUserID() {

        return userID;
    }

    /**
     *
     * @return contactID
     */
    public int getContactID() {

        return contactID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }


}
