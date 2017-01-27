package CPU;

import BUS.PerceptronListener;
import java.util.ArrayList;

public class ComputeUnit
{


    ArrayList<String> keywords;
    String repoName;
    PerceptronListener pl;
    String[] categories;
    double result;


    public ComputeUnit(String category, String[] categories, ArrayList<String> keywords, String repoName, ArrayList<DataSet> DBdataSet, PerceptronListener pl)
    {
        this.keywords = keywords;
        this.repoName = repoName;
        this.pl = pl;
        this.categories = categories;
        result = 0.0;
        System.out.println("ComputeUnit: " + category);

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

                double ratio = num/sum;
                double calcVal;

                if (ratio>=(1/7))
                {
                    calcVal = (ratio-1/7)*7/6;
                }

                else
                {
                    calcVal = (ratio*-7)-1;
                }

                result+=calcVal;
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
