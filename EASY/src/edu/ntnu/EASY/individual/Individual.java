package edu.ntnu.EASY.individual;

public interface Individual<GType,PType> {

	public PType getPhenome();

	public GType getGenome();
	
	public void growUp();
	
	public double calculateFitness(FitnessCalculator<PType> fitCalc);
}
