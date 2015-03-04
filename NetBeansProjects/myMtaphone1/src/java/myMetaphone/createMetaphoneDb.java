/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myMetaphone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ankita
 */
public class createMetaphoneDb {
    
    public static void main(String args[])
    {
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
/*
            sql = "create database if not exists convertMetaDB";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
*/
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/major", "root", "");

            sql = "Create table  if not exists convertword(priority integer, fromword varchar(255), toword varchar(255), Primary key(priority))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

            
            
            sql = "Create table  if not exists dropword(priority integer, fromdrop varchar(255), todrop varchar(255), Primary key(priority))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            /*
convertword fromword, toword, priority
        dropword priority, todrop , fromdrop
*/
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(parse1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(parse1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
