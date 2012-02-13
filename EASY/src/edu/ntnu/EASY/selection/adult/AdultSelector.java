package edu.ntnu.EASY.selection.adult;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Population;

public abstract class AdultSelector<PType> {
	Environment env = null;
	
	public abstract <GType> Population<GType, PType> select(Population<GType, PType> adults, Population<GType, PType> children);

	public void setEnvironment(Environment env) {
		this.env = env;
	}
}
