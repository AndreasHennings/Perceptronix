package controler;

public class GithubBridge
{
	DownloadListener downloadListener;

	public GithubBridge(DownloadListener downloadListener)
	{
		this.downloadListener = downloadListener;
		downloadListener.onDownloadFinished(null);
	}
	
	public String[] getAllKeywords(String url)
	{
		return null;
	}


}
