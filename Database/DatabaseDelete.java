package Database;

/*
 * This is a Database class for deleting records.
 *
 * Last Updated: April 20, 2020
 * @author Will Higdon, Quinn Tjin-A-Soe, Fernando Villarreal
 */
import Objects.PlanToListenObject;
import Objects.RatingObject;
import java.io.BufferedReader;
import Objects.UserObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseDelete {

    private static final String active = "true";
    private static final String inactive = "false";

    //=========================== PUBLIC METHODS ===============================
    /**
     * This method deletes a record in the PlanToListen File.
     *
     * @param _planToListen
     */
    public static void deletePlanToListen(PlanToListenObject _planToListen) {
        // Find the username in the file
        String username = _planToListen.getUsername();

        // Delete the user's record in userPlanToListen
        try {
            deleteRecord(DatabaseInterface.userPlanToListen, username);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method deletes a record in the UserRating File.
     *
     * @param _rating
     */
    public static void deleteRating(RatingObject _rating) {
        // Find the username in the file
        String username = _rating.getUsername();

        // Delete the user's record in userRatingFile
        try {
            deleteRecord(DatabaseInterface.userRatingFile, username);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method deletes a specified record from the database.
     *
     * @param _file
     * @param _user
     * @throws java.io.FileNotFoundException
     * @throws IOException
     */
    public static void deleteUserRecord(File _file, UserObject _user) throws FileNotFoundException, IOException, Exception {
        // Find the username in the file
        String username = _user.getUserName();

        // Delete the user's record in userInfoFile
        try {
            deleteRecord(DatabaseInterface.userInfoFile, username);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseDelete.class.getName()).log(Level.SEVERE, null, ex);
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

    //========================== PRIVATE METHODS ==============================
    /**
     * This method is the logic behind deleting any record in any file.
     *
     * @param _file
     * @param _record
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void deleteRecord(File _file, String _record) throws FileNotFoundException, IOException {
        File originalFile = _file;
        BufferedReader reader = new BufferedReader(new FileReader(originalFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.temporaryFile));
        String lineInFile = null;
        while ((lineInFile = reader.readLine()) != null) {
            // If the username is found, it will be made to inactive
            if (lineInFile.contains(_record)) {
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
    }

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
