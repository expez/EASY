package edu.ntnu.EASY.selection.parent;

import edu.ntnu.EASY.Population;

public class BoltzmanSelector<PType> implements ParentSelector<PType>{
	
//	private int numParents;
//	private FitnessProportionateSelector<PType> rouletteSelection;

	public BoltzmanSelector( int numParents ) {
		throw new RuntimeException("Not implemented.");
//		this.numParents = numParents;
//		rouletteSelection = new FitnessProportionateSelector<Individual>( numParents);
	}


	/**
	 * Simulated annealing is used where degree of randomness in selection is a function of the
	 * temperature parameter. Thus selection pressure increases as temperature drops.
	 * @param adults the list of individuals from which to choose new parents.
	 * @param temperature Higher temperature means more randomness in selection. Should
	 * increase in proportion to the number of generations.
	 * @return A list of parents.
	 */
	@Override
	public <GType> Population<GType, PType> select(Population<GType, PType> population) {
		throw new RuntimeException("Not implemented.");
//		List< Individual > adultsCopy = new LinkedList< Individual >( adults);
//		LinkedList< T > parents = new LinkedList< T >();
//		
//		double populationAverage = 0;
//		for (Individual adult : adults) {
//			populationAverage += Math.exp( adult.getFitness()  / temperature );
//		}
//		populationAverage /= adults.size();
//		
//		double boltzmanFitness;
//		for (Individual adult : adults) {
//			boltzmanFitness = Math.exp( adult.getFitness() / temperature ) / populationAverage;
//			adult.setFitness( boltzmanFitness );
//		}
//		
//		return rouletteSelection.getParents(adultsCopy);
	}

}
