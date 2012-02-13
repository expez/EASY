package edu.ntnu.EASY.incubator;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;

public interface Incubator<GType,PType> {

	public void setEnvironment(Environment env);
	
	public Population<GType,PType> makeChildren(Population<GType,PType> parents); 
	
	public Individual<GType,PType> randomIndividual();
}
