package Controllers;

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

public class SelectScreenController implements Initializable {

    @FXML
    private Button appointmentBtn;
    @FXML
    private Button customerBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button reportBtn;



    @FXML
    void onActionAppointmentScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/Apps.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onActionCustomerScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void onActionReportsScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/Reports.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);


    }



    @Override
    public void initialize(URL location, ResourceBundle resources){}

}