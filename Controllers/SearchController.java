package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          search fxml file which is the code for the search view.
 * Contributors: Eric Cortes
 * Last Updated: 04/28/2020
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import Objects.UserObject;

public class SearchController implements Initializable {

    //Variable to hold search selection
    @FXML
    private TextField searchContent;

    //Variable to hold the choice box selection
    @FXML
    private ChoiceBox searchType;

    //Variable to hold the scores being displayed in the choicebox
    private final ObservableList<String> typeOptions = FXCollections.observableArrayList("artists", "albums", "tracks");

    //Variable to hold user
    private UserObject user;

    //Variable containing the address of the fxml files
    private final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private final Boolean RESIZE = false;

    //Varaibles to hold the fxml file names
    private final String NOCONTENTSELECTED = this.ADDRESS + "SearchNoContentSelected.fxml";
    private final String NOTYPESELECTED = this.ADDRESS + "SearchNoTypeSelected.fxml";
    private final String ALBUMRESULT = this.ADDRESS + "Result(Album).fxml";
    private final String ARTISTRESULT = this.ADDRESS + "Result(Artist).fxml";
    private final String TRACKRESULT = this.ADDRESS + "Result(Track).fxml";
    private final String PROFILE = this.ADDRESS + "Profile.fxml";

    /**
     * This method handles the action of when the search button is clicked
     *
     * @param _event
     * @throws IOException
     */
    @FXML
    protected void searchButtonClicked(ActionEvent _event) throws IOException {

        //If else statement to make sure what is being searched is correct
        if (this.searchContent.getText().isEmpty()) {

            //Load and display no contect  selected view
            displayPage(_event, this.NOCONTENTSELECTED);

        } else if (this.searchType.getValue() == null){

            //Load and display no type selected view
            displayPage(_event, this.NOTYPESELECTED);

        }else if ("albums".equals((String)this.searchType.getValue())){

            //Load and display album result view
            displayPage(_event, this.ALBUMRESULT);

        } else if ("artists".equals((String)this.searchType.getValue())) {

            //Load and display artist result view
            displayPage(_event, this.ARTISTRESULT);

        }else {

            //Load and display track result view
            displayPage(_event, this.TRACKRESULT);

        }
    }

    /**
     * This method handles the action of when the return to profile button is
     * clicked
     *
     * @param _event
     * @throws IOException
     */
    @FXML
    protected void returnToProfileButtonClicked(ActionEvent _event) throws IOException {

        //Load and display profile view
        displayPage(_event, this.PROFILE);

    }

    /**
     * This method displays the page that was passed in as a string
     *
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    private void displayPage(ActionEvent _event, String _fxmlFile) throws IOException {

        if(_fxmlFile.equals(this.PROFILE)){

            //Obtain location of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Parent artistResultParent = loader.load();
            Scene artistResultScene = new Scene(artistResultParent);

            //Pass variables to controller
            ProfileController controller = loader.getController();
            controller.initializeUser(this.user);

            //Display stage
            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(artistResultScene);
            stage.resizableProperty().setValue(this.RESIZE);
            stage.show();

        } else if(_fxmlFile.equals(this.NOCONTENTSELECTED) || _fxmlFile.equals(this.NOTYPESELECTED)){

            //Obtain location of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Pass variables to controller
            SearchController controller = loader.getController();
            controller.initializeUser(this.user);

            //Display stage
            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.resizableProperty().setValue(this.RESIZE);
            stage.show();

        } else{

            //Obtain location of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Parent artistResultParent = loader.load();
            Scene artistResultScene = new Scene(artistResultParent);

            //Pass variables to controller
            ResultController controller = loader.getController();
            controller.initializeData(this.searchContent.getText(), (String)this.searchType.getValue(), this.user);

            //Display stage
            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(artistResultScene);
            stage.resizableProperty().setValue(this.RESIZE);
            stage.show();

        }
    }


    /**
     * This method initializes the user variable with the passed in user
     * @param _user
     */
    public void initializeUser(UserObject _user){

        //Initialize user to user passed in as parameter
        this.user = _user;

    }

    /**
     * Initializes the controller class.
     *
     * @param _url
     * @param _rb
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {

        Platform.runLater(() -> {

            //Display the search types
            this.searchType.getItems().addAll(typeOptions);

        });

    }

}
