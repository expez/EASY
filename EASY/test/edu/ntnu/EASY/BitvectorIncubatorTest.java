package edu.ntnu.EASY;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.individual.BitvectorIndividual;

public class BitvectorIncubatorTest {

	private static BitvectorIncubator incubator;
	private static List<BitvectorIndividual> parents;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		incubator = new BitvectorIncubator(0.01, 0.01);
		parents = BitvectorIndividual.getIndividuals(40, 40);
	}

	@Test
	public void testCrossover() {
		int[] momsGenome = {1, 1, 1, 1, 0, 0, 0, 0};
		int[] dadsGenome = {0, 0, 0, 0, 1, 1, 1, 1};
		int[] childsGenome = incubator.crossover( momsGenome, dadsGenome, 4) ;
		int[] expectedGenome = {1, 1, 1, 1, 1, 1, 1, 1};
		assertArrayEquals(expectedGenome, childsGenome);
		
		childsGenome = incubator.crossover( momsGenome, dadsGenome, 7 );
		expectedGenome = new int[] {1, 1, 1, 1, 0, 0, 0, 1};
		assertArrayEquals(expectedGenome, childsGenome);
	}
	
	@Test
	public void testGetChildren() {
		List<BitvectorIndividual> children;
		
		children = incubator.getChildren( parents, 99 );
		
		assertEquals( 99, children.size() );
	}

}
