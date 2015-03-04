
package tokenizer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
//import Java.util.regex;
/**
 *
 * @author Krishna Ghatia
 */
public class Parse {
    private static Object folder;
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
        String token = "", token1 = "";
        String wordlist[] = new String[10000];

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
            token = "";

                newFilePath = newPath + "/" + musicFile[index];
               title = musicFile[index];
               title  = ConvertForm.convertString(title);
               System.out.println(title);
                File file = new File(newFilePath);
               wordindex = 0;
               in = new Scanner(file);
               val = 1;/*
               while(val == 1)
               {
                   token = in.nextLine();
                   System.out.println(token);
                   if(token.equalsIgnoreCase("begin .entry-content"))
                       val =0;

               }
*/
               val =1;
               while(in.hasNextLine() && val == 1)
               {
                   token = in.nextLine();
                   if(token.indexOf("begin .entry-content")!=-1)
                       val =0;
               }
            System.out.println(token);
token = "";
               while(in.hasNextLine())
               {/*
                   if(in.hasNext("begin"))
                   {
                       in.nextLine();
                   }
                   while(in.hasNext("write")==false)
                   {
                       token = token + " " + in.nextLine();
                   }
                   while(in.hasNext("begin")!=true)
                   {
                       in.nextLine();
                  }*/
                   in.nextLine();
                   token = token + " " + in.nextLine();
          //Avi         System.out.println(token);
               }

     
              // token = "";

               token = token + " ";
          //     System.out.println(token);
               //st.index
               token = token.replace('.',' ');
               token = token.replace('(',' ');
               token = token.replace(')',' ');
               token.replace('-',' ');
             //  System.out.println(token);
               token = token.replaceAll(","," ");
               token = token.replaceAll("  "," ");
    //           conn = DriverManager.getConnection("com.mysql.jdbc.Driver","", "");
      //      Statement stmt = conn.createStatement();



//Avi               System.out.println(token);
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
                  }
            }//end of else
            }//end of while
               val = wordindex;
                while(val>0)
               {
//Avi                   System.out.println(wordlist[--val]+" " + val);
               }

               List list = Arrays.asList(wordlist);

               //CHECKING POSITION OF WORD IN THE LYRIC TITLE
                title = title.replace('-',' ');
                title = title.replaceAll(".txt","");
 //Avi               System.out.println(title);
                title = title + " ";
                intIndex = 0;
                pos = 0;
               while(pos!=-1){
               pos = title.indexOf(" ", intIndex);
              // System.out.println("position" + pos);
               if(pos == intIndex)
                   intIndex++;
               else
               {
                  if(pos!=-1){
                      word = title.substring(intIndex,pos);
                //      System.out.println("m here");
  //                    String sql = "INSERT INTO WordList " +"VALUES (WordId, word, //length)";
  //                     wordlist[wordindex++] = word ;

              System.out.println(word);
              val = list.indexOf(word);
                      System.out.println(val);
                wordPos[val][0] = 1;
               title = title.replaceAll(word+" ", " ");
//               wordId++;
                  }
            }//end of else
            }//end of while

            





               // CHECKING POSITION OF WORD IN FIRST 10 LINES OF LYRIC FILE
               val  = 10;
               in = new Scanner(file);
               while(val >0 && in.hasNextLine())
               {
                   token = token + " " + in.nextLine();
                   val --;
               }
               token = token + " ";
               token1 = token1+ " ";
               while(in.hasNextLine())
               {
                   token1 = token1+ " "+ in.hasNextLine();
               }
               token1 = token1+" ";
               for(val = 0; val<wordindex;val++)
               {
                    if(token.indexOf(wordlist[val])!=-1)
                    {
                        wordPos[val][1] = 1;
                    }
                    if(token1.indexOf(wordlist[val])!=-1)
                   {
                        wordPos[val][2] = 1;
                    }
               }
//createIndex(newPath);
               title = musicFile[index];
               createIndex(wordlist, title, newFilePath, wordPos);
               index++;

        }

    }






public static void createIndex(String[] wordlist, int wordindex, String filename, String filepath, int[][] wordpos) throws SQLException
    {
    Statement stmt = null;
    Connection conn = null;
        String sql = null;
    


        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= (Connection )DriverManager.getConnection("jdbc:mysql://localhost:3306/major","root","");



           int indexF = 0;
           String word ="";
           String LyricFilePath;
           Statement sttmt = null;
           Statement statement = null;
           ResultSet rs =null;
           int wordLength=0;
           int indexW=0;
           


               sql = "insert into  FILELINK" +
                         "(fid, fname, location)" +
                         "values(indexF, filename, filepath) ";


               while(indexW < wordindex)                //untill all the words are not traversed
               {
                   String Word = wordlist[wordindex];
                   wordLength = word.length();
                   String query = "select wid, word from WORDLIST";     //to check if the word already exists
                   sttmt = con.createStatement();
                   rs = sttmt.executeQuery(query);
                   while(rs.next())
                   {
                        String wrd = rs.getString("word");
                        if (wrd.equalsIgnoreCase(word))        //already present in wordlist
                        {
                            int wid = rs.getInt("wid");         //we have the wid of already existing word
                        }
                        else
                        {
                            rs.last();
                                int wid = rs.getInt("wid");
                            if(wrd.equalsIgnoreCase(word))
                            {
                                wid = rs.getInt("wid");
                            }
                            else
                            {
                                wid = wid + 1;                  //id next to that of the last word
                                statement=con.createStatement();
                                String sql1 = "insert into  WORDLIST" +
                                                "(wid, word, length)" +
                                                "values(wid, word, wordLength)";
                                statement.executeUpdate(sql1);
                            }
                        }
                    }
                    stmt = con.createStatement();




                    if(wordpos[][0]==1)
                    {
                        String sql2 = "insert into  INDEXFILE" +
                                                "(wid, fid, in_title, in_verse, other)" +
                                                "values(wid, fid, 1, 0, 0)";
                                                stmt.executeUpdate(sql2);
                        if(wordpos[][1]==1)
                        {
                            String sql3 = "insert into  INDEXFILE" +
                                                "(wid, fid, in_title, in_verse, other)" +
                                                "values(wid, fid, 1, 1, 0)";
                            stmt.executeUpdate(sql3);
                        }
                        if(wordpos[][2]==1)
                        {
                            String sql4 = "insert into  INDEXFILE" +
                                                "(wid, fid, in_title, in_verse, other)" +
                                                "values(wid, fid, 1, 1, 1)";
                            stmt.executeUpdate(sql4);
                        }
                    }
                    else
                    {
                        if(wordpos[][1]==1)
                        {
                            String sql3 = "insert into  INDEXFILE" +
                                                "(wid, fid, in_title, in_verse, other)" +
                                                "values(wid, fid, 0, 1, 0)";
                            stmt.executeUpdate(sql3);
                        }
                        if(wordpos[][2]==1)
                        {
                            String sql4 = "insert into  INDEXFILE" +
                                                "(wid, fid, in_title, in_verse, other)" +
                                                "values(wid, fid, 0, 1, 1)";
                            stmt.executeUpdate(sql4);
                        }
                    }
                }
                indexF++;

        }catch(Exception e){
        e.printStackTrace();
        }
}


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        //SimpleStreamTokenizer in;
tokenize("C:\\Users\\Krishna Ghatia\\Desktop\\test");
  //createIndex();

    }

}



