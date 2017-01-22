package CPU;

import BUS.PerceptronListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Perceptron
{
	String cat;
	ArrayList<String> keywords;
	String repoName;
	ResultSet rs;
	PerceptronListener pl;
	String[] categories;
	double result;
	
	public Perceptron(String cat, String[] categories, ArrayList<String> keywords, String repoName, ResultSet rs, PerceptronListener pl)
	{
		this.cat=cat;
		this.keywords=keywords;
		this.repoName=repoName;
		this.rs=rs;
		this.pl=pl;
		this.categories=categories;

		result=0.0;

		try
		{
			int counter =0;
			while (rs.next())
            {
				String s = rs.getString("keyword");

				if (keywords.contains(s))
				{
					System.out.println("Match found: "+s+" "+repoName+" "+cat);
					counter++;
					int num = rs.getInt(cat);
					int sum = 0;
					for (int i = 0; i<categories.length; i++ )
					{
						sum+= rs.getInt(categories[i]);
					}

					result+= num/sum;
				}

            }
			if (counter>0)
			{
				result = result / counter;
			}

			else
			{
				result=0.0;
			}
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}


		pl.onCalculationFinished(repoName+": "+cat+" ", result);
	}

}
