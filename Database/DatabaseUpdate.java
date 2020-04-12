package Database;

/**
 * This class holds all of the methods for updating a file.
 *
 * Last Updated: 04.12.2020.
 *
 * @authors Quinn Tjin-A-Soe, Will Higdon
 */
import static Database.DatabaseCreate.createUserRecord;
import static Database.DatabaseCreate.createUserRating;
import static Database.DatabaseInterface.userLoginFile;
import static Database.DatabaseInterface.userInfoFile;
import static Database.DatabaseInterface.userRatingFile;
import Objects.NewRating;
import Objects.RecordObject;
import Objects.UserObject;
import Objects.RatingObject;
import Objects.UserMusicList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        //This while loop looks for the matching username, and if it exists,
        //then the password will update
        while (scanner.hasNextLine()) {
            if (scanner.hasNext(userName)) {
                _userObject.makeInactive();
                _userObject.setUserPassword(_newPassword);
                try {
                    createUserRecord(_userObject);
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * This method updates a user's username, but it does not work yet.
     *
     * @param _file
     * @param _userObject
     * @param _newUserName
     * @throws FileNotFoundException
     */
    public void updateUserName(UserObject _userObject, String _newUserName) throws FileNotFoundException {
        Scanner scanner = new Scanner(userInfoFile);
        String userName = _userObject.getName();

        while (scanner.hasNextLine()) {
            if (scanner.hasNext(userName)) {
                _userObject.makeInactive();
                _userObject.setUserName(_newUserName);
                try {
                    createUserRecord(_userObject);
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * This method updates a user's email
     *
     * @param _file
     * @param _userObject
     * @param _newUserEmail
     * @throws FileNotFoundException
     */
    public void updateUserEmail(UserObject _userObject, String _newUserEmail) throws FileNotFoundException {
        Scanner scanner = new Scanner(userInfoFile);
        String userName = _userObject.getName();

        while (scanner.hasNextLine()) {
            if (scanner.hasNext(userName)) {
                _userObject.makeInactive();
                _userObject.setUserEmail(_newUserEmail);
                try {
                    createUserRecord(_userObject);
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * This method updates a user's rating
     *
     * @param _file
     * @param _ratingObject
     * @param _newUsersRating
     * @throws FileNotFoundException
     */
    public void updateUserRating(RatingObject _ratingObject, NewRating _rating) throws FileNotFoundException {
        Scanner scanner = new Scanner(userRatingFile);
        double rating = _ratingObject.getUsersRating();

        while (scanner.hasNextLine()) {
            if (scanner.hasNextDouble() && scanner.nextDouble() == rating) {
                _ratingObject.makeInactive();
                _ratingObject.setUsersRating(rating);
                try {
                    createUserRating(_rating);
                } catch (Exception ex) {
                    Logger.getLogger(DatabaseUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * This method updates the user's list of albums they've completed or plan
     * to listen
     *
     * @param _file
     * @param _userMusicList
     * @param _newUsersAlbumsCompleted
     * @param _newUsersAlnumsPlanToListen
     * @throws FileNotFoundException
     */
    public void updateUsersMusicList(UserMusicList _userMusicList, int _newUsersAlbumsCompleted, int _newUsersAlbumsPlanToListen) throws FileNotFoundException {
        Scanner scanner = new Scanner(userInfoFile);
    }

    /**
     * This method updates a user's list of favorite albums, arists, and tracks
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
    }
}
