package Database;

/*
 * This is a Database class for deleting records.
 *
 * Last Updated: April 20, 2020
 * @author Will Higdon, Quinn Tjin-A-Soe
 */
import Objects.UserObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseDelete {

    public static final String ACTIVE = "true";
    public static final String INACTIVE = "false";
    final static int USERINFORECORDLENGTH = 6;
    public static final File TEMPORARYFILE = new File("tempfile.txt");

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
    public static void deleteUserRecord(File _file, UserObject _user) throws FileNotFoundException, IOException {
        File originalFile = _file;
        BufferedReader reader = new BufferedReader(new FileReader(originalFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(TEMPORARYFILE));
        String lineInFile = null;
        String userName = _user.getUserName();

        // Read from the original file and write to the new
        // unless content matches data to be removed.
        while ((lineInFile = reader.readLine()) != null) {

            if (lineInFile.contains(userName)) {
                lineInFile = lineInFile.replace(ACTIVE, INACTIVE);
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
        if (!TEMPORARYFILE.renameTo(originalFile)) {
            System.out.println("Could not rename file");
        }
    }
}
