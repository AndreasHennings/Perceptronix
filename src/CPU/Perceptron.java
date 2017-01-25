package CPU;

import BUS.PerceptronListener;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Perceptron
{


    ArrayList<String> keywords;
    String repoName;
    PerceptronListener pl;
    String[] categories;
    double result;


    public Perceptron(String category, String[] categories, ArrayList<String> keywords, String repoName, ArrayList<DataSet> DBdataSet, PerceptronListener pl)
    {
        this.keywords = keywords;
        this.repoName = repoName;
        this.pl = pl;
        this.categories = categories;
        result = 0.0;
        System.out.println("Perceptron: " + category);

        int counter = 0;

        for (DataSet ds : DBdataSet)
        {
            String DB_Keyword = ds.getKeyword();

            if (keywords.contains(DB_Keyword))
            {
                counter++;

                int num = ds.getValueByCategory(category);
                int sum = 0;

                for (int i = 0; i < ds.getValues().length; i++)
                {
                    sum += ds.getValues()[i];
                }

                result += num / sum;
            }
        }

        if (counter > 0)
        {
            result = result / counter;
        }

        else
        {
            result = 0.0;
        }

        pl.onCalculationFinished(category, result);
    }

}
