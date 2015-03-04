/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameLauncher;

/**
 *
 * @author Krishna Ghatia
 */
public class gameLauncher {
    Player player1;
    Player player2;
    Player player3;
    public void startGame()
    {
        boolean p1Isright = false;
        boolean p2Isright = false;
        boolean p3Isright = false;
        
        int target;
        
        int guessP1;
        int guessP2;
        int guessP3;
        
        target = (int) (Math.random()*10);  //the game launcher chooses a number
            System.out.println("The target number is " +target);
                
        
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        
        //the players make their guesses
        while((p1Isright || p2Isright || p3Isright) !=true)
        {
            guessP1 = player1.guess();
            System.out.println("Player 1 guessed: " + guessP1);
            guessP2 = player2.guess();
            System.out.println("Player 2 guessed: " + guessP2);
            guessP3 = player3.guess();
            System.out.println("Player 3 guessed: " + guessP3);
            if(guessP1 == target)
            {
                p1Isright = true;
            }
            if(guessP2 == target)
            {
                p2Isright = true;
            }
            if(guessP3 == target)
            {
                p3Isright = true;
            }
            if((p1Isright || p2Isright || p3Isright) == true)
            {
                System.out.println("We have a winner!!");
                if(p1Isright)
                {
                    System.out.println("The winner is Player 1");
                }
                else if(p2Isright)
                {
                    System.out.println("The winner is Player 2");
                }
                else if(p3Isright)
                {
                    System.out.println("The winner is Player 3");
                }
            }
        }
    }
            
}
