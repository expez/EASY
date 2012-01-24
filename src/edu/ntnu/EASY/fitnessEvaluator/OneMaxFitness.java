package edu.ntnu.EASY.fitnessEvaluator;

public class OneMaxFitness implements FitnessEvaluator {

    /**
       Fitness is equal to sum of 1s in the individual's phenotype.

       @param individual to get the fitness for.

       @return The fitness of the input individual.
     */

    public OneMaxFitness() {
    }
    public double getFitness( Individual individual ) {
	int[] phenotype = individual.getPhenotype();
	int fitness = 0;
	for (int i = 0; i < phenotype.length ; i++) {
	    if( phenotype[ i ] == 1 ) {
		fitness++;
	    }
	}
	return fitness;
    } 
}
