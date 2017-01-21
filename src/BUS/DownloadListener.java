package BUS;

import java.util.ArrayList;

/**
 * Created by aend on 12.12.16.
 */
public interface DownloadListener
{
    public void onDownloadFinished(ArrayList<String> allKeywords, ArrayList<String> allCategories);
}
