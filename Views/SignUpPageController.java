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
 * Purpose: This class serves as the controller class for the sign up page view
 * of the sign up page fxml file Contributors: Eric Cortes Last Updated:
 * 02/24/2020
 */
public class SignUpPageController implements Initializable {

    //Varaibles of the information entered by user
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    @FXML
    private TextField email;

    /**
     * This method is responsible for when the sign up button is clicked
     */
    @FXML
    private void signUpButtonClicked(ActionEvent _event) throws IOException {

        //String variables of the information entered by the user
        String theUsername = this.username.getText();
        String thePassword = this.password.getText();
        String theConfirmPassword = this.confirmPassword.getText();
        String theEmail = this.email.getText();

        //This if else statement compares to see if information is adequate
        if ("username".equals(theUsername)) {

            //Load and display the sign up error page
            Parent loginPageErrorRoot = FXMLLoader.load(getClass().getResource("SignUpPageError.fxml"));
            Scene loginPageErrorScene = new Scene(loginPageErrorRoot);
            Stage loginPageErrorStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            loginPageErrorStage.setScene(loginPageErrorScene);
            loginPageErrorStage.show();
        } else if (!theConfirmPassword.equals(thePassword)) {

            //Load and display the second sign up error page
            Parent loginPageError2Root = FXMLLoader.load(getClass().getResource("SignUpPageError2.fxml"));
            Scene loginPageError2Scene = new Scene(loginPageError2Root);
            Stage loginPageError2Stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            loginPageError2Stage.setScene(loginPageError2Scene);
            loginPageError2Stage.show();
        } else {

            //Load and display the login page
            Parent loginPageRoot = FXMLLoader.load(getClass().getResource("LoginPageUI.fxml"));
            Scene loginPageScene = new Scene(loginPageRoot);
            Stage loginPageStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
            loginPageStage.setScene(loginPageScene);
            loginPageStage.show();
        }
    }

    /**
     * This method is responsible for the actions taken when the return to login
     * page button is clicked.
     */
    @FXML
    private void returnToLoginPageClicked(ActionEvent _event) throws IOException {

        //Load and display the login page
        Parent loginPageRoot = FXMLLoader.load(getClass().getResource("LoginPageUI.fxml"));
        Scene loginPageScene = new Scene(loginPageRoot);
        Stage loginPageStage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        loginPageStage.setScene(loginPageScene);
        loginPageStage.show();
    }

    /**
     * This method is inherited by the initializable class
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        // TODO
    }

}
