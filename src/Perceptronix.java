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

	public static void main(String[] args)
	{
		new Perceptronix().start();
	}

	public void start()
	{
		UserInterface ui = UserInterface.main();
		ui.init(this);
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
	public void onMessage(String target, String message)
	{
		System.out.println(target+message);
	}

	@Override
	public void onButtonPressed(String which)
	{
		switch (which)
		{
			case "train":
				trainAI();
				break;
			case "categorize":
				categorize();
				break;
		}
	}

	private void categorize()
	{
		System.out.println("categorize");
	}

	private void trainAI()
	{
		System.out.println("trainAI");
	}
}