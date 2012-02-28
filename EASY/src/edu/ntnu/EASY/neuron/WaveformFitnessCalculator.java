package edu.ntnu.EASY.neuron;

/**
 * Fitness calculator using the waveform distance metric to calculate fitness. 
 * The distance measure used is the sum of the differences in voltages for all
 * points in the spike train.
 *
 */
public class WaveformFitnessCalculator extends DistanceMetricCalculator {

	public WaveformFitnessCalculator(double[] target) {
		super(target);
	}

	@Override
	public double calculate(double[] phenome) {
		double delta = 0;
		for (int i = 0; i < phenome.length; i++) {
			//Absolute distance is what matters.
			delta += Math.pow(phenome[i] - target[i], 2);
		}
		//Normalize
		delta = Math.sqrt(delta) / phenome.length;
		// Turn into fitness value in interval [0,1].
		return 1 / (1 + delta);
	}

}
