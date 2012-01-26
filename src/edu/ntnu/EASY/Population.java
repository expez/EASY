package edu.ntnu.EASY;
import edu.ntnu.EASY.individual.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Population implements Iterable<Individual> {

    private List<Individual> individuals;

     /**
     Creates a new population using a list of individuals.

     @param individuals The list of individuals to enter the
     population.
     */
    public Population( List<Individual> individuals ) {
	this.individuals = individuals;
    }

    /**
       Returns this highest fitness value in the population.
     */
    public double getMaxFitness(  ) {
	double max = 0;
	for (Individual individual : individuals) {
	    if( max < individual.getFitness() ) {
		max = individual.getFitness();
	    }
	} 
	return max;
    }

    public Iterator<Individual> iterator() {
	return individuals.iterator();
    }

    public List<Individual> getIndividuals() {
	return individuals;
    } 

}
