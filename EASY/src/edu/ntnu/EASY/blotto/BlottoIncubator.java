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
package edu.ntnu.EASY.blotto;

import static edu.ntnu.EASY.util.Util.RNG;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.incubator.Replicator;
import edu.ntnu.EASY.individual.Individual;

public class BlottoIncubator implements Incubator<double[],double[]> {

	Replicator<double[]> replicator;
	private int numChildren;
	
	public BlottoIncubator(Replicator<double[]> replicator, int numChildren){
		this.replicator = replicator;
		this.numChildren = numChildren;
	}

	@Override
	public Population<double[], double[]> makeChildren(Population<double[], double[]> parents) {
    	Population<double[],double[]> children = new Population<double[],double[]>(parents.getFitnessCalculator());
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
	public Individual<double[], double[]> randomIndividual() {
		return new BlottoIndividual(replicator.randomGenome());
	}

	private Individual<double[], double[]> makeChild(Individual<double[],double[]> mom, Individual<double[],double[]> dad){
		double[] childsGenome;
		childsGenome = replicator.combine(mom.getGenome(), dad.getGenome());
		childsGenome = replicator.mutate(childsGenome);
		BlottoIndividual child = new BlottoIndividual(childsGenome);
		return child;
	}
}
