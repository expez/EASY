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

import edu.ntnu.EASY.BinaryGenotype;
public class BitvectorIndividual extends Individual{

    private BinaryGenotype genotype;
    private int[] phenotype;
        
    public BitvectorIndividual( int length ) {
    
    	genotype = new BinaryGenotype( length );
    	phenotype = new int[length];
    }

    public int[] getPhenotype() {
    	phenotype = genotype.getGenome();
    	return phenotype;
    } 

    public BinaryGenotype getGenotype() {
    	return genotype;
    }

    /**
       Returns a list of bitvectorindividuals.

       @param numIndividuals number of individuals to create.

       @param length of bitvector.

       @return a list of BitvectorIndividuals
     */
    public static LinkedList<BitvectorIndividual> getIndividuals( int numIndividuals, int length ) {
    	LinkedList< BitvectorIndividual > list = new LinkedList< BitvectorIndividual >();

    	for (int i = 0; i < numIndividuals; i++) {
    		list.add( new BitvectorIndividual( length ) );
    	}

    	return list;
    } 
}
