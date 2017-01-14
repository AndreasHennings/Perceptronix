package controler;

import org.json.JSONArray;
import org.json.JSONObject;


import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class GithubBridge
{
	DownloadListener downloadListener;
	ArrayList<String> allKeywords;
	String repo;

	public GithubBridge(DownloadListener downloadListener)
	{
		this.downloadListener = downloadListener;
		allKeywords = new ArrayList<String>();
		//downloadListener.onDownloadFinished(null);

		repo = "https://api.github.com/repos/AndreasHennings/Perceptronix";
		String s =getJSONfromGITHUB(repo +"/contents");

		processJSON(s);
		System.out.println(allKeywords);


	}

	private String getJSONfromGITHUB(String urlAsString)
	{
		String jsonString = "";
		
		try
		{
			URL url = new URL(urlAsString);

			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			//System.out.println(connection.getResponseCode());
			if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK)
			{
				InputStream is = connection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = br.readLine()) != null)
				{
					jsonString += line;
				}
				br.close();
				is.close();
			}
			connection.disconnect();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return jsonString;
	}

	private void processJSON(String jsonString)
	{
		JSONArray jsonArray = new JSONArray(jsonString);
		for (int i = 0; i < jsonArray.length(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String name = jsonObject.getString("name");
			String type = jsonObject.getString("type");

			if (type.equals("file") || type.equals("blob"))
			{
				if (name==null) {name=jsonObject.getString("path");}
				String[] filename = name.split("\\.");
				allKeywords.add(filename[0]);
				allKeywords.add("." + filename[1]);
				System.out.println(filename[0]+" : "+filename[1]);
			}

			if (type.equals("dir"))
			{
				try
				{
					String dirURL = "" + repo + "/git/trees/" + jsonObject.getString("sha") + "?recursive=1";
					System.out.println(getJSONfromGITHUB(dirURL));
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}