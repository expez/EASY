package edu.ntnu.EASY;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.genotype.BinaryGenotype;
import edu.ntnu.EASY.individual.BitvectorIndividual;

public class OneMaxFitnessTest {
	private static OneMaxFitness fitnessEvaluator;
	private static LinkedList<BitvectorIndividual> individuals;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fitnessEvaluator = new OneMaxFitness();
		individuals = BitvectorIndividual.getIndividuals(100, 40);
	}

	@Test
	public void testUpdateFitness() {
		
		fitnessEvaluator.updateFitness(individuals);
		boolean fitnessOK = false;
		for (BitvectorIndividual individual : individuals) {
			fitnessOK = individual.getFitness() <= 1 && 0 <= individual.getFitness(); 
			assertTrue( fitnessOK );
		}
		
		BinaryGenotype genome = new BinaryGenotype(10);
		int[] g = {1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
		genome.setGenome( g );
		BitvectorIndividual ind = new BitvectorIndividual(10);
		ind.getGenotype().setGenome( g );
		double fitness = ind.getFitness();
		assertEquals( 5 / 10, fitness, 0.001);
	}

}
