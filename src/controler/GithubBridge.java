package controler;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GithubBridge
{
	GitHub gitHub;
	DownloadListener downloadListener;

	public GithubBridge(DownloadListener downloadListener)
	{
		this.downloadListener = downloadListener;
		//downloadListener.onDownloadFinished(null);

		try
		{

			GHRepository repository = gitHub.getRepository("https://github.com/AndreasHennings/Perceptronix");
			GHContent c = repository.getReadme();
			String s = c.toString();
			downloadListener.onDownloadFinished(s);
		}

		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
	
	public String[] getAllKeywords(String url)
	{
		return null;
	}


}
