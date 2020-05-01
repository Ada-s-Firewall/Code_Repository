package Database;

/**
 * This class holds all of the methods for updating a file.
 *
 * Last Updated: 04.30.2020.
 *
 * @authors Quinn Tjin-A-Soe, Will Higdon
 */
import static Database.DatabaseInterface.userInfoFile;
import static Database.DatabaseInterface.userPlanToListen;
import static Database.DatabaseInterface.userLoginFile;
import static Database.DatabaseInterface.userRatingFile;
import Objects.UserObject;
import Objects.PlanToListenObject;
import Objects.RatingObject;
import Objects.UserMusicList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUpdate {

    //=========================== PUBLIC METHODS ================================
    /**
     * This method updates the user's password.
     *
     * @param _user
     * @param _newPassword
     */
    public static void updateUserPassword(UserObject _user, String _newPassword) {
        try {
            // Update the user password in UserLoginFile
            DatabaseUpdate.updateUserPassword(DatabaseInterface.userLoginFile, _user, _newPassword);
            // Update the file in the UserInfoFile
            DatabaseUpdate.updateUserPassword(DatabaseInterface.userInfoFile, _user, _newPassword);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method updates the user's username.
     *
     * @param _user
     * @param _newUsername
     */
    public static void updateUsername(UserObject _user, String _newUsername) {
        try {
            // Update the file in UserLoginFile
            DatabaseUpdate.updateUsername(DatabaseInterface.userLoginFile, _user, _newUsername);
            // Update the file in the UserInfoFile
            DatabaseUpdate.updateUsername(DatabaseInterface.userInfoFile, _user, _newUsername);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method updates the user's email.
     *
     * @param _user
     * @param _newUserEmail
     */
    public static void updateUserEmail(UserObject _user, String _newUserEmail) {
        try {
            // Update the file in UserLoginFile
            DatabaseUpdate.updateUserEmail(DatabaseInterface.userLoginFile, _user, _newUserEmail);
            // Update the file in the UserInfoFile
            DatabaseUpdate.updateUserEmail(DatabaseInterface.userInfoFile, _user, _newUserEmail);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * @param _user
     * @param _newUsersPlanToListen
     */
    public static void updateUsersPlanToListenList(UserMusicList _user, String _newUsersPlanToListen) {
        //Updates the plan to listen list in the UserPlanToListen file
        try {
            DatabaseUpdate.updateUsersPlanToListenList(DatabaseInterface.userPlanToListen, _user, _newUsersPlanToListen);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * public method for updating users rating
     *
     * @param _user
     * @param _newUsersRating
     */
    public static void updateUsersRating(RatingObject _user, String _newUsersRating) {
        //updates the user's rating in the userRating file
        try {
            DatabaseUpdate.updateUsersRating(DatabaseInterface.userRatingFile, _user, _newUsersRating);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //=========================== PRIVATE METHODS ===============================

    /**
     * This method updates a user password.
     *
     * @param _user
     * @param _oldPassword
     * @param _newPassword
     * @param _file
     * @throws FileNotFoundException
     */
    private static void updateUserPassword(File _file, UserObject _user, String _newPassword) throws FileNotFoundException, IOException {
        // The old password to be replaced
        String oldPassword = _user.getUserPassword();

        // Update the record in file
        DatabaseUpdate.updateFile(_file, oldPassword, _newPassword);
    }

    /**
     * This method updates a user's username.
     *
     * @param _file
     * @param _userObject
     * @param _newUserName
     * @throws FileNotFoundException
     */
    private static void updateUsername(File _file, UserObject _user, String _newUsername) throws FileNotFoundException, IOException {
        // The old username to be replaced
        String oldUsername = _user.getUserName();

        // Update the record in file
        DatabaseUpdate.updateFile(_file, oldUsername, _newUsername);
    }

    /**
     * This method updates a user's email
     *
     * @param _file
     * @param _userObject
     * @param _newUserEmail
     * @throws FileNotFoundException
     */
    private static void updateUserEmail(File _file, UserObject _user, String _newUserEmail) throws FileNotFoundException, IOException {
        // Old user email to be replaced
        String oldUserEmail = _user.getUserEmail();

        // Update the record in the file
        DatabaseUpdate.updateFile(_file, oldUserEmail, _newUserEmail);
    }

    /**
     * private method for updating a plan to listen list
     *
     * @param _file
     * @param _user
     * @param _newUsersPlanToListen
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void updateUsersPlanToListenList(File _file, UserMusicList _user, String _newUsersPlanToListen) throws FileNotFoundException, IOException {
        //old plan to listen list to be updated
        ArrayList<String> oldUsersPlanToListen = _user.getUsersPlanToListen();

        //string builder for the arraylists
        StringBuilder sb = new StringBuilder();
        for (String s : oldUsersPlanToListen) {
            sb.append(s);
            sb.append(" ");
        }

        //converts to strings
        String _stringOldUsersPlanToListen = sb.toString();

        //updates the record in the file
        DatabaseUpdate.updateFile(_file, _stringOldUsersPlanToListen, _newUsersPlanToListen);
    }

    /**
     * private method for updating users rating
     *
     * @param _file
     * @param _user
     * @param _newUsersRating
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void updateUsersRating(File _file, RatingObject _user, String _newUsersRating) throws FileNotFoundException, IOException {
        //the old rating that will be updated
        String _oldUsersRating = _user.getUsersRating();
        //brings in variables
        String _spotifyID = _user.getSpotifyId();
        String _userName = _user.getUsername();
        String _type = _user.getMusicObjectType();
        //updates the rating in the file
        DatabaseUpdate.updateFile(_file, _spotifyID, _userName, _oldUsersRating, _newUsersRating, _type);
    }

    /**
     * This method updates the record in file.
     *
     * @param _file
     * @param _oldRecord
     * @param _newRecord
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void updateFile(File _file, String _oldRecord, String _newRecord) throws FileNotFoundException, IOException {
        File originalFile = _file;
        /* Original code below
        String path = _file.getAbsolutePath();
        File originalFile = new File(path);
        */
        BufferedReader reader = new BufferedReader(new FileReader(originalFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.temporaryFile));
        String lineInFile = null;

        // While the file is not empty
        while ((lineInFile = reader.readLine()) != null) {

            // If the username is found, it will be made to inactive
            if (lineInFile.contains(_oldRecord)) {
                lineInFile = lineInFile.replace(_oldRecord, _newRecord);
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

    private static void updateFile(File _file, String _spotifyID, String _userName, String _oldUsersRating, String _newUserRating, String _type) throws FileNotFoundException, IOException {
        //original file
        File originalFile = _file;
        //scanner for the rating
        Scanner doubleScanner = new Scanner(originalFile);
        //string to store information
        String recordsDouble = "";

        //loop to check if the line contains the variable
        while (doubleScanner.hasNextLine()) {
            String nextLine = doubleScanner.nextLine();
            // If the record contains the rating and is active, overwrite the record as inactive.
            if (nextLine.contains(_spotifyID) && nextLine.contains(_userName) && nextLine.contains(_oldUsersRating) && nextLine.contains("" + DatabaseInterface.active)) {
                recordsDouble += doubleScanner.next() + "\t";
                recordsDouble += _userName + "\t";
                recordsDouble += _newUserRating + "\t";
                recordsDouble += _spotifyID + "\t";
                recordsDouble += _type + "\t";
                recordsDouble += DatabaseInterface.active;
                // If the record does not contain the rating, append it to recordsDouble without overwriting.
            } else {
                recordsDouble += nextLine + "\n";
            }
        }

        // Write the recordsDouble to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.userRatingFile, false));
        writer.write(recordsDouble);
        writer.close();

       /* // Delete the original file
        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
        // Rename the new file to the filename the original file had.
        if (!DatabaseInterface.temporaryFile.renameTo(originalFile)) {
            System.out.println("Could not rename file");
        }
               */
    }
}

