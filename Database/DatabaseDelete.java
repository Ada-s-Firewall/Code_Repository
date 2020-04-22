package Database;

/*
 * This is a Database class for deleting records.
 *
 * Last Updated: April 20, 2020
 * @author Will Higdon, Quinn Tjin-A-Soe, Fernando Villarreal
 */
import Objects.UserObject;
import java.io.BufferedReader;
import Objects.UserObject;
import Objects.RecordObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseDelete {
    public static final String active = "true";
    public static final String inactive = "false";

    //==================== PUBLIC METHODS ====================
    /**
     * This method deletes a specified record from the database. This method
     * writes the contents of one file onto another text file except for the
     * specified record, which will be set to inactive onto the temporary text
     * file. Afterwards, the temporary text file is renamed to the original text
     * file, and the original text file is deleted.
     *
     * @param _file
     * @param _user
     * @throws java.io.FileNotFoundException
     * @throws IOException
     */
    public static void deleteUserRecord(File _file, UserObject _user) throws FileNotFoundException, IOException, Exception {
        File originalFile = _file;
        BufferedReader reader = new BufferedReader(new FileReader(originalFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.temporaryFile));
        String lineInFile = null;
        String userName = _user.getUserName();
        // While the file is not empty
        while ((lineInFile = reader.readLine()) != null) {
            // If the username is found, it will be made to inactive
            if (lineInFile.contains(userName)) {
                lineInFile = lineInFile.replace(DatabaseDelete.active, DatabaseDelete.inactive);
                writer.write(lineInFile);
                writer.newLine();
            } else {
                writer.write(lineInFile);
                writer.newLine();
            }
            writer.flush();
        }
        writer.close();
        reader.close();

        // Delete the original file
        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        // Rename the new file to the filename the original file had.
        if (!DatabaseInterface.temporaryFile.renameTo(originalFile)) {
            System.out.println("Could not rename file");
        }

        // Delete the user's information from the UserLogin.txt file
        DatabaseDelete.deleteUserLogin(_user);

    }

    /**
     * Deletes the given user from the database.
     *
     * @param _user
     * @throws Exception
     */
    public static void deleteUserRecord(UserObject _user) throws Exception {
        DatabaseDelete.deleteUserRecord(DatabaseInterface.userInfoFile, _user);
    }

    /**
     * Deletes the user associated with the given username from the database.
     *
     * @param _username
     * @throws java.io.IOException
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
     *
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
