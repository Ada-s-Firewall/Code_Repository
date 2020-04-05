package Database;

/*
 * This is a Database class for deleting records.
 *
 * Last Updated: April 5, 2020
 * @author Will Higdon, Quinn Tjin-A-Soe
 */
import Objects.UserObject;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseDelete {

    public static void deleteUserRecord(File _file, UserObject _userObject) throws IOException {
        Scanner scanner = new Scanner(_file);
        String userName = _userObject.getName();
        while (scanner.hasNextLine()) {
            if (scanner.nextLine().equals(userName)) {
                _userObject.makeInactive();
            }
        }
    }
}
