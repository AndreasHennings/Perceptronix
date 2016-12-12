import controler.DownloadListener;
import controler.FileSysBridge;
import model.Brain;
import Config.Config;
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
		UserInterface.main();
		ArrayList<String> allListItems = FileSysBridge.getAllStrings(this, "Hallo.txt");

		UserInterface ui = new UserInterface(Config.CATEGORIES);
		Brain brain = new Brain(Config.CATEGORIES);
	}

	@Override
	public void onDownloadFinished(ArrayList<String> allLines)
	{

	}
}
