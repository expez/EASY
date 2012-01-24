package edu.ntnu.EASY.genotype;

import java.util.Random;
public class BinaryGenotype {
    
    int length;
    int[] genome;
    java.util.Random random;

    /**
    Creates a new binary genotype with a genome of length length.
     */
    public BinaryGenotype( int length ) {
	this.length = length;
	genome = new int[length];

	// Randomly populate the genome.
	random = new java.util.Random();
	
	for (int i = 0; i < length; i++) {
	    //nextInt from 0, inclusive, to 2 exclusive.
	    genome[ i ] = random.nextInt( 2 );
	}
    }

    /**
       Mutates each bit in the binary genome with probability
       mutationRate.
       
       @param mutationRate probability that a single bit is
       flipped. Double in interval [0,1].
     */
    public void mutate( double mutationRate ) throws InvalidArgumentExceptions {
	if( !( 0 <= mutationRate <= 1) ) {
	    throw new InvalidArgumentException( "MutationRate must be in interval [0,1]" );
	}
	// For every bit in the genome.
	for (int i = 0; i < length; i++) {
	    // Randomly flip it.
	    if( random.nextDouble <= mutationRate ) {
		genome[ i ] = flipBit( genome[ i ] );
	    }
	}
    } 

    private void flipBit( int bit) {
	if( bit == 1) {
	    return 0;
	}
	return 1;
    }
}
