/*Copyright (C) 2012 Lars Andersen, Tormund S. Haus.
larsan@stud.ntnu.no
tormunds@ntnu.no

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
		double normalizedFitness;
		//Put each endpoint into a linked list.
		for (int adultIndex = 0; adultIndex < adults.size(); adultIndex++) {
			fitness = adults.get( adultIndex ).getFitness();
			normalizedFitness = fitness / totalFitness;
			intervals.add( normalizedFitness + previousEndpoint);
			previousEndpoint += normalizedFitness;
		}

		return getParentList( adults, intervals, numParents);
	}
	
	
	static <T extends Individual> double getFitnessVariance( List< T > individuals, double mean) {
		double variance = 0;	
		for (T individual : individuals) {
			variance += ( individual.getFitness() - mean) * ( individual.getFitness() - mean);
		}
		variance /= individuals.size();
		return variance;
	}
	
	static <T extends Individual> List< T > getParentList(List< T > adults, List< Double > intervals, int numParents) {
		Random random = new Random();
		List< T > parents = new LinkedList< T >();
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
