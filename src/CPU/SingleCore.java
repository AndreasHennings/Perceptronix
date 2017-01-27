package CPU;

import BUS.MessageListener;
import BUS.PerceptronListener;

import java.util.ArrayList;

public class SingleCore implements PerceptronListener
{
	MessageListener ml;
	ArrayList<String> allKeywords;
	String repoURL;
	String[]categories;
	ArrayList<ValueSet>valueSets;
	
	public SingleCore(String repoURL, ArrayList<String>allKeywords, ArrayList<DataSet> dataSet, String[]categories, MessageListener ml)
	{
		this.ml=ml;
		this.allKeywords =allKeywords;
		this.repoURL=repoURL;
		this.categories=categories;
		ml.onMessage("New SingleCore for Repo: "+repoURL);
		valueSets=new ArrayList<>();

		for(int i=0; i<categories.length; i++)
		{
			String cat=categories[i];
			new ComputeUnit(cat, categories, allKeywords, repoURL, dataSet, this);
			ml.onMessage("New ComputeUnit for category: "+cat);
		}

		while (valueSets.size()< categories.length)
		{
			ml.onMessage("Calculating");
		}

		showResultForRepo();
	}

	private void showResultForRepo()
	{
		double max=0.0;
		String resultCat="Sorry, not enough data.";

		for (ValueSet vs : valueSets)
		{
			//double res = (vs.getVal()+1)*50;
			double res = vs.getVal();
			ml.onMessage("Probability for "+repoURL+" to be in category "+vs.getCat()+" is "+res+" percent");
			if (vs.getVal()>max)
			{
				resultCat = vs.getCat();
				max=vs.getVal();
			}
		}

		ml.onMessage("My bet for "+repoURL+" is: "+resultCat);
	}

	@Override
	public void onCalculationFinished(String category, double result)
	{
		ValueSet vs = new ValueSet(category, result);
		valueSets.add(vs);
	}
}
