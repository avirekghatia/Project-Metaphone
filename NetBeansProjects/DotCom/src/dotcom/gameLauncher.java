/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dotcom;
import java.util.Scanner;
/**
 *
 * @author Krishna Ghatia
 */
public class gameLauncher {
    public void startGame(){
        int target, count=0;
        int[] dotComShip;
        dotComShip = new int[7];
        int input;
        
        
        target = (int) (Math.random()*5);
        dotComShip[target]=1;   //setting the dotCom ship in the array.
        dotComShip[target+1]=1;
        dotComShip[target+2]=1;
        
        Scanner in = new Scanner(System.in); 
        
        while (count<3)
        {
            System.out.println("Enter a number from 1 to 7: " );
            input = in.nextInt();
            input--;
            if(dotComShip[input]==1)
            {
                System.out.println("HIT!!");
                dotComShip[input]=0;
                count++;
            }
            else
            {
                System.out.println("MISS!!");
            }
        }
    }
}
