import model.Brain;
import controler.GithubBridge;
import controler.Config;
import view.UserInterface;

public class Main 
{

	/**
	 * @param args
	 */
	
	Brain brain;
	GithubBridge githubBridge;
	UserInterface userInterface;
	
	
	public static void main(String[] args)

	{

	}
	
	public void init()
	{
		brain = new Brain(Config.CATEGORIES);
		githubBridge = new GithubBridge();
		userInterface = new UserInterface();		
	}
}
