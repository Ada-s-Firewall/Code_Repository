package Models;

/**
 * This model class checks the validity of the information for new Users and also
 * checks a user's credentials for the login process.
 * Last Updated: 4/16/2020
 * @author Fernando Villarreal
 */

import Objects.NewUserObject;
import Objects.UserObject;

public class CheckUserInfo {

    //===================== CLASS VARIABLES =====================

    // Database Adapter
    private final DBInfoRequest adapter;

    // Error and Results Codes for the methods
    private final int NO_ERRORS = 0;
    private final int USERNAME_ERROR = 1;
    private final int PASSWORD_ERROR = 2;
    private final int INVALID_EMAIL_ERROR = 3;
    private final int EMAIL_TAKEN_ERROR = 4;
    private final int INVALID_LOGIN = 5;

    //===================== CONSTRUCTORS =====================

    public CheckUserInfo() {
        this.adapter = new DBInfoRequest();
    }

    //===================== PUBLIC METHODS =====================

    /**
     * Checks if a new user's information is valid. Returns the appropriate results
     * code based on the information's validity.
     * @param _newUser
     * @return int
     */
    public int isNewUserValid(NewUserObject _newUser) {
        // Check if the new user's username is already taken. If it is, return false.
        String newUsername = _newUser.getUserName();
        UserObject existingUser = this.adapter.readUserRecord(newUsername);
        if (newUsername.equals(existingUser.getUserName())) {
            return this.USERNAME_ERROR;
        }
        // Check if the password and confirmPassword are the same. If not, return false.
        String password = _newUser.getUserPassword();
        String confirmPassword = _newUser.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            return this.PASSWORD_ERROR;
        }
        // Check if the email is valid. If not, return false.
        if (!this.isEmailValid(_newUser.getUserEmail())) {
            return this.INVALID_EMAIL_ERROR;
        }
        // Check if the email is already being used by an existing user
        String givenEmail = _newUser.getUserEmail();
        UserObject userWithEmail = this.adapter.readUserRecord(_newUser.getUserEmail());
        if (givenEmail.equals(userWithEmail.getUserEmail())) {
            return this.EMAIL_TAKEN_ERROR;
        }
        // Return true if the information is valid
        return this.NO_ERRORS;
    }

    /**
     * Checks if the given username and password correspond to existing ones. Returns
     * the appropriate results code based on the information's validity.
     * @param _theUsername
     * @param _thePassword
     * @return int
     */
    public int isUserCredentialsValid(String _theUsername, String _thePassword) {
        // Get the User associated with the given username
        UserObject existingUser = this.adapter.readUserRecord(_theUsername);
        String existingUsername = existingUser.getUserName();
        String existingPassword = existingUser.getUserPassword();
        // Check if the given and existing usernames and passwords match
        if (_theUsername.equals(existingUsername) && _thePassword.equals(existingPassword)) {
            return this.NO_ERRORS;
        }
        // If the usernames and passwords don't match, return an error code
        return this.INVALID_LOGIN;
    }

    //===================== PRIVATE METHODS =====================

    /**
     * Checks if the given email address is valid. Returns true if it is or returns
     * false if it's not.
     * @param _email
     * @return
     */
    private boolean isEmailValid(String _email) {
        String at = "@";
        String dot = ".";
        // If the email does not contain '@ ' or '.' characters, return false.
        if (!(_email.contains(at)) || !(_email.contains(dot))) {
            return false;
        }
        // If the '@' is not in a valid spot in the email address, return false.
        if (_email.startsWith(at) || _email.endsWith(at)) {
            return false;
        }
        // If the '.' is not in valid spot in the email address, return false.
        if (_email.startsWith(dot) || _email.endsWith(dot)) {
            return false;
        }
        // If '.' comes before '@' or if they are next to each other, return false.
        int atIndex = _email.indexOf(at);
        int dotIndex = _email.indexOf(dot);
        int distance = Math.abs(atIndex - dotIndex); // Distance between '@' and '.'
        if (atIndex >= dotIndex || distance <= 1) {
            return false;
        }
        // Return true if the email is in a valid format
        return true;
    }
}
