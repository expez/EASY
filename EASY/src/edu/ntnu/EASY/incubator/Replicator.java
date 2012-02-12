package edu.ntnu.EASY.incubator;

import java.util.Random;

public interface Replicator<GType> {

	public static final Random RNG = new Random();
	
	public GType mutate(GType genome);
	
	public GType combine(GType g1, GType g2);
	
	public GType randomGenome();
}
