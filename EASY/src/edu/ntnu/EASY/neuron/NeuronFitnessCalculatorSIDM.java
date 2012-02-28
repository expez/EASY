package edu.ntnu.EASY.neuron;

import java.util.List;

/**
 * Fitness calculator using the Spike Interval Distance Metric to calculate fitness. 
 * A spike interval is simply the gaps between successive spike times.
 */
public class NeuronFitnessCalculatorSIDM extends AbstractNeuronFitnessCalculator{

	public NeuronFitnessCalculatorSIDM(double[] target) {
		super(target);
	}

	@Override
	public double calculate(double[] phenome) {
		List<Integer> spikeTimes = getSpikeTimes(phenome); 
		int length = Math.min(spikeTimes.size(), targetSpikeTimes.length);
		int spikeTimeDelta;
		int targetSpikeTimeDelta;
		double distance = 0;
		for (int i = 1; i < length - 1; i++) {
			//Get difference in spiketimes for the two spike trains.
			spikeTimeDelta = spikeTimes.get(i) - spikeTimes.get(i - 1);
			targetSpikeTimeDelta = targetSpikeTimes[i] - targetSpikeTimes[i - 1];
			//Square and sum absolute distance.
			distance += Math.pow( spikeTimeDelta - targetSpikeTimeDelta, 2);
		}
		distance -= calculatePenalty(spikeTimes.size(),phenome.length);		
		//Sqrt to normalize.
		distance = Math.sqrt(distance) / (length - 1);
		//Turn into fitness value in interval [0,1]
		return 1 / (1 + distance);
	}

}
