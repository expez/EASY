package edu.ntnu.EASY.selection.parent;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Population;

public abstract class ParentSelector<PType> {

	protected Environment env = null;

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	public abstract <GType> Population<GType,PType> select(Population<GType, PType> population);
	
}
