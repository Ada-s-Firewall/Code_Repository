package Database;
/*
 * This Database class returns records.
 * This class uses scanners and reads from a textfile.
 */

/**
 * Last Updated: 03.30.2020
 *
 * @author Quinn Tjin-A-Soe
 */
import Objects.RecordObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseRead {

    /**
     * This method returns a specific record from the database.
     *
     * @param _file
     * @param _string
     * @return
     * @throws IOException
     */
    public static ArrayList readRecord(File _file, String _string) throws IOException {
        ArrayList<String> record = new ArrayList<>();
        Scanner scanner = new Scanner(_file);

        while (scanner.hasNextLine()) {
            if (scanner.nextLine().equals(_string)) {
                break;
            }
        }
        String stringRecord = scanner.nextLine();
        Scanner scannerRecord = new Scanner(stringRecord);
        scannerRecord.useDelimiter("\t");

        while (scannerRecord.hasNext()) {
            record.add(scannerRecord.next());
        }

        return record;
    }

}
