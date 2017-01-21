package IO;

import BUS.DownloadListener;
import BUS.FileOperationListener;
import BUS.MessageListener;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by aend on 11.12.16.
 * This class opens a .txt file and returns an ArrayList of Github repository URLS and their corresponding category
 */
public class FileSysBridge
{
    public static ArrayList<String> getAllStrings(FileOperationListener fol, MessageListener ml)
    {
        ArrayList<String> allLines = new ArrayList<String>();

        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader("./resources/defaultList.txt"));
            ml.onMessage("file", "file sucessfully opened");
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
            ml.onMessage("file", "Error: "+e.getMessage().toString());
        }


       //downloadListener.onDownloadFinished(allLines);

       return null;
    }
}