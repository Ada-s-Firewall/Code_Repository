package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          search results fxml file which is the code for the search results view.
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Models.DBInfoRequest;
import Models.MusicRequest;
import Objects.MusicObject;
import Objects.MusicObjectList;
import Objects.RatingObject;
import Objects.UserObject;

public class ResultController implements Initializable {

    //Variable to hold the table
    @FXML
    private TableView<MusicObject> tableView;

    //Variable for the first column
    @FXML
    private TableColumn<MusicObject, String> trackColumn;

    //Variable to hold the second column
    @FXML
    private TableColumn<MusicObject, String> artistColumn;

    //Variable to hold the third column
    @FXML
    private TableColumn<MusicObject, String> albumColumn;

    //Vairalbe to hold the fourth column
    @FXML
    private TableColumn<MusicObject, String> releasedColumn;

    //Variable to hold the choice box options
    @FXML
    private ChoiceBox rateScore;

    //Variable to hold the scores being displayed in the choicebox
    private final ObservableList<String> SCORES = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    //Varible contining the search content that was passed in by the search page
    private String searchContent;

    //Varible containin the search type that was passed in by the search page
    private String searchType;

    //Variabee to hold the list of the table
    private final ObservableList<MusicObject> TABLELIST = FXCollections.observableArrayList();

    //Variable to hold the current user
    private UserObject user;

    //Variable to hold the database adapter object
    private final DBInfoRequest DBADAPTER = new DBInfoRequest();

    //Variable tohold the api model
    private final MusicRequest MUSICREQUEST = new MusicRequest();

    //Variable containing the address of the fxml files
    private final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private final Boolean RESIZE = false;

    //Variables to hold the address of the fxml files
    private final String ALBUMRESULT = this.ADDRESS + "Result(Album).fxml";
    private final String ARTISTRESULT = this.ADDRESS + "Result(Artist).fxml";
    private final String TRACKRESULT = this.ADDRESS + "Result(Track).fxml";
    private final String ALBUMCONFIRMATON = this.ADDRESS + "ResultConfirmation(Album).fxml";
    private final String ARTISTCONFIRMATION = this.ADDRESS + "ResultConfirmation(Artist).fxml";
    private final String TRACKCONFIRMATION = this.ADDRESS + "ResultConfirmation(Track).fxml";
    private final String SEARCH = this.ADDRESS + "Search.fxml";

    /**
     * Method that handles the action for when the add to plan to listen to
     * button is clicked
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void addToMusicListButtonClicked(ActionEvent _event) throws IOException {

        //Obtain the item as a Music Object
        MusicObject selection = tableView.getSelectionModel().getSelectedItem();

        //Make sure selection or rate score are not empty
        if(selection == null || this.rateScore.getValue() == null){

            //Load and display the appropriate page
            if(this.searchType == "artists"){

                displayPage(_event, this.ARTISTRESULT);

            }else if (this.searchType == "albums"){

                displayPage(_event, this.ALBUMRESULT);

            }else{

                displayPage(_event, this.TRACKRESULT);

            }
        } else{

            //Obtain information to make database adapter request
            String username = this.user.getUserName();
            String spotifyID = selection.getId();
            String itemType = selection.getType();
            String userRating = (String)this.rateScore.getValue();

            //Make RatingObject with information obtained above
            RatingObject itemToSave = new RatingObject(username, userRating, spotifyID, itemType);

            //Make database request
            this.DBADAPTER.createUserRating(itemToSave);

            //Load and display the appropriate confirmation page
            if(this.searchType == "artists"){

                displayPage(_event, this.ARTISTCONFIRMATION);

            }else if(this.searchType == "albums"){

                displayPage(_event, this.ALBUMCONFIRMATON);

            }else{

                displayPage(_event, this.TRACKCONFIRMATION);

            }
        }
    }

    /**
     * This method handles the action for when the return to search button is
     * clicked
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void returnToSearchButtonClicked(ActionEvent _event) throws IOException {

        //Display the search view page
        displayPage(_event, this.SEARCH);
    }

    /**
     * This method displays the page that was passed in as a string
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    private void displayPage(ActionEvent _event, String _fxmlFile) throws IOException {

        if(_fxmlFile.equals(this.SEARCH)){

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

        } else{

            //Obtain location of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Pass variables to controller
            ResultController controller = loader.getController();
            controller.initializeData(this.searchContent, this.searchType, this.user);

            //Display stage
            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.resizableProperty().setValue(this.RESIZE);
            stage.show();
        }

    }

    /**
     * This method accepts two strings to initialize the view variables
     * @param _searchContent
     * @param _searchType
     * @param _user
     */
    public void initializeData(String _searchContent, String _searchType, UserObject _user){

        //Initialize variables with pssed in data as parameters
        this.searchContent = _searchContent;
        this.searchType = _searchType;
        this.user = _user;
    }

    /**
     * Initializes the controller class.
     *
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {

        Platform.runLater(() -> {

            if("tracks".equals(this.searchType)){

                //Initialize each column to look for key word content in object
                this.trackColumn.setCellValueFactory(new PropertyValueFactory <> ("name"));
                this.artistColumn.setCellValueFactory(new PropertyValueFactory <> ("artist"));
                this.albumColumn.setCellValueFactory(new PropertyValueFactory <> ("album"));
                this.releasedColumn.setCellValueFactory(new PropertyValueFactory <> ("year"));


                //Make music request with infomation passed in
                MusicObjectList searchResults = MUSICREQUEST.search(this.searchContent, this.searchType);

                //Add results to the table list to display
                for(int i = 1; i <= searchResults.getLength(); i++){
                    this.TABLELIST.add(searchResults.get(i));
                }

                //Set the table with the items in the table list
                this.tableView.setItems(this.TABLELIST);

            } else if ("albums".equals(this.searchType)){

                //Initialize each column to look for key word content in object
                this.albumColumn.setCellValueFactory(new PropertyValueFactory <> ("name"));
                this.artistColumn.setCellValueFactory(new PropertyValueFactory <> ("artist"));
                this.releasedColumn.setCellValueFactory(new PropertyValueFactory <> ("year"));


                //Make music request with infomation passed in
                MusicObjectList searchResults = MUSICREQUEST.search(this.searchContent, this.searchType);

                //Add results to the table list to display
                for(int i = 1; i <= searchResults.getLength(); i++){
                    this.TABLELIST.add(searchResults.get(i));
                }

                //Set the table with the items in the table list
                this.tableView.setItems(this.TABLELIST);

            } else{

                //Initialize each column to look for key word content in object
                this.artistColumn.setCellValueFactory(new PropertyValueFactory <> ("name"));

                //Make music request with infomation passed in
                MusicObjectList searchResults = MUSICREQUEST.search(this.searchContent, this.searchType);

                //Add results to the table list to display
                for(int i = 1; i <= searchResults.getLength(); i++){
                    this.TABLELIST.add(searchResults.get(i));
                }

                //Set the table with the items in the table list
                this.tableView.setItems(this.TABLELIST);
            }

            //Set the rate choice box scores
            this.rateScore.getItems().addAll(SCORES);

        });
    }

}
