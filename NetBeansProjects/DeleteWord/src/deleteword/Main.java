/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deleteword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Krishna Ghatia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File inputFile = new File("E:\\Blah\\Avirek3.txt");
   File tempFile = new File("E:\\Blah\\TempWordlist.txt");

   BufferedReader reader = new BufferedReader(new FileReader(inputFile));
   BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

   String lineToRemove = JOptionPane.showInputDialog(null, "avirek");
   String currentLine;

   while((currentLine = reader.readLine()) != null)
 {
   String trimmedLine = currentLine.trim();
   if(trimmedLine.equals(lineToRemove)) continue;
   writer.write(currentLine);
 }
   boolean successful = tempFile.renameTo(inputFile);
    }

}
