package Prototypes;

/*
*
* This file is a controller for displaying the user's information on their
* profile when they login.

* Last Updated: 3/19/2020
* @author Will Higdon  
*/

import Models.User;

public class DisplayUserInfo {
   public static void main(String[] args) {
      //Fetches user record based on their info from the database
      User model  = retrieveUserFromDatabase();
      UserController controller = new UserController(model);
   }

   // User test case, an example of the user's info.
   private static User retrieveUserFromDatabase(){
      User user = new User();
      // User info
      user.setUserName("WigginsDough");
      user.setUserPassword("69isGOAT");
      user.setUserEmail("dwiggins@yikes.com");
      user.setUserFirstName("Dan");
      user.setUserLastName("Wiggins");
      return user;
   }
}

// Controller class for the User
class UserController {
   private User model;

   public UserController(User _model){
      this.model = _model;
   }

   //=================  GETTERS ===============

   public String getUserName(){
      return this.model.getUserName();
   }

   public String getUserPassword(){
      return this.model.getUserPassword();
   }

   public String getUserEmail(){
      return this.model.getUserEmail();
   }

   public String getUserFirstName(){
      return this.model.getUserFirstName();
   }

   public String getUserLastName(){
      return this.model.getUserFirstName();
   }

   //=================  SETTERS ===============

   public void setUserName(String _userName){
      model.setUserName(_userName);
   }

   public void setUserPassword(String _userPassword){
      model.setUserPassword(_userPassword);
   }

   public void setUserEmail(String _userEmail){
      model.setUserEmail(_userEmail);
   }

   public void setUserFirstName(String _userFirstName){
      model.setUserFirstName(_userFirstName);
   }

   public void setUserLastName(String _userLastName){
      model.setUserLastName(_userLastName);
   }
}
