package edu.ntnu.EASY.neuron;

import edu.ntnu.EASY.individual.AbstractIndividual;

/**
 * This class implements the Izhikevich Neuron Model.
 * The genome is of length 5, the entries being the 5 parameters for the model.
 * In the course paper they are called a, b, c, d and k.
 * Thus, the genome will be [a, b, c, d, k].
 * 
 * The ranges for these parameters, for this assignment will be:
 * 
 * a in range [0.001, 0.2]
 * b in range [0.01, 0.3]
 * c in range [-80, -30]
 * d in range [0.1, 10]
 * k in range [0.01, 1.0]
 * 
 * The phenome for this individual will be a time series with 1001 entries.
 * Each entry represents the voltage difference across the cell membrane of the model
 * for each step of the time series.
 */
public class NeuronIndividual extends AbstractIndividual<double[], double[]>{
	
	private final static double T = 10;
	private final static int I = 10;
	private final static double INITIAL_VOLTAGE = -60; 
	private final static double INITIAL_RECOVERY_RATE = 0;
	public final static double SPIKE_THRESHOLD = 35;
	private double a;
	private double b;
	private double c;
	private double d;
	private double k;
	
	public NeuronIndividual(double[] genome) {
		super(genome);
		a = genome[0];
		b = genome[1];
		c = genome[2];
		d = genome[3];
		k = genome[4];
	}

	public void growUp() {	
		//The voltage difference across the membrane, unit is V. Initial value.
		double v = INITIAL_VOLTAGE;
		//The recovery factor, initial value.
		double u = INITIAL_RECOVERY_RATE;
		
		double phenome[] = new double[1001];
		phenome[0] = INITIAL_VOLTAGE;

		for ( int timeStep = 1; timeStep < phenome.length; timeStep++ ) {
			v += getVoltageDelta( v, u );
			u += getRecoveryFactorDelta( v, u );
			
			phenome[ timeStep ] = v;
			
			// A spike is assumed to be at 35mV for this assignment. Whenever a spike occurs there will be a reset:
			// u = u + d
			// v = c
			if( SPIKE_THRESHOLD <= v) {
				phenome[timeStep] = SPIKE_THRESHOLD;
				u += d;
				v = c;
			}
		}
		this.phenome = phenome;
	}
	
	private double getVoltageDelta( double v, double u ) {
		// The equation used to update v is :
		// v = v + v' where v' = 1/T (kv^2 + 5v + 140 - u + I)
		// T is the time constant, which is 10 for this assignment.
		// I is an external input which has a value which is also equal to 10 for this assignment.
		double voltageDelta = (1/T) * ( k*v*v + 5*v + 140 - u + I );
		return voltageDelta;
	}
	
	private double getRecoveryFactorDelta( double v, double u ) {
		//The equation used to update u is:
		//u = u + u' where u' = a/T (bv - u)
		return (a/T) * (b*v - u );	
	}

}
