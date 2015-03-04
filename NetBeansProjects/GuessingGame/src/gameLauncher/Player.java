/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameLauncher;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import java.lang.Math;
/**
 *
 * @author Krishna Ghatia
 */
public class Player {
    int number;
    public int guess()
    {
        System.out.println("Guessing a number: ");
        number = (int) (Math.random() *10);
        return number;
    }
}
