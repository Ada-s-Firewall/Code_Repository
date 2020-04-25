package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 * search fxml file which is the code for the search view. Contributors: Eric
 * Cortes Last Updated: 03/25/2020
 */
import Objects.UserObject;
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
    private final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private final Boolean RESIZE = false;

    //Variable to hold the choice box selection
    @FXML
    private ChoiceBox searchType;

    //Variable to hold search selection
    @FXML
    private TextField searchContent;

    //Variable to hold user
    private UserObject user;

    /**
     * This method handles the action of when the search button is clicked
     *
     * @param _event
     * @throws IOException
     */
    @FXML
    void searchButtonClicked(ActionEvent _event) throws IOException {

        //If else statement to make sure what is being searched is correct
        if (this.searchContent.getText().isEmpty()) {

            //If the type of seach isn't selected then a message is shown
            displayPage(_event, "NoContentSelected.fxml");

        } else if ((String)this.searchType.getValue() == "albums"){

            String theSearchType = (String)this.searchType.getValue();
            String theSearchContent = this.searchContent.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(ADDRESS + "AlbumResult.fxml"));
            Parent artistResultParent = loader.load();
            Scene artistResultScene = new Scene(artistResultParent);

            ResultController controller = loader.getController();
            controller.initializeData(theSearchContent, theSearchType, this.user);

            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(artistResultScene);
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

        } else if ((String)this.searchType.getValue() == "artists") {

            String theSearchType = (String)this.searchType.getValue();
            String theSearchContent = this.searchContent.getText();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ADDRESS + "ArtistResult.fxml"));
            Parent artistResultParent = loader.load();
            Scene artistResultScene = new Scene(artistResultParent);

            ResultController controller = loader.getController();
            controller.initializeData(theSearchContent, theSearchType, this.user);

            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(artistResultScene);
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

        }else {

            String theSearchType = (String)this.searchType.getValue();
            String theSearchContent = this.searchContent.getText();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ADDRESS + "TrackResult.fxml"));
            Parent artistResultParent = loader.load();
            Scene artistResultScene = new Scene(artistResultParent);

            ResultController controller = loader.getController();
            controller.initializeData(theSearchContent, theSearchType, this.user);

            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(artistResultScene);
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

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
    void returnToProfileButtonClicked(ActionEvent _event) throws IOException {

        //Display the login page
        displayPage(_event, "Profile.fxml");
    }

    /**
     * This method displays the page that was passed in as a string
     *
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    protected void displayPage(ActionEvent _event, String _fxmlFile) throws IOException {

        //Load and display a view page
        Parent root = FXMLLoader.load(getClass().getResource(ADDRESS + _fxmlFile));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.resizableProperty().setValue(RESIZE);
        stage.show();

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
        searchType.getItems().add("artists");
        searchType.getItems().add("albums");
        searchType.getItems().add("tracks");

    }

}
