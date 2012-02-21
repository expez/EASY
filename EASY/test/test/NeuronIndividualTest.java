package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ntnu.EASY.neuronModel.NeuronIndividual;

public class NeuronIndividualTest {

	private static NeuronIndividual individual;
	
	@Before
	public void setup() {
		individual = new NeuronIndividual(new double[] {0.1, 0.1, -50, 5.0, 0.5} );
	}
	@Test
	public void testGrowUp() {
		individual.growUp();
		assertTrue( individual.getPhenome().length == 1001 );
		assertTrue( individual.getPhenome()[0] == -60 );
	}

}
