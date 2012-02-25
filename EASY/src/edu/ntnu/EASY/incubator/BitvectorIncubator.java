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
package edu.ntnu.EASY.incubator;

import static edu.ntnu.EASY.util.Util.RNG;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.BitvectorIndividual;
import edu.ntnu.EASY.individual.Individual;


public class BitvectorIncubator implements Incubator<int[],int[]>{

	private Replicator<int[]> replicator;
	private int numChildren;
	
    public BitvectorIncubator(Replicator<int[]> replicator, int numChildren) {
    	this.replicator = replicator;
    	this.numChildren = numChildren;
    }
    
    BitvectorIndividual makeChild(Individual<int[],int[]> mom, Individual<int[],int[]> dad) {

    	int[] childsGenome;
    	childsGenome = replicator.combine(mom.getGenome(), dad.getGenome());
    	childsGenome = replicator.mutate(childsGenome);
    	BitvectorIndividual child = new BitvectorIndividual(childsGenome);
    	
    	return child;
    }
    
    /**
     * @param parents List of individuals from which to create children
     * @param children number of children to create.
     * @return a list of children
     */
    @Override
    public Population<int[], int[]> makeChildren(Population<int[], int[]> parents) {
    	Population<int[],int[]> children = new Population<int[],int[]>(parents.getFitnessCalculator());
    	int momIndex;
    	int dadIndex;
    	while(children.size() < numChildren) {
    		momIndex = RNG.nextInt(parents.size());
    		dadIndex = RNG.nextInt(parents.size());
    		children.add(makeChild(parents.get(momIndex),parents.get(dadIndex)));
    	}
    	return children;
    }

	@Override
	public Individual<int[], int[]> randomIndividual() {
		return new BitvectorIndividual(replicator.randomGenome());
	}
}
