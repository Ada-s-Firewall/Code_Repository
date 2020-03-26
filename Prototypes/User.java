package Prototypes;

/*
Model class for displaying the user's info on their profile when they login.

Last Updated: March 24, 2020
@author Will Higdon
*/

import Prototypes.DisplayUserInfo;

// User Model Class
public class User {
   private String userName;
   private String userPassword;
   private String userEmail;
   private String userFirstName;
   private String userLastName;

   //============== GETTERS ==================

   public String getUserName() {
      return this.userName;
   }

   public String getUserPassword() {
      return this.userPassword;
   }

   public String getUserEmail() {
      return this.userEmail;
   }

   public String getUserFirstName() {
      return this.userFirstName;
   }

   public String getUserLastName() {
      return this.userLastName;
   }

   //=============== SETTERS ==================

   public void setUserName(String _userName) {
      this.userName = _userName;
   }

   public void setUserPassword(String _userPassword) {
      this.userPassword = _userPassword;
   }

   public void setUserEmail(String _userEmail) {
      this.userEmail = _userEmail;
   }

   public void setUserFirstName(String _userFirstName) {
      this.userFirstName = _userFirstName;
   }

   public void setUserLastName(String _userLastName) {
      this.userLastName = _userLastName;
   }
}
