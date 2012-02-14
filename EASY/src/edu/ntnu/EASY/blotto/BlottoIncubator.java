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
