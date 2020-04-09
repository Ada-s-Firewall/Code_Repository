package Database;

import Objects.*;
import java.io.File;
import java.util.ArrayList;

/**
 * This interface holds all the methods for the database classes.
 *
 * Last Updated: 04.08.2020
 *
 * @author Quinn Tjin-A-Soe, Fernando Villarreal
 *
 */
public interface DatabaseInterface {
//=========================== CLASS VARIABLES ==================================

    public static final File userInfoFile = new File("Datastore/UserInfo.txt");
    public static final File userLoginFile = new File("Datastore/UserLogin.txt");
    public static final File userRatingFile = new File("Datastore/UserRating.txt");

//============================ METHODS ========================================
    /**
     * This method creates a new record.
     *
     * @param _file
     * @param _record
     */
    public abstract void creatRecord(File _file, ArrayList<String> _record);


    /**
     * Creates a new user record.
     * @param _user
     */
    public void createUserRecord(NewUser _user);

    /**
     * Create a new user's rating record.
     * @param _rating
     */
    public void createUserRating(NewRating _rating);

    /**
     * This method "deletes" a record of a user.
     *
     * @param _file
     * @param _userObject
     */
    public abstract void deleteUserRecord(File _file, UserObject _userObject);

    /**
     * This method returns a specified record from the database.
     *
     * @param _file
     * @param _string
     * @return
     */
    public abstract ArrayList readRecord(File _file, String _string);
    
    /**
     * Read and return a UserObject using the given username.
     * @param _username
     * @return UserObject
     */
    public UserObject readUserRecord(String _username);

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
