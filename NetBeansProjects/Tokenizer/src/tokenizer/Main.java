/* http://www.tutorialspoint.com/jdbc/jdbc-create-tables.htm
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tokenizer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
//import Java.util.regex;
/**
 *
 * @author Krishna Ghatia
 */
public class Main {
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


public static void tokenize(String newPath) throws FileNotFoundException, SQLException
    {

        //String newPath = "E:\\Blah1";
        String token = "";
        String wordlist[] = new String[1000];
        String word = "";
        String title = "";
        int index=0, follen=0, val, pos,intIndex, wordindex = 0;
        int wordPos[][] = new int[1000][3];
        int noOfFile = 10000,x;
        String newFilePath;
        Scanner in;
        StringTokenizer st ;
        final File newFolder= new File(newPath);
        String[] musicFile = new String[noOfFile];
        follen = listFileForFolder(newFolder, musicFile);
        Connection conn;
        while(index < follen){
                newFilePath = newPath + "/" + musicFile[index];
               title = musicFile[index];
               System.out.println(title);
                File file = new File(newFilePath);
               wordindex = 0;
               in = new Scanner(file);
               while(in.hasNextLine())
               {
                   token = token + " " + in.nextLine();

               }
               token = token + " ";
               System.out.println(token);
               //st.index
               token = token.replace('.',' ');
               System.out.println(token);
               token = token.replaceAll(","," ");
               token = token.replaceAll("  "," ");
    //           conn = DriverManager.getConnection("com.mysql.jdbc.Driver","", "");
      //      Statement stmt = conn.createStatement();



               System.out.println(token);
               intIndex = 0;
               int wordId=0;
               val = 20;
               pos = 0;
               while(pos!=-1){
               pos = token.indexOf(" ", intIndex);
              // System.out.println("position" + pos);
               if(pos == intIndex)
                   intIndex++;
               else
               {
                  if(pos!=-1){
                      word = token.substring(intIndex,pos);
  //                    String sql = "INSERT INTO WordList " +"VALUES (WordId, word, //length)";
                       wordlist[wordindex++] = word ;

//               System.out.println(word);
               token = token.replaceAll(" "+word+" ", " ");
               wordId++;   }
            }//end of else
            }//end of while
               /* while(wordindex>0)
               {
                   System.out.println(wordlist[--wordindex]);
               }*/

               //CHECKING POSITION OF WORD IN THE LYRIC TITLE






               // CHECKING POSITION OF WORD IN FIRST 10 LINES OF LYRIC FILE
               val  = 10;
               in = new Scanner(file);
               while(val >0 && in.hasNextLine())
               {
                   token = token + " " + in.nextLine();
                   val --;
               }
               token = token + " ";





               index++;

        }
        
    }





public static void createIndex() 
    {
    /*
     * 1. For every word, make an entry in the table 'fileLink'
     * 2. store the fid
     * 3. Search the word in the wordlist
     * 4. If found, note wid
     * 5. search the wid in indexfile
     *

     */


        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= (Connection )DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            PreparedStatement ps = con.prepareStatement("insert into emp values (3)");
            ps.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
}


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        //SimpleStreamTokenizer in;
//tokenize("E:\\Blah1");
//  createIndex();
  
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= (Connection )DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            PreparedStatement ps = con.prepareStatement("insert into emp values (3)");
            ps.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
    }
    }



