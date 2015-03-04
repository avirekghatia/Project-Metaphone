/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package meta;

/**
 *
 * @author Krishna Ghatia
 */
import java.sql.*;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String url = "jdbc:mysql://localhost:3306/";
          String dbName = "demo”;
          String driver = "com.mysql.jdbc.Driver";
          String userName = "root";
          //String password = "mypasswd";
          try {
          Class.forName(driver).newInstance();
          Connection conn = DriverManager.getConnection(url+dbName,userName);

          conn.close();
          } catch (Exception e) {
          e.printStackTrace();
          }
          }
}


    }

}
