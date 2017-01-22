package CPU;

import BUS.MessageListener;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Brain
{
	Perceptron[] allPerceptrons;
	MessageListener ml;
	ResultSet rs;
	ArrayList<String> keywords;
	
	public Brain(MessageListener ml, ResultSet rs, ArrayList<String>keywords)
	{
		this.ml=ml;
		this.rs=rs;
		this.keywords=keywords;
	}

}
