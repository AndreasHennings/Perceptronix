package controler;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by aend on 11.12.16.
 * This class opens a .txt file and returns an ArrayList of Github repository URLS and their corresponding category
 */
public class FileReader
{



    public static ArrayList<String> getAllStrings(String filename)
    {
        ArrayList<String> allLines = new ArrayList<String>();

        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader("Hallo.txt"));
            while(true)
            {
                String line = br.readLine();
                if (line==null)
                {
                    br.close();
                    break;
                }
                allLines.add(line);
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        return allLines;
    }
}
