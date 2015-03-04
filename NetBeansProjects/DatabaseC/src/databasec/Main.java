/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package databasec;

/**
 *
 * @author Krishna Ghatia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        MySQLAccess dao = new MySQLAccess();
        dao.readDatabase();
    }

}
