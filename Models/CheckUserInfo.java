package Models;

/*
* Model for the user's information.
*
* @author: Will Higdon
* Last Updated: March 31, 2020
*/

import Controllers.UserInfoController;
import Views.DisplayUserDetails;

public class CheckUserInfo {
    /*
    String variables for all of the user's information.
    */
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userFirstName;
    private String userLastName;

    //=================  GETTERS ==================

    public String getUserName() {
        return this.getUserName();
    }

    public String getUserPassword(){
      return this.getUserPassword();
    }

   public String getUserEmail(){
      return this.getUserEmail();
   }

   public String getUserFirstName(){
      return this.getUserFirstName();
   }

   public String getUserLastName(){
      return this.getUserFirstName();
   }

   //=================  SETTERS =================

   public void setUserName(String _userName){
      this.userName = userName;
   }

   public void setUserPassword(String _userPassword){
      this.userPassword = userPassword;
   }

   public void setUserEmail(String _userEmail){
      this.userEmail = userEmail;
   }

   public void setUserFirstName(String _userFirstName){
      this.userFirstName = userFirstName;
   }

   public void setUserLastName(String _userLastName){
      this.userLastName = userLastName;
   }
}
