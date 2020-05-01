package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          login process of the application fxml files which holds the code
 *          for the views of the login process of the application.
 * Contributors: Eric Cortes | Modified: Fernando Villarreal
 * Last Updated: 04/28/2020
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Models.DBInfoRequest;
import Models.CheckUserInfo;
import Objects.NewUserObject;
import Objects.UserObject;

public class LoginController implements Initializable {

    //The password variable that corresponds to the user input
    @FXML
    private TextField password;

    //The username variable that corresponds to the user input
    @FXML
    private TextField username;

    //The confirm password variable that corresponds to the user input
    @FXML
    private TextField confirmPassword;

    //The email variable that corresponds to the user input
    @FXML
    private TextField email;

    //The confirm email variable that corresponds to the user input
    @FXML
    private TextField confirmEmail;

    //Variable to hold the current user
    private UserObject user;

    // CheckUserInfo Object
    private final CheckUserInfo USERINFOCHECKER = new CheckUserInfo();

    // DBInfoRequest Object (Adapter for database operations)
    private final DBInfoRequest DBADAPTER = new DBInfoRequest();

    //Variable containing the address of the fxml files
    private final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private final Boolean RESIZE = false;

    // Results Codes for the validity of a NewUserObject
    private final int NO_ERRORS = 0;
    private static final int USERNAME_ERROR = 1;
    private static final int PASSWORD_ERROR = 2;
    private static final int INVALID_EMAIL_ERROR = 3;
    private static final int EMAIL_TAKEN_ERROR = 4;

    //Variables to hold the fxml files
    private final String PROFILEFXML = this.ADDRESS + "Profile.fxml";
    private final String LOGINPAGEERROR = this.ADDRESS + "LoginPageError.fxml";
    private final String SIGNUPPAGE = this.ADDRESS + "SignUpPage.fxml";
    private final String FORGOTPASSWORD = this.ADDRESS + "ForgotPassword.fxml";
    private final String SIGNUPUSERNAMEERROR = this.ADDRESS + "SignUpUsernameError.fxml";
    private final String SIGNUPPASSWORDERROR = this.ADDRESS + "SignUpPasswordError.fxml";
    private final String SIGNUPSUCCESSFUL = this.ADDRESS + "SignUpSuccessful.fxml";
    private final String SIGNUPIMPROPEREMAILERROR = this.ADDRESS + "SignUpImproperEmailError.fxml";
    private final String SIGNUPEMAILERROR = this.ADDRESS + "SignUpEmailError.fxml";
    private final String LOGINPAGE = this.ADDRESS + "LoginPage.fxml";

    /**
     * This method is responsible for the action taken when the login button is
     * clicked. It either displays the username and password if it is registered
     * or it displays the error scene.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void loginButtonClicked(ActionEvent _event) throws IOException {

        //String variables of the data inserted by user
        String theUsername = this.username.getText();
        String thePassword = this.password.getText();

        // Check the provided login information
        int userLoginValidityCode = this.USERINFOCHECKER.isUserCredentialsValid(theUsername, thePassword);

        // If the login is valid, load the Profile page.
        if (userLoginValidityCode == this.NO_ERRORS) {

            //Obtain the current user as a UserObject
            this.user = this.DBADAPTER.readUserRecord(theUsername);

            //Display the profile page
            displayPage(_event, this.PROFILEFXML);

        }else{

            // If the login is not valid, load the Login Error page.
            displayPage(_event, this.LOGINPAGEERROR);

        }

    }

    /**
     * This method displays the sign up view scene when the button sign up is
     * clicked
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void signUpButtonClicked(ActionEvent _event) throws IOException {

        //Sign up page fxml is displayed
        displayPage(_event, this.SIGNUPPAGE);

    }

    /**
     * This method displays user forgot password if forgot password button is
     * clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void forgotPasswordButtonClicked(ActionEvent _event) throws IOException {

        //Forgot password page fxml is displayed
        displayPage(_event, this.FORGOTPASSWORD);
    }


    /**
     * This method is responsible for when the sign up button is clicked
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void registerButtonClicked(ActionEvent _event) throws IOException {

        //String variables of the information entered by the user
        String theUsername = this.username.getText();
        String thePassword = this.password.getText();
        String theConfirmPassword = this.confirmPassword.getText();
        String theEmail = this.email.getText();

        // First and last names fields needed in 'SingUpPage.fxml'
        String firstName = "Johnathan"; // placeholder
        String lastName = "Doe"; // placeholder

        // Create a NewUserObject and check if its information is valid
        NewUserObject newUser = new NewUserObject(theUsername, thePassword, theConfirmPassword, theEmail, firstName, lastName);
        int userValidityCode = this.USERINFOCHECKER.isNewUserValid(newUser);

        // Load the appropriate page based on the error code: userValidityCode
        switch (userValidityCode) {
            // Load and display the username error page
            case LoginController.USERNAME_ERROR:
                displayPage(_event, this.SIGNUPUSERNAMEERROR);
                break;
            // Load and display the password error page
            case LoginController.PASSWORD_ERROR:
                displayPage(_event, this.SIGNUPPASSWORDERROR);
                break;
            // Load and display the sign up improper email page
            case LoginController.INVALID_EMAIL_ERROR:
                displayPage(_event, this.SIGNUPIMPROPEREMAILERROR);
                break;
            // Load and display the email already exists page
            case LoginController.EMAIL_TAKEN_ERROR:
                displayPage(_event, this.SIGNUPEMAILERROR);
                break;
            // Create the new user then load and display the login page
            default:
                this.DBADAPTER.createUserRecord(newUser);
                displayPage(_event, this.SIGNUPSUCCESSFUL);
                break;
        }
    }

    /**
     * This method is responsible for the actions taken when the return to login
     * page button is clicked.
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void returnToLoginPageClicked(ActionEvent _event) throws IOException {

        //Load and display the login page
        displayPage(_event, this.LOGINPAGE);

    }


    /**
     * This method displays the page that was passed in as a string
     * @param _event
     * @param _fxmlFile
     * @throws IOException
     */
    private void displayPage(ActionEvent _event, String _fxmlFile) throws IOException{

        if(_fxmlFile == this.PROFILEFXML){

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
            stage.resizableProperty().setValue(this.RESIZE);
            stage.show();

        }else{

            Parent root = FXMLLoader.load(getClass().getResource(_fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.resizableProperty().setValue(this.RESIZE);
            stage.show();
        }
    }

    /**
     * This method is inherited by initializable class
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }

}
