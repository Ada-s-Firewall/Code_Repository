
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


public class SignUpPageController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    @FXML
    private TextField email;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void signUpButtonClicked(ActionEvent event) throws IOException {
        
        String theUsername = username.getText();
        String thePassword = password.getText();
        String theConfirmPassword = confirmPassword.getText();
        String theEmail = email.getText();
        
        System.out.println("Username: " + theUsername);
        System.out.println("Password: " + thePassword);
        System.out.println("Confirm Password: " + theConfirmPassword);
        System.out.println("Email: " + theEmail);
        
        Parent loginPageRoot = FXMLLoader.load(getClass().getResource("LoginPageUI.fxml"));
        Scene loginPageScene = new Scene(loginPageRoot);
        Stage loginPageStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        loginPageStage.setScene(loginPageScene);
        loginPageStage.show();
    }

    @FXML
    private void returnToLoginPageClicked(ActionEvent event) throws IOException {
        Parent loginPageRoot = FXMLLoader.load(getClass().getResource("LoginPageUI.fxml"));
        Scene loginPageScene = new Scene(loginPageRoot);
        Stage loginPageStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        loginPageStage.setScene(loginPageScene);
        loginPageStage.show();
    }
    
}
