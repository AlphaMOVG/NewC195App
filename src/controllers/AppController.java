package controllers;
import helperClasses.AppointmentHelper;
import helperClasses.ContactHelper;
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
    private RadioButton appointmentsRBtn;
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
    private ComboBox<String> sTimeCombo;
    @FXML
    private ComboBox<String> eTimeCombo;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<Users> userCombo;
    @FXML
    private ComboBox<Customers> customerIDCombo;


    @FXML
    private TableView<Appointments> appointmentsTableView;
    @FXML
    private TableColumn<?, ?> appointmentIdCol;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableColumn<?, ?> descriptionCol;
    @FXML
    private TableColumn<?, ?> locationCol;
    @FXML
    private TableColumn<?, ?> startCol;
    @FXML
    private TableColumn<?, ?> endCol;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<?, ?> customerIdCol;
    @FXML
    private TableColumn<?, ?> userIdCol;
    @FXML
    private TableColumn<?, ?> contactCol;


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

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

        LocalTime startComboStart = LocalTime.of(8, 0);
        LocalTime endComboEnd = LocalTime.of(8, 30);
        while (startComboStart.isBefore(endComboEnd.plusSeconds(1))) {
            sTimeCombo.getItems().add(String.valueOf(startComboStart));
            eTimeCombo.getItems().add(String.valueOf(endComboEnd));
            startComboStart = startComboStart.plusMinutes(30);
            endComboEnd = endComboEnd.plusMinutes(30);

            /*if (startComboStart == endComboEnd) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Appointment times cannot be at the same time.");
                Optional<ButtonType> result = alert.showAndWait();
            }
            if(startComboStart.isAfter(endComboEnd)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Start time cannot be after the end time");Optional<ButtonType> result = alert.showAndWait();

            }*/
        }


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
    void onActionMonths(ActionEvent event) throws SQLException {
        try {
            ObservableList<Appointments> allAppointmentsList = AppointmentHelper.getAllAppointments();
            ObservableList<Appointments> appointmentsMonth = FXCollections.observableArrayList();

            LocalDateTime MonthStart = LocalDateTime.now().minusMonths(1);
            LocalDateTime MonthEnd = LocalDateTime.now().plusMonths(1);


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
    void onActionWeeks(ActionEvent event) throws SQLException {
        try {

            ObservableList<Appointments> allAppointmentsList = AppointmentHelper.getAllAppointments();
            ObservableList<Appointments> appointmentsWeek = FXCollections.observableArrayList();

            LocalDateTime weekStart = LocalDateTime.now().minusWeeks(1);
            LocalDateTime weekEnd = LocalDateTime.now().plusWeeks(1);

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
        try{

        } catch (Exception e) {
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
        try{

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
        try{

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @FXML
    void onActionEdit(ActionEvent event) {

    }



    @FXML
    void onActionClear(ActionEvent event) {

    }

}
