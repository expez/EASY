package edu.ntnu.EASY;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.adultSelectionMechanism.FullGenerationalReplacement;
import edu.ntnu.EASY.individual.BitvectorIndividual;

public class FullGenerationReplacementTest {
	
	private static LinkedList<BitvectorIndividual> adults;
	private static LinkedList<BitvectorIndividual> children;
	private static FullGenerationalReplacement< BitvectorIndividual > adultSelectionStrategy;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		adults = BitvectorIndividual.getIndividuals(150, 40);
		children = BitvectorIndividual.getIndividuals(99, 40);
		adultSelectionStrategy = new FullGenerationalReplacement< BitvectorIndividual >();
	}

	@Test
	public void testGetAdults() {
		assertSame(children, adultSelectionStrategy.getAdults( children, adults ));
	}

}
