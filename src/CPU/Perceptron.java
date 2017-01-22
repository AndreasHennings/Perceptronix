package CPU;

import BUS.PerceptronListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Perceptron
{
	String cat;
	ArrayList<String> keywords;
	ResultSet rs;
	PerceptronListener pl;
	String[] categories;
	double result;
	
	public Perceptron(String cat, String[] categories, ArrayList<String> keywords, ResultSet rs, PerceptronListener pl)
	{
		this.cat=cat;
		this.keywords=keywords;
		this.rs=rs;
		this.pl=pl;
		this.categories=categories;

		result=0.0;

		try
		{
			while (rs.next())
            {
				String s = rs.getString("keyword");
				if (keywords.contains(s))
				{
					int num = rs.getInt(cat);
					int sum = 0;
					for (int i = 0; i<categories.length; i++ )
					{
						sum+= rs.getInt(categories[i]);
					}

					double d = num/sum;
				}

            }
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}


		pl.onCalculationFinished(cat, result);
	}

}
