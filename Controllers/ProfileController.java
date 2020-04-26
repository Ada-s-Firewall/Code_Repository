package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          related business of the user information of the application.
 * Contributors: Eric Cortes
 * Last Updated: 04/22/2020
 */

import Objects.PlanToListenObject;
import Models.DBInfoRequest;
import Objects.MusicObject;
import Objects.RatingObject;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ProfileController implements Initializable {


//======================= Profile View Variables ===============================


    //Variable to hold the table.
    @FXML
    private TableView<RatingObject> tableView;

    //Variable to hold the first column.
    @FXML
    private TableColumn<RatingObject, String> nameColumn;

    //Variable to hold the second column.
    @FXML
    private TableColumn<RatingObject, String> typeColumn;

    //Variable to hold the second column.
    @FXML
    private TableColumn<RatingObject, String> ratingColumn;

    //Variabee to hold the list of the table
    private final ObservableList<RatingObject> tableList = FXCollections.observableArrayList();


//======================= Rating View Varaiables ===============================


    //Variable to hold the choice box options
    @FXML
    private ChoiceBox<String> rateScore;

    //Variable to hold the scores being displayed in the choicebox
    private final ObservableList<String> SCORES = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    //Variable to hold the item that is being rated
    private MusicObject item;

    //Variable to hold the database adapter
    private DBInfoRequest dbAdapter;


//========================== General Variables =================================


    //Variable to hold the current user that is logged in.
    private UserObject user;

    //Variable set to false which coresponds to the maximize functionality.
    private final Boolean RESIZE = false;

    //Variable containing the address of the fxml files.
    private final String ADDRESS = "/Views/";

    //Variables containing fxml files obtainable from this fxml view
    private final String LOGINFXML = ADDRESS + "LoginPage.fxml";
    private final String RATINGFXML = ADDRESS + "Rating.fxml";
    private final String SETTINGSFXML = ADDRESS + "Settings.fxml";
    private final String SEARCHFXML = ADDRESS + "Search.fxml";



//======================== Profile  View Methods ===============================


    /**
     * This method handles the action for when the rate button is clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void rateItemButtonClicked(ActionEvent _event) throws IOException {

        //Display rating window
        displayPage(_event, this.RATINGFXML);

    }

    /**
     * This method handles the action for when the log out button is clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void logOutButtonClicked(ActionEvent _event) throws IOException {

        //Display log in page
        displayPage(_event, this.LOGINFXML);

    }

    /**
     * This method handles the action for when the settings button is clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void settingsButtonClicked(ActionEvent _event) throws IOException {

        //Display settings page
        displayPage(_event, this.SETTINGSFXML);
    }

    /**
     * This method handles the action for when the search button is clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void searchButtonClicked(ActionEvent _event) throws IOException {

        //Display search page
        displayPage(_event, this.SEARCHFXML);
    }


//======================== Rating View Methods =================================


    /**
     * This method handles the action for when the search button is clicked.
     * @param event
     */
    @FXML
    void rateButtonClicked(ActionEvent _event) {

        if(this.rateScore.getValue() != null){

            //Obtain the selected number
            String score = (String)this.rateScore.getValue();

            //Obtain required information to make rating object
            String username = this.user.getUserName();
            double numberScore = (double)Integer.parseInt(score);
            String itemID = this.item.getId();
            String itemType = this.item.getType();

            //Update Score
            RatingObject rate = new RatingObject(username, numberScore, itemID, itemType);
            this.dbAdapter.createUserRating(rate);

            //Close the window
            Stage stage = (Stage)((Node) _event.getSource()).getScene().getWindow();
            stage.close();

        }else{

            //Display error message.
            System.out.println("Did not select a score.");

        }
    }

    /**
     * This method initializes the itemID corresponding to the item being rated
     * @param _itemID
     */
    public void initializeItem (MusicObject _itemID){

        //Initialize itemID to the passed in parameter
        this.item = _itemID;

    }


//======================== General Methods =====================================


    /**
     * This method displays the page that was passed in as a string
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    private void displayPage(ActionEvent _event, String _fxmlFile) throws IOException{

        if(_fxmlFile == this.LOGINFXML){

            //Load and display the login page
            Parent root = FXMLLoader.load(getClass().getResource(_fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

        }else if(_fxmlFile == this.RATINGFXML){

            //Obtain location of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();

            //Pass variables to controller
            ProfileController controller = loader.getController();
            controller.initializeUser(this.user);

            //controller.initializeItem(item);

            //Display stage
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

        }else if(_fxmlFile == this.SEARCHFXML){

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
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

        }else if(_fxmlFile == this.SETTINGSFXML){

            //Obtain location of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Pass variables to controller
            SettingsController controller = loader.getController();
            controller.initializeUser(this.user);

            //Display stage
            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.resizableProperty().setValue(RESIZE);
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
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {

        Platform.runLater(() -> {

            

            rateScore.getItems().addAll(SCORES);
        });
    }

}
