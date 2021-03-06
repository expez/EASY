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
import edu.ntnu.EASY.incubator.Replicator;
import edu.ntnu.EASY.util.Util;

public class BlottoReplicator implements Replicator<double[]> {

	private int genomeLength;
	private double mutationRate;
	private double crossoverRate;
	
	public BlottoReplicator(int genomeLength, double mutationRate, double crossoverRate){
		this.genomeLength = genomeLength;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
	}
	
	@Override
	public double[] mutate(double[] genome) {
		for (int i = 0; i < genome.length; i++) {
			if(RNG.nextDouble() <= mutationRate) {
				genome[i] += 0.05*RNG.nextGaussian();
				if(genome[i] < 0) 
					genome[i] = 0;
			}
		}
		Util.normalize(genome);
		return genome;
	}

	@Override
	public double[] combine(double[] g1, double[] g2) {
		double[] child = new double[g1.length];
		System.arraycopy(g1,0,child,0,g1.length);

		// Randomly crossover.
		if(RNG.nextDouble() <= crossoverRate) {
			int cross = RNG.nextInt(child.length);
			System.arraycopy(g2,cross + 1,child,cross + 1,child.length - cross - 1);
		}
		Util.normalize(child);
		return child;
	}

	@Override
	public double[] randomGenome() {
		double[] genome = new double[genomeLength];
		for(int i = 0; i < genomeLength; i++){
			genome[i] = RNG.nextDouble();
		}
		Util.normalize(genome);
		return genome;
	}
}


