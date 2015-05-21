/**
 * This class parses the database and build the index
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

public class parse {

    public static int listFileForFolder(final File folder, String[] str) {
        int i = 0;
        for (final File fileEntry : folder.listFiles()) {
            str[i] = fileEntry.getName();
            i++;
        }
        return i;
    }

    public static void tokenize(String newPath) throws FileNotFoundException, SQLException {

        //String newPath = "E:\\Blah1";
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
        //System.out.println("folder length " + follen);
        Connection conn;
        while (index < follen) {
            //System.out.println("m here 1");
            token = "";

            newFilePath = newPath + "/" + musicFile[index];

            title = " " + musicFile[index];
            title = ConvertForm.convertString(title);
            //System.out.println(title);
            wordindex = 0;
            File file = new File(newFilePath);

            wordindex = 0;

            in = new Scanner(file);


            val = 1;
  
            //System.out.println(token);
            //GETTING LYRICS
            token = "";
            while (in.hasNextLine()) {
                token = token + " " + in.nextLine();
            }

            token = token + " ";
            //  //System.out.println(token);

            //  REPLACING EXTRA CHARACTERS
            token = token.replace('.', ' ');
            token = token.replace('(', ' ');
            token = token.replace(')', ' ');
            token = token.replace('[', ' ');
            token = token.replace(']', ' ');
            token = token.replace('-', ' ');
            token = token.replace('+', ' ');
            token = token.replace('1', ' ');
            token = token.replaceAll(",", " ");
            token = token.replaceAll("â€“", "");
            token = token.replaceAll(" ", " ");
            token = token.replaceAll("'", "");
            token = token.replaceAll("\"", "");
            token = token.replaceAll("“", "");
            token = token.replace('?', ' ');
            token = token.replace('|', ' ');

            //System.out.println(token);

            intIndex = 0;
            val = 20;
            pos = 0;

            while (pos != -1) {
                pos = token.indexOf(" ", intIndex);
                // //System.out.println("position" + pos);
                if (pos == intIndex) {
                    intIndex++;
                } else {
                    if (pos != -1) {
                        word = token.substring(intIndex, pos);
                        //System.out.println(word+" "+ wordindex);
                        wordlist[wordindex++] = word;

                        //               //System.out.println(word);
                        token = token.replaceAll(" " + word + " ", " ");
                    }
                }//end of else
                }//end of while



            val = wordindex;
            //        while (val > 0)
            //      {
            //System.out.println(wordlist[--val]+" " + val);
            //    }


            //CODE FOR FILLING THE POSTION OF  THE WORDS

            List list = Arrays.asList(wordlist);

            //CHECKING POSITION OF WORD IN THE LYRIC TITLE
            title = title.replace('-', ' ');
            title = title.replaceAll(".txt", "");
            //Avi               //System.out.println(title);
            title = title + " ";
            intIndex = 0;
            pos = 0;
            while (pos != -1) {
                pos = title.indexOf(" ", intIndex);
                //  //System.out.println("position" + pos);
                if (pos == intIndex) {
                    intIndex++;
                } else {
                    if (pos != -1) {
                        word = title.substring(intIndex, pos);
                        //      //System.out.println("m here");
                        //System.out.println(word);
                        val = list.indexOf(word);
                        //System.out.println(val);
                        if (val != -1) {
                            wordPos[val][0] = 1;
                        }
                        title = title.replaceAll(" " + word + " ", " ");
                    }
                }//end of else
                }//end of while

            //System.out.println("start checking words");
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
            //System.out.println(token);
            //System.out.println("read token");
            token1 = token1 + " ";
            while (in.hasNextLine()) {
                token1 = token1 + " " + in.nextLine();
            }
            token1 = token1 + " ";

            //System.out.println("token 1");
            for (val = 0; val < wordindex; val++) {
                if (token.indexOf(wordlist[val]) != -1) {
                    wordPos[val][1] = 1;
                }
                if (token1.indexOf(wordlist[val]) != -1) {
                    wordPos[val][2] = 1;
                }
            }

            title = musicFile[index];
            //createIndex(wordlist,wordindex, title, newFilePath, wordPos);
            buildIndex(wordlist, wordindex, title, newFilePath, wordPos);
            //System.out.println("read file complete");
            index++;
        }//end of while
    }

    public static void createIndex() {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/";

        try {
//            Class.forName("com.mysql.jdbc.Driver");
            // Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("connecting to database");
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


            sql = "Create table if not exists indexfile(wid integer, fid integer, in_title integer, in_verse integer, other integer, primary key(wid, fid), foreign key(wid) references wordlist(wid), foreign key(fid) references filelink(fid))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();



            sql = "Create table if not exists search_query(sid integer auto_increment, query varchar(255), primary key(sid))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();


            sql = "Create table if not exists result(fid integer, sid integer, rank integer, primary key(sid, fid), foreign key(sid) references search_query(sid), foreign key(fid) references filelink(fid))";
            ps = conn.prepareStatement(sql);
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();



            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(parse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(parse.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void buildIndex(String[] wordlist, int wordindex, String filename, String fileloc, int[][] wordpos) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("connecting to database");
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
                sql = "select wid from wordlist where word = '" + word + "'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery(sql);
                if (rs.next()) {
                    wid = rs.getInt(1);
                    //System.out.println(word +" word id "+wid);

                } else {
                    len = word.length();
                    sql = "insert into wordlist values(default,'" + word + "'," + len + ")";
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();
                    sql = "select last_insert_id() as lastid from wordlist";
                    rs = ps.executeQuery(sql);
                    rs.next();
                    wid = rs.getInt(1);
                    //System.out.println(word +" in else word id" + wid);
                }

                sql = "select * from indexfile where wid = " + wid + " and fid = " + fid;
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery(sql);
                if (!rs.next()) {
                    pos1 = wordpos[val][0];
                    pos2 = wordpos[val][1];
                    pos3 = wordpos[val][2];
                    sql = "insert into indexfile values(" + wid + "," + fid + "," + pos1 + "," + pos2 + "," + pos3 + ")";
                    ps = conn.prepareStatement(sql);
                    ps.executeUpdate();
                }

                //System.out.println(wid + "wordid");
                val--;
            }

            //System.out.println(fid +" file id");
        } catch (SQLException ex) {
            Logger.getLogger(parse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(parse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        //SimpleSankitatreamTokenizer in;
        createIndex();
        tokenize("C:\\Users\\Krishna Ghatia\\Desktop\\Database2new");
        System.out.println("complete");
    }
}