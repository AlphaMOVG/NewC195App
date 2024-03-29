package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Select screen controller class that implements Initializable
 */
public class SelectScreenController implements Initializable {

    /**
     * Appointment Button
     */
    @FXML
    private Button appointmentBtn;
    /**
     * Customer Button
     */
    @FXML
    private Button customerBtn;
    /**
     * Exit Button
     */
    @FXML
    private Button exitBtn;
    /**
     * Reports Button
     */
    @FXML
    private Button reportBtn;


    /**
     * On action appointments screen event
     */
    @FXML
    void onActionAppointmentScreen(ActionEvent event) throws IOException {

        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("../view/Apps.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * On action customer screen event
     */
    @FXML
    void onActionCustomerScreen(ActionEvent event) throws IOException {

        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("../view/Customer.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * On action report screen event
     */
    @FXML
    void onActionReportsScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/Reports.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * On action Exit event
     */
    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);


    }



    @Override
    public void initialize(URL location, ResourceBundle resources){}

}