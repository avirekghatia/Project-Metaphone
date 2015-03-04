/*
 * This class performs searches songs corresponding to input query and then ranks the retrieved results
 * @author Bhavya Dwivedi
 * references : alvinalexander.com(database connection queries)
 */
package myMetaphone;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Input_1 {

    String inputstr;
    String[] token;
    public int sid;
    public boolean isresult = true;
    public boolean isLongQuery = false;
    public float appxpercent;
    private ArrayList<Integer>[] wids = (ArrayList<Integer>[]) new ArrayList[15];//only 100 words can be linked with each token of input query from indexfile
    private ArrayList<Integer>[] fids = (ArrayList<Integer>[]) new ArrayList[10];//limits search to the first 10 words of the input query
    private PreparedStatement ps = null, ps1 = null, ps2 = null;
    private ResultSet resultset = null;
    private ResultSet res = null;
    private ResultSet res2 = null;
    private Connection con = null, conn = null;

    public void deleteTempFiles() {
        String tablename;
        String sq;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major1", "root", "");
            DatabaseMetaData dbmd = conn.getMetaData();
            res = dbmd.getTables(null, null, "%", null);
            while (res.next()) {
                tablename = res.getString(3);
                if (!tablename.equals("filelink") && !tablename.equals("indexfile") && !tablename.equals("result") && !tablename.equals("search_query") && !tablename.equals("wordlist")) {
                    sq = "drop table " + tablename;
                    System.out.println(sq);
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String replaceSpChar(String str) {
        str = str.replace('+', ' ');
        str = str.replace('1', ' ');
        str = str.replace('2', ' ');
        str = str.replace('3', ' ');
        str = str.replace('4', ' ');
        str = str.replace('5', ' ');
        str = str.replace('6', ' ');
        str = str.replace('7', ' ');
        str = str.replace('8', ' ');
        str = str.replace('9', ' ');
        str = str.replace('0', ' ');
        str = str.replace('!', ' ');
        str = str.replace('@', ' ');
        str = str.replace('#', ' ');
        str = str.replace('$', ' ');
        str = str.replace('%', ' ');
        str = str.replace('^', ' ');
        str = str.replace('&', ' ');
        str = str.replace('*', ' ');
        str = str.replace('(', ' ');
        str = str.replace(')', ' ');
        str = str.replace('[', ' ');
        str = str.replace(']', ' ');
        str = str.replace('{', ' ');
        str = str.replace('}', ' ');
        str = str.replace('=', ' ');
        str = str.replace('\\', ' ');
        str = str.replace('/', ' ');
        str = str.replace('?', ' ');
        str = str.replace('<', ' ');
        str = str.replace('>', ' ');
        str = str.replace(':', ' ');
        str = str.replace(';', ' ');
        str = str.replace(',', ' ');
        str = str.replace('.', ' ');
        str = str.replaceAll("( )+", " ");
        return str;
    }

    /* function to get input from user*/
    public void getInput(String lyric) // change function name later
    {
        deleteTempFiles();
        System.out.println("approximation value" + appxpercent);
        System.out.println("Input search query");
        Scanner s = new Scanner(System.in);
        inputstr = lyric;
        inputstr = replaceSpChar(inputstr);
        inputstr = inputstr.replaceAll("( )+", " ");
        inputstr = inputstr.trim();
        inputstr = inputstr.toLowerCase();
        //Handling blank query
        if ((inputstr.compareTo("") == 0) || (inputstr.compareTo(" ") == 0) || (inputstr.compareTo("\n") == 0)) {
            isresult = false;
            return;
        }
        modifyInput(inputstr);
        parseInput(inputstr);
        if (token.length >= 50) {
            isresult = false;
            isLongQuery = true;
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major1", "root", "");
            int initialsearch = readDataBaseSearchQuery();
            if (initialsearch == -1) {
                System.out.println("No saved search found. Making search in wordlist now.");
                //now search in index and rank and enter result in searchquery database
                String sq = "insert into search_query values(DEFAULT,'" + inputstr + "') ";
                System.out.println(sq);
                ps = conn.prepareStatement(sq);
                ps.executeUpdate();
                sq = "select last_insert_id() as lastid from search_query";
                res = ps.executeQuery(sq);
                res.next();
                sid = res.getInt(1);

                try {
                    searchWordList(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(initialsearch);// print returned file-id
                sid = initialsearch;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    /*function to modify search query according to metaphone rules*/
    private void modifyInput(String str) {
        ConvertForm cf = new ConvertForm();
        inputstr = cf.convertString(str);
    }

    /*function to extract tokens from search query*/
    private void parseInput(String str) {
        int i = 0;
        String delims = "[ ]+";
        str = str.replace('.', ' ');
        str = str.replace('(', ' ');
        str = str.replace(')', ' ');
        str = str.replace('[', ' ');
        str = str.replace(']', ' ');
        str = str.replace('-', ' ');
        str = str.replace('+', ' ');
        str = str.replace('1', ' ');
        str = str.replace('2', ' ');
        str = str.replace('3', ' ');
        str = str.replace('4', ' ');
        str = str.replace('5', ' ');
        str = str.replace('6', ' ');
        str = str.replace('7', ' ');
        str = str.replace('8', ' ');
        str = str.replace('9', ' ');
        str = str.replace('0', ' ');
        str = str.replaceAll(",", " ");
        str = str.replaceAll("'", "");
        str = str.replaceAll("\"", "");
        str = str.replaceAll("â€œ", "");
        str = str.replace('?', ' ');
        str = str.replace('|', ' ');
        str = str.replace('!', ' ');
        str = str.replace('@', ' ');
        str = str.replace('#', ' ');
        str = str.replace('&', ' ');
        str = str.replace(';', ' ');
        str = str.replace(':', ' ');
        str = str.replace('^', ' ');
        str = str.replace('=', ' ');
        str = str.replace('}', ' ');
        str = str.replace('{', ' ');
        str = str.replace('<', ' ');
        str = str.replace('>', ' ');
        str = str.replace('_', ' ');
        token = str.split(delims);
        System.out.println(" Tokens :");
        System.out.println(" ");
    }

    /*
     * using edit distance search to find all words which satisfy 75% matching with each word in the input query
     * start of code for edit distance
     */
    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int computeEditDistance(String str1, String str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= str2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
            }
        }
        return distance[str1.length()][str2.length()];
    }
    /*
     *end of edit distance code
     */

    /* function to search query in previously stored search results in search query database*/
    public int readDataBaseSearchQuery() throws SQLException {
        int tempres = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major1", "root", "");
            ps1 = con.prepareStatement("select  * from search_query");
            resultset = ps1.executeQuery();
            int l = 0;
            while (resultset.next()) {
                String query = resultset.getString("query");
                int queryid = resultset.getInt("sid");
                l = inputstr.compareTo(query);
                if (l == 0) {
                    //ps1.close();// close connection
                    tempres = queryid;// now search fid using result table
                }
            }
            ps1.close();
        }//end of try
        catch (Exception e) {
            e.printStackTrace();
        }
        return tempres;
    }

    /*function to search in index if no stored search results are found in search query database*/
    private void searchWordList(String[] token) throws SQLException, ClassNotFoundException {
        int distance; // stores edit distance
        int tokenlength;//stores length of a token of input query
        //float appxpercent = 0.75f;
        int appx; //stores the number of characters which must match while computing edit distance
        String sq, sq1;//for creating temporary tables
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major1", "root", "");
        try {
            System.out.println("In try block");
            ps1 = con.prepareStatement("select * from wordlist");
            for (int i = 0; i < token.length; i++) {
                System.out.println("Searching for token:" + token[i]);
                wids[i] = new ArrayList<Integer>();//storing wid corresponding to each token in a different arraylist
                System.out.println("Wordid arraylist created for: " + token[i]);
                tokenlength = token[i].length();
                resultset = ps1.executeQuery();
                while (resultset.next()) {
                    String word = resultset.getString("word");
                    int wordlength = resultset.getInt("length");
                    int wid = resultset.getInt("wid");
                    //check if token and word begin with same character
                    int com = token[i].substring(0, 1).compareTo(word.substring(0, 1));
                    if (com == 0) {
                        // now compute edit distance on the larger word
                        if (tokenlength >= wordlength) {
                            //edit distance computed on tokenlength
                            appx = (int) (appxpercent * tokenlength);// 75% approximation formula
                            System.out.println("approx " + appx);
                            distance = computeEditDistance(token[i], word);
                            int margin = tokenlength - appx;
                            System.out.println(" Distance: " + distance + " margin: " + margin);
                            if (distance <= margin) {
                                //now word can be included in list of wordids
                                wids[i].add(wid);
                                System.out.println("Word added is: " + word);
                            }
                        } else {
                            //edit distance computed on wordlength
                            appx = (int) (appxpercent * wordlength);// 75% approximation formula
                            distance = computeEditDistance(token[i], word);
                            int margin = wordlength - appx;
                            if (distance <= margin) {
                                //now word can be included in list of wordids
                                wids[i].add(wid);
                                System.out.println("Word added is: " + word);
                            }
                        }
                    }//end of com if
                }//end of while
            }//end of for
            ps1.close();
        }//end of try block
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int i;
            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost/";
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major1", "root", "");
            Class.forName("com.mysql.jdbc.Driver");
            ps1 = con.prepareStatement("select * from indexfile");
            //appropriate wordids have been retrieved. Now we retrieve the files corresponding to the retrieved wordids.
            System.out.println("In try block retrieving filenames");
            for (i = 0; i < token.length; i++) {
                sq = "Create table if not exists";
                System.out.println(" token[i] " + token[i]);
                sq = sq + " " + token[i] + "(wid integer, fid integer, in_title integer, in_verse integer, other integer) ";
                System.out.println("Sq: " + sq);
                ps = conn.prepareStatement(sq);//check if con can be used
                ps.executeUpdate();
                System.out.println(" Temporary table " + i + 1 + " created");
                for (int j = 0; j < wids[i].size(); j++) {
                    resultset = ps1.executeQuery();
                    while (resultset.next()) {
                        int indexfile_wid = resultset.getInt("wid");
                        int indexfile_fid = resultset.getInt("fid");
                        int title = resultset.getInt("in_title");
                        int verse = resultset.getInt("in_verse");
                        int other = resultset.getInt("other");
                        if (wids[i].get(j) == indexfile_wid) {
                            System.out.println("wid : " + wids[i].get(j) + " or " + indexfile_wid + " file id added is: " + indexfile_fid);
                            sq1 = "insert into " + token[i] + " values('" + indexfile_wid + "','" + indexfile_fid + "','" + title + "','" + verse + "','" + other + "')";
                            System.out.println("sq1: " + sq1);
                            ps2 = conn.prepareStatement(sq1);
                            ps2.executeUpdate();
                        }
                    }//end of while
                }//end of inner for
            }//end of outer for
            /*now ranking the retrieved files and making entry of result in result table*/
            //first finding the file which are common to all the temporary tables

            sq = "Create table if not exists fids0(tfid integer)";
            ps = conn.prepareStatement(sq);//check if con can be used
            ps.executeUpdate();
            System.out.println(" Fids0 table created");
            //now putting fids of table token[0] in fids
            ps1 = conn.prepareStatement("select distinct fid from " + token[0]);
            res2 = ps1.executeQuery();
            System.out.println("fids selected from token[0]");
            while (res2.next()) {
                int tfid = res2.getInt("fid");
                System.out.println("Inserting fid: " + tfid);
                sq = "insert into fids0 value(" + tfid + ")";
                ps2 = conn.prepareStatement(sq);
                ps2.executeUpdate();
            }
            // now selecting the fids which are common to all tokens
            for (i = 0; i < token.length; i++) {
                sq = "select  distinct tfid from fids" + i + " where tfid in(select distinct fid from " + token[i] + ")";
                /* */
                System.out.println("sq :" + sq);
                ps = conn.prepareStatement(sq);
                res2 = ps.executeQuery();
                sq = "Create table if not exists temp0(fid integer, in_title integer, in_verse integer, other integer)";
                ps = conn.prepareStatement(sq);//check if con can be used
                ps.executeUpdate();
                System.out.println("temp0 table created");
                //now putting fids of table token[0] in fids
                ps1 = conn.prepareStatement("select distinct fid,in_title,in_verse,other from " + token[0]);
                res2 = ps1.executeQuery();
                System.out.println("fid, in_title,in_verse,other selected from token[0] into temp0");
                while (res2.next()) {
                    int tfid = res2.getInt("fid");
                    int tin_title = res2.getInt("in_title");
                    int tin_verse = res2.getInt("in_verse");
                    int tother = res2.getInt("other");
                    System.out.println("Inserting fid, in_title, in_verse, other: " + tfid + " " + tin_title + " " + tin_verse + " " + tother);
                    sq1 = "insert into temp0 values(" + tfid + "," + tin_title + "," + tin_verse + "," + tother + ")";
                    ps2 = conn.prepareStatement(sq1);
                    ps2.executeUpdate();
                    System.out.println("Updated ");
                }
                for (i = 0; i < token.length; i++) {
                    int j = i + 1;
                    sq1 = "Create table if not exists temp" + j + "(fid integer, in_title integer, in_verse integer, other integer)";
                    ps1 = conn.prepareStatement(sq1);
                    ps1.executeUpdate();
                    System.out.println(" temp" + j + " table created");
                    System.out.println("ab run karo in query on token tables");
                    sq = "select  distinct t1.fid, t1.in_title, t1.in_verse, t1.other, t2.in_title, t2.in_verse, t2.other from temp" + i + " as t1, " + token[i] + " as t2 where t1.fid = t2.fid AND (t1.in_title=t2.in_title OR t1.in_verse=t2.in_verse OR t1.other=t2.other)";
                    System.out.println("sq :" + sq);
                    ps = conn.prepareStatement(sq);
                    res2 = ps.executeQuery();
                    while (res2.next()) {
                        int f = res2.getInt(1);
                        int t1 = res2.getInt(2);
                        int v1 = res2.getInt(3);
                        int o1 = res2.getInt(4);
                        int t2 = res2.getInt(5);
                        int v2 = res2.getInt(6);
                        int o2 = res2.getInt(7);
                        int t = 0;
                        int v = 0;
                        int o = 0;
                        if (t1 == 1 && t2 == 1) {
                            t = 1;
                        }
                        if (v1 == 1 && v2 == 1) {
                            v = 1;
                        }
                        if (o1 == 1 && o2 == 1) {
                            o = 1;
                        }
                        System.out.println("inserted into fid tempnew: " + f + " " + t + " " + v + " " + o);
                        sq1 = "insert into temp" + j + " values(" + f + "," + t + "," + v + "," + o + ")";
                        ps2 = conn.prepareStatement(sq1);
                        ps2.executeUpdate();
                    }//end of while
                }
                // now we rank the files which are common to all the tokens
                System.out.println("value of i: " + i);
                sq = "select distinct * from temp" + i;
                ps = conn.prepareStatement(sq);
                resultset = ps.executeQuery();
                int recordcount = 0;
                while (resultset.next()) {
                    recordcount++;
                }
                System.out.println("No of records : " + recordcount);
                sq = "select distinct fid from temp" + i;
                ps = conn.prepareStatement(sq);
                res2 = ps.executeQuery();
                int fileId;
                int rank = 1;
                while (res2.next()) {
                    sq = "select distinct fid, fname, location from filelink where fid = " + res2.getInt(1);
                    ps = conn.prepareStatement(sq);
                    res = ps.executeQuery();
                    String filename;
                    if (res.next()) {
                        filename = res.getString("fname");
                        fileId = res.getInt("fid");
                        File file = new File(res.getString("location"));
                        Scanner in = new Scanner(file);
                        String filestring = "";
                        while (in.hasNextLine()) {
                            filestring = filestring + " " + in.nextLine();
                        }
                        if (filestring.indexOf(inputstr) != -1) {
                            System.out.println(filename);
                            sq = "insert into result values(" + fileId + "," + sid + "," + rank + ")";
                            ps = conn.prepareStatement(sq);
                            ps.executeUpdate();
                            rank++;
                            sq = "delete from temp" + i + " where fid =" + fileId;
                            ps = conn.prepareStatement(sq);
                            ps.executeUpdate();
                        }//end of inner if
                    }//end of outer if
                }//end of while

                // where query found in title
                sq = "select distinct fid from temp" + i + " where in_title =1";
                ps = conn.prepareStatement(sq);
                res2 = ps.executeQuery();
                while (res2.next()) {
                    fileId = res2.getInt(1);
                    sq = "insert into result values(" + fileId + "," + sid + "," + rank + ")";
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                    rank++;
                    sq = "delete from temp" + i + " where fid =" + fileId;
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                }
                // where query found in verse and other
                sq = "select distinct fid from temp" + i + " where in_verse =1 and other = 1";
                ps = conn.prepareStatement(sq);
                res2 = ps.executeQuery();

                while (res2.next()) {
                    fileId = res2.getInt(1);
                    sq = "insert into result values(" + fileId + "," + sid + "," + rank + ")";
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                    rank++;
                    sq = "delete from temp" + i + " where fid =" + fileId;
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                }

                // where query prsent in verse only
                sq = "select distinct fid from temp" + i + " where in_verse =1";
                ps = conn.prepareStatement(sq);
                res2 = ps.executeQuery();

                while (res2.next()) {
                    fileId = res2.getInt(1);
                    sq = "insert into result values(" + fileId + "," + sid + "," + rank + ")";
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                    rank++;
                    sq = "delete from temp" + i + " where fid =" + fileId;
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                }
                // where query prsent in other only
                sq = "select distinct fid from temp" + i + " where other =1";
                ps = conn.prepareStatement(sq);
                res2 = ps.executeQuery();

                while (res2.next()) {
                    fileId = res2.getInt(1);
                    sq = "insert into result values(" + fileId + "," + sid + "," + rank + ")";
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                    rank++;
                    sq = "delete from temp" + i + " where fid =" + fileId;
                    ps = conn.prepareStatement(sq);
                    ps.executeUpdate();
                }
                ps.close();
                ps1.close();
                ps2.close();
            }//end of try
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of searchwordlist function
}//end of Input class

class Main1 {

    public static void main(String[] args) {   // get song to be searched
        Input_1 i = new Input_1();
    }
}
