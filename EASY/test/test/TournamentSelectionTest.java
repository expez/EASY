package test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.incubator.BitvectorIncubator;
import edu.ntnu.EASY.incubator.BitvectorReplicator;
import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.selection.parent.ParentSelector;
import edu.ntnu.EASY.selection.parent.TournamentSelector;
public class TournamentSelectionTest {

	static Environment env;
	static ParentSelector<int[]> tournamentSelector;
	static Population<int[], int[]> population;
	static Population<int[], int[]> parents;
	static int genomeSize;
	@Before
	public void setUp(){
		env = new Environment();
		env.numParents = 30;
		env.populationSize = 100;
		genomeSize = 40;
		tournamentSelector = new TournamentSelector<int[]>();
		BitvectorReplicator replicator = new BitvectorReplicator(genomeSize);
		replicator.setEnvironment(env);
		BitvectorIncubator incubator = new BitvectorIncubator(replicator);
		incubator.setEnvironment(env);
		population = new Population<int[], int[]>( incubator, env.populationSize);
		tournamentSelector.setEnvironment(env);
		
	}
	
	@Test
	public void testNumParents() {
		parents = tournamentSelector.select(population);
		assertTrue( parents.size() == env.numParents);
	}

}
