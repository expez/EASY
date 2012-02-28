package edu.ntnu.EASY.neuron;

import java.util.ArrayList;
import java.util.List;

import edu.ntnu.EASY.FitnessCalculator;
import edu.ntnu.EASY.Population;

public abstract class DistanceMetricCalculator implements FitnessCalculator<double[]> {
	
	//A neuron is said to be spiking when it's voltage is above this value.
	static final int ACTIVATION_THRESHOLD = 0;
	
	
	
	protected double[] target;
	protected int[] targetSpikeTimes;
	
	public DistanceMetricCalculator(double[] target ) {
		this.target = target;
		List<Integer> spikeList = getSpikeTimes(target);
		targetSpikeTimes = new int[spikeList.size()];
		for (int i = 0; i < targetSpikeTimes.length; i++) {
			targetSpikeTimes[i] = spikeList.get(i);
		}
	}

	public abstract double calculate(double[] phenome);

	@Override
	public void setPopulation(Population<?, double[]> population) {
		
	}
	
	/**
	 * @param phenome The phenome of the neuron individual.
	 * @return A list of spike times for the neuron.
	 */
	protected List<Integer> getSpikeTimes(double[] phenome) {
		List<Integer> spikeTimes = new ArrayList<Integer>();
		//Check for spikes in a window of size k. 
		//Define a single spike to be the maximum value in this window.
		int k = 5;
		for (int i = 0; i < phenome.length - k; i++) {
			int middle = i + 2;
			boolean spike = phenome[middle] >= ACTIVATION_THRESHOLD;
			for(int j = i; spike && j < i + k; j++){
				if(phenome[middle] < phenome[j]){	
					spike = false;
				}		
			}
			if(spike){
				spikeTimes.add(middle);
			}
		}
		return spikeTimes; 
	}

	/**
	 * Calculate the penalty.
	 * @param spikesTimes
	 * @param L
	 * @return
	 */
	protected double calculatePenalty(int spikesTimes, int L) {
		int N = Math.max(spikesTimes,targetSpikeTimes.length);
		double M = Math.min(spikesTimes,targetSpikeTimes.length);
		if(M == 0) {
			M = 0.001;
		}
		return ((N - M) * L ) / (2.0 * M);
	}
}
