package edu.ntnu.EASY.incubator;

import edu.ntnu.EASY.Population;

public interface Incubator<GType,PType> {

	public Population<GType,PType> makeChildren(Population<GType,PType> parents); 
	
}
