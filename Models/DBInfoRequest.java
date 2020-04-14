package Models;

/**
 * This class acts an adapter for performing CRUD operations with the Database.
 * Last Updated: 4/13/2020
 * @author Fernando Villarreal
 */

import Database.*;
import Objects.RatingObject;
import Objects.RecordObject;
import Objects.RecordObjectList;
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
    public void createUserRecord(UserObject _user) {
        try {
            DatabaseCreate.createUserRecord(_user);
        } catch (Exception ex) {
            Logger.getLogger(DBInfoRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createUserRating(RatingObject _rating) {
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
    public ArrayList<String> readRecord(File _file, String _string) {
        try {
            return DatabaseRead.readRecord(_file, _string);
        } catch (Exception ex) {
            Logger.getLogger(DBInfoRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UserObject readUserRecord(String _username) {
        try {
            return DatabaseRead.readUserRecord(_username);
        } catch (Exception ex) {
            Logger.getLogger(DBInfoRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public RecordObjectList readUsersRatings(String _username) {
        try {
            return DatabaseRead.readUsersRatings(_username);
        } catch (Exception ex) {
            Logger.getLogger(DBInfoRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
