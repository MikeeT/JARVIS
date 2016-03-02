package Personality;

import java.io.*;
import java.util.Scanner;

/**
 * This class will represent the 'personality' of J.A.R.V.I.S.
 * How he talks, and responds to certain commands (voice/text)
 * @author Michael Topolski
 * February 2016.
 */
public class Talk
{
    /**
     * This is the main function called to return the answer
     * from J.A.R.V.I.S.
     * @param in the command entered in string format
     * @pre-condition 
     *      in must be in lowercase
     * @return the string answer from J.A.R.V.I.S.
     */
    public String answer(String in)
    {
        String ans = "";
        String[] inArray = in.split(" ");
        File filename = new File("src/textDocs/word.txt");
        Scanner words = null;
        try {
            words = new Scanner(filename);
            while(words.hasNextLine())
            {
                String line = words.nextLine();
                String word = line.split(" ")[0];
                if (word.toLowerCase().equals(inArray[0]))
                {
                    int count = 1;
                    ans = "";
                    while (count < line.split(" ").length)
                    {
                        ans += line.split(" ")[count] + " ";
                        count++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e);
        }
        return ans;
    }
}
