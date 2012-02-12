package edu.ntnu.EASY.individual;


import edu.ntnu.EASY.Population;

public interface FitnessCalculator<PType> {
	
	public void setPopulation(Population<?,PType> population);
	
	public double calculate(PType phenom);

}
