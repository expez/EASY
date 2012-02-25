package edu.ntnu.EASY.parentSelectionMechanism;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.OneMaxFitness;
import edu.ntnu.EASY.individual.BitvectorIndividual;

public class TournamentSelectionTest {

	private static List< BitvectorIndividual > adults;
	private static List< BitvectorIndividual > parents;
	private static int NUM_PARENTS = 71;
	private static OneMaxFitness fitnessEvaluator;
	private static TournamentSelection< BitvectorIndividual > tournamentSelection;
	private static int TOURNAMENT_SIZE = 5;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		adults = new LinkedList< BitvectorIndividual >( BitvectorIndividual.getIndividuals( 233, 40 ) );
		
		fitnessEvaluator = new OneMaxFitness();
		fitnessEvaluator.updateFitness( adults );
		
		tournamentSelection = new TournamentSelection< BitvectorIndividual >( NUM_PARENTS, TOURNAMENT_SIZE );
		
		parents = tournamentSelection.getParents( adults );
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
	}
}
