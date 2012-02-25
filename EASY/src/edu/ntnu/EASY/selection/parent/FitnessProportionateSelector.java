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

import static edu.ntnu.EASY.util.Util.RNG;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;

public class FitnessProportionateSelector<PType> implements ParentSelector<PType> {

	private int numParents;

	public FitnessProportionateSelector(int numParents){
		this.numParents = numParents;
	}
	
	/**
	 * Fitness proportional selection of parents.
	 * @param population a list to pick parents from.
	 * @return A list of size numParents containing the parents.
	 */
	@Override
	public <GType> Population<GType, PType> select(Population<GType, PType> population) {
		double totalFitness = 0;
		for (Individual<GType, PType> individual : population) {
			totalFitness += individual.getFitness();
		}
		
		//Sort the input list in ascending order.
		population.sort();
		
		//A list of intervals. [0, 1).
		double[] intervals = new double[population.size()];
		double previousEndpoint = 0;
		double fitness;
		double normalizedFitness;
		//Put each endpoint into an array.
		for (int adultIndex = 0; adultIndex < population.size(); adultIndex++) {
			fitness = population.get(adultIndex).getFitness();
			normalizedFitness = fitness / totalFitness;
			intervals[adultIndex] = (normalizedFitness + previousEndpoint);
			previousEndpoint += normalizedFitness;
		}
		return getParentList(population, intervals, numParents);
	}
	
	 static <GType, PType> Population<GType,PType> getParentList(Population<GType, PType> population, double[] intervals, int numParents) {
		Population<GType,PType> parents = new Population<GType, PType>(population.getFitnessCalculator());
		while(parents.size() < numParents) {
			double parentIndex = RNG.nextDouble();
			//Compare with the end points to pick a parent at random. The intervals are listed in ascending order and don't overlap.
			for (int i = 0; i < intervals.length; i++) {
				if( parentIndex < intervals[i]) {
					//The order of the two lists will be the same so this mapping should be correct.
					parents.add(population.get(i));
					break;
				}
			}
		}
		return parents;
	}
}
