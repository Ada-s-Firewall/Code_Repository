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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPageController implements Initializable {
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
    }
    
    @FXML
    private javafx.scene.control.TextField password;

    @FXML
    private javafx.scene.control.TextField username;

    @FXML
    public void loginButtonClicked(ActionEvent event) throws IOException{
        
        String theUsername = username.getText();
        String thePassword = password.getText();
        
        if("username".equals(theUsername) && "password".equals(thePassword)){
            System.out.println("Username: " + theUsername);
            System.out.println("Password: " + thePassword);
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("LoginPageError.fxml"));
            Scene rootScene = new Scene(root);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(rootScene);
            appStage.show();
        }
    }

    @FXML
    public void signUpButtonClicked(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        Scene rootScene = new Scene(root);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(rootScene);
        appStage.show();
    }

    @FXML
    public void forgotPasswordButtonClicked(){
        System.out.println("Idiot User Forgot Password!");
    }


}
