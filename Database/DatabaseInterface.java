package Database;

/**
 * This interface holds all the methods for the database classes. Last Updated:
 * 04.29.2020
 *
 * @author Quinn Tjin-A-Soe, Fernando Villarreal
 */
import Objects.*;
import java.io.File;
import java.util.ArrayList;

public interface DatabaseInterface {

//=========================== CLASS VARIABLES ==================================
    public static final File userInfoFile = new File("src/Datastore/UserInfo.txt");
    public static final File userLoginFile = new File("src/Datastore/UserLogin.txt");
    public static final File userRatingFile = new File("src/Datastore/UserRating.txt");
    public static final File userPlanToListen = new File("src/Datastore/UserPlanToListen.txt");
    public static final File temporaryFile = new File("src/Datastore/TemporaryFile.txt");
    public static final boolean active = true;
    public static final boolean inactive = false;

//============================ METHODS ========================================
    /**
     * This method creates a new record.
     *
     * @param _file
     * @param _record
     */
    public abstract void createRecord(File _file, ArrayList<String> _record);

    /**
     * Creates a new user record.
     *
     * @param _user
     */
    public void createUserRecord(UserObject _user);

    /**
     * Create a new user's rating record.
     *
     * @param _rating
     */
    public void createUserRating(RatingObject _rating);

    /**
     * Creates a new plan-to-listen record.
     *
     * @param _planToListen
     */
    public void createUserPlanToListen(PlanToListenObject _planToListen);

    /**
     * This method "deletes" a record of a user.
     *
     * @param _file
     * @param _userObject
     */
    public void deleteUserRecord(File _file, UserObject _userObject);

    /**
     * Deletes the given user from the database.
     *
     * @param _user
     */
    public void deleteUserRecord(UserObject _user);

    /**
     * Deletes the user with the associated username from the database.
     *
     * @param _username
     */
    public void deleteUserRecord(String _username);

    /**
     *
     * @param _rating
     */
    public void deleteRating(RatingObject _rating);

    /**
     *
     * @param _planToListen
     */
    public void deletePlanToListen(PlanToListenObject _planToListen);

    /**
     *
     * @param _user
     * @param _newPassword
     */
    public abstract void updateUserPassword(UserObject _user, String _newPassword);

    /**
     *
     * @param _user
     * @param _newUsername
     */
    public abstract void updateUsername(UserObject _user, String _newUsername);

    /**
     *
     * @param _user
     * @param _newUserEmail
     */
    public abstract void updateUserEmail(UserObject _user, String _newUserEmail);

    /**
     *
     * @param _ratingObject
     * @param _newUsersRating
     */
    public abstract void updateUsersRating(RatingObject _ratingObject, String _newUsersRating);

    /**
     *
     * @param _user
     * @param _newUsersPlanToListen
     */
    public abstract void updateUsersPlanToListen(UserMusicList _user, ArrayList<String> _newUsersPlanToListen);

    /**
     * This method returns a specified record from the database.
     *
     * @param _file
     * @param _string
     * @return
     */
    public abstract ArrayList<String> readRecord(File _file, String _string);

    /**
     * Read and return a UserObject using the given username.
     *
     * @param _username
     * @return UserObject
     */
    public UserObject readUserRecord(String _username);

    /**
     * Read and return a RecordObjectList containing the specified user's
     * ratings.
     *
     * @param _username
     * @return RecordObjectList
     */
    public RecordObjectList readUsersRatings(String _username);

    /**
     * Read and return a RecordObjectList containing the specified user's
     * plan-to-listen records
     *
     * @param _username
     * @return RecordObjectList
     */
    public RecordObjectList readUsersPlanToListen(String _username);

    /**
     * This method makes a record active.
     *
     * @return
     */
    public RecordObject makeActive();

    /**
     * This method makes a record inactive (deleted).
     *
     * @return
     */
    public RecordObject makeInactive();

}
