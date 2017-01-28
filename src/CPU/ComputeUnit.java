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
    double inverse;

    public ComputeUnit(String category, String[] categories, ArrayList<String> keywords, String repoName, ArrayList<DataSet> DBdataSet, PerceptronListener pl)
    {
        this.keywords = keywords;
        this.repoName = repoName;
        this.pl = pl;
        this.categories = categories;
        result = 0.0;
        inverse = 1.0/categories.length;
        System.out.println("ComputeUnit: " + category+"dataSize: "+DBdataSet.size()+ "Inverse: "+inverse);

        int counter = 0;

        for (DataSet ds : DBdataSet)
        {
            String DB_Keyword = ds.getKeyword();


            if (keywords.contains(DB_Keyword))
            {
                counter++;

                double num = (double) ds.getValueByCategory(category);
                double sum = 0.0;

                System.out.println("Match found: keyword "+DB_Keyword+" in category "+category+"num: "+num);

                for (int i = 0; i < ds.getValues().length; i++)
                {
                    System.out.println(categories[i]+": "+ds.getValues()[i]);
                    sum += ds.getValues()[i];
                }

                System.out.println("Sum of all: "+sum);

                double ratio = num/sum;
                System.out.println("Ratio: "+ratio);

                double calcVal=dataSetStatisticCorrelation(ratio);

                System.out.println("Value of Dataset: "+calcVal);
                result+=calcVal;
                System.out.println("Result: "+result);
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

    private double dataSetStatisticCorrelation(double ratio)
    {
        if (ratio >= inverse)
        {
            System.out.println("Hi");
            return  (ratio-inverse)*(categories.length/(categories.length-1));
        }

        else
        {
            System.out.println("Lo");
            return (ratio * categories.length)-1;
        }
    }

}
