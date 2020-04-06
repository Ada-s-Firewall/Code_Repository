package Database;

import Objects.RecordObject;
import Objects.UserObject;
import java.io.File;
import java.util.ArrayList;

/**
 * This interface holds all the methods for the database classes.
 *
 * Last Updated: 04.05.2020
 *
 * @author Quinn Tjin-A-Soe
 *
 */
public interface DatabaseInterface {
//=========================== CLASS VARIABLES ==================================

    public static final File userInfoFile = new File("src/Datastore/UserInfo.txt");
    public static final File userLoginFile = new File("src/Datastore/Userlogin.txt");
    public static final File userRatingFile = new File("src/Datastore/UserRating.txt");

//============================ METHODS ========================================
    /**
     * This method creates a new record.
     *
     * @param _file
     * @param _record
     */
    public abstract void creatRecord(File _file, ArrayList _record);

    /**
     * This method creates a new record of a user.
     *
     * @param _file
     * @param _userObject
     */
    public abstract void createUserRecord(File _file, UserObject _userObject);

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
