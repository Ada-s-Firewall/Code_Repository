package Database;

/**
 * This Exception class throws exception messages for database operations. Methods and other class
 * properties are inherited from Exception.
 * Last Updated: 4/14/2020
 * @author Fernando Villarreal
 */
public class DatabaseException extends Exception {

    //===================== CONSTRUCTORS =====================

    public DatabaseException(String _message) {
        super(_message);
    }

}
