package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          related business of the user information of the application.
 * Contributors: Eric Cortes
 * Last Updated: 04/22/2020
 */

import Objects.UserObject;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class ProfileController implements Initializable {

    //Variable to hold the table.
    @FXML
    private TableView<?> tableView;

    //Variable to hold the first column.
    @FXML
    private TableColumn<?, ?> listenedToColumn;

    //Variable to hold the second column.
    @FXML
    private TableColumn<?, ?> planToListenToColumn;

    //Varibale to hold the rating scores
    @FXML
    private ChoiceBox rateScore;

    //Variable to hold the current user that is logged in.
    private UserObject user;

    //Variable to hold the list of the table.
    private final ObservableList<?> tableList = FXCollections.observableArrayList();

    //Variable to hold the possible rating scores
    private final String[] ratingScores = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //Variable containing the address of the fxml files.
    private final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality.
    private final Boolean RESIZE = false;

    /**
     * This method handles the action for when the rate button is clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void rateItemButtonClicked(ActionEvent _event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(ADDRESS + "Rating.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            //ProfileController controller = loader.getController();
            //controller.initializeData(this.user);

            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(RESIZE);
            stage.show();
    }

    /**
     * This method handles the action for when the log out button is clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void logOutButtonClicked(ActionEvent _event) throws IOException {

        //Display log in page
        displayPage(_event, "LoginPage.fxml");
    }

    /**
     * This method handles the action for when the settings button is clicked.
     * @param _event
     */
    @FXML
    protected void settingsButtonClicked(ActionEvent _event) {
        //Method in progress
    }

    /**
     * This method handles the action for when the search button is clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void searchButtonClicked(ActionEvent _event) throws IOException {
        displayPage(_event, "Search.fxml");
    }

    /**
     * This method handles the action for when the search button is clicked.
     * @param event
     */
    @FXML
    void rateButtonClicked(ActionEvent event) {

        if(this.rateScore.getValue() != null){

            //Obtain the selected number
            String score = (String)this.rateScore.getValue();

            //Display Score
            System.out.println(score);

        }else{

            //Display error message.
            System.out.println("Did not select a score.");

        }
    }

    /**
     * This method displays the page that was passed in as a string
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    private void displayPage(ActionEvent _event, String _fxmlFile) throws IOException{

        //Load and display the login page
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
    public void initializeData(UserObject _user){

        //Initialize user to user passed in as parameter
        this.user = _user;
    }

    /**
     * Initializes the controller class.
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {

        Platform.runLater(() -> {
            rateScore.getItems().addAll(ratingScores);
        });
    }

}
