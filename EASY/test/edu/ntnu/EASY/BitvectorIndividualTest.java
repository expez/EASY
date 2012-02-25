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
package edu.ntnu.EASY;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ntnu.EASY.individual.BitvectorIndividual;

import java.util.LinkedList;
import java.util.Random;
public class BitvectorIndividualTest {

	BitvectorIndividual bitvectorIndividual;
	private final static int GENOME_LENGTH = 40;
	private static Random random;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		random = new Random();
	}

	@Test
	public void testCompareTo() {
		LinkedList< BitvectorIndividual > individuals = BitvectorIndividual.getIndividuals(2, GENOME_LENGTH);
		for (BitvectorIndividual bitvectorIndividual : individuals) {
			bitvectorIndividual.setFitness( random.nextDouble( ) );
		}
		
		BitvectorIndividual ind1 = individuals.get(0);
		BitvectorIndividual ind2 = individuals.get(1);
		
		if( ind1.getFitness() < ind2.getFitness() ) {
			assertEquals( -1, ind1.compareTo(ind2));
		}
		else if( ind2.getFitness() < ind1.getFitness() ) {
			assertEquals( 1, ind1.compareTo( ind2 ) );
		}
		
		assertEquals( 0, ind1.compareTo( ind1 ) );
		
	}

}
