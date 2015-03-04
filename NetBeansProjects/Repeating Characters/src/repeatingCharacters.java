
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Krishna Ghatia
 */

public class repeatingCharacters {
    public static void main (String[] args)
    {
        char c;
        String myString = "teeter";
        c=repeatingChar(myString);
        System.out.println("Character: " +c);
    }
    public static char repeatingChar(String str)
    {
        HashMap<Character, Integer> charHash = new HashMap<Character, Integer>();
        int length, i;
        char c;
        length=str.length();
        for(i=0;i<length;i++)
        {
            c=str.charAt(i);
            if(charHash.containsKey(c))
                charHash.put(c, charHash.get(c)+1);
            else
                charHash.put(c, 1);
            System.out.println(c);
            System.out.print(charHash.get(c));
        }
        for(i=0;i<length;i++)
        {
            c=str.charAt(i);
            if(charHash.get(c)==1)
                return c;
        }
        return '\0';
    }    
}
