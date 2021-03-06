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
	//private String accessToken="?<insert token here";

	ArrayList<String> allKeywords;
	ArrayList<String> allCategories;

	public GithubBridge(DownloadListener downloadListener, MessageListener ml, String repo)
	{
		this.ml = ml;
		this.downloadListener = downloadListener;

		allKeywords = new ArrayList<String>();
		allCategories = new ArrayList<String>();


		String[] infos = repo.split("\\s");
		ml.onMessage(infos.toString());

		if (infos.length<2)
		{
			String t =infos[0];
			infos= new String[2];
			infos[0]=t;
			infos[1]=t;
		}

		String r = "https://api.github.com/repos/"+infos[0].substring(19)+"/contents";
		ml.onMessage("Connecting to: "+r);
		String s = getJSONfromGITHUB(r);
		processJSON(s, infos);


		downloadListener.onDownloadFinished(allKeywords, allCategories);
	}

	private String getJSONfromGITHUB(String urlAsString)
	{
		String jsonString = "";

		try
		{
			URL url = new URL(urlAsString);

			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			ml.onMessage("Connection status: "+Integer.toString(connection.getResponseCode()));
			int respCode = connection.getResponseCode();
			if (respCode == HttpsURLConnection.HTTP_OK)
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

			else
			{
				ml.onMessage("Error. ResposeCode: "+respCode);
			}
			connection.disconnect();


		}

		catch (IOException e)
		{
			ml.onMessage("Connection error");
		}

		return jsonString;
	}

	private void processJSON(String jsonString, String[] infos)
	{
		JSONArray jsonArray=null;
		try
		{
			jsonArray = new JSONArray(jsonString);
		}

		catch (Exception e)
		{
			jsonString="["+jsonString+"]";
			jsonArray = new JSONArray(jsonString);
		}

		for (int i = 0; i < jsonArray.length(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String name = jsonObject.getString("name");
			String type = jsonObject.getString("type");

			if (type.equals("dir") || type.equals("tree"))
			{
				allKeywords.add("DIRECTORY_"+name.toLowerCase());
				allCategories.add(infos[1]);

				if (type.equals("tree"))
				{
					try
					{
						String dirURL = "" + infos[0] + "/git/trees/" + jsonObject.getString("sha") + "?recursive=1";// add +accessToken to String if you entered one above
						ml.onMessage("Connecting to: "+dirURL);
						processJSON(getJSONfromGITHUB(dirURL), infos);
					}
					catch (Exception e)
					{
						ml.onMessage("HTTPS connection error: "+e.getMessage().toString());
					}
				}
			}

			if (type.equals("file") || type.equals("blob"))
			{
				if (name==null) {name=jsonObject.getString("path");}

				String[] filename = name.split("\\.");

				allKeywords.add("FILENAME_" + filename[0].toLowerCase());
				allCategories.add(infos[1]);

				if (filename.length>1)
				{

					allKeywords.add("FILETYPE_" + filename[1].toLowerCase());
					allCategories.add(infos[1]);
				}

			}
		}
	}
}