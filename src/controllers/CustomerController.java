package controllers;

import models.Customers;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    void onActionCountryCombo(ActionEvent event) {

    }


    @FXML
    void onActionDivisionCombo(ActionEvent event) {

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
        ObservableList<Customers> tableCustomerFillerList = FXCollections.observableArrayList();
        customerTableView.setItems(tableCustomerFillerList);

    }



}