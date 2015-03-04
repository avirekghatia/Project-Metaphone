/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package readingfile;
import java.io.*;
import java.util.*;
/**
 *
 * @author Krishna Ghatia
 */
public class Main {


    
    // function : parameter - location of the folder

    // function : create database

    // function: indexing:(string of words and its location, file )
    /*  1. make an entry for the file in the table filelink
            1.1. note down the fid
     * loop
            2. search the words in wordlist
     *          if found note its wid
     *              search the wid in table indexfile
     *                  if wid found ,
     *                            enter the fid and location of word in the file
     *                  else not found
     *                              make an entry of wid
     *                              enter the fild and location of the word
     *
     *         else wid not found in table wordlist
     *              make an entry into wordlist for the word and note down the wid
     *              make an entry of wid in indexfile table
     *              enter the fild and location of the word
     * loop ends
     */

    public static void main(String[] args) throws IOException {
        /*File fileR = new File("E:\\Blah\\Avirek2.txt"); // pass location through parameter
        fileR.createNewFile(); //Creates a new File
        FileReader fr = new FileReader(fileR);//Reads the contents of a file
        //while(fileR.hasNext)
        char[] a = new char[1400];
        fr.read(a); //reads the contents to the array
        for(char c : a)
            System.out.print(c); //prints each character for output here
        fr.close();
*/

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
        writer.close();
*/



        /*here we start deleting the words  of value*/
        //File file = new File("E:\\Blah\\Avirek2.txt");

        try {

            //Scanner sc = new Scanner(file);
            //1. copy the conetnts of file in some temp file
            //2. lowercase the  temp file
            //3. in temp file erase unwanted data
            // 4. parse the file i.e., separate the words - store the words in array of string and also mark there location
            int noOfFile = 1000; int follen = 0;
            Scanner in;
            String oldPath = "E:/Blah";
            String newPath = "E:/Blah2";
            String filePath;
            String newFilePath;
            String str, st1, torep, byrep;
            final File folder = new File(oldPath);
            final File newfolder = new File(newPath);
            newfolder.mkdir();
            String[] musicFile = new String[noOfFile];
            follen = listFileForFolder(folder, musicFile);
            System.out.println(follen);
            while(index < follen){
                filePath = oldPath + "/" + musicFile[index];
                newFilePath = newPath + "/" + musicFile[index];
                File file = new File(filePath);
                File newFile = new File(newFilePath);
                fstream = new FileWriter(newFile);
                out = new BufferedWriter(fstream);




            while (sc.next() == null ? "BEGIN" != null : !sc.next().equals("BEGIN")) {
                String b = sc.next();
            }
        } catch()
        {

        }
    }
    }