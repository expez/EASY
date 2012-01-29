package edu.ntnu.EASY;
import java.util.List;

import edu.ntnu.EASY.individual.*;
public class OneMaxFitness {

    /**
       Fitness is equal to sum of 1s in the individual's phenotype.

       @param individual to get the fitness for.
     */

    public OneMaxFitness() {}
    
	 public void updateFitness(List<BitvectorIndividual> individuals) {
		double fitness;	
		int[] phenotype;
		for (BitvectorIndividual individual : individuals) {
			fitness = 0;
			phenotype = individual.getPhenotype();
	    	for (int i = 0; i < phenotype.length; i++) {
	    		if( phenotype[ i ] == 1 ) {
	    			fitness++;
	    		}
	    	}
	    	individual.setFitness( fitness / (double)phenotype.length);
		}
	 }
}