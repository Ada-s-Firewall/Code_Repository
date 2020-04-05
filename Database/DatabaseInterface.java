package Database;

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

    public abstract void creatRecord(File _file, ArrayList _record);

    public abstract void createUserRecord(File _file, UserObject _userObject);

    public abstract void deleteUserRecord(File _file, UserObject _userObject);

    public abstract ArrayList readRecord(File _file, String _string);

}
