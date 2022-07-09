package Controllers;

import DAO.AppointmentDBA;
import DAO.CountryDBA;
import DAO.CustomerDBA;
import DAO.DivisionsDBA;
import Main.JDBC;
import Models.Appointments;
import Models.Country;
import Models.Customers;
import Models.Divisions;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private Button clearBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button exitBtn;


    @FXML
    private TextField customerIdTxt;
    @FXML
    private TextField customerNameTxt;
    @FXML
    private TextField phoneNumberTxt;
    @FXML
    private TextField postalCodeTxt;
    @FXML
    private ComboBox<String> divisionCombo;
    @FXML
    private ComboBox<String> countryCombo;
    @FXML
    private TextField addressTxt;


    @FXML
    private TableView<Customers> customerTableView;
    @FXML
    private TableColumn<?, ?> customerIdCol;
    @FXML
    private TableColumn<?, ?> customerNameCol;
    @FXML
    private TableColumn<?, ?> phoneNumberCol;
    @FXML
    private TableColumn<?, ?> postalCodeCol;
    @FXML
    private TableColumn<?, ?> divisionCol;
    @FXML
    private TableColumn<?, ?> countryCol;
    @FXML
    private TableColumn<?, ?> addressCol;








    @FXML
    void onActionAdd(ActionEvent event) {

        try {
            Connection connection = JDBC.startConnection();

            if (!customerNameTxt.getText().isEmpty() || !addressTxt.getText().isEmpty() || !customerIdTxt.getText().isEmpty() || !postalCodeTxt.getText().isEmpty() || !phoneNumberTxt.getText().isEmpty() || !divisionCombo.getValue().isEmpty() || !countryCombo.getValue().isEmpty())
            {

                //create random ID for new customer id
                Integer newCustomerID = (int) (Math.random() * 100);

                int firstLevelDivisionName = 0;
                for (Divisions firstLevelDivision : DivisionsDBA.getAllDivisions()) {
                    if (divisionCombo.getSelectionModel().getSelectedItem().equals(firstLevelDivision.getDivisionName())) {
                        firstLevelDivisionName = firstLevelDivision.getDivisionID();
                    }
                }
                String insertStatement = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
                JDBC.setPreparedStatement(insertStatement, JDBC.getConnection());
                PreparedStatement ps = JDBC.getPreparedStatement();
                ps.setInt(1, newCustomerID);
                ps.setString(2, customerNameTxt.getText());
                ps.setString(3, addressTxt.getText());
                ps.setString(4, postalCodeTxt.getText());
                ps.setString(5, phoneNumberTxt.getText());
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(7, "admin");
                ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(9, "admin");
                ps.setInt(10, firstLevelDivisionName);
                ps.execute();

                customerIdTxt.clear();
                customerNameTxt.clear();
                addressTxt.clear();
                postalCodeTxt.clear();
                phoneNumberTxt.clear();

                ObservableList<Customers> refreshCustomersList = CustomerDBA.getAllCustomers(connection);
                customerTableView.setItems(refreshCustomersList);

            }
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
    void onActionDelete(ActionEvent event) throws SQLException {
        Connection connection = JDBC.startConnection();
        ObservableList<Appointments> getAllAppointmentsList = AppointmentDBA.getAllAppointments();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected customer and all associated appointments? ");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if ((confirmation).isPresent() && confirmation.get() == ButtonType.OK) {
            int deleteCustomerID = customerTableView.getSelectionModel().getSelectedItem().getCustomerID();
            AppointmentDBA.deleteAppointment(deleteCustomerID, connection);

            String sqlDelete = "DELETE FROM customers WHERE Customer_ID = ?";
            JDBC.setPreparedStatement(sqlDelete, JDBC.getConnection());

            PreparedStatement psDelete = JDBC.getPreparedStatement();
            int customerFromTable = customerTableView.getSelectionModel().getSelectedItem().getCustomerID();

            //Delete all customer appointments from database.
            for (Appointments appointment: getAllAppointmentsList) {
                int customerFromAppointments = appointment.getCustomerID();
                if (customerFromTable == customerFromAppointments) {
                    String deleteStatementAppointments = "DELETE FROM appointments WHERE Appointment_ID = ?";
                    JDBC.setPreparedStatement(deleteStatementAppointments, JDBC.getConnection());


                }
            }
            psDelete.setInt(1, customerFromTable);
            psDelete.execute();
            ObservableList<Customers> refreshCustomersList = CustomerDBA.getAllCustomers(connection);
            customerTableView.setItems(refreshCustomersList);
        }

    }


    @FXML
    void onActionUpdate(ActionEvent event) {

        try {
            JDBC.startConnection();
            Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

            String divisionName = "", countryName = "";

            if (selectedCustomer != null) {
                ObservableList<CountryDBA> getAllCountries = CountryDBA.getCountries();
                ObservableList<Divisions> getFLDivisionNames = DivisionsDBA.getAllDivisions();
                ObservableList<String> allFLDivision = FXCollections.observableArrayList();

                divisionCombo.setItems(allFLDivision);

                customerIdTxt.setText(String.valueOf((selectedCustomer.getCustomerID())));
                customerNameTxt.setText(selectedCustomer.getCustomerName());
                addressTxt.setText(selectedCustomer.getCustomerAddress());
                postalCodeTxt.setText(selectedCustomer.getCustomerPostalCode());
                phoneNumberTxt.setText(selectedCustomer.getCustomerPhone());

                for (Divisions flDivision: getFLDivisionNames) {
                    allFLDivision.add(flDivision.getDivisionName());
                    int countryIDToSet = flDivision.getCountryID();

                    if (flDivision.getDivisionID() == selectedCustomer.getCustomerDivisionID()) {
                        divisionName = flDivision.getDivisionName();

                        for (Country country: getAllCountries) {
                            if (Country.getCountryID() == countryIDToSet) {
                                countryName = country.getCountryName();
                            }
                        }
                    }
                }
                divisionCombo.setValue(divisionName);
                countryCombo.setValue(countryName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }





    @FXML
    void onActionSave(ActionEvent event) {

    }



    @FXML
    void onActionClear(ActionEvent event) {


    }

    @FXML
    void onActionExit(ActionEvent event) throws IOException {

        System.exit(0);

    }

    public void customerEditCountryDropDown(ActionEvent event) throws SQLException {
        try {
            JDBC.startConnection();

            String selectedCountry = countryCombo.getSelectionModel().getSelectedItem();

            ObservableList<Divisions> getAllFirstLevelDivisions = DivisionsDBA.getAllDivisions();

            ObservableList<String> flDivisionUS = FXCollections.observableArrayList();
            ObservableList<String> flDivisionUK = FXCollections.observableArrayList();
            ObservableList<String> flDivisionCanada = FXCollections.observableArrayList();

            getAllFirstLevelDivisions.forEach(firstLevelDivision -> {
                if (Divisions.getCountryID() == 1) {
                    flDivisionUS.add(Divisions.getDivisionName());
                } else if (Divisions.getCountryID() == 2) {
                    flDivisionUK.add(Divisions.getDivisionName());
                } else if (Divisions.getCountryID() == 3) {
                    flDivisionCanada.add(Divisions.getDivisionName());
                }
            });


            if (selectedCountry.equals("U.S")) {
                divisionCombo.setItems(flDivisionUS);
            }
            else if (selectedCountry.equals("UK")) {
                divisionCombo.setItems(flDivisionUK);
            }
            else if (selectedCountry.equals("Canada")) {
                divisionCombo.setItems(flDivisionCanada);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection connection = JDBC.startConnection();

            ObservableList<CountryDBA> allCountries = CountryDBA.getCountries();
            ObservableList<String> countryNames = FXCollections.observableArrayList();
            ObservableList<Divisions> allFirstLevelDivisions = DivisionsDBA.getAllDivisions();
            ObservableList<String> firstLevelDivisionAllNames = FXCollections.observableArrayList();
            ObservableList<Customers> allCustomersList = CustomerDBA.getAllCustomers(connection);

            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
            phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
            divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));


            allCountries.stream().map(Country::getCountryName).forEach(countryNames::add);
            divisionCombo.setItems(countryNames);

            // lambda #1
            allFirstLevelDivisions.forEach(firstLevelDivision -> firstLevelDivisionAllNames.add(firstLevelDivision.getDivisionName()));

            divisionCombo.setItems(firstLevelDivisionAllNames);
            customerTableView.setItems(allCustomersList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}