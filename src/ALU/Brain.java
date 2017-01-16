package ALU;

public class Brain
{
	Perceptron[] allPerceptrons;
	
	public Brain(String[] categories)
	{
		allPerceptrons = new Perceptron[categories.length];
		
		for (int i = 0; i<allPerceptrons.length; i++)
		{
			allPerceptrons[i] = new Perceptron(categories[i]);			
		}
		
	}

}
