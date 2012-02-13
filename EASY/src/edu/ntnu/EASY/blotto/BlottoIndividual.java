package edu.ntnu.EASY.blotto;

import edu.ntnu.EASY.individual.AbstractIndividual;

public class BlottoIndividual extends AbstractIndividual<double[],double[]>{

	public BlottoIndividual(double[] genome) {
		super(genome);
	}

	@Override
	public void growUp() {
		phenome = genome;
	}
	
}
