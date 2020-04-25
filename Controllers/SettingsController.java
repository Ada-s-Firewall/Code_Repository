package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          settings fxml file which is the code for the settings view.
 * Contributors: Eric Cortes Last Updated: 04/24/2020
 */

import Models.CheckUserInfo;
import Models.DBInfoRequest;
import Objects.UserObject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

    //Variable to hold the password
    @FXML
    private TextField password;

    //Variable to hold the confirm password
    @FXML
    private TextField confirmPassword;

    //Variable to hold the email
    @FXML
    private TextField email;

    //Variable to hold the confirm email
    @FXML
    private TextField confirmEmail;

    //Variable to hold the current user object
    private UserObject user;

    //Variable set to false which coresponds to the maximize functionality.
    private final Boolean RESIZE = false;

    //Variable containing the address of the fxml files.
    private final String ADDRESS = "/Views/";

    //Variable to hold the database adapter
    private DBInfoRequest dbAdapter;

    //Variable to hold the CheckUserInfo object
    private CheckUserInfo userInfoChecker;

    //Variables containing fxml files obtainable from this fxml view
    private final String LOGIN = ADDRESS + "LoginPage.fxml";
    private final String PROFILE = ADDRESS + "Profile.fxml";
    private final String PASSWORDCHANGED = ADDRESS + "PasswordChanged.fxml";
    private final String EMAILCHANGED = ADDRESS + "EmailChanged.fxml";
    private final String SETTINGSPASSWORDSDONTMATCH = ADDRESS + "SettingsPasswordsDontMatch.fxml";
    private final String SETTINGSEMAILSDONTMATCH = ADDRESS + "SettingsEmailsDontMatch.fxml";
    private final String DELETEACCOUNTCONFIRMATION = ADDRESS + "DeleteAccountConfirmation.fxml";
    private final String SETTINGSIMPROPEREMAIL = ADDRESS + "SettingsImproperEmail.fxml";
    private final String SETTINGS = ADDRESS + "Settings.fxml";

    /**
     * This method handles the action for when the change email button is clicked
     * @param _event
     */
    @FXML
    protected void changeEmailButtonClicked(ActionEvent _event) throws IOException {

        //Get the email and confirm email
        String theEmail = this.email.getText();
        String theConfirmEmail = this.confirmEmail.getText();

        if(theEmail.isEmpty()){
            displayPage(_event, this.SETTINGS);
        }else if(theEmail.equals(theConfirmEmail)){

            if(this.userInfoChecker.isEmailValid(theEmail)){

                //Change password
                this.dbAdapter.updateUserEmail(user, theEmail);

                //Load and display the sucessfull password change view
                displayPage(_event, this.EMAILCHANGED);
            }else{
                //Load and display the inproper email view
                displayPage(_event, this.SETTINGSIMPROPEREMAIL);
            }
        }else{

            //Load and display the passwords do not match view
            displayPage(_event, this.SETTINGSEMAILSDONTMATCH);
        }
    }

    /**
     * This method handles the action for when the change password button is clicked
     * @param _event
     */
    @FXML
    void changePasswordButtonClicked(ActionEvent _event) throws IOException {

        //Get the password and confirm password
        String thePassword = this.password.getText();
        String theConfirmPassword = this.confirmPassword.getText();

        if(thePassword.isEmpty()){
            displayPage(_event, this.SETTINGS);
        }else if(thePassword.equals(theConfirmPassword)){

            //Change password
            dbAdapter.updateUserPassword(user, thePassword);

            //Load and display the sucessfull password change view
            displayPage(_event, this.PASSWORDCHANGED);
        }else{

            //Load and display the passwords do not match view
            displayPage(_event, this.SETTINGSPASSWORDSDONTMATCH);
        }

    }

    /**
     * This method handles the action for when the delete account button is clicked
     * @param _event
     */
    @FXML
    void deleteAccountButtonClicked(ActionEvent _event) throws IOException {

        //Display the confirmation box
        displayPage(_event, this.DELETEACCOUNTCONFIRMATION);
    }

    /**
     * This method handles the action for when the back button is clicked
     * @param _event
     */
    @FXML
    void backButtonClicked(ActionEvent _event) throws IOException {

        //Display profile page
        displayPage(_event, this.PROFILE);
    }

    /**
     * This method handles the action for when the yes button is clicked
     * @param _event
     */
    @FXML
    void yesButtonClicked(ActionEvent _event) throws IOException {

        //Delete
        this.dbAdapter.deleteUserRecord(this.user);

        //Display login view
        displayPage(_event, this.LOGIN);

        //Close the window
        Stage stage = (Stage)((Node) _event.getSource()).getScene().getWindow();
        stage.close();

    }

    /**
     * This method handles the action for when the no button is clicked
     * @param _event
     */
    @FXML
    void noButtonClicked(ActionEvent _event) {

        //Close the window
        Stage stage = (Stage)((Node) _event.getSource()).getScene().getWindow();
        stage.close();

    }

    /**
     * This method displays the page that was passed in as a string
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    private void displayPage(ActionEvent _event, String _fxmlFile) throws IOException{

        if(_fxmlFile == this.LOGIN){

            //Load and display the login page
            Parent root = FXMLLoader.load(getClass().getResource(_fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

        }else if(_fxmlFile == this.PROFILE){

            //Obtain location of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Pass variables to controller
            ProfileController controller = loader.getController();
            controller.initializeUser(this.user);

            //Display stage
            Stage stage = (Stage)((Node)_event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

        }else if(_fxmlFile == this.DELETEACCOUNTCONFIRMATION){

            //Obtain location of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();

            //Pass variables to controller
            SettingsController controller = loader.getController();
            controller.initializeUser(this.user);

            //Display stage
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(RESIZE);
            stage.show();

        }else{

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
        });
    }

}
