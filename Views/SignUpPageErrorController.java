
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


public class SignUpPageErrorController implements Initializable {
    
    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    void signUpButtonClicked(ActionEvent event) throws IOException {
        
        String theUsername = username.getText();
        String thePassword = password.getText();
        String theConfirmPassword = confirmPassword.getText();
        String theEmail = email.getText();
        
        if("username".equals(theUsername)){
            Parent loginPageErrorRoot = FXMLLoader.load(getClass().getResource("SignUpPageError.fxml"));
            Scene loginPageErrorScene = new Scene(loginPageErrorRoot);
            Stage loginPageErrorStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            loginPageErrorStage.setScene(loginPageErrorScene);
            loginPageErrorStage.show();
        }
        else if(theConfirmPassword != thePassword){
            System.out.println("Confirm Password does not equal password");
        }
        else{
            Parent loginPageRoot = FXMLLoader.load(getClass().getResource("LoginPageUI.fxml"));
            Scene loginPageScene = new Scene(loginPageRoot);
            Stage loginPageStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            loginPageStage.setScene(loginPageScene);
            loginPageStage.show();
        }
    }

    @FXML
    void returnToLoginPageClicked(ActionEvent event) throws IOException {
        Parent loginPageRoot = FXMLLoader.load(getClass().getResource("LoginPageUI.fxml"));
        Scene loginPageScene = new Scene(loginPageRoot);
        Stage loginPageStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        loginPageStage.setScene(loginPageScene);
        loginPageStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
}
