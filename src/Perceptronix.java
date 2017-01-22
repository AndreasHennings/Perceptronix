import BUS.ButtonListener;
import BUS.DownloadListener;
import BUS.FileOperationListener;
import BUS.MessageListener;
import CPU.Brain;
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
	/**
	 * @param args
	 */

	UserInterface ui;
	public static final String[] CATEGORIES = Config.CATEGORIES;
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
		switch (which)
		{
			case "train":
				trainAI(filename);
				break;
			case "categorize":
				categorize(filename);
				break;
			default: break;
		}
	}

	private void trainAI(String filename)
	{
		onMessage("Accessing file");
		FileSysBridge.getAllStrings(this, this, filename);
	}

	private void categorize(String filename)
	{
		onMessage("Accessing file");
		FileSysBridge.getAllStrings(this, this, filename);

	}

	@Override
	public void onFileOperationFinished(ArrayList<String> repos)
	{
		onMessage("File read");

		for (String repo : repos)
		{
			new GithubBridge(this, this, repo);
		}
	}

	@Override
	public void onDownloadFinished(ArrayList<String> allKeywords, ArrayList<String> allCategories)
	{
		onMessage("Download Finished! "+ Integer.toString(allKeywords.size())+" "+Integer.toString(allCategories.size()));

		ArrayList<String> usedRepos = new ArrayList<String>();

		for (int i=0; i<allCategories.size(); i++)
		{

			String cat=allCategories.get(i);

			if (cat.startsWith("https"))
			{
				if (!usedRepos.contains(cat))
				{
					usedRepos.add(cat);
					new Brain(this, d.getData(), allKeywords, cat, CATEGORIES);
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








}