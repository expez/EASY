package edu.ntnu.EASY.incubator;

import edu.ntnu.EASY.Environment;

public interface Replicator<GType> {

	public void setEnvironment(Environment env);
	
	public GType mutate(GType genome);
	
	public GType combine(GType g1, GType g2);
	
	public GType randomGenome();
}
