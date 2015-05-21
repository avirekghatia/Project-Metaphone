/**
 * This class newparse2s the database and build the index
 *
 * @author Ankita Agrawal
 */
package myMetaphone;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class newparse2 {

    // retrive the name of the files from the specified folder
    
    public static int listFileForFolder(final File folder, String[] str) {
        int i = 0;
        for (final File fileEntry : folder.listFiles()) {
            str[i] = fileEntry.getName();
            i++;
        }
        return i;
    }

    
    // separate the words from the specified file
    public static void tokenize(String newPath) throws FileNotFoundException, SQLException {

        String token = "", token1 = "";
        String wordlist[] = new String[10000];

        String word = "";
        String title = "";
        int index = 0, follen = 0, val, pos, intIndex, wordindex = 0;
        int wordPos[][] = new int[1000][3];
        int noOfFile = 10000, x;
        String newFilePath;
        Scanner in;
        StringTokenizer st;

        final File newFolder = new File(newPath);
        String[] musicFile = new String[noOfFile];

        follen = listFileForFolder(newFolder, musicFile);
        Connection conn;
        while (index < follen) {
            token = "";

            newFilePath = newPath + "/" + musicFile[index];

            title = " " + musicFile[index];
            title = ConvertForm.convertString(title);
            wordindex = 0;
            File file = new File(newFilePath);

            wordindex = 0;

            in = new Scanner(file);

            val = 1;

            //GETTING LYRICS
            token = "";
            while (in.hasNextLine()) {
                token = token + " " + in.nextLine();
            }

           // token = token + " ";
        
           //
            //  REPLACING EXTRA CHARACTERS
            token = token.replace('.', ' ');
                        token = token.replace('@', ' ');
            token = token.replace('#', ' ');
            token = token.replace('$', ' ');
            token = token.replace('%', ' ');
            token = token.replace('^', ' ');
            token = token.replace('&', ' ');
            token = token.replace('*', ' ');
            token = token.replace('_', ' ');
            token = token.replace('=', ' ');
            token = token.replace('+', ' ');
            token = token.replace('<', ' ');
            token = token.replace('>', ' ');
            token = token.replace('–', ' ');
            token = token.replaceAll("  ", " ");
token = token.replaceAll("  ", " ");
            token = token.replace('!', ' ');
            token = token.replace('/', ' ');
            
            token = token.replace('(', ' ');
            token = token.replace(')', ' ');
            token = token.replace('[', ' ');
            token = token.replace(']', ' ');
            token = token.replace('-', ' ');
            token = token.replace('+', ' ');
            token = token.replace('1', ' ');
            token = token.replace('2', ' ');
            token = token.replace('3', ' ');
            token = token.replace('4', ' ');
            token = token.replace('5', ' ');
            token = token.replace('6', ' ');
            token = token.replace('7', ' ');
            token = token.replace('8', ' ');
            token = token.replace('9', ' ');
            token = token.replace('0', ' ');
            token = token.replace('ṭ','t');
            token = token.replace('…', ' ');
            
            token = token.replaceAll(",", " ");
            token = token.replace('\n',' ');
            token = token.replaceAll("  ", " ");
            
            token = token.replace('—', ' ');
token = token.replaceAll("( )+", " ");
            token = token.replace(' ', ' ');
            token = token.replace('ć', 'c');
            token = token.replace('‘', ' ');
            token = token.replace(':', ' ');
            token = token.replaceAll("male", " ");
            token = token.replaceAll("div "," ");
            token = token.replaceAll("clas "," ");
            token = token.replace(';',' ');
             token = token.replace('ḍ','d');
            token = token.replaceAll("genericfont "," ");
            token = token.replaceAll("female", " ");
            token = token.replaceAll("â€“", "");
            token = token.replaceAll(" ", " ");
            token = token.replaceAll("'", "");
            token = token.replaceAll("\"", "");
            token = token.replaceAll("“", "");
            token = token.replace('?', ' ');
            token = token.replace('|', ' ');
token = token.replaceAll("\\s+", " ").trim();


System.out.println(token);
            
            
            intIndex = 0;
            val = 20;
            pos = 0;

            while (pos != -1) {
                pos = token.indexOf(" ", intIndex);
                if (pos == intIndex) {
                    intIndex++;
                } else {
                    if (pos != -1) {
                        word = token.substring(intIndex, pos);
                        if(!word.equals(" "))
                            wordlist[wordindex++] = word;
                        token = token.replaceAll("" + word + " ", " ");
                    }
                }//end of else
            }//end of while

            val = wordindex;
        
            for(int i=0;i<val;i++)
                System.out.println(wordlist[i]);
            
            
            
            
            //CODE FOR FILLING THE POSTION OF  THE WORDS
            List list = Arrays.asList(wordlist);

            //CHECKING POSITION OF WORD IN THE LYRIC TITLE
            title = title.replace('-', ' ');
            title = title.replaceAll(".txt", "");
            title = title + " ";
            intIndex = 0;
            pos = 0;
            while (pos != -1) {
                pos = title.indexOf(" ", intIndex);
                if (pos == intIndex) {
                    intIndex++;
                } else {
                    if (pos != -1) {
                        word = title.substring(intIndex, pos);
                        val = list.indexOf(word);
                        if (val != -1) {
                            wordPos[val][0] = 1;
                        }
                        title = title.replaceAll(" " + word + " ", " ");
                    }
                }//end of else
            }//end of while

            // CHECKING POSITION OF WORD IN FIRST 10 LINES OF LYRIC FILE
            val = 1;
            in = new Scanner(file);
            val = 10;
            token = "";
            while (val > 0 && in.hasNextLine()) {
                token = token + " " + in.nextLine();
                val--;
            }

            token = token + " ";
            token1 = token1 + " ";
            while (in.hasNextLine()) {
                token1 = token1 + " " + in.nextLine();
            }
            token1 = token1 + " ";

            for (val = 0; val < wordindex; val++) {
                if (token.indexOf(wordlist[val]) != -1) {
                    wordPos[val][1] = 1;
                }
                if (token1.indexOf(wordlist[val]) != -1) {
                    wordPos[val][2] = 1;
                }
            }

            title = musicFile[index];
            buildIndex(wordlist, wordindex, title, newFilePath, wordPos);
            index++;
        }//end of while
    }

    
    
    // this function creates the database and the tables;
    public static void createIndex() {
        int valueOfa='a',valueOfz = 'z', val; 
        char c;
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
            PreparedStatement ps;
            String sql;

            sql = "create database if not exists major";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major", "root", "");

            sql = "Create table  if not exists wordlist(wid integer auto_increment, word varchar(255), length varchar(255), Primary key(wid))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
            
            
            sql = "Create table if not exists filelink(fid integer auto_increment, fname varchar(255), location varchar(255), Primary key(fid))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
               val = valueOfa;
        //RULE 3 - Drop all duplicate adjacent aplhabets
        do {
            c = (char) val;
            
            
                     sql = "Create table  if not exists "+c+"words(wid integer, word varchar(255), length varchar(255),Primary key(wid),foreign key(wid) references wordlist(wid))";
            
                     System.out.println(sql);
                     ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            
            
               sql = "Create table  if not exists "+c+"index(wid integer, fid integer, in_title integer, in_verse integer, other integer, primary key(wid, fid), foreign key(wid) references wordlist(wid), foreign key(fid) references filelink(fid))";
            
                     System.out.println(sql);
                     ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            
            
            
            val++;
        } while (val <= valueOfz);

            
                           
            
            


            sql = "Create table if not exists search_query(sid integer auto_increment, query varchar(255), primary key(sid))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            sql = "Create table if not exists result(fid integer, sid integer, rank integer, primary key(sid, fid), foreign key(sid) references search_query(sid), foreign key(fid) references filelink(fid))";
            ps = conn.prepareStatement(sql);
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(newparse2.class.getName()).log(Level.SEVERE, null, ex);
            //System.exit(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(newparse2.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    }

    // this function builds the index on the wordlsit for the specified file
    // Tables modifed: filelink, wordlist, indexfile;
    public static void buildIndex(String[] wordlist, int wordindex, String filename, String fileloc, int[][] wordpos) {
        try {
            char c;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major", "root", "");
            PreparedStatement ps;
            ResultSet rs;
            String word;
            int val = wordindex - 1, fid, wid, len, pos1, pos2, pos3;
            String sql;
            sql = "insert into filelink values(DEFAULT,'" + filename + "','" + fileloc + "')";

            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            sql = "select last_insert_id() as lastid from filelink";
            rs = ps.executeQuery(sql);
            rs.next();
            fid = rs.getInt(1);
            while (val >= 0) {
                word = wordlist[val];
                System.out.println(word);
                
                c = word.charAt(0);
                sql = "select wid from "+c+"words where word = '" + word + "'";
                System.out.println(sql);
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery(sql);
                if (rs.next()) {
                    wid = rs.getInt(1);
        
                } else {
                    len = word.length();
                    sql = "insert into wordlist values(default,'" + word + "'," + len + ")";
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();
                    sql = "select last_insert_id() as lastid from wordlist";
                    rs = ps.executeQuery(sql);
                    rs.next();
                    wid = rs.getInt(1);
                    
                    sql = "insert into "+c+"words(wid, word, length) values("+wid+",'" + word + "'," + len + ")";
                    System.out.println(sql);
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();
                    
                }

                
                sql = "select * from "+c+"index where wid = " + wid + " and fid = " + fid; 
                System.out.println(sql);
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery(sql);
                if (!rs.next()) {
                    pos1 = wordpos[val][0];
                    pos2 = wordpos[val][1];
                    pos3 = wordpos[val][2];
                    sql = "insert into "+c+"index values(" + wid + "," + fid + "," + pos1 + "," + pos2 + "," + pos3 + ")";
                    System.out.println(sql);
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();
                }

                val--;
            }

        } catch (SQLException ex) {
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        createIndex();
       tokenize("C:/Users/Ankita/Desktop/newlyric1");
        System.out.println("complete");
    }
}
