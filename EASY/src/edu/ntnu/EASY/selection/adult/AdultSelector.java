package edu.ntnu.EASY.selection.adult;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Population;

public interface AdultSelector<PType> {

	public void setEnvironment(Environment env);
	
	public <GType> Population<GType, PType> select(Population<GType, PType> adults, Population<GType, PType> children);

	
}
