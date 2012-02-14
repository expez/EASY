package edu.ntnu.EASY.incubator;

public interface Replicator<GType> {

	public GType mutate(GType genome);
	
	public GType combine(GType g1, GType g2);
	
	public GType randomGenome();
}
