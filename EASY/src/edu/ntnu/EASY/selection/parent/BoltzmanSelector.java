/*Copyright (C) 2012 Lars Andersen, Tormund S. Haus.
larsan@stud.ntnu.no
tormunds@stud.ntnu.no

EASY is free software: you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
 
EASY is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.
 
You should have received a copy of the GNU General Public License
    along with EASY.  If not, see <http://www.gnu.org/licenses/>.*/
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
