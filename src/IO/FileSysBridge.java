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
    public static void getAllStrings(FileOperationListener fol, MessageListener ml, String filename)
    {
        ArrayList<String> allLines = new ArrayList<String>();

        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader("./resources/"+filename));
            ml.onMessage("File sucessfully opened");
            while(true)
            {
                String line = br.readLine();
                if (line==null)
                {
                    br.close();
                    break;
                }
                allLines.add(line);
                ml.onMessage(line);
            }
        }

        catch (Exception e)
        {
            ml.onMessage("Error: "+e.getMessage().toString());
        }

        fol.onFileOperationFinished(allLines);
    }
}
