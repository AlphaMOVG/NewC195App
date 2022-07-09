package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class
 * @author Juan Falcon
 */

public class Main extends Application {


    /**
     * starts the first scene
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        stage.setTitle("Main Form");
        stage.setScene(new Scene(root, 300, 400));
        stage.show();

    }

    /**
     * ensures that int are not repeatedly added
     */
    private static boolean firstTime = true;



    /**
     * Java program entry point
     */
    public static void main(String[] args) {

        //  Locale.setDefault(newLocale("fr"));



        JDBC.startConnection();
        launch(args);
        JDBC.closeConnection();


    }

}

