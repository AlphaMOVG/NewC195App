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



    @FXML
    void onActionCountryCombo(ActionEvent event) throws SQLException {

       Country c = countryCombo.getValue();
       divisionCombo.setItems(DivisionsHelper.getAllFilteredDivisions(c.getCountryID()));

    }

    @FXML
    void onActionAdd(ActionEvent event) {
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
    void onActionDelete(ActionEvent event) throws SQLException {
        try{


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @FXML
    void onActionUpdate(ActionEvent event) {
        try{

        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    @FXML
    void onActionSave(ActionEvent event) {
        try{

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    void onActionClear(ActionEvent event) {


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
            divisionCombo.setItems(DivisionsHelper.getAllDivisions());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        divisionCombo.setVisibleRowCount(5);
        divisionCombo.setPromptText("- Choose A Division -");



    }





}