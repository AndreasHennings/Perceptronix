import BUS.ButtonListener;
import BUS.DownloadListener;
import BUS.FileOperationListener;
import BUS.MessageListener;
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

	public static void main(String[] args)
	{
		new Perceptronix().start();
	}

	public void start()
	{
		/*
		DBController d= DBController.getInstance();
		d.main(this, CATEGORIES);
		ResultSet rs = d.getData();

		System.out.println(rs.toString());

		*/

		ui = UserInterface.main();
		ui.init(this);
		onMessage("User Interface sucessfully initialized");

		//FileSysBridge.getAllStrings(this);
		//new GithubBridge(this, this);
		//
		//
		//ResultSet rs = d.getData();

		//Brain brain = new Brain(Config.CATEGORIES);
	}

	@Override
	public void onDownloadFinished(ArrayList<String> allKeywords, ArrayList<String> allCategories)
	{
		DBController d= DBController.getInstance();
		d.main(this, CATEGORIES);

		for (int i =0; i<allKeywords.size(); i++)
		{
			onMessage(allKeywords.get(i));
			onMessage(allCategories.get(i));
			d.updateEntry(allKeywords.get(i), allCategories.get(i));
		}
		//ResultSet rs = d.getData();

		//System.out.println(rs.toString());

	}

	@Override
	public void onMessage(String message)
	{
		ui.setText(message);
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

	private void categorize(String filename)
	{
		System.out.println("categorize");
	}

	private void trainAI(String filename)
	{
		FileSysBridge.getAllStrings(this, this, filename);
	}

	@Override
	public void onFileOperationFinished(ArrayList<String> repos)
	{
		new GithubBridge(this, this, repos);
	}
}