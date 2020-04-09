package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 * search results fxml file which is the code for the search results view.
 * Contributors: Eric Cortes Last Updated: 03/30/2020
 */
import Models.MusicRequest;
import Objects.MusicObject;
import Objects.MusicObjectList;
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

public class ResultController implements Initializable {

    //Varible contining the search content that was passed in by the search page
    private  String searchContent;

    //Varible containin the search type that was passed in by the search page
    private String searchType;

    //Varialbe to hold the list of the table
    ObservableList<MusicObject> tableList = FXCollections.observableArrayList();

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

    //Variable containing the number of searches that whill be displayed
    private static final int SEARCHNUMBER = 10;

    //Variable containing the address of the fxml files
    private static final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private static final Boolean RESIZE = false;

    /**
     * This method accepts to strings to initialize the view variables
     * @param search
     * @param _searchContent
     * @param _searchType
     */
    public void initData(String _searchContent, String _searchType){

        //Initialize variables with pssed in data as parameters
        searchContent = _searchContent;
        searchType = _searchType;

    }

    /**
     * This method handles the action for when the add to listened to button is
     * clicked
     *
     * @param event
     */
    @FXML
    void listenToButonClicked(ActionEvent _event) throws IOException {

        //Make an observable list of the MusicObjects the user selected
        ObservableList<MusicObject> selection = tableView.getSelectionModel().getSelectedItems();

        if(selection.isEmpty()){

            System.out.println("Nothing was selected");
            //Display no items were added message
            //displayPage(_event, "ArtistResultError.fxml");

        }else{

            //Print api id's of the songs selected
            for(MusicObject theSelection: selection){
                System.out.println("The ID: " + theSelection.getId() + ", was added to the listened to playlist");
            }
        }
    }

    /**
     * Method that handles the action for when the add to plan to listen to
     * button is clicked
     *
     * @param event
     */
    @FXML
    void planToListenButtonClicked(ActionEvent _event) throws IOException {

        //Make an observable list of the MusicObjects the user selected
        ObservableList<MusicObject> selection = tableView.getSelectionModel().getSelectedItems();

        if(selection.isEmpty()){

            //Display no items were added message
            displayPage(_event, "ArtistResultError.fxml");

        }else{

            //Print api id's of the songs selected
            for(MusicObject theSelection: selection){
                System.out.println("The ID: " + theSelection.getId() + ", was added to the plan to listen to playlist");
            }
        }

    }

    /**
     * This method handles the action for when the return to search button is
     * clicked
     *
     * @param _event
     */
    @FXML
    void returnToSearchButtonClicked(ActionEvent _event) throws IOException {

        //Display the search view page
        displayPage(_event, "Search.fxml");
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
     * Initializes the controller class.
     *
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {

        Platform.runLater(() -> {

            if(searchType == "tracks"){

                //Initialize each column to look for key word content in object
                trackColumn.setCellValueFactory(new PropertyValueFactory <> ("name"));
                artistColumn.setCellValueFactory(new PropertyValueFactory <> ("artist"));
                albumColumn.setCellValueFactory(new PropertyValueFactory <> ("album"));
                releasedColumn.setCellValueFactory(new PropertyValueFactory <> ("year"));


                //Make music request with infomation passed in
                MusicRequest request = new MusicRequest();
                MusicObjectList searchResults = request.search(searchContent, searchType, SEARCHNUMBER);

                //Add results to the table list to display
                for(int i = 1; i <= searchResults.getLength(); i++){
                    tableList.add(searchResults.get(i));
                }

                //Set the table with the items in the table list
                tableView.setItems(tableList);

            } else if (searchType == "albums"){

                //Initialize each column to look for key word content in object
                albumColumn.setCellValueFactory(new PropertyValueFactory <> ("name"));
                artistColumn.setCellValueFactory(new PropertyValueFactory <> ("artist"));
                releasedColumn.setCellValueFactory(new PropertyValueFactory <> ("year"));


                //Make music request with infomation passed in
                MusicRequest request = new MusicRequest();
                MusicObjectList searchResults = request.search(searchContent, searchType, SEARCHNUMBER);

                //Add results to the table list to display
                for(int i = 1; i <= searchResults.getLength(); i++){
                    tableList.add(searchResults.get(i));
                }

                //Set the table with the items in the table list
                tableView.setItems(tableList);

            } else{

                //Initialize each column to look for key word content in object
                artistColumn.setCellValueFactory(new PropertyValueFactory <> ("name"));

                //Make music request with infomation passed in
                MusicRequest request = new MusicRequest();
                MusicObjectList searchResults = request.search(searchContent, searchType, SEARCHNUMBER);

                //Add results to the table list to display
                for(int i = 1; i <= searchResults.getLength(); i++){
                    tableList.add(searchResults.get(i));
                }

                //Set the table with the items in the table list
                tableView.setItems(tableList);
            }
        });
    }

}