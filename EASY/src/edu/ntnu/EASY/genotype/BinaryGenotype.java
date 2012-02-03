/*Copyright (C) 2012 Lars Andersen, Tormund S. Haus.
larsan@stud.ntnu.no
tormunds@ntnu.no

EASY is free software: you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
 
EASY is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.
 
You should have received a copy of the GNU General Public License
    along with EASY.  If not, see <http://www.gnu.org/licenses/>.*/

package edu.ntnu.EASY.genotype;

import java.util.Random;

public class BinaryGenotype {
    
    private int length;
    private int[] genome;
    private Random random;

    /**
    Creates a new binary genotype with a genome of length length.
     */
    public BinaryGenotype( int length ) {
	this.length = length;
	genome = new int[length];

	// Randomly populate the genome.
	random = new Random();
	
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
    public void mutate( double mutationRate ) throws RuntimeException {
	//Make sure mutationRate is in interval [0,1]
	if( !( 0 <= mutationRate && mutationRate <= 1) ) {
	    throw new RuntimeException( "MutationRate must be in interval [0,1]" );
	}
	// For every bit in the genome.
	for (int i = 0; i < length; i++) {
	    // Randomly flip it.
	    if( random.nextDouble() <= mutationRate ) {
		genome[ i ] = flipBit( genome[ i ] );
	    }
	}
    } 
    
    /**
       Prints each bit in the genome.
     */
    public String toString() {
	String genomeString = "";
	for (int i = 0; i < length; i++) {
	    genomeString += genome[ i ];
	}
	return genomeString;
    } 
    private int flipBit( int bit) {
	if( bit == 1) {
	    return 0;
	}
	return 1;
    }
    
    public int[] getGenome() {
	return genome;
    } 

    public void setGenome( int[] genome ) {
	this.genome = genome;
    } 

}
