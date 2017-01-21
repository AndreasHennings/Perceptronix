package IO;

import BUS.DownloadListener;
import BUS.MessageListener;
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
	MessageListener ml;

	ArrayList<String> allKeywords;
	ArrayList<String> allCategories;

	public GithubBridge(DownloadListener downloadListener, MessageListener ml, ArrayList<String> repos)
	{
		this.ml = ml;
		this.downloadListener = downloadListener;

		allKeywords = new ArrayList<String>();
		allCategories = new ArrayList<String>();

		for (String repo : repos)
		{
			String[] infos = repo.split("\\s");
			String r = "https://api.github.com/repos/"+infos[0].substring(19)+"/contents";
			ml.onMessage(r);
			String s = getJSONfromGITHUB(r);
			processJSON(s, infos);
		}

		downloadListener.onDownloadFinished(allKeywords, allCategories);
	}

	private String getJSONfromGITHUB(String urlAsString)
	{
		String jsonString = "";
		
		try
		{
			URL url = new URL(urlAsString);

			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			ml.onMessage(Integer.toString(connection.getResponseCode()));
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

		//jsonString+="]";
		return jsonString;
	}

	private void processJSON(String jsonString, String[] infos)
	{
		JSONArray jsonArray = new JSONArray(jsonString);
		for (int i = 0; i < jsonArray.length(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String name = jsonObject.getString("name");
			String type = jsonObject.getString("type");

			if (type.equals("dir") || type.equals("tree"))
			{
				allKeywords.add("DIRECTORY_"+name);
				allCategories.add(infos[1]);

				if (type.equals("tree"))
				{
					try
					{
						String dirURL = "" + infos[0] + "/git/trees/" + jsonObject.getString("sha") + "?recursive=1";
						processJSON(getJSONfromGITHUB(dirURL), infos);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}

			if (type.equals("file") || type.equals("blob"))
			{
				if (name==null) {name=jsonObject.getString("path");}

				String[] filename = name.split("\\.");

				try
				{
					allKeywords.add("FILENAME_" + filename[0]);
					allCategories.add(infos[1]);
				}
				catch (Exception e){}

				try
				{
					allKeywords.add("FILETYPE_" + filename[1]);
					allCategories.add(infos[1]);
				}

				catch (Exception e){}
			}
		}
	}
}