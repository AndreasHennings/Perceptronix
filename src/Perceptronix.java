import controler.DownloadListener;
import model.Brain;
import Config.Config;
import view.UserInterface;

public class Perceptronix
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
		Brain brain = new Brain(Config.CATEGORIES);
		DownloadListener downloadListener = new DownloadListener()
		{
			@Override
			public void onDownloadFinished()
			{

			}
		};
	}

}
