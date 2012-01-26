package edu.ntnu.EASY.parentSelectionMechanism;

import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Rank implements ParentSelectionMechanism {

    private int rank;
    
    public Rank( int rank ) {
	this.rank = rank;
    }

    public Population getParents( Population population ) {

	//Copy the individuals
	List<Individual> individuals = new ArrayList<Individual>( population.getIndividuals() );

	//Sort the individuals according to fitness.
	Collections.sort(individuals);
 	
	int numToDrop = 0;

	// Find number of individuals to drop from list.
	// We only want rank number of individuals to remain.
	while( numToDrop < individuals.size() - rank ) {
	    numToDrop++;
	}
	// Return a new population from subset of individuals.
	Population parents =  new Population( individuals.subList( numToDrop, individuals.size() ) );
	return parents;
    }
}

