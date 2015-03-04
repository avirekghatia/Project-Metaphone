
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
public class removeChar {
    public static void main(String[] args)
    {
        String str="Battle of the vowels: Hawaii vs. Grozny";
        String remove="aeiou";
        String returnString;
        returnString=removeCharacters(str, remove);
    }
    public static String removeCharacters(String str, String remove)
    {
        int length, i, actual=0;
        char c;
        String temp=new String();
        HashMap<Character,Integer> characterHash = new HashMap<Character,Integer>();
        length=remove.length();
        for(i=0;i<length;i++)
        {
            c=remove.charAt(i);
            characterHash.put(c, 1);
        }
        length=str.length();
        for(i=0;i<length;i++)
        {
            c=str.charAt(i);
            if(characterHash.containsKey(c))
            {
                
            }   
            else
            {
                temp.
                actual++;
            }
        }
    }
}
