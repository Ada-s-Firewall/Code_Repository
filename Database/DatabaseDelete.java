package Database;

/*
 * This is a Database class for deleting records.
 *
 * Last Updated: April 13, 2020
 * @author Will Higdon, Quinn Tjin-A-Soe
 */
import static Database.DatabaseInterface.userLoginFile;
import Objects.UserObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseDelete {

    /**
     * This method deletes a specified record from the database. This method
     * scans the entire file for the specified record. If it exists, then it
     * will set it to "inactive" and overwrite the previous file.
     *
     * @param _userObject
     * @throws IOException
     */
    public static void deleteUserRecord(UserObject _userObject) throws IOException {
        Scanner scanner = new Scanner(userLoginFile);
        String userName = _userObject.getName();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        while (scanner.hasNext()) {
            //If this record exists in the file, then it will be made inactive.
            if (scanner.hasNext(userName)) {
                stringRecord += _userObject.getUuid() + "\t";
                stringRecord += _userObject.getName() + "\t";
                stringRecord += _userObject.getUserPassword() + "\t";
                stringRecord += "\t" + DatabaseInterface.inactive + "\n";
            }
            if (scanner.hasNext("\n")) {
                stringRecord += "\n";
            } //This else statement adds the rest of the records that do not match.
            else {
                stringRecord += scanner.next() + "\t";
            }
        }
        //This second parameter is made to be false in order to overwrite the file.
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.userLoginFile, false));
        writer.write(stringRecord);
        writer.close();
    }
}
