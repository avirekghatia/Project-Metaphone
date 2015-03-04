/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication8;
import java.util.*;
import java.io.*;
/**
 *
 * @author Krishna Ghatia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader fr;
        BufferedReader br;
        String result="";
        String word= new String();
        String target = "gay";
        try{ //read one text file
                fr = new FileReader ("E:\\Blah\\Avi.txt");
                br = new BufferedReader(fr);
                Scanner scan = new Scanner(br);
                while(scan.hasNext()){
                    scan.next();
                result = scan.findWithinHorizon(target,0);
                System.out.println("Hooooooooooo!!!!!!!!!");
                if(result!=null) {
                    System.out.println("Hooooooooooo");
                        //word = (scan.next() + scan.findWithinHorizon("", 0));
                        ArrayList<String> names = new ArrayList<String>();
                        names.add(word);
                        for (int i=0; i< names.size() ; i++) {
                            System.out.println(names.get(i));
                        }

                }
               }
        }
        catch(FileNotFoundException e) {
                System.err.println("FileNotFoundException: Hello " + e.getMessage());
        
        
            
        }
       }
    }





/*File files[] = directory.listFiles();
for (File f : files) {
   // do whatever you want with each  File f
}*/