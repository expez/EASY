package edu.ntnu.EASY.individual;
public class Individual implements Comparable<Individual>{

	private double fitness;

	private void growup() {

	}

	public void setFitness( double fitness ) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public int compareTo(Individual individual) {
		if( this.fitness == individual.getFitness() ) {
			return 0;
		}
		else if( this.fitness > individual.getFitness() ) {
			return 1;
		}
		return -1;
	}
}
