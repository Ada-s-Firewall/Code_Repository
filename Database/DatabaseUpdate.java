package Database;

/**
 * This class holds all of the methods for updating a file.
 *
 * Last Updated: 04.28.2020.
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

    /**
     * This method will update a user's music list and their ratings
     *

     * @param _userRatingFile
     * @param _ratingObject
     * @param _newUsersRating
     * @throws FileNotFoundException
     * @throws IOExeption
     */
    public void updateUsersRating(File _userRatingFile, RatingObject _ratingObject, String _newUsersRating) throws Exception {
        Scanner scanner = new Scanner(userRatingFile);
        String userName = _ratingObject.getName();
        String usersRating = _ratingObject.getUsersRating();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        //This will update the user's rating
        _ratingObject.setUsersRating(_newUsersRating);

        while (scanner.hasNext()) {
            //If this record exists in the file, then the rating will be updated.
            if (scanner.hasNext(userName)) {
                stringRecord += _ratingObject.getUuid() + "\t";
                stringRecord += _ratingObject.getName() + "\t";
                stringRecord += _ratingObject.getUsersRating() + "\t";
                stringRecord += DatabaseInterface.active + "\n";
            }
            if (scanner.hasNext("\n")) {
                stringRecord += "\n";
            } //This else statement adds the rest of the records that do not match.
            else {
                stringRecord += scanner.next() + "\t";
            }
        }
        //This second parameter is now true to overwrite the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.userRatingFile, true));
        writer.write(stringRecord);
        writer.close();
    }

    /**
     * This method updates the user's list of albums they've completed or plan
     * to listen
     *
     * @param _userPlanToListenFile
     * @param _userMusicList
     * @param _newUsersAlbumsCompleted
     * @param _newUsersAlbumsPlanToListen
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void updateUsersMusicList(File _userPlanToListenFile, UserMusicList _userMusicList, int _newUsersAlbumsCompleted, int _newUsersAlbumsPlanToListen) throws Exception {
        Scanner scanner = new Scanner(_userPlanToListenFile);
        String userName = _userMusicList.getUserName();
        //int usersAlbumsCompleted = _userMusicList.getUsersAlbumsCompleted();
        //int usersAlbumsPlanToListen = _userMusicList.getUsersAlbumsPlanToListen();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        //Updates the number of albums completed in the user's music list
        //_userMusicList.setUsersAlbumsCompleted(_newUsersAlbumsCompleted);
        //Updates the number of albums that the user has in their plan to listen list
        //_userMusicList.setUsersAlbumsPlanToListen(_newUsersAlbumsPlanToListen);

        while (scanner.hasNext()) {
            //If this record exists in the file, then the lists will be updated.
            if (scanner.hasNext(userName)) {

                stringRecord += _userMusicList.getUserName() + "\t";
                stringRecord += _userMusicList.getUsersAlbumsCompleted() + "\t";
                stringRecord += _userMusicList.getUsersAlbumsPlanToListen() + "\t";
                stringRecord += DatabaseInterface.active + "\n";
            }
            if (scanner.hasNext("\n")) {
                stringRecord += "\n";
            } //This else statement adds the rest of the records that do not match.
            else {
                stringRecord += scanner.next() + "\t";
            }
        }
    }
     /*
     * @param _user
     * @param _newUsersPlanToListen
     */
    public static void updateUsersPlanToListenList(UserMusicList _user, ArrayList<String> _newUsersPlanToListen) {
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
    public static void updateUsersRating(RatingObject _user, double _newUsersRating) {
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
    private static void updateUsersPlanToListenList(File _file, UserMusicList _user, ArrayList<String> _newUsersPlanToListen) throws FileNotFoundException, IOException {
        //old plan to listen list to be updated
        ArrayList<String> oldUsersPlanToListen = _user.getUsersPlanToListen();

        //string builder for the arraylists
        StringBuilder sb = new StringBuilder();
        for (String s : oldUsersPlanToListen) {
            sb.append(s);
            sb.append(" ");
        }
        for (String k : _newUsersPlanToListen) {
            sb.append(k);
            sb.append(" ");
        }
        //converts to strings
        String _stringOldUsersPlanToListen = sb.toString();
        String _stringNewUsersPlanToListen = sb.toString();

        //updates the record in the file
        DatabaseUpdate.updateFile(_file, _stringOldUsersPlanToListen, _stringNewUsersPlanToListen);
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
    private static void updateUsersRating(File _file, RatingObject _user, double _newUsersRating) throws FileNotFoundException, IOException {
        //the old rating that will be updated
        double oldUsersRating = _user.getUsersRating();

        //converts the double ratings to strings for the UpdateFile method
        String _stringOldUsersRating = Double.toString(oldUsersRating);
        String _stringNewUsersRating = Double.toString(_newUsersRating);

        //updates the user's rating
        DatabaseUpdate.updateFile(_file, _stringOldUsersRating, _stringNewUsersRating);
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
}

