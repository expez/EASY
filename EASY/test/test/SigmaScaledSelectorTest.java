package test;


import static org.junit.Assert.assertTrue;

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
import edu.ntnu.EASY.selection.parent.SigmaScaledSelector;
public class SigmaScaledSelectorTest {

	static Environment env;
	static ParentSelector<int[]> selector;
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
		selector = new SigmaScaledSelector<int[]>();
		BitvectorReplicator replicator = new BitvectorReplicator(genomeSize);
		replicator.setEnvironment(env);
		BitvectorIncubator incubator = new BitvectorIncubator(replicator);
		incubator.setEnvironment(env);
		fitCalc = IntegerArrayFitnessCalculators.ONE_MAX_FITNESS;
		population = Population.getRandomPopulation(incubator, fitCalc, env.populationSize);
		selector.setEnvironment(env);
		population.updateFitness();
		selector.setEnvironment(env);
		parents = selector.select(population);
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
