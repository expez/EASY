package edu.ntnu.EASY;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.genotype.BinaryGenotype;

public class BinaryGenotypeTest {

	private static final int GENOME_LENGTH = 40;
	private static BinaryGenotype genotype;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		genotype = new BinaryGenotype( GENOME_LENGTH);
	}

	@Test ( expected = RuntimeException.class )
	public final void testMutateException() {
		genotype.mutate(5);
	} 

	@Test
	public final void testMutate() {
		int length = genotype.getGenome().length;
		String genome = genotype.toString();
		genotype.mutate(0.5);
		String mutatedGenome = genotype.toString();
		
		//Check to see if the two strings are unequal
		boolean unequal = ( genome.compareTo(mutatedGenome) != 0 );
		assertEquals(true, unequal);
		
		assertEquals( length , genotype.getGenome().length );
	} 
}
