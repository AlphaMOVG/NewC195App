package controllers;


import helperClasses.CountryHelper;
import helperClasses.CustomerHelper;
import helperClasses.DivisionsHelper;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Country;
import models.Customers;
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
import javafx.stage.Stage;
import models.Divisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private Button clearBtn;
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
    private Button editBtn;


    @FXML
    private TextField customerIdTxt;
    @FXML
    private TextField customerNameTxt;
    @FXML
    private TextField phoneNumberTxt;
    @FXML
    private TextField postalCodeTxt;
    @FXML
    private ComboBox<Divisions> divisionCombo;
    @FXML
    private ComboBox<CountryHelper> countryCombo;
    @FXML
    private TextField addressTxt;


    @FXML
    private TableView<Customers> customerTableView;
    @FXML
    private TableColumn<CustomerHelper, Integer> customerIdCol;
    @FXML
    private TableColumn<CustomerHelper, String> customerNameCol;
    @FXML
    private TableColumn<CustomerHelper, String> phoneNumberCol;
    @FXML
    private TableColumn<CustomerHelper, String> postalCodeCol;
    @FXML
    private TableColumn<CustomerHelper, Integer> divisionCol;
    @FXML
    private TableColumn<CustomerHelper, Integer> countryCol;
    @FXML
    private TableColumn<CustomerHelper, String> addressCol;

    private Divisions divisions;
    private CountryHelper countryID;


    @FXML
    void onActionCountryCombo(ActionEvent event) throws SQLException {

        if(countryCombo.getValue() == null) {
            divisionCombo.setDisable(true);
        }

        else if(countryCombo.getValue() != null){
            divisionCombo.setDisable(false);
        }

       Country cs = countryCombo.getValue();
       if (cs != null){
       divisionCombo.setItems(DivisionsHelper.getAllFilteredDivisions(cs.getCountryID()));}

    }

    @FXML
    void onActionAdd(ActionEvent event) {
        try{
            if(customerNameTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please add a customer to the form");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String  addCustomerName = customerNameTxt.getText();

            if(phoneNumberTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please add a phone number");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String addPhoneNumber = phoneNumberTxt.getText();

            if(postalCodeTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please add a postal code");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String addPostalCode = postalCodeTxt.getText();

            if(divisionCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please Add a Country or Division");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            Integer addDivisionID = divisionCombo.getValue().getDivisionID();

            if(addressTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please add an address");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String addAddress = addressTxt.getText();

             CustomerHelper.createCustomer(addCustomerName,addAddress, addPostalCode, addPhoneNumber, addDivisionID);
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Customer added successfully");Optional<ButtonType> result = alert.showAndWait();
               ObservableList<Customers> allCustomers = null;
               try {
                   allCustomers = CustomerHelper.getAllCustomers();
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
               customerTableView.setItems(allCustomers);


        } catch (NumberFormatException | NullPointerException e ) {
            e.printStackTrace(); // alert here
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
        try {

            Customers deleteSelectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

                if (deleteSelectedCustomer == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Customer not selected");
                    alert.showAndWait();
                } else {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Alert");
                    alert.setContentText("Would you like to remove the selected customer?");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        CustomerHelper.deleteCustomer(deleteSelectedCustomer.getCustomerID());
                    }
                }
            ObservableList<Customers> allCustomers = null;
            try {
                allCustomers = CustomerHelper.getAllCustomers();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            customerTableView.setItems(allCustomers);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionUpdate(ActionEvent event) {
        try{


            String updateCustomerID = customerIdTxt.getText();

            if(customerNameTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please add a customer name");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String  updateCustomerName = customerNameTxt.getText();

            if(phoneNumberTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please add a phone number");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String updatePhoneNumber = phoneNumberTxt.getText();

            if(postalCodeTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please add a postal code");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String updatePostalCode = postalCodeTxt.getText();

            if(divisionCombo.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please Add a Country or Division");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            Integer updateDivisionID = divisionCombo.getValue().getDivisionID();

            if(addressTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Please add an address");Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            String updateAddress = addressTxt.getText();

            CustomerHelper.updateCustomer(updateCustomerID,updateCustomerName,updateAddress, updatePostalCode, updatePhoneNumber, updateDivisionID);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);alert.setTitle("Alert");alert.setContentText("Customer updated successfully!");Optional<ButtonType> result = alert.showAndWait();
            ObservableList<Customers> allCustomers = null;
            try {
                allCustomers = CustomerHelper.getAllCustomers();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            customerTableView.setItems(allCustomers);


        } catch (NumberFormatException | NullPointerException e ) {
            e.printStackTrace();
        }

    }

    @FXML
    void onActionEdit(ActionEvent event) throws SQLException {
        Customers selectCustomer = customerTableView.getSelectionModel().getSelectedItem();

        customerIdTxt.setText(String.valueOf(selectCustomer.getCustomerID()));
        customerNameTxt.setText(selectCustomer.getCustomerName());
        phoneNumberTxt.setText(selectCustomer.getCustomerPhoneNumber());
        postalCodeTxt.setText(selectCustomer.getCustomerPostalCode());
        addressTxt.setText(selectCustomer.getCustomerAddress());

        for(CountryHelper c : countryCombo.getItems()){
            if (selectCustomer.getCountryID() == c.getCountryID()){
                countryCombo.setValue(c);
                break;
            }
        }

        for(Divisions D : divisionCombo.getItems()){
            if(selectCustomer.getDivisionID() == D.getDivisionID()){
                divisionCombo.setValue(D);
                break;

            }
        }

    }

    @FXML
    void onActionClear(ActionEvent event) throws SQLException {
            customerIdTxt.clear();
            customerNameTxt.clear();
            phoneNumberTxt.clear();
            postalCodeTxt.clear();
            addressTxt.clear();
            countryCombo.setValue(null);
            divisionCombo.setValue(null);
    }

    @FXML
    void onActionExit(ActionEvent event) throws IOException {

        System.exit(0);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Customers> allCustomers = null;
        try {
            allCustomers = CustomerHelper.getAllCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        customerTableView.setItems(allCustomers);

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("countryID"));

        ObservableList<CountryHelper> countries = CountryHelper.getAllCountries();
        countryCombo.setItems(CountryHelper.getAllCountries());
        countryCombo.setVisibleRowCount(5);
        countryCombo.setPromptText("- Choose A Country -");

        try {
            ObservableList<Divisions> divisions = DivisionsHelper.getAllDivisions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            //ask why this isn't working
            if(countryCombo.getValue() == null) {
                divisionCombo.setDisable(true);
            }

            else if(countryCombo.getValue() != null){
                divisionCombo.setDisable(false);
            }



            divisionCombo.setItems(DivisionsHelper.getAllDivisions());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        divisionCombo.setVisibleRowCount(5);
        divisionCombo.setPromptText("- Choose A Division -");

    }
}