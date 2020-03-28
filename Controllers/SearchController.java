
package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          search fxml file which is the code for the search view.
 * Contributors: Eric Cortes
 * Last Updated: 03/25/2020
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SearchController implements Initializable {

    //Variable containing the address of the fxml files
    private static final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private static final Boolean RESIZE = false;

    @FXML
    private ChoiceBox searchType;

    @FXML
    private TextField searchContent;

    @FXML
    void searchButtonClicked(ActionEvent _event) throws IOException {

        if(this.searchContent.getText() == " "){
            displayPage(_event, "NoContentSelected.fxml");
        }
        else if (this.searchType.getValue() == null){
            displayPage(_event, "NoTypeSelected.fxml");
        }
        else{
            System.out.println("Search bar text: " + this.searchContent.getText());
            System.out.println("Type: " + this.searchType.getValue());
        }
    }

    @FXML
    void returnToProfileButtonClicked(ActionEvent _event) throws IOException {
        displayPage(_event, "LoginPage.fxml");
    }

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
    public void initialize(URL _url, ResourceBundle _rb) {
        searchType.getItems().add("Artist");
        searchType.getItems().add("Album");
        searchType.getItems().add("Track");


    }

}
