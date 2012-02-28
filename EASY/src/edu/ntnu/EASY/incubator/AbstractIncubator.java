package edu.ntnu.EASY.incubator;

import static edu.ntnu.EASY.util.Util.RNG;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;

public abstract class AbstractIncubator<GType, PType> implements Incubator<GType, PType>{
	
	protected Replicator<GType> replicator;
	protected int numChildren;
	
	public AbstractIncubator(Replicator<GType> replicator, int numChildren){
		this.replicator = replicator;
		this.numChildren = numChildren;
	}

	public Population<GType, PType> makeChildren(Population<GType, PType> parents) {
    	Population<GType,PType> children = new Population<GType,PType>(parents.getFitnessCalculator());
    	int momIndex;
    	int dadIndex;
    	while(children.size() < numChildren) {
    		momIndex = RNG.nextInt(parents.size());
    		dadIndex = RNG.nextInt(parents.size());
    		children.add(makeChild(parents.get(momIndex),parents.get(dadIndex)));
    	}
		return children;
	}

	protected abstract Individual<GType, PType> makeChild(
			Individual<GType, PType> mom,
			Individual<GType, PType> dad);

}
