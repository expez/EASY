package edu.ntnu.EASY.neuronModel;

import static edu.ntnu.EASY.util.Util.RNG;
import edu.ntnu.EASY.incubator.Replicator;

public class NeuronReplicator implements Replicator<double[]>{

	/* Valid ranges for parameters will be:
	 * • a ∈ [0.001, 0.2]  (genome[0])
	 * • b ∈ [0.01, 0.3]   (genome[1])
	 * • c ∈ [−80, −30]    (genome[2])
	 * • d ∈ [0.1, 10]     (genome[3])
	 * • k ∈ [0.01, 1.0]   (genome[4])
	 */
	
	private static final int GENOME_LENGTH = 5;
	private static final int A = 0;
	private static final int B = 1;
	private static final int C = 2;
	private static final int D = 3;
	private static final int K = 4;
	private static final int MIN = 0;
	private static final int MAX = 1;
	
	
	private static final double[][] PARAMETERS = {  /*	   |   MIN ,  MAX  | */
													/*a ∈*/{  0.001,  0.200},
													/*b ∈*/{  0.010,  0.300},
													/*c ∈*/{-80.000,-30.000},
													/*d ∈*/{  0.100, 10.000},
													/*k ∈*/{  0.010,  1.000},
												};
	private double mutationRate;
	private double crossoverRate;
	
	public NeuronReplicator(double mutationRate,double crossoverRate){
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
	}
	
	@Override
	public double[] mutate(double[] genome) {
		for(int i = 0; i < GENOME_LENGTH; i++){
			genome[i] += mutationRate > RNG.nextDouble() ? 0.05*RNG.nextGaussian() : 0;
			genome[i] = Math.max(genome[i],PARAMETERS[i][MIN]);
			genome[i] = Math.min(genome[i],PARAMETERS[i][MAX]);
		}
		return genome;
	}

	@Override
	public double[] combine(double[] g1, double[] g2) {
		double[] genome = new double[GENOME_LENGTH];
		for(int i = 0; i < GENOME_LENGTH; i++){
			genome[i] = crossoverRate > RNG.nextDouble()  ? g1[i] : g2[i];
		}
		return null;
	}

	@Override
	public double[] randomGenome() {
		double[] genome = new double[GENOME_LENGTH];
		for(int i = 0; i < GENOME_LENGTH; i++){
			genome[i] = PARAMETERS[i][MIN] + RNG.nextDouble() * (PARAMETERS[i][MAX] - PARAMETERS[i][MIN]);
		}
		return genome;
	}
}
