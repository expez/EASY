package edu.ntnu.EASY.parentSelectionMechanism;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;
import java.util.ArrayList;
import java.util.Collections;
public class Rank implements ParentSelectionMechanism {

    private int rank;
    
    public Rank( int rank ) {
	this.rank = rank;
    }

    public Population getParents( Population population ) {
	//Sort the population according to fitness.
	ArrayList sortedPopulation =
	    new ArrayList( Collections.sort( population.getIndividuals() ) );
	
	//Go through the list and drop unfit individuals.
	for (Individual individual : Population) {
	    
	    //We're done when rank number of individuals remain.
	    if( rank <= sortedPopulation.size() ) {
		break;
	    }
	    
	    population.remove( individual );
	} 
	return sortedPopulation;
    }
}
