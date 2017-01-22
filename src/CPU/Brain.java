package CPU;

import BUS.MessageListener;
import BUS.PerceptronListener;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Brain implements PerceptronListener
{
	//Perceptron[] allPerceptrons;
	MessageListener ml;
	ResultSet rs;
	ArrayList<String> allKeywords;
	String repoName;
	String[]categories;
	ArrayList<Perceptron>ps;

	
	public Brain(MessageListener ml, ResultSet rs, ArrayList<String>allKeywords, String repoName, String[]categories)
	{
		this.ml=ml;
		this.rs=rs;
		this.allKeywords =allKeywords;
		this.repoName=repoName;
		this.categories=categories;
		ml.onMessage("New Brain: "+repoName);

		for(int i=0; i<categories.length; i++)
		{
			String cat=categories[i];
			ml.onMessage("New Perceptron: "+cat+" "+repoName);
			new Perceptron(cat, categories, allKeywords, repoName, rs, this);

		}

	}


	@Override
	public void onCalculationFinished(String cat, double result)
	{
		ml.onMessage(cat+": "+result);
	}
}
