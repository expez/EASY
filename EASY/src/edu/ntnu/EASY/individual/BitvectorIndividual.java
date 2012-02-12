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

package edu.ntnu.EASY.individual;

import java.util.LinkedList;

public class BitvectorIndividual extends AbstractIndividual<int[],int[]>{

    public BitvectorIndividual(int[] genome) {
    	super(genome);
    }

    @Override
    public void growUp() {
    	phenome = new int[genome.length];
    	System.arraycopy(genome,0,phenome,0,genome.length);
    }
    
    /**
       Returns a list of bitvectorindividuals.

       @param numIndividuals number of individuals to create.

       @param length of bitvector.

       @return a list of BitvectorIndividuals
     */
    public static LinkedList<BitvectorIndividual> getIndividuals( int numIndividuals, int[] genome) {
    	LinkedList< BitvectorIndividual > list = new LinkedList< BitvectorIndividual >();

    	for (int i = 0; i < numIndividuals; i++) {
    		list.add( new BitvectorIndividual(genome));
    	}

    	return list;
    }
}
