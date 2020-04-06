package Database;

import static Database.DatabaseInterface.userLoginFile;
import static Database.DatabaseRead.readRecord;
import Objects.UserObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds the methods for creating a record in the database.
 *
 * @author Quinn Tjin-A-Soe Last updated: 04.05.2020
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
    public static void creatRecord(File _file, ArrayList _record) throws IOException {
        String stringRecord = "\n";
        int recordLength = _record.size();

        for (int index = 0; index < recordLength; index++) {
            //add every thing from the arraylist to the string
            stringRecord += _record.get(index);
            //tab over each time
            if (index < (recordLength - 1)) {
                stringRecord += "\t";
            }
        }
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
        int recordLength = record.size();
        record.add(_userObject.getUuid());
        record.add(_userObject.getName());
        record.add(_userObject.getUserPassword());

        for (int index = 0; index < recordLength; index++) {
            //add every thing from the arraylist to the string
            stringRecord += record.get(index);
            //tab over each time
            if (index < (recordLength - 1)) {
                stringRecord += "\t";
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(userLoginFile, true));
        writer.write(stringRecord);
        writer.close();
    }

}
