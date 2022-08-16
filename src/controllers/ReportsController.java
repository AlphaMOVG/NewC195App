package controllers;

import helperClasses.AppointmentHelper;
import helperClasses.ContactHelper;
import helperClasses.ReportsHelper;
import models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Month;
import java.util.Collections;

/**
 * Reports controller
 * Lambda #1 that adds appointments to the report by the type of appointment that they are.
 * Lambda #2 that gives the added results of the appointment by the Local date time of months.
 * Lambda #3 assigns key value pairs and filters the appointments by months for the report.
 * */
public class ReportsController {
    /**
     * Tableview
     * */
    @FXML
    private TableView<Appointments> allAppointmentsTable;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentContact;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentCustomerID;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentDescription;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentEnd;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentID;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentLocation;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentStart;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentTitle;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentTotalsAppointmentTypeCol;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentTotalsByMonth;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentTotalsMonthTotal;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentTotalsTypeTotalCol;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> appointmentType;
    /**
     * Button
     * */
    @FXML private Button backToMainMenu;
    /**
     * Combo Box
     * */
    @FXML private ComboBox<String> contactScheduleContactBox;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> tableContactID;
    /**
     * Table View
     * */
    @FXML private TableView<ReportType> appointmentTotalsAppointmentType;
    /**
     * Tab
     * */
    @FXML private Tab appointmentTotalsTab;
    /**
     * Table View
     * */
    @FXML private TableView<MonthReport> appointmentTotalAppointmentByMonth;
    /**
     * Table View
     * */
    @FXML private TableView<Reports> customerByCountry;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> countryName;
    /**
     * Table Column
     * */
    @FXML private TableColumn<?, ?> countryCounter;

    /**
     * Initialize and setup fields on the form.
     *
     */
    public void initialize() throws SQLException {

        countryName.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        countryCounter.setCellValueFactory(new PropertyValueFactory<>("countryCount"));
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tableContactID.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentTotalsAppointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentTotalsTypeTotalCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTotal"));
        appointmentTotalsByMonth.setCellValueFactory(new PropertyValueFactory<>("appointmentMonth"));
        appointmentTotalsMonthTotal.setCellValueFactory(new PropertyValueFactory<>("appointmentTotal"));

        ObservableList<Contacts> contactsObservableList = ContactHelper.getAllContacts();
        ObservableList<String> allContactsNames = FXCollections.observableArrayList();
        contactsObservableList.forEach(contacts -> allContactsNames.add(contacts.getContactName()));
        contactScheduleContactBox.setItems(allContactsNames);

    }

    /**
     * Fill fxml form with appointment data by contact.
     */
    @FXML
    public void appointmentDataByContact() {
        try {

            int contactID = 0;

            ObservableList<Appointments> getAllAppointmentData = AppointmentHelper.getAllAppointments();
            ObservableList<Appointments> appointmentInfo = FXCollections.observableArrayList();
            ObservableList<Contacts> getAllContacts = ContactHelper.getAllContacts();

            Appointments contactAppointmentInfo;

            String contactName = contactScheduleContactBox.getSelectionModel().getSelectedItem();

            for (Contacts contact: getAllContacts) {
                if (contactName.equals(contact.getContactName())) {
                    contactID = contact.getContactID();
                }
            }

            for (Appointments appointment: getAllAppointmentData) {
                if (appointment.getContactID() == contactID) {
                    contactAppointmentInfo = appointment;
                    appointmentInfo.add(contactAppointmentInfo);
                }
            }
            allAppointmentsTable.setItems(appointmentInfo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Total number of customer appointments by type and month report.
     *  Lambda #1 that adds appointments to the report by the type of appointment that they are.
     *  Lambda #2 that gives the added results of the appointment by the Local date time of months.
     *  Lambda #3 assigns key value pairs and filters the appointments by months for the report.
     *
     * @throws SQLException
     */
    public void appointmentTotalsTab() throws SQLException {
        try {
            ObservableList<Appointments> getAllAppointments = AppointmentHelper.getAllAppointments();
            ObservableList<Month> appointmentMonths = FXCollections.observableArrayList();
            ObservableList<Month> monthOfAppointments = FXCollections.observableArrayList();

            ObservableList<String> appointmentType = FXCollections.observableArrayList();
            ObservableList<String> uniqueAppointment = FXCollections.observableArrayList();

            ObservableList<ReportType> reportType = FXCollections.observableArrayList();
            ObservableList<MonthReport> reportMonths = FXCollections.observableArrayList();


            /**
             * Lambda #1 that adds appointments to the report by the type of appointment that they are.
             *
             */
            getAllAppointments.forEach(appointments -> {
                appointmentType.add(appointments.getAppointmentType());
            });

            /**
             * Lambda #2 that gives the added results of the appointment by the Local date time of months.
             *
             */
            getAllAppointments.stream().map(appointment -> {
                return appointment.getStart().getMonth();
            }).forEach(appointmentMonths::add);

            /**
             * Lambda #3 assigns key value pairs and filters the appointments by months for the report.
             *
             */
            appointmentMonths.stream().filter(month -> {
                return !monthOfAppointments.contains(month);
            }).forEach(monthOfAppointments::add);

            for (Appointments appointments: getAllAppointments) {
                String appointmentsAppointmentType = appointments.getAppointmentType();
                if (!uniqueAppointment.contains(appointmentsAppointmentType)) {
                    uniqueAppointment.add(appointmentsAppointmentType);
                }
            }

            for (Month month: monthOfAppointments) {
                int totalMonth = Collections.frequency(appointmentMonths, month);
                String monthName = month.name();
                MonthReport appointmentMonth = new MonthReport(monthName, totalMonth);
                reportMonths.add(appointmentMonth);
            }
            appointmentTotalAppointmentByMonth.setItems(reportMonths);

            for (String type: uniqueAppointment) {
                String typeToSet = type;
                int typeTotal = Collections.frequency(appointmentType, type);
                ReportType appointmentTypes = new ReportType(typeToSet, typeTotal);
                reportType.add(appointmentTypes);
            }
            appointmentTotalsAppointmentType.setItems(reportType);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Custom report to display number of appointments in each Country.
     * @throws SQLException
     */
    public void customerByCountry() throws SQLException {
        try {

            ObservableList<Reports> aggregatedCountries = ReportsHelper.getCountries();
            ObservableList<Reports> countriesToAdd = FXCollections.observableArrayList();


            aggregatedCountries.forEach(countriesToAdd::add);

            customerByCountry.setItems(countriesToAdd);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
/**
 *
 *
 */

    /**
     * Button to go back to main menu.
     *
     */
    @FXML
    public void backToMainMenu (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/SelectScreen.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

}

