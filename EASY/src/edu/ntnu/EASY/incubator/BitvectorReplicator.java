package edu.ntnu.EASY.incubator;


public class BitvectorReplicator implements Replicator<int[]>{

	private double mutationRate;
	private double crossoverRate;
	private int genomeLength; 
	
	public BitvectorReplicator(double mutationRate, double crossoverRate, int genomeLength){
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.genomeLength = genomeLength;
	}
		
	
	/**
	       Mutates each bit in the binary genome with probability
	       mutationRate.

	       @param mutationRate probability that a single bit is
	       flipped. Double in interval [0,1].
	 */
	public int[] mutate(int[] genome) {
		// For every bit in the genome.
		for (int i = 0; i < genomeLength; i++) {
			// Randomly flip it.
			if(RNG.nextDouble() <= mutationRate) {
				genome[i] = genome[i] == 0 ? 1 : 0;
			}
		}
		return genome;
	} 

	@Override
	public int[] combine(int[] g1, int[] g2) {

    	int[] child = new int[genomeLength];

    	// Randomly crossover.
    	if(RNG.nextDouble() <= crossoverRate) {
    		// Randomly find crossover point, between 0 (inclusive)
    		// and genomeLength (exclusive).
    		int cross = RNG.nextInt(genomeLength);
        	System.arraycopy(g1,0,child,0,cross);
        	System.arraycopy(g2,cross + 1,child,cross + 1,genomeLength - cross - 1);
    	}
		return child;
    }
	
	@Override
	public int[] randomGenome() {
		int[] genome = new int[genomeLength];
		for(int i = 0; i < genomeLength; i++){
			genome[i] = RNG.nextBoolean() ? 1 : 0;
		}
		return genome;
	}

}
