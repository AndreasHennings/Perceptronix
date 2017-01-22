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
	ArrayList<String> keywords;
	String[]categories;

	
	public Brain(MessageListener ml, ResultSet rs, ArrayList<String>keywords, String[]categories)
	{
		this.ml=ml;
		this.rs=rs;
		this.keywords=keywords;
		this.categories=categories;

		for(String cat : categories)
		{
			new Perceptron(cat,categories, keywords,rs,this);
		}

	}


	@Override
	public void onCalculationFinished(String cat, double result)
	{

	}
}
