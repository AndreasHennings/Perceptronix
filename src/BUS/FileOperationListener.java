package BUS;

import java.util.ArrayList;

/**
 * Created by aend on 21.01.17.
 */
public interface FileOperationListener
{
    public void onFileOperationFinished(ArrayList<String> repos);
}
