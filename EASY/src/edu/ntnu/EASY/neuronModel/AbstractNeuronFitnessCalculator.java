package edu.ntnu.EASY.neuronModel;

import java.util.LinkedList;
import java.util.List;

import edu.ntnu.EASY.FitnessCalculator;
import edu.ntnu.EASY.Population;

public abstract class AbstractNeuronFitnessCalculator implements FitnessCalculator<double[]> {
	
	//A neuron is said to be spiking when it's voltage is above this value.
	static final int ACTIVATION_THRESHOLD = 0;
	protected double[] target;
	
	public AbstractNeuronFitnessCalculator(double[] target ) {
		this.target = target;
	}

	public abstract double calculate(double[] phenome);

	/**
	 * @param phenome The phenome of the neuron individual.
	 * @return A list of spike times for the neuron.
	 */
	protected List<Integer> getSpikeTimes(double[] phenome) {
		List<Integer> spikeTimes = new LinkedList<Integer>();
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

	@Override
	public void setPopulation(Population<?, double[]> population) {
		
	}
}
