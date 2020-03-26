package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          related business of the user information of the application.
 * Contributors: Eric Cortes
 * Last Updated: 03/25/2020
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class UserInfoController implements Initializable {

    //Variable containing the address of the fxml files
    private static final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private static final Boolean RESIZE = false;

    /**
     * This method displays the page that was passed in as a string
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    protected void displayPage(ActionEvent _event, String _fxmlFile) throws IOException{

        //Load and display the login page
        Parent root = FXMLLoader.load(getClass().getResource(ADDRESS + _fxmlFile));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.resizableProperty().setValue(RESIZE);
        stage.show();

    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
