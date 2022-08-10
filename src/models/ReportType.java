package models;

/***
 * ReportType Class.
 */

public class ReportType {
    public String appointmentType;
    public int appointmentTotal;

    /**
     * ReportType Constructor.
     * @param appointmentTotal
     * @param appointmentType
     */
    public ReportType(String appointmentType, int appointmentTotal) {
        this.appointmentType = appointmentType;
        this.appointmentTotal = appointmentTotal;
    }

    /**
     * getters
     */
    public String getAppointmentType() {

        return appointmentType;
    }

    public int getAppointmentTotal() {

        return appointmentTotal;
    }

}
