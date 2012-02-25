
package edu.ntnu.EASY.parentSelectionMechanism;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.OneMaxFitness;
import edu.ntnu.EASY.individual.BitvectorIndividual;
import edu.ntnu.EASY.parentSelectionMechanism.SigmaScaledSelection;

public class RouletteSelectionTest {

	private static List< BitvectorIndividual > adults;
	private static List< BitvectorIndividual > parents;
	private static int NUM_PARENTS = 71;
	private static OneMaxFitness fitnessEvaluator;
	private static FitnessProportionalSelection< BitvectorIndividual > rouletteSelection;
	private static SigmaScaledSelection< BitvectorIndividual > sigmaScaledSelection;
	private static List< BitvectorIndividual > sigmaParents;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		adults = new LinkedList< BitvectorIndividual >( BitvectorIndividual.getIndividuals( 233, 40 ) );
		
		fitnessEvaluator = new OneMaxFitness();
		fitnessEvaluator.updateFitness( adults );
		
		rouletteSelection = new FitnessProportionalSelection< BitvectorIndividual >( NUM_PARENTS );
		
		parents = rouletteSelection.getParents( adults );
		sigmaScaledSelection = new SigmaScaledSelection< BitvectorIndividual >( NUM_PARENTS );
		sigmaParents = sigmaScaledSelection.getParents( adults );

	}

	@Test
	public void testAverageFitnessIncreased() {
		double oldFitness = 0;
		double newFitness = 0;
		
		for (BitvectorIndividual adult : adults) {
			oldFitness += adult.getFitness();
		}
		
		for (BitvectorIndividual parent : parents) {
			newFitness += parent.getFitness();
		}
		
		oldFitness /= adults.size();
		newFitness /= parents.size();
		
		assertTrue( oldFitness <= newFitness );
	}
	
	@Test
	public void testNumParents() {
		assertEquals( NUM_PARENTS, parents.size() );
		assertEquals( NUM_PARENTS, sigmaParents.size() );
	}
	
	@Test
	public void testVarianceCalculator() {
		List< BitvectorIndividual > individuals = BitvectorIndividual.getIndividuals(6, 40);
		for (int i = 0; i < individuals.size(); i++) {
			individuals.get( i ).setFitness( i + 1);
		}
		
		assertEquals(2.9, rouletteSelection.getFitnessVariance(individuals, 3.5), 0.1);
	}
	
	@Test
	public void testSigmaAverageFitnessIncreased() {
		double oldFitness = 0;
		double newFitness = 0;
		
		for (BitvectorIndividual adult : adults) {
			oldFitness += adult.getFitness();
		}
		
		for (BitvectorIndividual parent : sigmaParents) {
			newFitness += parent.getFitness();
		}
		
		oldFitness /= adults.size();
		newFitness /= parents.size();
		
		assertTrue( oldFitness <= newFitness );
	}
}
