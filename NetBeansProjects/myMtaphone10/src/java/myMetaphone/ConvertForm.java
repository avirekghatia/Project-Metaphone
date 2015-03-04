/*
 *This is baiscally a class that will perform converstion of an string to the desired form
 *@author Ankita Agrawal
 */
package myMetaphone;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class ConvertForm {

    public static int listFileForFolder(final File folder, String[] str)
     {
         int i = 0;
         for(final File fileEntry : folder.listFiles())
         {
             str[i] = fileEntry.getName();
             i++;
         }
         return i;
     }


// function that coverts a String into desired Metaphone form
public static String convertString(String str)
{ 
    int valueOfa = 'a', valueOfz = 'z', val, index;
    char c;
    String st1, torep, byrep;
           
    //System.out.println(str);
    //RULE 11
                    str = str.replaceAll("ee", "i");
                    str = str.replaceAll("aa", "a");
                    str = str.replaceAll("uu", "u");
                    str = str.replaceAll("ii", "i");
                    str = str.replaceAll("oo", "u");
                    str = str.replaceAll("ae", "e");
                    str = str.replaceAll("ai", "e");
                    str = str.replaceAll("ao", "o");
                    str = str.replaceAll("ei", "e");
                    str = str.replaceAll("eo", "iyo");
                    str = str.replaceAll("ea", "iya");
                    str = str.replaceAll("eu", "iyu");
                    str = str.replaceAll("io", "iyo");
                    str = str.replaceAll("ia", "iya");


                    val = valueOfa;
        //            //System.out.println("m here");
                    // replace all consecutive alphabets
                    //code start here
                   // //System.out.println(val);
                    //RULE 3 - Drop all duplicate adjacent aplhabets
                    do{
                        c = (char)val;
                    //    //System.out.println(c);
                        byrep = String.valueOf(Character.toChars(val));
      //                  //System.out.println(byrep);
                        torep = byrep + ""+ byrep;

                    str = str.replaceAll(torep, byrep);
                    val++;
                    }while(val <= valueOfz);

                    //#rule 4: convert PH to  F
                    str = str.replaceAll("ph", "f");

                   //#rule 6: DROP K if k is initial alphabet followed by S

                    while((index = str.indexOf(" ks"))!= -1)
                    {
                        index++;
                        str = str.substring(0,index) + str.substring(index + 1);
                    }

                    //#rule 7: convert Q to k

                    str = str.replaceAll("q", "k");



                    // code ends here
                    ////System.out.println(st1);
    return str;
}

/*
public static String[] insertVowels(String str)
{
    String[] fewstr = new String[10];

    

    return fewstr;
}
*/

      public static  void convertFile()
      {
          try {
            int noOfFile = 10000;
            FileWriter fstream;
            BufferedWriter out;
            char c;
            int index = 0, follen = 0, valueOfa = 'a', valueOfz = 'z', val;
            //System.out.println(valueOfa);
            Scanner in;
            String oldPath = "C:/Users/Ankita/Desktop/lyric";
            String newPath = "C:/Users/Ankita/Desktop/newlyric";
            String filePath;
            String newFilePath;
            String str, st1, torep, byrep;
            final File folder = new File(oldPath);
            final File newfolder = new File(newPath);
            newfolder.mkdir();
            String[] musicFile = new String[noOfFile];
            follen = listFileForFolder(folder, musicFile);
            //System.out.println(follen);
            while(index < follen){
                filePath = oldPath + "/" + musicFile[index];
                newFilePath = newPath + "/" + musicFile[index];
                File file = new File(filePath);
                File newFile = new File(newFilePath);
                fstream = new FileWriter(newFile);
                out = new BufferedWriter(fstream);

                index ++;
                in = new Scanner(file);
                ////System.out.println("m here 1");
                while (in.hasNextLine()) {
                    str = in.nextLine().toLowerCase();
                    //convertString(str);
                    ////System.out.println(str);
    /*                str = str.replaceAll("ee", "i");
                    str = str.replaceAll("oo", "u");
                    str = str.replaceAll("ae", "e");
                    val = valueOfa;
                    //System.out.println("m here");
                    // replace all consecutive alphabets
                    //code start here
                    //System.out.println(val);
                    do{
                        c = (char)val;
                        //System.out.println(c);
                        byrep = String.valueOf(Character.toChars(val));
                        //System.out.println(byrep);
                        torep = byrep + ""+ byrep;

                    str = str.replaceAll(torep, byrep);
                    val++;
                    }while(val <= valueOfz);
                    // code ends here
                    ////System.out.println(st1);*/
                   // out.write(str);
                    ////System.out.println(convertString(str));
                    out.write(convertString(str));
                    out.newLine();
                }
                out.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConvertForm.class.getName()).log(Level.SEVERE, null, ex);
        }
      }


     public static void main(String args[])
     {
      //   String str = new String("this is Kshamta");

        // int index = str.indexOf(" Ks");
         //index++;
        convertFile();
         //str = str.substring(0, index) + str.substring(index + 1 );
         ////System.out.println(str);
     }
}

