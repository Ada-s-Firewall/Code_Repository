package Views;

/**
 * Purpose: This method launches the initial login page scene
 * Contributors: Eric Cortes
 * Last Updated: 02/24/2020
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPageLaunch extends Application {

    /**
     * This method loads the initial login page view and displays it
     */
    @Override
    public void start(Stage _stage) throws Exception {

        //Load and display login page scene
        Parent loginPageRoot = FXMLLoader.load(getClass().getResource("LoginPageUI.fxml"));
        Scene loginPageScene = new Scene(loginPageRoot);
        _stage.setScene(loginPageScene);
        _stage.show();
    }

    /**
     * This is the main method that launches the application
     */
    public static void main(String[] _args) {
        launch(_args);
    }

}
