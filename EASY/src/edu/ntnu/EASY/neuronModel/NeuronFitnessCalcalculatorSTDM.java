package edu.ntnu.EASY.neuronModel;

import java.util.List;


/**
 * Fitness calculator using the Spike train distance measure called spike time distance measure.
 * This distance measure uses the difference between spike times to compare two spiketimes.
 *
 */
public class NeuronFitnessCalcalculatorSTDM extends AbstractNeuronFitnessCalculator{
	
	public NeuronFitnessCalcalculatorSTDM(double[] target) {
		super(target);
	}

	@Override
	public double calculate(double[] phenome) {
		List<Integer> spikeTimes = getSpikeTimes(phenome);
		List<Integer> targetSpikeTimes = getSpikeTimes(target);
		return 1 / ( 1 + calculateDistance(spikeTimes, targetSpikeTimes ) - calculatePenalty(spikeTimes.size(), targetSpikeTimes.size(), phenome.length));		
	}
	
	private double calculateDistance(List<Integer> spikeList, List<Integer> targetSpikeList) {
		
		//We have to find the shortest list to iterate over.
		int shortestListLength= Math.min(spikeList.size(),targetSpikeList.size());
		
		double sum = 0;
		for (int spikeTime = 0; spikeTime < shortestListLength; spikeTime++) {
			sum += Math.pow( spikeList.get(spikeTime) + targetSpikeList.get(spikeTime), 2);
		}
		sum = Math.sqrt(sum);
		sum /= shortestListLength;
		return sum;
	}

	//Calculate a penalty proportional to the difference in number of spikes.
	private double calculatePenalty(int spikes1, int spikes2, int L) {
		int N = Math.max(spikes1,spikes2);
		double M = Math.min(spikes1,spikes2);
		if( M == 0) {
			M = 0.001;
		}
		return ((N - M) * L )/ (2.0 * M);
	}
	
	
}
