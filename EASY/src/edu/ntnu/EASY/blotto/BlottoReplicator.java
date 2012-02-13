package edu.ntnu.EASY.blotto;

import static edu.ntnu.EASY.util.Util.RNG;
import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.incubator.Replicator;

public class BlottoReplicator implements Replicator<double[]> {

	private Environment env = null;
	private int genomeLength;
	
	public BlottoReplicator(int genomeLength){
		this.genomeLength = genomeLength;
	}
	
	@Override
	public void setEnvironment(Environment env) {
		this.env = env;
	}

	@Override
	public double[] mutate(double[] genome) {
		for (int i = 0; i < genome.length; i++) {
			if(RNG.nextDouble() <= env.mutationRate) {
				genome[i] += RNG.nextBoolean() ? -0.05 : 0.05;
			}
		}
		normalize(genome);
		return genome;
	}

	@Override
	public double[] combine(double[] g1, double[] g2) {
		double[] child = new double[g1.length];
		System.arraycopy(g1,0,child,0,g1.length);

		// Randomly crossover.
		if(RNG.nextDouble() <= env.crossoverRate) {
			int cross = RNG.nextInt(child.length);
			System.arraycopy(g2,cross + 1,child,cross + 1,child.length - cross - 1);
		}
		normalize(child);
		return child;
	}

	@Override
	public double[] randomGenome() {
		double[] genome = new double[genomeLength];
		for(int i = 0; i < genomeLength; i++){
			genome[i] = RNG.nextDouble();
		}
		normalize(genome);
		return genome;
	}
	
	public static void normalize(double[] a){
		double total = 0.0;
		for(double d : a){
			total += d;
		}
		for (int i = 0; i < a.length; i++) {
			a[i] /= total;
		}
	}
}


