package String;

public class realNumberGA 
{
	int[] population;
	int target;
	double mutationRate;
	int generation = 0;
	
	public realNumberGA(int populationSize, double mutationRate, int target)
	{
		this.mutationRate = mutationRate;
		this.target = target;
		generatePopulation(populationSize);
	}
	
	public void generatePopulation(int populationSize)
	{
		this.population = new int[populationSize];
		
		for(int i = 0; i < this.population.length; i++)
		{
			this.population[i] = (int)(Math.random() * 16777216);

		}
	}
	
	public int fitness(int chrom)
	{	
		return Math.abs(target-chrom);
	}
	
	public void Evolve()
	{
		int[] newPopulation = new int[population.length];
		
		for(int i = 0; i < population.length; i++)
		{
			int p1 = SelectParent();
			int p2 = SelectParent();
			
			int child = CrossOver(p1, p2);
			
			child = Mutate(child);
			
			newPopulation[i] = child;
		}
		generation  ++;
		population = newPopulation;
	}
	
	private int SelectParent()
	{
		int t1 = population[(int)(Math.random() * population.length)];
		int t2 = population[(int)(Math.random() * population.length)];
		
		return (fitness(t1) < fitness(t2)) ? t1 : t2;

	}
	
	private int CrossOver(int p1, int p2)
	{
		return (p1 + p2) /2;

	}
	
	private int Mutate(int child)
	{
		if(mutationRate > Math.random())
			child += (int)(Math.random() * 20000) - 10000;
		return child;
	}

}
