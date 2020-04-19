package Database;

/*
 * This is a Database class for deleting records.
 *
 * Last Updated: April 19, 2020
 * @author Will Higdon, Quinn Tjin-A-Soe, Fernando Villarreal
 */
import Objects.UserObject;
import Objects.RecordObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseDelete {

    //==================== PUBLIC METHODS ====================

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
            String nextLine = scanner.nextLine();
            if (nextLine.contains(userName) && nextLine.contains("" + DatabaseInterface.active)) {
                stringRecord += _user.getUuid() + "\t";
                stringRecord += _user.getUserName() + "\t";
                stringRecord += _user.getUserPassword() + "\t";
                stringRecord += _user.getUserEmail() + "\t";
                stringRecord += _user.getUserFirstName() + "\t";
                stringRecord += _user.getUserLastName() + "\t";
                stringRecord += DatabaseInterface.inactive + "\n";
            } else {
                stringRecord += nextLine + "\n";
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(_file, false));
        writer.write(stringRecord);
        writer.close();
        // Delete the user's information from the UserLogin.txt file
        DatabaseDelete.deleteUserLogin(_user);
    }

    /**
     * Deletes the given user from the database.
     * @param _user
     * @throws Exception
     */
    public static void deleteUserRecord(UserObject _user) throws Exception {
        DatabaseDelete.deleteUserRecord(DatabaseInterface.userInfoFile, _user);
    }

    /**
     * Deletes the user associated with the given username from the database.
     * @param _username
     * @throws Exception
     */
    public static void deleteUserRecord(String _username) throws IOException, Exception {
        // Load the UserObject using the given username
        UserObject user = DatabaseRead.readUserRecord(_username);
        // Delete the user from the database
        DatabaseDelete.deleteUserRecord(DatabaseInterface.userInfoFile, user);
    }

    //==================== PRIVATE METHODS ====================

    /**
     * Deletes a user's login information from the UserLogin.txt file.
     * @param _user
     * @throws Exception
     */
    private static void deleteUserLogin(UserObject _user) throws Exception {
        // Create a scanner for the UserLogin.txt file
        Scanner scanner = new Scanner(DatabaseInterface.userLoginFile);
        // Create an empty string to append all of the contents of the file
        String recordsString = "";
        String username = _user.getUserName();
        // Iterate over the file to find a record with the given username
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            // If the record contains the username and is active, overwrite the record as inactive.
            if (nextLine.contains(username) && nextLine.contains("" + DatabaseInterface.active)) {
                recordsString += username + "\t" + _user.getUserPassword() + "\t";
                recordsString += DatabaseInterface.inactive + "\n";
            // If the record does not contain the username, append it to recordsString without overwriting.
            } else {
                recordsString += nextLine + "\n";
            }
        }
        // Write the recordsString to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.userLoginFile, false));
        writer.write(recordsString);
        writer.close();
    }
}
