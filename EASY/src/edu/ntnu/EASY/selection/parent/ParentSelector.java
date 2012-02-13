package edu.ntnu.EASY.selection.parent;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Population;

public interface ParentSelector<PType> {

	public void setEnvironment(Environment env);
	
	public <GType> Population<GType,PType> select(Population<GType, PType> population);
	
}
