package edu.ntnu.EASY;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ntnu.EASY.individual.BitvectorIndividual;
public class IndividualTest {

	private static final int GENOME_LENGTH = 20;
	private BitvectorIndividual bitvectorIndividual;
	@Before
	public final void setup() {
		//New bitvector individual with bitvector of length 20.

		bitvectorIndividual = new BitvectorIndividual( GENOME_LENGTH );
	}

	@Test
	public final void testGetFitness() {
		double maxFitness = 1;
		double minFitness = 0;
		for (int i = 0; i < 20; i++) {
			bitvectorIndividual = new BitvectorIndividual( GENOME_LENGTH );

			if( bitvectorIndividual.getFitness() > maxFitness ) {
				maxFitness = bitvectorIndividual.getFitness();
			}
			else if( bitvectorIndividual.getFitness() < minFitness ) {
				minFitness = bitvectorIndividual.getFitness();
			}
		}

		assertTrue(minFitness <= 20);
		assertTrue(minFitness >= 0);
		assertTrue(maxFitness <= 20);
		assertTrue(maxFitness >= 0);
	}

}
