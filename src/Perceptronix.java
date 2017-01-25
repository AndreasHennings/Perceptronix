import BUS.ButtonListener;
import BUS.DownloadListener;
import BUS.FileOperationListener;
import BUS.MessageListener;
import CPU.Brain;
import CPU.DataSet;
import IO.FileSysBridge;
import IO.GithubBridge;
import IO.UserInterface;
import MEM.DBController;
import ROM.Config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Perceptronix implements DownloadListener, MessageListener, ButtonListener, FileOperationListener
{
	public static final String[] CATEGORIES = Config.CATEGORIES;

	UserInterface ui;
	DBController d;

	public static void main(String[] args)
	{
		new Perceptronix().start();
	}

	public void start()
	{
		ui = UserInterface.main();
		ui.init(this);

		d= DBController.getInstance();
		d.main(this, CATEGORIES);

		onMessage("User Interface sucessfully initialized");
	}

	@Override
	public void onButtonPressed(String which, String filename)
	{
		onMessage("Accessing file: "+filename);
		FileSysBridge.getAllStrings(this, this, filename);
	}

	@Override
	public void onFileOperationFinished(ArrayList<String> repos)
	{
		onMessage("File operation finished");

		for (String repo : repos)
		{
			new GithubBridge(this, this, repo);
		}
	}

	@Override
	public void onDownloadFinished(ArrayList<String> allKeywords, ArrayList<String> allCategories)
	{
		onMessage("Download finished, received keywords: "+ Integer.toString(allKeywords.size()));

		ArrayList<String> usedRepos = new ArrayList<String>();
		ArrayList<DataSet> dataSet = null;

		try
		{
			dataSet = getDataSet();
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}

		for (int i=0; i<allCategories.size(); i++)
		{

			String cat=allCategories.get(i);

			if (cat.startsWith("https"))
			{

				if (!usedRepos.contains(cat))
				{
					usedRepos.add(cat);
					String repoURL = cat;

					new Brain(repoURL, allKeywords, dataSet,CATEGORIES, this);
				}
			}

			else
			{
				onMessage(allKeywords.get(i));
				onMessage(allCategories.get(i));
				d.updateEntry(allKeywords.get(i), allCategories.get(i));
			}
		}
	}

	@Override
	public void onMessage(String message)
	{
		ui.setText(message);
	}


	public ArrayList<DataSet> getDataSet() throws SQLException
	{
		ArrayList<DataSet> dataSet = new ArrayList<>();
		ResultSet resultSet = d.getData();
		while (resultSet.next())
		{
			String keyword = resultSet.getString("keyword");

			int[]values=new int[CATEGORIES.length];

			for (int i=0; i< values.length; i++)
			{
				values[i]=resultSet.getInt(CATEGORIES[i]);
			}

			DataSet ds = new DataSet(keyword,values);
			dataSet.add(ds);
		}
		return dataSet;
	}
}