/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package readfolder;
import java.io.*;
import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
/**
 *
 * @author Krishna Ghatia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try{
            int noOfFile = 10000, charRead;
            FileWriter fstream;
            BufferedWriter out;
            char c;
            int index=0, follen=0, val;
            Scanner in;
            String oldPath = "E:\\Blah";
            String newPath = "E:\\Blah1";
            String filePath;
            String newFilePath;
            String str, str1, torep, byrep;
            final File folder = new File(oldPath);
            final File newFolder= new File(newPath);
            newFolder.mkdir();
            String[] musicFile = new String[noOfFile];
            follen = listFileForFolder(folder, musicFile);
            //System.out.println(follen);
            while(index < follen){
                filePath = oldPath + "/" + musicFile[index];
                newFilePath = newPath + "/" + musicFile[index];
                /*File file = new File(filePath);
                File newFile = new File(newFilePath);
                FileReader fr = new FileReader(file);
                fstream = new FileWriter(newFile);
                out = new BufferedWriter(fstream);*/
                File fileR = new File(filePath); // pass location through parameter
                //in = new Scanner(fileR);
                //System.out.println("Blah");
                /*while (in.hasNextLine()) {
                    str = in.nextLine().toLowerCase();
                }*/
                File newFile = new File(newFilePath); //Creates a new File
                FileReader fr = new FileReader(fileR);//Reads the contents of a file
                char[] a = new char[1400];
                fr.read(a); //reads the contents to the array
                for(char ch : a)
                    System.out.print(ch); //prints each character for output here
                fr.close();

                /*Writing into the files*/

                //File fileW = new File("E:\\Blah\\Temp1.txt");
                newFile.createNewFile();
                FileWriter writer = new FileWriter(newFile);
                writer.write(a);
                writer.flush();
                writer.close();

                Scanner reader;
                reader = new Scanner(new FileReader(newFilePath));
                while(reader.hasNext())
                {
                    reader.nextLine().toLowerCase();
                }

                /*File file = new File("E:\\Blah\\Avirek2.txt");

                try {

                    Scanner sc = new Scanner(file);

                    while (sc.hasNext()) {
                        String a = sc.next();
                    }
                    sc.close();
                }
                catch (FileNotFoundException e) {
                }*/
        /*
                //Now we write it into a temporary file to make changes in it
                File fileW = new File("E:\\Blah\\Temp1.txt");
                fileW.createNewFile();
                FileWriter writer = new FileWriter(fileW);
                writer.write(a);
                writer.flush();
                writer.close();*/
                System.out.println("ZhingalaHOHHOHOHOHO!!");
                index ++;
                System.out.println("Zhingala!!");
                
                /*in = new Scanner(fileR);
                while (in.hasNextLine()) {
                    str = in.nextLine().toLowerCase();
        }*/
        }

    } catch (IOException ex) {
        System.out.println("Exception");
    }
    }



public static int listFileForFolder(final File folder, String[] str)
     {
         int i = 0;
         for(final File fileEntry : folder.listFiles())
         {
             str[i] = fileEntry.getName();
             i++;
         }
         return i;
     }
}
