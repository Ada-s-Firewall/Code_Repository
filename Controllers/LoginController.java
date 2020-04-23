package Controllers;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          login process of the application fxml files which holds the code
 *          for the views of the login process of the application.
 * Contributors: Eric Cortes | Modified: Fernando Villarreal
 * Last Updated: 04/16/2020
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

public class LoginController implements Initializable {

    //Variable containing the address of the fxml files
    private static final String ADDRESS = "/Views/";

    //Variable set to false which coresponds to the maximize functionality
    private static final Boolean RESIZE = false;

    // CheckUserInfo Object
    private final CheckUserInfo checker = new CheckUserInfo();

    // DBInfoRequest Object (Adapter for database operations)
    private final DBInfoRequest dbAdapter = new DBInfoRequest();

    // Results Codes for the validity of a NewUserObject
    private final int NO_ERRORS = 0;
    private static final int USERNAME_ERROR = 1;
    private static final int PASSWORD_ERROR = 2;
    private static final int INVALID_EMAIL_ERROR = 3;
    private static final int EMAIL_TAKEN_ERROR = 4;

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
        int userLoginValidityCode = this.checker.isUserCredentialsValid(theUsername, thePassword);
        // If the login is valid, load the Search page.
        if (userLoginValidityCode == this.NO_ERRORS) {
            displayPage(_event, "Search.fxml");
        }
        // If the login is not valid, load the Login Error page.
        displayPage(_event, "LoginPageError.fxml");

        /* ORIGINAL CODE BELOW
        //Test if statement to see if username and password are registered
        if ("username".equals(theUsername) && "password".equals(thePassword)) {

            //Display the user's profile page
            displayPage(_event, "Search.fxml");
        } else {

            //If username and password are not registered error scene is opened
            displayPage(_event, "LoginPageError.fxml");
        }
        */
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
        displayPage(_event, "SignUpPage.fxml");

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
        displayPage(_event, "ForgotPassword.fxml");
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
        int userValidityCode = this.checker.isNewUserValid(newUser);

        // Load the appropriate page based on the error code: userValidityCode
        switch (userValidityCode) {
            // Load and display the username error page
            case LoginController.USERNAME_ERROR:
                displayPage(_event, "SignUpUsernameError.fxml");
                break;
            // Load and display the password error page
            case LoginController.PASSWORD_ERROR:
                displayPage(_event, "SignUpPasswordError.fxml");
                break;
            // Load and display the sign up improper email page
            case LoginController.INVALID_EMAIL_ERROR:
                displayPage(_event, "SignUpImproperEmailError.fxml");
                break;
            // Load and display the email already exists page
            case LoginController.EMAIL_TAKEN_ERROR:
                displayPage(_event, "SignUpEmailError.fxml");
                break;
            // Create the new user then load and display the login page
            default:
                this.dbAdapter.createUserRecord(newUser);
                displayPage(_event, "SignUpSuccessful.fxml");
                break;
        }

        /* ORIGINAL CODE BELOW
        //This if else statement compares to see if information is adequate
        if ("username".equals(theUsername)) {

            //Load and display the sign up error page
            displayPage(_event, "SignUpUsernameError.fxml");

        } else if (!theConfirmPassword.equals(thePassword)) {

            //Load and display the second sign up error page
            displayPage(_event, "SignUpPasswordError.fxml");

        } else if (!(theEmail.contains("@") || theEmail.contains("."))){

            //Load and display the sign up improper email page
            displayPage(_event, "SignUpImproperEmailError.fxml");

        } else if("yahoo@gmail.com".equals(theEmail)){

            //Load and display the email already exists page
            displayPage(_event, "SignUpEmailError.fxml");

        }else {
            //Load and display the login page
            displayPage(_event, "SignUpSuccessful.fxml");

        }
        */
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
        displayPage(_event, "LoginPage.fxml");

    }

    /**
     * This method is responsible for the actions taken when the send me a new
     * password button is clicked
     * @param _event
     * @throws java.io.IOException
     */
    @FXML
    protected void sendMeNewPasswordButtonClicked(ActionEvent _event) throws IOException {

        //String variables of information entered by user
        String theEmail = email.getText();
        String theConfirmEmail = confirmEmail.getText();

        //Display apporpriate message depending on input
        if(!(theEmail.equals(theConfirmEmail))){

            //Load and display the emails dont match page
            displayPage(_event, "ForgotPasswordEmailError.fxml");

        }else if (!("yahoo@gmail.com".equals(theEmail))){

            //Load and display the email does not exist page
            displayPage(_event, "ForgotPasswordEmailDoesNotExistError.fxml");

        }else{

            //Load and display new password sent
            displayPage(_event, "ForgotPasswordSuccessful.fxml");

        }
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
     * This method is inherited by initializable class
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }

}
