
package LoginPage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class UIController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void loginButtonClicked(){
        System.out.println("User logged in!");
    }
    
    public void signUpButtonClicked(){
        System.out.println("User signed up!");
    }
    
    public void forgotPasswordButtonClicked(){
        System.out.println("Idiot User Forgot Password!");
    }
    
    
}
