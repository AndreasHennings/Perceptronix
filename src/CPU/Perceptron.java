package CPU;

import BUS.PerceptronListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Perceptron
{

	String name;
	ArrayList<String> keywords;
	String repoName;
	ResultSet rs;
	PerceptronListener pl;
	String[] categories;
	double result;

	
	public Perceptron(String name, String[] categories, ArrayList<String> keywords, String repoName, ResultSet rs, PerceptronListener pl)
	{

		this.name = name;
		this.keywords = keywords;
		this.repoName = repoName;
		this.rs = rs;
		this.pl = pl;
		this.categories = categories;
		result=0.0;
		System.out.println("Perceptron: " + name); //Hier stimmts noch, name = kategorie

		try
		{
			int counter=0;

			while (rs.next())
            {
				String s = rs.getString("keyword");


				if (keywords.contains(s))
				{
					System.out.println("Match found: "+s+" "+repoName+" "+name);
					counter++;
					int num = rs.getInt(name);
					int sum = 0;
					for (int i = 0; i<categories.length; i++ )
					{
						//System.out.println(rs.getInt(categories[i]));
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


		pl.onCalculationFinished(repoName+": "+name+" ", result);
	}

}
