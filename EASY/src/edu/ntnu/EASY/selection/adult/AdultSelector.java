package edu.ntnu.EASY.selection.adult;

import edu.ntnu.EASY.Population;

public interface AdultSelector<PType> {
	
	public abstract <GType> Population<GType, PType> select(Population<GType, PType> adults, Population<GType, PType> children);

}
