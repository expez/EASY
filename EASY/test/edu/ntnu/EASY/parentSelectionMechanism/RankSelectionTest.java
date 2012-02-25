package edu.ntnu.EASY.parentSelectionMechanism;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.OneMaxFitness;
import edu.ntnu.EASY.individual.BitvectorIndividual;
import edu.ntnu.EASY.parentSelectionMechanism.RankSelection;

public class RankSelectionTest {
	
	private static List<BitvectorIndividual> individuals;
	private static List<BitvectorIndividual> parents;
	private static RankSelection< BitvectorIndividual > rankSelection;
	private static OneMaxFitness fitnessEvaluator;
	private static int rank;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rank = 30;
		individuals = new LinkedList<BitvectorIndividual>();
		parents = new LinkedList< BitvectorIndividual >();
		individuals = BitvectorIndividual.getIndividuals(100, 40);
		fitnessEvaluator = new OneMaxFitness();
		rankSelection = new RankSelection< BitvectorIndividual >(rank);
		
		fitnessEvaluator.updateFitness(individuals);
		parents = rankSelection.getParents(individuals);
	}

	@Test
	public void testSize() {

		assertEquals( rank, parents.size() );
	}
	
	public void testFitness() {
		double prevFitness = 0;
		double newFitness = 0;
		
		for (BitvectorIndividual individual : individuals) {
			prevFitness += individual.getFitness();
		}
		prevFitness /= individuals.size();
		
		for (BitvectorIndividual individual : parents) {
			newFitness += individual.getFitness();
		}
		newFitness /= parents.size();
		
		assertTrue( prevFitness <= newFitness);
	}
	

}
