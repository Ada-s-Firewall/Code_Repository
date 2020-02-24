package Views;

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

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 * login page fxml file which is the code for the login page view. Contributors:
 * Eric Cortes Last Updated: 02/24/2020
 */
public class LoginPageController implements Initializable {

    //The password variable that corresponds to the user input
    @FXML
    private TextField password;

    //The username variable that corresponds to the user input
    @FXML
    private TextField username;

    /**
     * This method is responsible for the action taken when the login button is
     * clicked. It either displays the username and password if it is registered
     * or it displays the error scene.
     */
    @FXML
    public void loginButtonClicked(ActionEvent _event) throws IOException {

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
            Parent loginPageErrorRoot = FXMLLoader.load(getClass().getResource("LoginPageError.fxml"));
            Scene loginPageScene = new Scene(loginPageErrorRoot);
            Stage loginPageStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            loginPageStage.setScene(loginPageScene);
            loginPageStage.show();
        }
    }

    /**
     * This method displays the sign up view scene when the button sign up is
     * clicked
     */
    @FXML
    public void signUpButtonClicked(ActionEvent _event) throws IOException {

        //Sign up page fxml is displayed
        Parent root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        Scene rootScene = new Scene(root);
        Stage appStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        appStage.setScene(rootScene);
        appStage.show();
    }

    /**
     * This method displays user forgot password if forgot password button is
     * clicked.
     */
    @FXML
    public void forgotPasswordButtonClicked() {

        //Print idiot user forgot password
        System.out.println("Idiot User Forgot Password!");
    }

    /**
     * This method is inherited by initializable class
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }

}
