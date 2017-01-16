import BUS.DownloadListener;
import MEM.DBController;

import java.sql.ResultSet;

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
		d.updateEntry("neu", "c1");
		d.updateEntry("nasdeu", "c1");
		d.updateEntry("neu", "c2");
		d.updateEntry("neasu", "c1");
		d.updateEntry("neu", "c2");
		d.updateEntry("neau", "c1");
		d.updateEntry("neu", "c1");
		d.updateEntry("neu", "c1");
		d.getData();
		//ResultSet rs = d.getData();




		//UserInterface ui = new UserInterface(Config.CATEGORIES);
		//Brain brain = new Brain(Config.CATEGORIES);
	}

	@Override
	public void onDownloadFinished(String s)
	{
		System.out.println(s);
	}
}
