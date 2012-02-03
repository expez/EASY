package edu.ntnu.EASY.parentSelectionMechanism;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.ntnu.EASY.individual.Individual;

public class SigmaScaledSelection< T extends Individual > {

	private int numParents;

	public SigmaScaledSelection(int numParents) {
		this.numParents = numParents;
	}
	
	public List< T > getParents( List< T > adults) {
		double totalFitness = 0;
		
		for (T adult : adults) {
			totalFitness += adult.getFitness();
		}
		
		//Sort the input list in ascending order.
		Collections.sort( adults );
		
		//When the list is reversed the larger intervals will come first and lookup will go faster.
		Collections.reverse(adults);
		
		//A list of intervals. [0, 1).
		List< Double > intervals = new LinkedList< Double >();
		
		double previousEndpoint = 0;
		double fitness;
		double meanFitness = totalFitness / adults.size();
		double fitnessVariance = FitnessProportionalSelection.getFitnessVariance(adults, meanFitness);
		double sigmaFitness;
		
		//Put each endpoint into a linked list.
		for (int adultIndex = 0; adultIndex < adults.size(); adultIndex++) {
			fitness = adults.get( adultIndex ).getFitness();
			sigmaFitness = 1 + ( ( fitness - meanFitness ) / ( 2 * fitnessVariance) ); 
			intervals.add( sigmaFitness + previousEndpoint );
			previousEndpoint += sigmaFitness;
		}
		
		//Normalize the sigma values so the intervals are in the range [0,1]
		for (Double interval : intervals) {
			//The value of the last end point is the sum of all the sigmaFitness values.
			interval /= previousEndpoint;
		}
		
		return FitnessProportionalSelection.getParentList( adults, intervals, numParents );
	}

}
