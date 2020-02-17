
//test controller

public class Controller {
   public static void main(String[] args) {

      //fetch user record based on their info from the database
      User model  = retrieveUserFromDatabase();

      //Creates a view to write the user's details onto the console
      UserView view = new UserView();

      UserController controller = new UserController(model, view);

      controller.updateView();
      
   }

   // User test case, retrieves the user's info from the Database
   private static User retrieveUserFromDatabase(){
      User user = new User();
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
   public void printUserDetails(String userName, String userPassword, String userEmail, String userFirstName, String userLastName){
      System.out.println("USER INFO: ");
      System.out.println("Username: " + userName);
      System.out.println("Password: " + userPassword);
      System.out.println("Email Address: " + userEmail);
      System.out.println("First Name: " + userFirstName);
      System.out.println("Last Name: " + userLastName);
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
      return userName;
   }
   
   public String getUserPassword() {
      return userPassword;
   }
   
   public String getUserEmail() {
      return userEmail;
   }
   
   public String getUserFirstName() {
      return userFirstName;
   }
   
   public String getUserLastName() {
      return userLastName;
   }
   
   //============== SETTERS ==================
   
   public void setUserName(String userName) {
      this.userName = userName;
   }
   
   public void setUserPassword(String userPassword) {
      this.userPassword = userPassword;
   }
   
   public void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
   }
   
   public void setUserFirstName(String userFirstName) {
      this.userFirstName = userFirstName;
   }
   
   public void setUserLastName(String userLastName) {
      this.userLastName = userLastName;
   }
}

// Controller class for the User
class UserController {
   private User model;
   private UserView view;

   public UserController(User model, UserView view){
      this.model = model;
      this.view = view;
   }

   //================= GETTERS =====================
  
   public String getUserName(){
      return model.getUserName();		
   }

   public String getUserPassword(){
      return model.getUserPassword();		
   }
   
   public String getUserEmail(){
      return model.getUserEmail();		
   }
   
   public String getUserFirstName(){
      return model.getUserFirstName();		
   }
   
   public String getUserLastName(){
      return model.getUserFirstName();		
   }
   
   //================= SETTERS =====================
   
   public void setUserName(String userName){
      model.setUserName(userName);		
   }
   
   public void setUserPassword(String userPassword){
      model.setUserPassword(userPassword);		
   }

   public void setUserEmail(String userEmail){
      model.setUserEmail(userEmail);		
   }
   
   public void setUserFirstName(String userFirstName){
      model.setUserFirstName(userFirstName);		
   }

   public void setUserLastName(String userLastName){
      model.setUserLastName(userLastName);		
   }

   //Updates the View
   public void updateView(){				
      view.printUserDetails(model.getUserName(), model.getUserPassword(), model.getUserEmail(), model.getUserFirstName(), model.getUserLastName());
   }	
}
