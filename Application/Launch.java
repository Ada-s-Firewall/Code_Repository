package Application;

/**
 * Purpose: This method launches the initial login page scene
 * Contributors: Eric Cortes
 * Last Updated: 03/25/2020
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launch extends Application {

    //Variable containing the address of the fxml files
    private static final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private static final Boolean RESIZE = false;

    //Variale contaiing the name of the fxml file which is set to display first
    private static final String FIRSTVIEW = "LoginPage.fxml";

    /**
     * This method loads the initial login page view and displays it
     */
    @Override
    public void start(Stage _stage) throws Exception {

        //Load and display login page scene
        Parent loginPageRoot = FXMLLoader.load(getClass().getResource(ADDRESS + FIRSTVIEW));
        Scene loginPageScene = new Scene(loginPageRoot);
        _stage.setScene(loginPageScene);
        _stage.resizableProperty().setValue(RESIZE);
        _stage.show();
    }

    /**
     * This is the main method that launches the application
     */
    public static void main(String[] _args) {
        launch(_args);
    }

}
