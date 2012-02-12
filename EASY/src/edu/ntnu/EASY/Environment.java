package edu.ntnu.EASY;

public class Environment {

	public final int populationSize;
	public final int maxGenerations;
	public final double fitnessThreshold;
	
	private Environment(int populationSize, int maxGenerations, double fitnessThreshold){
		this.populationSize = populationSize;
		this.maxGenerations = maxGenerations;
		this.fitnessThreshold = fitnessThreshold;
	}
}
