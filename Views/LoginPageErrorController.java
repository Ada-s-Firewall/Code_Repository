package Views;

/**
 * Purpose: The purpose of this class is to serve as the controller for the
 *          login page error view.
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


public class LoginPageErrorController implements Initializable {

    //This is the variable of the password entered by the user
    @FXML
    private TextField password;

    //This is the varaible of the username entered by the user
    @FXML
    private TextField username;

    /**
     * This method is responsible for handling what is done once the login
     * button is clicked.
     */
    @FXML
    void loginButtonClicked(ActionEvent _event) throws IOException {

        //String variable of the username and password entered by the user
        String theUsername = this.username.getText();
        String thePassword = this.password.getText();

        //If statement to see if the username and password are registered
        if ("username".equals(theUsername) && "password".equals(thePassword)) {

            //Display username and password if they are registered
            System.out.println("Username: " + theUsername);
            System.out.println("Password: " + thePassword);

        } else {

            //Load and display login page error scene if username and password are not registered
            Parent loginPageErrorRoot = FXMLLoader.load(getClass().getResource("LoginPageError.fxml"));
            Scene loginPageErrorScene = new Scene(loginPageErrorRoot);
            Stage loginPageErrorStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            loginPageErrorStage.setScene(loginPageErrorScene);
            loginPageErrorStage.show();
        }
    }

    /**
     * This method displayed the sign up page when the sign up page button is
     * clicked
     */
    @FXML
    void signUpButtonClicked(ActionEvent _event) throws IOException {

        //Load and display sign up page scene
        Parent signUpPageRoot = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        Scene signUpPageScene = new Scene(signUpPageRoot);
        Stage signUpPageStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        signUpPageStage.setScene(signUpPageScene);
        signUpPageStage.show();
    }

    /**
     * This method displayed that the user forgot password when the button
     * forgot password is clicked
     */
    @FXML
    void forgotPasswordButtonClicked(ActionEvent event) {

        //Print statement for when the user clicks forgot password
        System.out.println("Idot user forgot password!");
    }

    /**
     * This method is inherited from the initializable class
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        // TODO
    }
}
