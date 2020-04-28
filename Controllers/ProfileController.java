package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          related business of the user information of the application.
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Models.DBInfoRequest;
import Objects.RecordObject;
import Objects.RecordObjectList;
import Objects.UserObject;


public class ProfileController implements Initializable {

    //Variable to hold the table.
    @FXML
    private TableView<RecordObject> tableView;

    //Variable to hold the first column.
    @FXML
    private TableColumn<RecordObject, String> nameColumn;

    //Variable to hold the second column.
    @FXML
    private TableColumn<RecordObject, String> typeColumn;

    //Variable to hold the second column.
    @FXML
    private TableColumn<RecordObject, String> ratingColumn;

    //Variable to hold the list of the table
    private final ObservableList<RecordObject> TABLELIST = FXCollections.observableArrayList();

    //Variable to hold the current user that is logged in.
    private UserObject user;

    //Variable to hold the instance of the database model
    private final DBInfoRequest DBADAPTER = new DBInfoRequest();

    //Variable set to false which coresponds to the maximize functionality.
    private final Boolean RESIZE = false;

    //Variable containing the address of the fxml files.
    private final String ADDRESS = "/Views/";

    //Variables containing fxml files obtainable from this fxml view
    private final String LOGINFXML = this.ADDRESS + "LoginPage.fxml";
    private final String RATINGFXML = this.ADDRESS + "Rating.fxml";
    private final String SETTINGSFXML = this.ADDRESS + "Settings.fxml";
    private final String SEARCHFXML = this.ADDRESS + "Search.fxml";

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

    /**
     * This method displays the page that was passed in as a string
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    private void displayPage(ActionEvent _event, String _fxmlFile) throws IOException{

        if(_fxmlFile.equals(this.LOGINFXML)){

            //Load and display the login page
            Parent root = FXMLLoader.load(getClass().getResource(_fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.resizableProperty().setValue(this.RESIZE);
            stage.show();

        }else if(_fxmlFile.equals(this.RATINGFXML)){

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
            stage.resizableProperty().setValue(this.RESIZE);
            stage.show();

        }else if(_fxmlFile.equals(this.SEARCHFXML)){

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

        }else if(_fxmlFile.equals(this.SETTINGSFXML)){

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
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {

        Platform.runLater(() -> {

            //Initialize each column to look for key word content in object
            this.nameColumn.setCellValueFactory(new PropertyValueFactory <> ("name"));
            this.typeColumn.setCellValueFactory(new PropertyValueFactory <> ("musicObjectType"));
            this.ratingColumn.setCellValueFactory(new PropertyValueFactory <> ("usersRating"));

            //Obtain user's username
            String username = this.user.getUserName();

            //Call database model to obtain playlist items
            RecordObjectList results = this.DBADAPTER.readUsersRatings(username);

            //Add results to the table list to display
            for(int i = 1; i <= results.getLength(); i++){
                this.TABLELIST.add(results.get(i));
            }

            //Set the table with the items in the table list
            this.tableView.setItems(this.TABLELIST);
        });
    }

}
