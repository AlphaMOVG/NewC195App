package Controllers;

import DAO.AppointmentDBA;
import DAO.UserDBA;
import Models.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button clearBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField languageBox;
    @FXML
    private TextField passwordTxt;
    @FXML
    private TextField usernameTxt;


    @FXML
    void onActionClear(ActionEvent event) {
        languageBox.clear();
        passwordTxt.clear();
        usernameTxt.clear();

    }


    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    void onActionLogin(ActionEvent event)  {

        try {

            ObservableList<Appointments> getAllAppointments = AppointmentDBA.getAllAppointments();
            LocalDateTime currentTimeMinus15Min = LocalDateTime.now().minusMinutes(15);
            LocalDateTime currentTimePlus15Min = LocalDateTime.now().plusMinutes(15);
            LocalDateTime startTime;
            int getAppointmentID = 0;
            LocalDateTime displayTime = null;
            boolean appointmentWithin15Min = false;

            ResourceBundle rb = ResourceBundle.getBundle("LanguageRBs", Locale.getDefault());

            String username = usernameTxt.getText();
            String password = passwordTxt.getText();
            int userId = UserDBA.validateUser(username, password);

            FileWriter fileWriter = new FileWriter("file.Txt", true);
            PrintWriter outputFile = new PrintWriter(fileWriter);

            if (userId > 0) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/SelectScreen.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


                outputFile.print("User: " + username + " successfully logged in at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");


                for (Appointments appointment : getAllAppointments) {
                    startTime = appointment.getStart();
                    if ((startTime.isAfter(currentTimeMinus15Min) || startTime.isEqual(currentTimeMinus15Min)) && (startTime.isBefore(currentTimePlus15Min) || (startTime.isEqual(currentTimePlus15Min)))) {
                        getAppointmentID = appointment.getAppointmentID();
                        displayTime = startTime;
                        appointmentWithin15Min = true;
                    }
                }
                if (appointmentWithin15Min != false) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment within 15 minutes: " + getAppointmentID + " and appointment start time of: " + displayTime);
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    System.out.println(rb.getString("There is an appointment within 15 minutes"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No upcoming appointments.");
                    Optional<ButtonType> confirmation = alert.showAndWait();
                    System.out.println(rb.getString("no upcoming appointments"));
                }
            } else if (userId < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("Error"));
                alert.setContentText(rb.getString("Incorrect user ID"));
                alert.show();

                //log the failed login attempt
                outputFile.print("user: " + username + " failed login attempt at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");

            }
            outputFile.close();
        } catch (IOException | SQLException ioException) {
            ioException.printStackTrace();
        }

    }

// as questions about this

    public void initialize(URL url, ResourceBundle rb) {
        try {

            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);

            ZoneId zone = ZoneId.systemDefault();


            languageBox.setText(String.valueOf(zone));

            rb = ResourceBundle.getBundle("LanguageRBs", Locale.getDefault());

            clearBtn.setText(rb.getString("Clear"));
            usernameTxt.setText(rb.getString("Username"));
            passwordTxt.setText(rb.getString("Password"));
            loginBtn.setText(rb.getString("Login"));
            exitBtn.setText(rb.getString("Exit"));
            languageBox.setText(rb.getString("Language"));

        } catch (MissingResourceException e) {
            System.out.println("Resource file missing: " + e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
