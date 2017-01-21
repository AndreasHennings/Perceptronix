import BIOS.Config;
import BUS.ButtonListener;
import BUS.DownloadListener;
import BUS.MessageListener;
import IO.GithubBridge;
import IO.UserInterface;
import MEM.DBController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Perceptronix implements DownloadListener, MessageListener, ButtonListener
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




		//
		//Brain brain = new Brain(Config.CATEGORIES);
	}

	@Override
	public void onDownloadFinished(String s)
	{
		System.out.println(s);
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
		}
	}

	private void categorize(String filename)
	{
		System.out.println("categorize");
	}

	private void trainAI(String filename)
	{
		ui.setText("trainAI: "+filename);
	}
}