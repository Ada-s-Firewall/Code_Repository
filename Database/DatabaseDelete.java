package Database;

/*
 * This is a Database class for deleting records.
 *
 * Last Updated: April 13, 2020
 * @author Will Higdon, Quinn Tjin-A-Soe
 */
import Objects.UserObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseDelete {

    /**
     * This method deletes a specified record from the database. This method
     * scans the entire file for the specified record. If it exists, then it
     * will set it to "inactive" and overwrite the previous file.
     *
     * @param _file
     * @param _user
     * @throws IOException
     */
    public static void deleteUserRecord(File _file, UserObject _user) throws IOException, Exception {
        Scanner scanner = new Scanner(_file);
        String userName = _user.getUserName();
        ArrayList<String> arrayRecord = new ArrayList<>();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        while (scanner.hasNextLine()) {
            if (scanner.hasNext(userName) == true) {
                stringRecord += _user.getUserName() + "\t";
                stringRecord += _user.getUserPassword() + "\t";
                stringRecord += _user.getUserEmail() + "\t";
                stringRecord += _user.getName() + "\t";
                stringRecord += _user.getUserLastName() + "\t";
                stringRecord += DatabaseInterface.inactive + "\n";
            } else {
                stringRecord += scanner.nextLine() + "\n";
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(_file, false));
        writer.write(stringRecord);
        writer.close();
    }
}
