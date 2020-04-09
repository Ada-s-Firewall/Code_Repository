package Database;

import static Database.DatabaseInterface.userLoginFile;
import Objects.RecordObject;
import Objects.UserObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class holds the methods for updating a file.
 *
 * Last Updated: 04.06.2020.
 *
 * @author Quinn Tjin-A-Soe
 */
public class DatabaseUpdate {

    /**
     * This method updates a user password, but it does not work yet.
     *
     * @param _file
     * @param _userObject
     * @param _newPassword
     * @throws FileNotFoundException
     */
    public void updateUserPassword(UserObject _userObject, String _newPassword) throws FileNotFoundException {
        Scanner scanner = new Scanner(userLoginFile);
        String userName = _userObject.getName();
        String fileContent = "";

        //This while loop looks for the matching username, and if it exists,
        //then the password will update
        while (scanner.hasNextLine()) {
            if (scanner.hasNext(userName)) {
                _userObject.setUserPassword(_newPassword);
            }
        }
    }

}
