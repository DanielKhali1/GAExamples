package Color;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.*;

public class StringMatchingGA 
{
	public String[] population;
	public double mutationRate;
	private String alph;
	public String word;
	public int generation = 0;
	
	public StringMatchingGA(int populationSize, double mutationRate, String word, boolean color)
	{
		this.alph = (color)? "1234567890abcdef" : "abcdefghijklmnopqrstuvwxyz ";
		this.word = word;
		this.mutationRate = mutationRate;
		generatePopulation(populationSize, word.length());
	}
	
	public void generatePopulation(int populationSize, int chromSize)
	{
		this.population = new String[populationSize];
		
		for(int i = 0; i < this.population.length; i++)
		{
			this.population[i] = "";

			for(int j = 0; j < chromSize; j++)
			{
				int rand = (int)(Math.random() * alph.length());
				this.population[i] += alph.substring(rand, rand+1);
			}
		}
	}
	
	public int fitness(String chrom)
	{
		int sum = 0;
		for(int i = 0; i < word.length(); i++)
		{
			if(word.substring(i, i+1).equals(chrom.substring(i, i+1)))
			{
				sum++;
			}
		}
		
		return sum;
	}
	
	public void Evolve()
	{
		String[] newPopulation = new String[population.length];
		
		for(int i = 0; i < population.length; i++)
		{
			String p1 = SelectParent();
			String p2 = SelectParent();
			
			String child = CrossOver(p1, p2);
			
			child = Mutate(child);
			
			newPopulation[i] = child;
		}
		generation ++;
		population = newPopulation;
	}
	
	private String SelectParent()
	{
		String t1 = population[(int)(Math.random() * word.length())];
		String t2 = population[(int)(Math.random() * word.length())];
		
		return (fitness(t1) > fitness(t2)) ? t1 : t2;

	}
	
	private String CrossOver(String p1, String p2)
	{
		//pick a random crossover point
		int cp = (int)(Math.random() * p1.length());
		
		return p1.substring(0, cp) + p2.substring(cp);

	}
	
	private String Mutate(String child)
	{
		String c = "";
		for(int i = 0; i < child.length(); i++)
		{
			if(mutationRate > Math.random())
			{
				int rand = (int)(Math.random() * alph.length());
				c += alph.charAt(rand);
			}
			else
			{
				c += child.charAt(i);
			}
		}
		
		return c;
	}
	
//	private String best() {
//		return Arrays.stream(population)
//				.max(Comparator.comparing(StringMatchingGA::fitness));
//		
//	}
//	
//	class ClassifiedString implements Comparator<String>
//	{
//		String str;
//		
//		public ClassifiedString(String str) {
//			this.str = str;
//		}
//
//		@Override
//		public int compare(String o1, String o2) {
//			
//			return 0;
//		}
//		
//		
//		
//	}

}
