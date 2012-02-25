package edu.ntnu.EASY;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.adultSelectionMechanism.GenerationalMixing;
import edu.ntnu.EASY.individual.BitvectorIndividual;

public class GenerationalMixingTest {
	
	private static List< BitvectorIndividual > oldAdults;
	private static List< BitvectorIndividual > adults;
	private static List< BitvectorIndividual > children;
	private static GenerationalMixing< BitvectorIndividual > generationalMixing;
	private static final int NUM_ADULTS = 55;
	private static OneMaxFitness fitnessEvaluator;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		oldAdults = BitvectorIndividual.getIndividuals( 100, 40 );
		children = BitvectorIndividual.getIndividuals( 50, 40 );
		
		generationalMixing = new GenerationalMixing< BitvectorIndividual >( NUM_ADULTS);
		
		fitnessEvaluator = new OneMaxFitness();
		fitnessEvaluator.updateFitness( oldAdults );
		fitnessEvaluator.updateFitness( children );
		adults = generationalMixing.getAdults( children, oldAdults );
	}

	@Test
	public void testSizeOfAdults() {
		assertTrue( NUM_ADULTS == adults.size() );
	}
	
	@Test
	public void testAdultFitness() {
		double oldFitness = 0;
		double newFitness = 0;
		
		for (BitvectorIndividual individual : oldAdults) {
			oldFitness += individual.getFitness();
		}
		
		for (BitvectorIndividual individual : adults) {
			newFitness += individual.getFitness();
		}
		
		oldFitness /= oldAdults.size();
		newFitness /= adults.size();
		
		assertTrue( oldFitness <= newFitness );
		
	}
	
	@Test
	public void testContainsIndividual() {
		// Make sure a fit individual got copied over.
		
		//Get random individual in top NUM_ADULTS of oldAdults.
		Random random = new Random();
		int survivorIndex = random.nextInt( NUM_ADULTS ) + oldAdults.size() - NUM_ADULTS;
		
		Collections.sort(oldAdults);
		BitvectorIndividual survivor = oldAdults.get( survivorIndex );
		assertTrue( adults.contains( survivor ));
	}
}
