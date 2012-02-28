package edu.ntnu.EASY.neuron;

import java.util.List;


/**
 * Fitness calculator using the Spike train distance measure called spike time distance measure.
 * This distance measure uses the difference between spike times to compare two spiketimes.
 *
 */
public class SpikeTimeFitnessCalculator extends DistanceMetricCalculator{
	
	public SpikeTimeFitnessCalculator(double[] target) {
		super(target);
	}

	@Override
	public double calculate(double[] phenome) {
		List<Integer> spikeTimes = getSpikeTimes(phenome);
		double distance = calculateDistance(spikeTimes, targetSpikeTimes);
<<<<<<< HEAD
		distance += calculatePenalty(spikeTimes.size(), phenome.length); 
=======
		distance -= calculatePenalty(spikeTimes.size(), phenome.length); 
>>>>>>> branch 'master' of git@github.com:Expez/EASY
		return 1 / (1 + distance);		
	}
	
	private double calculateDistance(List<Integer> spikeList, int[] targetSpikeTimes) {
		
		//We have to find the shortest list to iterate over.
		int shortestListLength= Math.min(spikeList.size(),targetSpikeTimes.length);
		
		if(shortestListLength == 0)
			shortestListLength = 1;
		
		double distance = 0;
		for (int spikeTime = 1; spikeTime < shortestListLength; spikeTime++) {
			distance += Math.pow( spikeList.get(spikeTime) - targetSpikeTimes[spikeTime], 2);
		}
		distance = Math.sqrt(distance) / shortestListLength;
		
<<<<<<< HEAD
		return distance;
=======
		return 1 / (1 + distance);
>>>>>>> branch 'master' of git@github.com:Expez/EASY
	}
}
