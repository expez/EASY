package test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.FitnessCalculator;
import edu.ntnu.EASY.IntegerArrayFitnessCalculators;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.incubator.BitvectorIncubator;
import edu.ntnu.EASY.incubator.BitvectorReplicator;
import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.selection.parent.ParentSelector;
import edu.ntnu.EASY.selection.parent.TournamentSelector;
public class TournamentSelectorTest {

	static Environment env;
	static ParentSelector<int[]> tournamentSelector;
	static Population<int[], int[]> population;
	static Population<int[], int[]> parents;
	static int genomeSize;
	static FitnessCalculator<int[]> fitCalc;
	@Before
	public void setUp(){
		env = new Environment();
		env.numParents = 30;
		env.populationSize = 100;
		env.rank = 15;
		genomeSize = 40;
		tournamentSelector = new TournamentSelector<int[]>(env.rank, env.numParents);
		BitvectorReplicator replicator = new BitvectorReplicator(genomeSize, 0.5, 0.5);
		BitvectorIncubator incubator = new BitvectorIncubator(replicator, env.numChildren);
		fitCalc = IntegerArrayFitnessCalculators.ONE_MAX_FITNESS;
		population = Population.getRandomPopulation(incubator, fitCalc, env.populationSize);
		population.updateFitness();
		parents = tournamentSelector.select(population);
		parents.updateFitness();
		
	}
	
	@Test
	public void testNumParents() {
		assertTrue( parents.size() == env.numParents);
	}
	
	@Test
	public void testFitnessIncreased() {
		double oldFitness = 0;
		double newFitness = 0;
		for (Individual<int[], int[]> individual : population) {
			oldFitness += individual.getFitness();
			oldFitness /= population.size();
		}
		for (Individual<int[], int[]> individual : parents) {
			newFitness += individual.getFitness();
			newFitness /= parents.size();
		}
		assertTrue(oldFitness < newFitness);
	}

}
