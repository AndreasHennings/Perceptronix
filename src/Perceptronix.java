import BIOS.Config;
import BUS.ButtonListener;
import BUS.DownloadListener;
import BUS.FileOperationListener;
import BUS.MessageListener;
import IO.FileSysBridge;
import IO.GithubBridge;
import IO.UserInterface;
import MEM.DBController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Perceptronix implements DownloadListener, MessageListener, ButtonListener, FileOperationListener
{
	/**
	 * @param args
	 */

	UserInterface ui;

	public static void main(String[] args)
	{
		new Perceptronix().start();
	}

	public void start()
	{
		ui = UserInterface.main();
		ui.init(this);
		onMessage("User Interface sucessfully initialized");
		//FileSysBridge.getAllStrings(this);
		//new GithubBridge(this, this);
		//DBController d= DBController.getInstance();
		//d.main(this);
		//ResultSet rs = d.getData();

		//Brain brain = new Brain(Config.CATEGORIES);
	}

	@Override
	public void onDownloadFinished(ArrayList<String> allKeywords, ArrayList<String> allCategories)
	{
		System.out.println();
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