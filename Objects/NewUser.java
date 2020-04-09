package Objects;

/**
 * This class...
 * Last Updated: 4/8/2020
 * @author Fernando Villarreal
 */

import java.util.ArrayList;

public class NewUser extends NewDBObject{

    //=============== CLASS VARIABLES ===============

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    //=============== CONSTRUCTORS ===============

    public NewUser(String _username, String _password, String _email, String _firstName, String _lastName) {
        super();
        this.username = _username;
        this.password = _password;
        this.email = _email;
        this.firstName = _firstName;
        this.lastName = _lastName;
    }

    //=============== METHODS ===============

    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = super.toArrayList();
        list.add(this.username);
        list.add(this.password);
        list.add(this.email);
        list.add(this.firstName);
        list.add(this.lastName);
        return list;
    }

    @Override
    public String toString() {
        String userInfo = "\nUsername: " + this.username + "\nPassword: " + this.password + "\nEmail: " + this.email
                + "\nFirst Name: " + this.firstName + "\nLast Name: " + this.lastName;
        return super.toString() + userInfo;
    }

    //=============== GETTERS ===============

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    //=============== SETTERS ===============

    public void setUsername(String _username) {
        this.username = _username;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public void setEmail(String _email) {
        this.email = _email;
    }

    public void setFirstName(String _firstName) {
        this.firstName = _firstName;
    }

    public void setLastName(String _lastName) {
        this.lastName = _lastName;
    }
}
