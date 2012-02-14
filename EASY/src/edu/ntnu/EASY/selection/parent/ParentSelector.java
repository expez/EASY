package edu.ntnu.EASY.selection.parent;

import edu.ntnu.EASY.Population;

public interface ParentSelector<PType> {
	
	public abstract <GType> Population<GType,PType> select(Population<GType, PType> population);
	
}
