package Models;

/**
 * This class acts an adapter for performing CRUD operations with the Database.
 * Last Updated: 4/8/2020
 * @author Fernando Villarreal
 */

import Database.DatabaseCreate;
import Database.DatabaseInterface;
import Objects.NewRating;
import Objects.NewUser;
import Objects.RecordObject;
import Objects.UserObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBInfoRequest implements DatabaseInterface{

    //============== CONSTRUCTOR ==============

    public DBInfoRequest() {};

    //============== METHODS ==============

    @Override
    public void creatRecord(File _file, ArrayList<String> _record) {
        try {
            DatabaseCreate.createRecord(_file, _record);
        } catch (IOException ex) {
            Logger.getLogger(DBInfoRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createUserRecord(NewUser _user) {
        try {
            DatabaseCreate.createUserRecord(_user);
        } catch (Exception ex) {
            Logger.getLogger(DBInfoRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createUserRating(NewRating _rating) {
        try {
            DatabaseCreate.createUserRating(_rating);
        } catch (Exception ex) {
            Logger.getLogger(DBInfoRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUserRecord(File _file, UserObject _userObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList readRecord(File _file, String _string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserObject readUserRecord(String _username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecordObject makeActive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecordObject makeInactive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
