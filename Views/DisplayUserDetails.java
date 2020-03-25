package Views;

/*
View class for displaying User's info on their profile when they login

@author Will Higdon
Last Updated: March 24, 2020
*/

import Models.User;
import Controllers.DisplayUserInfo;

public class DisplayUserDetails {
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
}
