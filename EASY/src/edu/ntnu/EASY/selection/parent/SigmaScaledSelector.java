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
import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.util.Util;

public class SigmaScaledSelector<PType> implements ParentSelector<PType> {

	int numParents;
	
	public SigmaScaledSelector(int numParents) {
		this.numParents = numParents;
	}

	@Override
	public <GType> Population<GType, PType> select(Population<GType, PType> population) {
		double totalFitness = 0;
		
		for (Individual<GType, PType> individual : population) {
			totalFitness += individual.getFitness();
		}
		population.sort(true);
		
		//A list of intervals. [0, 1).
		double[] intervals = new double[population.size()];
		double previousEndpoint = 0;
		double fitness;
		double meanFitness = totalFitness / population.size();
		double sigmaFitness;
		double fitnessVariance = Util.getFitnessVariance(population, meanFitness);
		
		//Put each endpoint into a linked list.
		for (int adultIndex = 0; adultIndex < population.size(); adultIndex++) {
			fitness = population.get( adultIndex ).getFitness();
			sigmaFitness = 1 + ( ( fitness - meanFitness ) / ( 2 * fitnessVariance) ); 
			intervals[adultIndex] = sigmaFitness + previousEndpoint;
			previousEndpoint += sigmaFitness;
		}
		
		//Normalize the sigma values so the intervals are in the range [0,1]
		for(int i = 0; i < intervals.length; i++){
			//The value of the last end point is the sum of all the sigmaFitness values.
			intervals[i] /= previousEndpoint;
		}
		return FitnessProportionateSelector.getParentList(population, intervals, numParents);
	}
}
