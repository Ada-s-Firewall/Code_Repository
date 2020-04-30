package Database;

/**
 * This class acts as an adapter for the Database. Last Updated: 4/29/2020
 *
 * @authors Fernando Villarreal, Will Higdon
 */
import Objects.PlanToListenObject;
import Objects.RatingObject;
import Objects.UserMusicList;
import Objects.RecordObject;
import Objects.RecordObjectList;
import Objects.UserObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseAdapter implements DatabaseInterface {

    //============== CONSTRUCTOR ==============
    public DatabaseAdapter() {
    }

    //============== METHODS ==============
    @Override
    public void createRecord(File _file, ArrayList<String> _record) {
        try {
            DatabaseCreate.createRecord(_file, _record);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createUserRecord(UserObject _user) {
        try {
            DatabaseCreate.createUserRecord(_user);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createUserRating(RatingObject _rating) {
        try {
            DatabaseCreate.createUserRating(_rating);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createUserPlanToListen(PlanToListenObject _planToListen) {
        try {
            DatabaseCreate.createUserPlanToListen(_planToListen);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUserRecord(File _file, UserObject _userObject) {
        try {
            DatabaseDelete.deleteUserRecord(_file, _userObject);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUserRecord(UserObject _user) {
        try {
            DatabaseDelete.deleteUserRecord(_user);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUserRecord(String _username) {
        try {
            DatabaseDelete.deleteUserRecord(_username);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteRating(RatingObject _rating) {
        try {
            DatabaseDelete.deleteRating(_rating);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletePlanToListen(PlanToListenObject _planToListen) {
        try {
            DatabaseDelete.deletePlanToListen(_planToListen);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public ArrayList<String> readRecord(File _file, String _string) {
        try {
            return DatabaseRead.readRecord(_file, _string);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UserObject readUserRecord(String _username) {
        try {
            return DatabaseRead.readUserRecord(_username);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public RecordObjectList readUsersRatings(String _username) {
        try {
            return DatabaseRead.readUsersRatings(_username);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public RecordObjectList readUsersPlanToListen(String _username) {
        try {
            DatabaseRead.readUsersPlanToListen(_username);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public void updateUserPassword(UserObject _user, String _newPassword) {
        try {
            DatabaseUpdate.updateUserPassword(_user, _newPassword);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateUsername(UserObject _user, String _newUsername) {
        try {
            DatabaseUpdate.updateUsername(_user, _newUsername);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateUserEmail(UserObject _user, String _newUserEmail) {
        try {
            DatabaseUpdate.updateUserEmail(_user, _newUserEmail);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateUsersRating(RatingObject _ratingObject, String _newUsersRating) {
        try {
            DatabaseUpdate.updateUsersRating(_ratingObject, _newUsersRating);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateUsersPlanToListen(UserMusicList _user, String _newUsersPlanToListen) {
        try {
            DatabaseUpdate.updateUsersPlanToListenList(_user, _newUsersPlanToListen);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
