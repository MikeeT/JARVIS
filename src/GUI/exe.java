package GUI;

import Exceptions.AppNotFoundException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Class used to represent each program on
 * the computer.
 * @author Michael Topolski
 * February 2016.
 */
public class exe
{
    /**
     * this function will take in a key code for an application and
     * return the locations of the application
     * @param app the key code for an application
     * @return the file location of an application
     */
    public static String file(String app) throws AppNotFoundException
    {
        String file = "";
        File filename = new File("src/textDocs/exelocations.txt");
        try {
            Scanner exelocations = new Scanner(filename);
            while(exelocations.hasNextLine())
            {
                String line = exelocations.nextLine();
                String lineapp = line.split("~")[0];
                if (lineapp.equals(app)) return line.split("~")[1];
            }
            throw new AppNotFoundException("Could not find app" + app);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * This function will take in a new application name and the location
     * of the file, and place the information into the exelocations.txt file.
     * If the application is already in the file the function exits.
     * @param newName the key code for the new application
     * @param location the file location of the application
     */
    public static void addApp(String newName, String location)
    {
        File filename = new File("src/textDocs/exelocations.txt");
        try
        {
            Scanner exelocations = new Scanner(filename);
            while (exelocations.hasNextLine())
            {
                String line = exelocations.nextLine();
                String lineapp = line.split("~")[0];
                if (lineapp.equals(newName)) return;
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try
        {
            FileWriter fw = new FileWriter(
                    "C:/Users/Michael T/IdeaProjects/MyProjects/J.A.R.V.I.S/src/GUI/exelocations.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(newName + "~" + location);
            bw.close();
        } catch (Exception e) {}
    }
}
