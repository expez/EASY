package edu.ntnu.EASY.fitnessEvaluator;
import edu.ntnu.EASY.individual.*;
public class OneMaxFitness{

    /**
       Fitness is equal to sum of 1s in the individual's phenotype.

       @param individual to get the fitness for.
     */

    public OneMaxFitness() {
    }
    public double getFitness( BitvectorIndividual bitvectorIndividual ) {
	int[] phenotype = bitvectorIndividual.getPhenotype();
	int fitness = 0;
	for (int i = 0; i < phenotype.length ; i++) {
	    if( phenotype[ i ] == 1 ) {
		fitness++;
	    }
	}
	return fitness;
    } 
}
