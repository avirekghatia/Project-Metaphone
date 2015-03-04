/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package karel1;

/**
 *
 * @author Krishna Ghatia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello hi chhodiye, jai mata di boliye!!");// TODO code application logic here
        while(frontIsNotClear)
        {
            turnLeft();
            move();
            turnRight();
        }
    }
}

}
