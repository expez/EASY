package edu.ntnu.EASY;



public interface FitnessCalculator<PType> {
	
	public void setPopulation(Population<?,PType> population);
	
	public double calculate(PType phenom);

}
