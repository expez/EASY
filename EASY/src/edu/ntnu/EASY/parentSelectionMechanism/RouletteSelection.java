package edu.ntnu.EASY.parentSelectionMechanism;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import edu.ntnu.EASY.individual.Individual; 

public class RouletteSelection< T extends Individual > {

	private int numParents;

	public RouletteSelection(int numParents) {
		this.numParents = numParents;
	}
	
	
	/**
	 * Fitness proportional selection of parents.
	 * @param adults a list to pick parents from.
	 * @return A list of size numParents containing the parents.
	 */
	public List< T >getParents( List< T > adults) {
		Random random = new Random();
		List< T > parents = new LinkedList< T >();
		double totalFitness = 0;
		
		for (T adult : adults) {
			totalFitness += adult.getFitness();
		}
		
		//Sort the input list in ascending order.
		Collections.sort( adults );
		
		//A list of intervals. [0, 1).
		List< Double > intervals = new LinkedList< Double >();
		double previousEndpoint = 0;
		double fitness;
		double scaledFitness;
		//Put each endpoint into a linked list.
		for (int adultIndex = 0; adultIndex < adults.size(); adultIndex++) {
			fitness = adults.get( adultIndex ).getFitness();
			scaledFitness = fitness / totalFitness;
			intervals.add( scaledFitness + previousEndpoint);
			previousEndpoint += scaledFitness;
		}
		
		while( parents.size() < numParents ) {
			double parentIndex = random.nextDouble();

			//Compare with the end points to pick a parent at random. The intervals are listed in ascending order and don't overlap.
			for (int i = 0; i < intervals.size(); i++) {
				if( parentIndex < intervals.get( i ) ) {
					//The order of the two lists will be the same so this mapping should be correct.
					parents.add( adults.get( i) );
					break;
				}
			}
		}	
		return parents;
	}
}
