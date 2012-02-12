package edu.ntnu.EASY.selection.parent;

import java.util.List;

import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;

public interface ParentSelector<PType> {


	public Population<?,PType> select(Population<?, PType> population);
	
}
