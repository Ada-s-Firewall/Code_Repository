package Database;

/**
 * This class holds all of the methods for updating a file.
 *
 * Last Updated: 04.21.2020.
 *
 * @authors Quinn Tjin-A-Soe, Will Higdon
 */
import static Database.DatabaseInterface.userInfoFile;
import Objects.UserObject;
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
     * This method updates a user's rating
     *
     * @param _userRatingFile
     * @param _ratingObject
     * @param _newUsersRating
     * @throws FileNotFoundException
     * @throws IOExeption
     */
    public void updateUsersRating(File _userRatingFile, RatingObject _ratingObject, double _newUsersRating) throws Exception {
        Scanner scanner = new Scanner(_userRatingFile);
        String userName = _ratingObject.getName();
        double usersRating = _ratingObject.getUsersRating();
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
     */
    public void updateUsersMusicList(File _userPlanToListenFile, UserMusicList _userMusicList, int _newUsersAlbumsCompleted, int _newUsersAlbumsPlanToListen) throws FileNotFoundException {
        Scanner scanner = new Scanner(userInfoFile);
        String userName = _userMusicList.getUserName();
        int usersAlbumsCompleted = _userMusicList.getUsersAlbumsCompleted();
        int usersAlbumsPlanToListen = _userMusicList.getUsersAlbumsPlanToListen();

        //Updates the users number of albums completed in their music list
        _userMusicList.setUsersAlbumsCompleted(_newUsersAlbumsCompleted);
        //Updates the user's number of albums that they plan to listen in their music list
        _userMusicList.setUsersAlbumsPlanToListen(_newUsersAlbumsPlanToListen);
    }

    /**
     * This method updates a user's list of favorite albums, artists, and tracks
     *
     * @param _file
     * @param _userMusicList
     * @param _newUsersFavoriteAlbums
     * @param _newUsersFavoriteArtists
     * @param _newUsersFavoriteTracks
     * @throws FileNotFoundException
     */
    public void updateUsersMusicList(UserMusicList _userMusicList, ArrayList<String> _newUsersFavoriteAlbums, ArrayList<String> _newUsersFavoriteArtists, ArrayList<String> _newUsersFavoriteTracks) throws FileNotFoundException {
        Scanner scanner = new Scanner(userInfoFile);
        String userName = _userMusicList.getUserName();
        ArrayList<String> usersFavoriteAlbums = _userMusicList.getUsersFavoriteAlbums();
        ArrayList<String> usersFavoriteArtists = _userMusicList.getUsersFavoriteArtists();
        ArrayList<String> usersFavoriteTracks = _userMusicList.getUsersFavoriteTracks();
        String stringRecord = "";
        scanner.useDelimiter("\t");

        //Adds a selected album to the user's list of favorite albums
        _userMusicList.setUsersFavoriteAlbums(_newUsersFavoriteAlbums);
        //Adds a selected artist to the user's list of favorite artists
        _userMusicList.setUsersFavoriteArtists(_newUsersFavoriteArtists);
        //Adds a selected track to the user's list of favorite tracks
        _userMusicList.setUsersFavoriteTracks(_newUsersFavoriteTracks);

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

        // Update userObject password
        //_user.setUserPassword(_newPassword);

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

        // Set the object new username
        //_user.setUserName(_newUsername);

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

        // Set object new user email
        //_user.setUserEmail(_newUserEmail);

        // Update the record in the file
        DatabaseUpdate.updateFile(_file, oldUserEmail, _newUserEmail);
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
