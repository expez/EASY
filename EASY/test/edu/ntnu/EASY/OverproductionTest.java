package edu.ntnu.EASY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.adultSelectionMechanism.Overproduction;
import edu.ntnu.EASY.individual.BitvectorIndividual;

public class OverproductionTest {
	private static List<BitvectorIndividual> adults;
	private static List<BitvectorIndividual> oldAdults;
	private static List<BitvectorIndividual> children;
	private static Overproduction< BitvectorIndividual > overproduction;
	private static int NUM_ADULTS;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NUM_ADULTS = 75;
		oldAdults = new LinkedList< BitvectorIndividual >( BitvectorIndividual.getIndividuals( 50, 40) );
		children = new LinkedList< BitvectorIndividual >( BitvectorIndividual.getIndividuals( 100, 40) );
		overproduction = new Overproduction< BitvectorIndividual >( NUM_ADULTS );
		adults = overproduction.getAdults( children, oldAdults );
		
	}

	@Test
	public void testSizeOfAdultList() {
		assertEquals (NUM_ADULTS, adults.size() );
	}
	
	@Test
	public void testAdultFitness() {
		double childrenFitness = 0;
		double adultFitness = 0;
		
		// Add up fitness values.
		for (BitvectorIndividual bitvectorIndividual : adults) {
			adultFitness += bitvectorIndividual.getFitness();
		}
		
		for (BitvectorIndividual bitvectorIndividual : children) {
			childrenFitness += bitvectorIndividual.getFitness();
		}
		
		//Normalize
		adultFitness /= adults.size();
		childrenFitness /= children.size();
		
		assertTrue( childrenFitness <= adultFitness);
	}
	
	@Test
	public void testIndividualPresent() {
		Collections.sort( children );
		
		Random random = new Random();
		
		// Find a random individual with high enough fitness to survive the culling.
		int individualIndex = random.nextInt( NUM_ADULTS ) + children.size() - NUM_ADULTS;
		
		BitvectorIndividual individual = children.get( individualIndex );
		
		assertTrue( adults.contains( individual ) );
	}

}
