package edu.ntnu.EASY.individual;

public interface Individual<GType,PType> extends Comparable<Individual<?, PType>>{

	public double getFitness();
	
	public PType getPhenome();

	public GType getGenome();
	
	public void growUp();
	
	public double calculateFitness(FitnessCalculator<PType> fitCalc);
}
