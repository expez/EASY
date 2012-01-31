package edu.ntnu.EASY.adultSelectionMechanism;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GenerationalMixing< T extends Comparable< ? super T >> {
	
	private int numAdults;

	public GenerationalMixing( int numAdults) {
		this.numAdults = numAdults;
	}

	public List< T > getAdults( List< T > children, List< T > adults ) {
		List< T> population = new LinkedList< T >();
		population.addAll( children );
		population.addAll( adults );
		Collections.sort( population );
		
		int numToDrop = population.size() - numAdults;
		
		return population.subList( numToDrop, population.size() );
	}
}
