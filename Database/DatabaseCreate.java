package Database;

import Objects.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds the methods for creating a record in the database.
 * Last updated: 04.09.2020
 * @author Quinn Tjin-A-Soe, Fernando Villarreal
 */
public class DatabaseCreate {

    /**
     * This method creates a record that is stored in the database. It takes an
     * ArrayList of Strings and writes it onto a text file.
     *
     * @param _file
     * @param _record
     * @throws java.io.IOException
     */
    public static void createRecord(File _file, ArrayList<String> _record) throws IOException {
        String stringRecord = "";
        int recordLength = _record.size();

        for (int index = 0; index < recordLength; index++) {
            //add every thing from the arraylist to the string
            stringRecord += _record.get(index);
            //tab over each time
            if (index < (recordLength - 1)) {
                stringRecord += "\t";
            }
        }

        stringRecord += "\t" + DatabaseInterface.active + "\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter(_file, true));
        writer.write(stringRecord);
        writer.close();

    }

    /**
     * This method takes a userObject and writes its information onto the
     * database.
     *
     * @param _file
     * @param _userObject
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void createUserRecord(UserObject _userObject) throws FileNotFoundException, IOException {
        String stringRecord = "";
        ArrayList<String> record = new ArrayList<>();
        record.add(_userObject.getUuid());
        record.add(_userObject.getName());
        record.add(_userObject.getUserPassword());
        int recordLength = record.size();

        for (int index = 0; index < recordLength; index++) {
            //add every thing from the arraylist to the string
            stringRecord += record.get(index);
            //tab over each time
            if (index < (recordLength - 1)) {
                stringRecord += "\t";
            }
        }

        stringRecord += "\t" + DatabaseInterface.active + "\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter(DatabaseInterface.userLoginFile, true));
        writer.write(stringRecord);
        writer.close();
    }

    /**
     * Creates records for a new user in UserLogin.txt and UserInfo.txt
     * @param _user
     * @throws Exception
     */
    public static void createUserRecord(NewUser _user) throws Exception {
        // Create a record in UserInfo.txt
        DatabaseCreate.createRecord(DatabaseInterface.userInfoFile, _user.toArrayList());
        // Create a record in UserLogin.txt
        ArrayList<String> userLogin = new ArrayList<>();
        userLogin.add(_user.getUsername());
        userLogin.add(_user.getPassword());
        DatabaseCreate.createRecord(DatabaseInterface.userLoginFile, userLogin);
    }

    public static void createUserRating(NewRating _rating) throws Exception{
        // Create a record in UserRating.txt
        DatabaseCreate.createRecord(DatabaseInterface.userRatingFile, _rating.toArrayList());
    }
}
