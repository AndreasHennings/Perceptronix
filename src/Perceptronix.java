import controler.DownloadListener;
import controler.FileSysBridge;
import controler.GithubBridge;
import model.DBController;
import config.Config;
import view.UserInterface;

import java.util.ArrayList;

public class Perceptronix implements DownloadListener
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
		d.updateEntry("word", "keyword");
		d.getData();



		//UserInterface ui = new UserInterface(Config.CATEGORIES);
		//Brain brain = new Brain(Config.CATEGORIES);
	}

	@Override
	public void onDownloadFinished(String s)
	{
		System.out.println(s);
	}
}
