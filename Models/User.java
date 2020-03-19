package Models;

/*
Model class for displaying the user info.

Last Updated: March 19, 2020
@author Will Higdon
*/

import Controllers.DisplayUserInfo;

// User Model Class, holds all user info
public class User {
   private String userName;
   private String userPassword;
   private String userEmail;
   private String userFirstName;
   private String userLastName;

   //displays the user's info on their profile
   public void printUserDetails(String _userName, String _userPassword, String _userEmail, String _userFirstName, String _userLastName){
      System.out.println("My Info: ");
      System.out.println("");
      System.out.println("Username: " + _userName);
      System.out.println("Password: " + _userPassword);
      System.out.println("Email Address: " + _userEmail);
      System.out.println("First Name: " + _userFirstName);
      System.out.println("Last Name: " + _userLastName);
   }

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
