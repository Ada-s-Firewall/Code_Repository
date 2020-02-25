/*
*
* This file is a controller for displaying the user's information on their
* profile when they login.
*
* @author Will Higdon  Last Updated: 2/24/2020
*/
public class Controller {
   public static void main(String[] args) {
      //Fetches user record based on their info from the database
      User model  = retrieveUserFromDatabase();
      //Creates a view to display the user's infor on their profile
      UserView view = new UserView();
      UserController controller = new UserController(model, view);
      controller.updateView();
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

// View class for the User
class UserView {
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

// User Model Class, holds all user info
class User {
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

// Controller class for the User
class UserController {
   private User model;
   private UserView view;

   public UserController(User _model, UserView _view){
      this.model = _model;
      this.view = _view;
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

   //Updates the View
   public void updateView(){
      view.printUserDetails(model.getUserName(), model.getUserPassword(), model.getUserEmail(), model.getUserFirstName(), model.getUserLastName());
   }
}
