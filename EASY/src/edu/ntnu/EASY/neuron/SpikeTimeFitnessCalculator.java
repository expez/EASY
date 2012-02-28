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
		double delta = calculateDistance(spikeTimes, targetSpikeTimes);
		delta -= calculatePenalty(spikeTimes.size(), phenome.length); 
		return 1 / (1 + delta);		
	}
	
	private double calculateDistance(List<Integer> spikeList, int[] targetSpikeTimes) {
		
		//We have to find the shortest list to iterate over.
		int shortestListLength= Math.min(spikeList.size(),targetSpikeTimes.length);
		
		double sum = 0;
		for (int spikeTime = 0; spikeTime < shortestListLength; spikeTime++) {
			sum += Math.pow( spikeList.get(spikeTime) + targetSpikeTimes[spikeTime], 2);
		}
		return Math.sqrt(sum) / shortestListLength;
	}
}
