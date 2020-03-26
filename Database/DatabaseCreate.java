package Database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Quinn Tjin-A-Soe
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

}
