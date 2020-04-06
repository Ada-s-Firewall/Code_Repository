package Database;

/*
 * This is a Database class for deleting records.
 *
 * Last Updated: April 5, 2020
 * @author Will Higdon, Quinn Tjin-A-Soe
 */
import static Database.DatabaseInterface.userLoginFile;
import Objects.UserObject;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseDelete {

    public static void deleteUserRecord(UserObject _userObject) throws IOException {
        Scanner scanner = new Scanner(userLoginFile);
        String userName = _userObject.getName();
        while (scanner.hasNextLine()) {
            if (scanner.hasNext(userName)) {
                _userObject.makeInactive();
            }
        }
    }
}
