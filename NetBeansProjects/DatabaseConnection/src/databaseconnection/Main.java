/*/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package databaseconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= (Connection )DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            PreparedStatement ps = con.prepareStatement("insert into emp values (3)");
            ps.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }



}






}


