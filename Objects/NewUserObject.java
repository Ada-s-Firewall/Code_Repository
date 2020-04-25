package Objects;

/**
 * This subclass of UserObject has a confirmPassword variable.
 * Last Updated: 4/16/2020
 * @author Fernando Villarreal
 */

public class NewUserObject extends UserObject{

    //===================== CLASS VARIABLES =====================

    private String confirmPassword;

    //===================== CONSTRUCTORS =====================

    public NewUserObject(String _userName, String _password, String _confirmPassword, String _email, String _firstName, String _lastName) {
        super(_userName, _password, _email, _firstName, _lastName);
        this.confirmPassword = _confirmPassword;
    }

    //===================== GETTERS =====================

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    //===================== SETTERS =====================

    public void setConfirmPassword(String _confirmPassword) {
        this.confirmPassword = _confirmPassword;
    }
}
