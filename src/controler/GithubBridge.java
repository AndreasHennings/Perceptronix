package controler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;


import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;


public class GithubBridge
{
	DownloadListener downloadListener;
	ArrayList<String> allKeywords;

	public GithubBridge(DownloadListener downloadListener)
	{
		this.downloadListener = downloadListener;
		allKeywords = new ArrayList<String>();
		//downloadListener.onDownloadFinished(null);

	}

	private String getJSONfromGITHUB(URL url)
	{
		String jsonString = "";

		try
		{
			//URL url = new URL("https://api.github.com/repos/AndreasHennings/Perceptronix/contents");

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

			if (type.equals("file"))
			{
				String[] filename = name.split("\\.");
				allKeywords.add(filename[0]);
				allKeywords.add("." + filename[1]);
				//System.out.println(filename[0]+" : "+filename[1]);
			} else
			{

			}

			System.out.println(allKeywords);
		}
	}

}