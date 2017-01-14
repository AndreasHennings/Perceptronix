package controler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

	public GithubBridge(DownloadListener downloadListener)
	{
		this.downloadListener = downloadListener;
		//downloadListener.onDownloadFinished(null);


		try
		{
			String jsonString = "[";

			URL url = new URL("https://api.github.com/repos/AndreasHennings/Perceptronix");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			System.out.println(connection.getResponseCode());
			if (connection.getResponseCode()==HttpsURLConnection.HTTP_OK)
			{
				InputStream is = connection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line=br.readLine())!=null)
				{
					jsonString+=line;

				}
				br.close();
				is.close();
			}



			connection.disconnect();
			jsonString+="]";
			JSONArray jsonArray = new JSONArray(jsonString);
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			String name = jsonObject.getString("name");
			System.out.println(name);

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
