package edu.ntnu.EASY.selection.parent;

import java.util.LinkedList;
import java.util.List;
import java.lang.Math;

import edu.ntnu.EASY.individual.Individual;

public class BoltzmanSelection< T extends Comparable< ? super Individual > > {
	
	private int numParents;
	private FitnessProportionalSelection< ? extends Individual > rouletteSelection;

	public BoltzmanSelection( int numParents ) {
		this.numParents = numParents;
		rouletteSelection = new FitnessProportionalSelection<Individual>( numParents);
	}
	
	
	/**
	 * Simulated annealing is used where degree of randomness in selection is a function of the
	 * temperature parameter. Thus selection pressure increases as temperature drops.
	 * @param adults the list of individuals from which to choose new parents.
	 * @param temperature Higher temperature means more randomness in selection. Should
	 * increase in proportion to the number of generations.
	 * @return A list of parents.
	 */
	public List<? extends Individual> getParents( List< ? extends Individual > adults, int temperature) {
		List< Individual > adultsCopy = new LinkedList< Individual >( adults);
		LinkedList< T > parents = new LinkedList< T >();
		
		double populationAverage = 0;
		for (Individual adult : adults) {
			populationAverage += Math.exp( adult.getFitness()  / temperature );
		}
		populationAverage /= adults.size();
		
		double boltzmanFitness;
		for (Individual adult : adults) {
			boltzmanFitness = Math.exp( adult.getFitness() / temperature ) / populationAverage;
			adult.setFitness( boltzmanFitness );
		}
		
		return rouletteSelection.getParents(adultsCopy);
	}

}
