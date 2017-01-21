import BUS.DownloadListener;
import BUS.MessageListener;
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
		//UserInterface.main();
		//FileSysBridge.getAllStrings(this);
		//new GithubBridge(this);
		DBController d= DBController.getInstance();
		d.main();

		//ResultSet rs = d.getData();




		//UserInterface ui = new UserInterface(Config.CATEGORIES);
		//Brain brain = new Brain(Config.CATEGORIES);
	}

	@Override
	public void onDownloadFinished(String s)
	{
		System.out.println(s);
	}

	@Override
	public void onMessage()
	{

	}
}
