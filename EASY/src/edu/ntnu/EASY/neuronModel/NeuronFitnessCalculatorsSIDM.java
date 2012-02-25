package edu.ntnu.EASY.neuronModel;

import java.util.List;

import edu.ntnu.EASY.Population;

/**
 * Fitness calculator using the Spike Interval Distance Metric to calculate fitness. 
 * A spike interval is simply the gaps between successive spike times.
 */
public class NeuronFitnessCalculatorsSIDM extends AbstractNeuronFitnessCalculator{

	public NeuronFitnessCalculatorsSIDM(double[] target) {
		super(target);
	}

	@Override
	public void setPopulation(Population<?, double[]> population) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double calculate(double[] phenome) {
		List<Integer> spikeTimes = getSpikeTimes(phenome); 
		List<Integer> targetSpikeTimes = getSpikeTimes(target);
		int length = Math.min(spikeTimes.size(), targetSpikeTimes.size());
		int spikeTimeDelta;
		int targetSpikeTimeDelta;
		double distance = 0;
		for (int i = 1; i < length - 1; i++) {
			//Get difference in spiketimes for the two spike trains.
			spikeTimeDelta = spikeTimes.get(i) - spikeTimes.get(i - 1);
			targetSpikeTimeDelta = targetSpikeTimes.get(i) - targetSpikeTimes.get(i - 1);
			//Square and sum absolute distance.
			distance += Math.pow( spikeTimeDelta - targetSpikeTimeDelta, 2);
		}
		//Sqrt to normalize.
		distance = Math.sqrt(distance) / (length - 1);
		//Turn into fitness value in interval [0,1]
		return 1 / (1 + distance);
	}

}
