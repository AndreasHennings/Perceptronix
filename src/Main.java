import controler.FileReader;
import model.Brain;
import controler.GithubBridge;
import controler.Config;
import view.UserInterface;

public class Main 
{

	/**
	 * @param args
	 */
	

	
	public static void main(String[] args)


	{

		UserInterface.main();

		UserInterface ui = new UserInterface(Config.CATEGORIES);
		Brain brain = new Brain(Config.CATEGORIES);
		GithubBridge githubBridge = new GithubBridge();

	}


	

}
