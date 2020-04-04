package Objects;

/**
 *
 * @author Quinn Tjin-A-Soe Last updated: 04.02.2020
 */
public class UserObject extends RecordObject {


    /*
     * Object for the user and their information.
     *
     * @author: Will Higdon
     * Last Updated: April 2, 2020
     */
    /*
     String variables for all of the user's information.
     */
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userFirstName;
    private String userLastName;

    //=================  CONSTRUCTORS ==================
    public UserObject(String _userName, String _userPassword, String _userEmail, String _userFirstName, String _userLastName) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    //=================  GETTERS ==================
    public String getUserName() {
        return this.getUserName();
    }

    public String getUserPassword() {
        return this.getUserPassword();
    }

    public String getUserEmail() {
        return this.getUserEmail();
    }

    public String getUserFirstName() {
        return this.getUserFirstName();
    }

    public String getUserLastName() {
        return this.getUserFirstName();
    }

    //=================  SETTERS =================
    public void setUserName(String _userName) {
        this.userName = userName;
    }

    public void setUserPassword(String _userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String _userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserFirstName(String _userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String _userLastName) {
        this.userLastName = userLastName;
    }

}
