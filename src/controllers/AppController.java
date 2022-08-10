package controllers;
import helperClasses.AppointmentHelper;
import helperClasses.ContactHelper;
import helperClasses.CustomerHelper;
import helperClasses.UserHelper;
import models.Appointments;
import models.Contacts;
import models.Customers;
import models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private Button clearBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private RadioButton weeksRBtn;
    @FXML
    private RadioButton monthsRBtn;
    @FXML
    private RadioButton allAppointmentsRBtn;
    @FXML
    private ToggleGroup toggleRBtn;


    @FXML
    private TextField appointmentIdTxt;
    @FXML
    private TextField titleTxt;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField locationTxt;
    @FXML
    private ComboBox<Contacts> contactCombo;
    @FXML
    private TextField typeTxt;
    @FXML
    private ComboBox<LocalTime> sTimeCombo;
    @FXML
    private ComboBox<LocalTime> eTimeCombo;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<Users> userCombo;
    @FXML
    private ComboBox<Customers> customerCombo;


    @FXML
    private TableView<Appointments> appointmentsTableView;
    @FXML
    private TableColumn<AppointmentHelper, Integer> appointmentIdCol;
    @FXML
    private TableColumn<AppointmentHelper, String> titleCol;
    @FXML
    private TableColumn<AppointmentHelper, String> descriptionCol;
    @FXML
    private TableColumn<AppointmentHelper, String> locationCol;
    @FXML
    private TableColumn<AppointmentHelper, LocalDateTime> startCol;
    @FXML
    private TableColumn<AppointmentHelper, LocalDateTime> endCol;
    @FXML
    private TableColumn<AppointmentHelper, String> typeCol;
    @FXML
    private TableColumn<AppointmentHelper, Integer> customerIdCol;
    @FXML
    private TableColumn<AppointmentHelper, Integer> userIdCol;
    @FXML
    private TableColumn<AppointmentHelper, Integer> contactCol;


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        allAppointmentsRBtn.setSelected(true);

        ObservableList<Appointments> allAppointments = null;
        try {
            allAppointments = AppointmentHelper.getAllAppointments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        appointmentsTableView.setItems(allAppointments);

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID")); // make the column a name instead of Contact
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        ObservableList<Customers> customers = null;
        try {
            customers = CustomerHelper.getAllCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        customerCombo.setItems(customers);
        customerCombo.getValue();


        ObservableList<Users> users = null;
        try {
            users = UserHelper.getAllUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        userCombo.setItems(users);
        userCombo.getValue();


        ObservableList<Contacts> contacts = null;
        try {
            contacts = ContactHelper.getAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        contactCombo.setItems(contacts);
        contactCombo.getValue();


        LocalDate date = LocalDate.now();
        LocalTime startComboStart = LocalTime.of(8, 0);
        ZoneId localZone = ZoneId.systemDefault();
        ZonedDateTime startEST = ZonedDateTime.of(date, startComboStart, ZoneId.of("America/New_York"));
        ZonedDateTime startZDT = startEST.withZoneSameInstant(localZone);
        ZonedDateTime endZDT = startZDT.plusHours(14);

        while (startZDT.isBefore(endZDT)) {
            sTimeCombo.getItems().add(startZDT.toLocalTime());
            startZDT = startZDT.plusMinutes(30);
            eTimeCombo.getItems().add(startZDT.toLocalTime());

        }

        datePicker.setValue(date);
    }


    /**
     * When radio button for "All Appointments" is selected.
     *
     * @throws SQLException
     */
    @FXML
    void onActionAllAppointments(ActionEvent event) {
        try {
            ObservableList<Appointments> allAppointmentsList = AppointmentHelper.getAllAppointments();

            if (allAppointmentsList != null)
                for (Appointments appointment : allAppointmentsList) {
                    appointmentsTableView.setItems(allAppointmentsList);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * When radio button for "Month" is selected.
     *
     * @throws //SQLException
     */
    @FXML
    void onActionMonths(ActionEvent event) {
        try {
            ObservableList<Appointments> allAppointmentsList = AppointmentHelper.getAllAppointments();
            ObservableList<Appointments> appointmentsMonth = FXCollections.observableArrayList();

            LocalDateTime MonthStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
            LocalDateTime MonthEnd = MonthStart.plusDays(30);


            if (allAppointmentsList != null)

                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getEnd().isAfter(MonthStart) && appointment.getEnd().isBefore(MonthEnd)) {
                        appointmentsMonth.add(appointment);
                    }
                    appointmentsTableView.setItems(appointmentsMonth);
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * When radio button for week is selected.
     *
     * @throws SQLException
     */
    @FXML
    void onActionWeeks(ActionEvent event) {
        try {

            ObservableList<Appointments> allAppointmentsList = AppointmentHelper.getAllAppointments();
            ObservableList<Appointments> appointmentsWeek = FXCollections.observableArrayList();

            LocalDateTime weekStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
            LocalDateTime weekEnd = weekStart.plusWeeks(1);

            if (allAppointmentsList != null)

                allAppointmentsList.forEach(appointment -> {
                    if (appointment.getEnd().isAfter(weekStart) && appointment.getEnd().isBefore(weekEnd)) {
                        appointmentsWeek.add(appointment);
                    }
                    appointmentsTableView.setItems(appointmentsWeek);
                });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @FXML
    void onActionAdd(ActionEvent event) throws SQLException, IOException {
        try {


            if (titleTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a Title to the appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String addTitle = titleTxt.getText();

            if (descriptionTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a description to the appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String addDescription = descriptionTxt.getText();

            if (locationTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a Location to the appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String addLocation = locationTxt.getText();

            if (contactCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a contact to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            int addContact = contactCombo.getValue().getContactID();


            if (typeTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a type to the appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String addType = typeTxt.getText();

            if (sTimeCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a start time to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }

            if (eTimeCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add an end time to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }


            if (datePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a date to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }

            LocalDate addDate = datePicker.getValue();

            System.out.println(addDate + "cat");
            LocalDateTime addStart = LocalDateTime.of(addDate, sTimeCombo.getValue());
            LocalDateTime addEnd = LocalDateTime.of(addDate, eTimeCombo.getValue());

            if (userCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a user to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            int addUser = userCombo.getValue().getUserID();

            if (customerCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a customer to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            int addCustomer = customerCombo.getValue().getCustomerID();

            if (dateChecker(addDate) || startTimeChecker(addStart, addEnd) || sameTimeChecker(addStart, addEnd) || endTimeChecker(addStart, addEnd) || checkAppointmentOverlapAddOne(addStart, addCustomer) || checkAppointmentOverlapAddTwo(addEnd, addCustomer) || checkAppointmentOverlapAddThree(addStart, addEnd, addCustomer)) {
                System.out.println("check");
            } else {
                AppointmentHelper.createAppointment(addTitle, addDescription, addLocation, addType, addStart, addEnd, addCustomer, addUser, addContact);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Appointment added successfully!");
                Optional<ButtonType> result = alert.showAndWait();
            }
            ObservableList<Appointments> allAppointments = null;
            try {
                allAppointments = AppointmentHelper.getAllAppointments();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            appointmentsTableView.setItems(allAppointments);
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onActionBack(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/SelectScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void onActionDelete(ActionEvent event) {
        try {
            Appointments deleteSelectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

            if (deleteSelectedAppointment == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Appointment not selected");
                alert.showAndWait();
            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Would you like to remove the selected appointment?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    AppointmentHelper.deleteAppointment(deleteSelectedAppointment.getAppointmentID());
                }
            }
            ObservableList<Appointments> allAppointments = null;
            try {
                allAppointments = AppointmentHelper.getAllAppointments();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            appointmentsTableView.setItems(allAppointments);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void onActionExit(ActionEvent event) throws IOException {

        System.exit(0);

    }


    @FXML
    void onActionUpdate(ActionEvent event) {
        try {
            if (titleTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a Title to the appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String updateTitle = titleTxt.getText();

            if (descriptionTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a description to the appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String updateDescription = descriptionTxt.getText();

            if (locationTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a Location to the appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String updateLocation = locationTxt.getText();

            if (contactCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a contact to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            int updateContact = contactCombo.getValue().getContactID();


            if (typeTxt.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a type to the appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String updateType = typeTxt.getText();

            if (sTimeCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a start time to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }

            if (eTimeCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add an end time to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }


            if (datePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a date to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }

            LocalDate updateDate = datePicker.getValue();
            LocalDateTime updateStart = LocalDateTime.of(updateDate, sTimeCombo.getValue());
            LocalDateTime updateEnd = LocalDateTime.of(updateDate, eTimeCombo.getValue());

            if (userCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a user to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            int updateUser = userCombo.getValue().getUserID();

            if (customerCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Please add a customer to the appointment");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            int updateCustomer = customerCombo.getValue().getCustomerID();

            if (dateChecker(updateDate) || startTimeChecker(updateStart, updateEnd) || sameTimeChecker(updateStart, updateEnd) || endTimeChecker(updateStart, updateEnd) || checkAppointmentOverlapUpdateOne(updateStart, updateCustomer) || checkAppointmentOverlapUpdateTwo(updateEnd, updateCustomer) || checkAppointmentOverlapUpdateThree(updateStart, updateEnd, updateCustomer)) {
                System.out.println("check check");
            } else {

                AppointmentHelper.updateAppointment(updateTitle, updateDescription, updateLocation, updateType, updateStart, updateEnd, updateCustomer, updateUser, updateContact);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Appointment updated successfully!");
                Optional<ButtonType> result = alert.showAndWait();
            }

            ObservableList<Appointments> allAppointments = null;
            try {
                allAppointments = AppointmentHelper.getAllAppointments();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            appointmentsTableView.setItems(allAppointments);

        } catch (NumberFormatException | NullPointerException | SQLException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void onActionEdit(ActionEvent event) {
        Appointments selectAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

        appointmentIdTxt.setText(String.valueOf(selectAppointment.getAppointmentID()));
        titleTxt.setText(selectAppointment.getAppointmentType());
        descriptionTxt.setText(selectAppointment.getAppointmentDescription());
        locationTxt.setText(selectAppointment.getAppointmentLocation());
        typeTxt.setText(selectAppointment.getAppointmentType());

        for (Customers c : customerCombo.getItems())
            if (selectAppointment.getCustomerID() == c.getCustomerID()) {
                customerCombo.setValue(c);
                break;
            }

        for (Users u : userCombo.getItems())
            if (selectAppointment.getUserID() == u.getUserID()) {
                userCombo.setValue(u);
                break;
            }

        for (Contacts c : contactCombo.getItems())
            if (selectAppointment.getContactID() == c.getContactID()) {
                contactCombo.setValue(c);
                break;
            }

        sTimeCombo.setValue(LocalTime.from(selectAppointment.getStart()));
        eTimeCombo.setValue(LocalTime.from(selectAppointment.getEnd()));

        datePicker.setValue((selectAppointment.getStart().toLocalDate())); // ask questions on why this wont select from the table.

    }


    @FXML
    void onActionClear(ActionEvent event) throws SQLException {
        appointmentIdTxt.clear();
        titleTxt.clear();
        descriptionTxt.clear();
        locationTxt.clear();
        typeTxt.clear();
        datePicker.setValue(null);
        sTimeCombo.setValue(null);
        eTimeCombo.setValue(null);
        userCombo.setValue(null);
        customerCombo.setValue(null);
        contactCombo.setValue(null);

    }


    private boolean dateChecker(LocalDate datePicker) {
        if (datePicker.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Appointment must be set on, or after the current date.");
            Optional<ButtonType> result = alert.showAndWait();
            return true;
        } else {
            return false;
        }
    }


    private boolean startTimeChecker(LocalDateTime sTimeCombo, LocalDateTime eTimeCombo) {
        if (sTimeCombo.isAfter(eTimeCombo)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Appointment must start before the end time.");
            Optional<ButtonType> result = alert.showAndWait();
            return true;
        } else {
            return false;
        }
    }

    private boolean sameTimeChecker(LocalDateTime sTimeCombo, LocalDateTime eTimeCombo) {
        if (sTimeCombo.isEqual(eTimeCombo)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Appointment start and end times cannot be the same.");
            Optional<ButtonType> result = alert.showAndWait();
            return true;
        } else {
            return false;
        }
    }

    private boolean endTimeChecker(LocalDateTime sTimeCombo, LocalDateTime eTimeCombo) {
        if (eTimeCombo.isBefore(sTimeCombo)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Appointment must end after the start time.");
            Optional<ButtonType> result = alert.showAndWait();
            return true;
        } else {
            return false;
        }
    }


    private boolean checkAppointmentOverlapAddOne(LocalDateTime Start, int customerID) throws SQLException {
        for (Appointments a : AppointmentHelper.getAllAppointments()) {
            if (a.getCustomerID() == customerID) {
                continue;
            }
            if (Start.isAfter(a.getStart()) || Start.isEqual(a.getStart()) || Start.isBefore(a.getEnd())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");                                        //1
                alert.setContentText("Start time overlaps another appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    private boolean checkAppointmentOverlapAddTwo(LocalDateTime End, int customerID) throws SQLException {
        for (Appointments a : AppointmentHelper.getAllAppointments()) {
            if (a.getCustomerID() == customerID) {
                continue;
            }
            if (End.isAfter(a.getStart()) || End.isBefore(a.getEnd()) || End.isEqual(a.getEnd())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");                                        //2
                alert.setContentText("End time overlaps another appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return true;
            } else {
                continue;
            }
        }
        return false;

    }


    private boolean checkAppointmentOverlapAddThree(LocalDateTime Start, LocalDateTime End, int customerID) throws SQLException {
        for (Appointments a : AppointmentHelper.getAllAppointments()) {
            if (a.getCustomerID() == customerID) {
                continue;
            }
            if (Start.isBefore(a.getStart()) || Start.isEqual(a.getStart()) || End.isAfter(a.getEnd()) || End.isEqual(a.getEnd())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");                                                //3
                alert.setContentText("Start and End overlap a current appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return true;
            } else {
                continue;
            }
        }

        return false;
    }


    private boolean checkAppointmentOverlapUpdateOne(LocalDateTime Start, int customerID) throws SQLException {
        for (Appointments a : AppointmentHelper.getAllAppointments()) {
            if (a.getCustomerID() != customerID) {
                continue;
            }
            if (Start.isAfter(a.getStart()) || Start.isEqual(a.getStart()) || Start.isBefore(a.getEnd())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");                                        //1
                alert.setContentText("Start time overlaps another appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return true;
            } else {
                continue;
            }
        }
        return false;
    }


    private boolean checkAppointmentOverlapUpdateTwo(LocalDateTime End, int customerID) throws SQLException {
        for (Appointments a : AppointmentHelper.getAllAppointments()) {
            if (a.getCustomerID() != customerID) {
                continue;
            }
            if (End.isAfter(a.getStart()) || End.isBefore(a.getEnd()) || End.isEqual(a.getEnd())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");                                        //2
                alert.setContentText("End time overlaps another appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return true;
            } else {
                continue;
            }
        }
        return false;
    }


    private boolean checkAppointmentOverlapUpdateThree(LocalDateTime Start, LocalDateTime End, int customerID) throws SQLException {
        for (Appointments a : AppointmentHelper.getAllAppointments()) {
            if (a.getCustomerID() != customerID) {
                continue;
            }
            if (Start.isBefore(a.getStart()) || Start.isEqual(a.getStart()) || End.isAfter(a.getEnd()) || End.isEqual(a.getEnd())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");                                                //3
                alert.setContentText("Start and End overlap a current appointment.");
                Optional<ButtonType> result = alert.showAndWait();
                return true;
            } else {
                continue;
            }
        }
        return false;
    }


}

