package controler;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by aend on 11.12.16.
 * This class opens a .txt file and returns an ArrayList of Github repository URLS and their corresponding category
 */
public class FileSysBridge
{



    public static ArrayList<String> getAllStrings(DownloadListener downloadListener)
    {
        ArrayList<String> allLines = new ArrayList<String>();


        try
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader("/home/aend/workspace/Perceptronix/src/config/defaultList.txt"));
            while(true)
            {
                String line = br.readLine();
                System.out.println(line);
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


       downloadListener.onDownloadFinished(allLines);

       return null;
    }
}
