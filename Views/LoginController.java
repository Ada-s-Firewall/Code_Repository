package Views;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          login page fxml file which is the code for the login page view.
 * Contributors: Eric Cortes
 * Last Updated: 02/24/2020
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

    /**
     * This method is responsible for the action taken when the login button is
     * clicked. It either displays the username and password if it is registered
     * or it displays the error scene.
     */
    @FXML
    private void loginButtonClicked(ActionEvent _event) throws IOException {

        //String variables of the data inserted by user
        String theUsername = this.username.getText();
        String thePassword = this.password.getText();

        //Test if statement to see if username and password are registered
        if ("username".equals(theUsername) && "password".equals(thePassword)) {

            //Print username and password if they are registered
            System.out.println("Username: " + theUsername);
            System.out.println("Password: " + thePassword);
        } else {
            //If username and password are not registered error scene is opened

            displayPage(_event, "LoginPageError.fxml");
        }
    }

    /**
     * This method displays the sign up view scene when the button sign up is
     * clicked
     */
    @FXML
    private void signUpButtonClicked(ActionEvent _event) throws IOException {

        //Sign up page fxml is displayed
        displayPage(_event, "SignUpPage.fxml");

    }

    /**
     * This method displays user forgot password if forgot password button is
     * clicked.
     */
    @FXML
    private void forgotPasswordButtonClicked() {

        //Print idiot user forgot password
        System.out.println("Idiot User Forgot Password!");
    }


    /**
     * This method is responsible for when the sign up button is clicked
     */
    @FXML
    private void registerButtonClicked(ActionEvent _event) throws IOException {

        //String variables of the information entered by the user
        String theUsername = this.username.getText();
        String thePassword = this.password.getText();
        String theConfirmPassword = this.confirmPassword.getText();
        String theEmail = this.email.getText();


        //This if else statement compares to see if information is adequate
        if ("username".equals(theUsername)) {

            //Load and display the sign up error page
            displayPage(_event, "SignUpUsernameError.fxml");

        } else if (theConfirmPassword != thePassword) {

            //Load and display the second sign up error page
            displayPage(_event, "SignUpPasswordError.fxml");

        } else {
            //Load and display the login page
            displayPage(_event, "LoginPage.fxml");

        }
    }

    /**
     * This method is responsible for the actions taken when the return to login
     * page button is clicked.
     */
    @FXML
    private void returnToLoginPageClicked(ActionEvent _event) throws IOException {

        //Load and display the login page
        displayPage(_event, "LoginPage.fxml");

    }

    /**
     * This method displays the page that was passed in as a string
     * @param _event
     * @param fxmlFile
     * @throws IOException
     */
    private void displayPage(ActionEvent _event, String _fxmlFile) throws IOException{

        //Load and display the login page
        Parent root = FXMLLoader.load(getClass().getResource(_fxmlFile));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This method is inherited by initializable class
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }

}
