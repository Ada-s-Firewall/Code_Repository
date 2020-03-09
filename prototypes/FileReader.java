package prototypes;

/*
 * Prototype for reading and writing files
 * @author Quinn Tjin-A-Soe
 * Last Updated: 03.09.2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
    private static String textFile = "C:\\Users\\Halie Pearson\\Documents\\NetBeansProjects\\AdasFirewall\\src\\prototypes\\SampleText";
    private static String newText = "C:\\Users\\Halie Pearson\\Documents\\NetBeansProjects\\AdasFirewall\\src\\prototypes\\newFile";
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Reading from the text file
        File file = new File(textFile);
        Scanner scan = new Scanner(file);
        String fileContent = "ASDF";

        /**
         * writes from new text file onto String
         */
        while(scan.hasNextLine()){
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }
        /**
         * writes onto new text file
         * creates new text file
         */
        FileWriter writer = new FileWriter(newText);
        writer.write(fileContent);
        writer.close();
    }
}
