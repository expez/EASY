package edu.ntnu.EASY.individual;

import edu.ntnu.EASY.FitnessCalculator;

public interface Individual<GType,PType> extends Comparable<Individual<?, PType>>{

	public double getFitness();
	
	public PType getPhenome();

	public GType getGenome();
	
	public void growUp();
	
	public double updateFitness(FitnessCalculator<PType> fitCalc);
}
