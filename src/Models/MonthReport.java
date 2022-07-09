package Models;

public class MonthReport {
    public String appointmentMonth;
    public int appointmentTotal;

    /**
     * @param appointmentMonth
     * @param appointmentTotal
     */
    public MonthReport(String appointmentMonth, int appointmentTotal) {
        this.appointmentMonth = appointmentMonth;
        this.appointmentTotal = appointmentTotal;
    }

    /**
     * @return appointmentMonth
     */
    public String getAppointmentMonth() {

        return appointmentMonth;
    }

    /**
     * @return appointmentTotal
     */
    public int getAppointmentTotal() {

        return appointmentTotal;
    }
}
