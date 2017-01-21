import BIOS.Config;
import BUS.DownloadListener;
import BUS.MessageListener;
import IO.GithubBridge;
import IO.UserInterface;
import MEM.DBController;

import java.sql.ResultSet;

public class Perceptronix implements DownloadListener, MessageListener
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
		UserInterface.main();
		UserInterface ui = new UserInterface(Config.CATEGORIES);

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
}
