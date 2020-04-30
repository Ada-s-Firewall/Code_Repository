package Models;

/**
 * This class acts wrapper for an instance of the DatabaseAdapter. Last Updated:
 * 4/28/2020
 *
 * @author Fernando Villarreal
 */
import Database.DatabaseAdapter;
import Database.DatabaseInterface;
import Objects.PlanToListenObject;
import Objects.RatingObject;
import Objects.RecordObject;
import Objects.UserMusicList;
import Objects.RecordObjectList;
import Objects.UserObject;
import java.io.File;
import java.util.ArrayList;

public class DBInfoRequest implements DatabaseInterface {

    //============== CLASS VARIABLES ==============
    private final DatabaseAdapter adapter = new DatabaseAdapter();

    //============== METHODS ==============
    @Override
    public void createRecord(File _file, ArrayList<String> _record) {
        this.adapter.createRecord(_file, _record);
    }

    @Override
    public void createUserRecord(UserObject _user) {
        this.adapter.createUserRecord(_user);
    }

    @Override
    public void createUserRating(RatingObject _rating) {
        this.adapter.createUserRating(_rating);
    }

    @Override
    public void createUserPlanToListen(PlanToListenObject _planToListen) {
        this.adapter.createUserPlanToListen(_planToListen);
    }

    @Override
    public void deleteUserRecord(File _file, UserObject _userObject) {
        this.adapter.deleteUserRecord(_file, _userObject);
    }

    @Override
    public void deleteUserRecord(UserObject _user) {
        this.adapter.deleteUserRecord(_user);
    }

    @Override
    public void deleteUserRecord(String _username) {
        this.adapter.deleteUserRecord(_username);
    }

    @Override
    public void deleteRating(RatingObject _rating) {
        this.adapter.deleteRating(_rating);
    }

    @Override
    public void deletePlanToListen(PlanToListenObject _planToListen) {
        this.adapter.deletePlanToListen(_planToListen);
    }

    @Override
    public ArrayList<String> readRecord(File _file, String _string) {
        return this.adapter.readRecord(_file, _string);
    }

    @Override
    public UserObject readUserRecord(String _username) {
        return this.adapter.readUserRecord(_username);
    }

    @Override
    public RecordObjectList readUsersRatings(String _username) {
        return this.adapter.readUsersRatings(_username);
    }

    @Override
    public RecordObjectList readUsersPlanToListen(String _username) {
        return this.adapter.readUsersPlanToListen(_username);
    }

    @Override
    public RecordObject makeActive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecordObject makeInactive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUserPassword(UserObject _user, String _newPassword) {
        this.adapter.updateUserPassword(_user, _newPassword);
    }

    @Override
    public void updateUsername(UserObject _user, String _newUsername) {
        this.adapter.updateUsername(_user, _newUsername);
    }

    @Override
    public void updateUserEmail(UserObject _user, String _newUserEmail) {
        this.adapter.updateUserEmail(_user, _newUserEmail);
    }

    @Override
    public void updateUsersRating(RatingObject _ratingObject, String _newUsersRating){
        this.adapter.updateUsersRating(_ratingObject, _newUsersRating);
    }

    @Override
    public void updateUsersPlanToListen(UserMusicList _user, ArrayList<String> _newUsersPlanToListen) {
        this.adapter.updateUsersPlanToListen(_user, _newUsersPlanToListen);
    }
}
