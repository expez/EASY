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
import java.util.List;

import edu.ntnu.EASY.individual.*;
public class OneMaxFitness {

    /**
       Fitness is equal to sum of 1s in the individual's phenotype.

       @param individual to get the fitness for.
     */

    public OneMaxFitness() {}
    
	 public void updateFitness(List<BitvectorIndividual> individuals) {
		double fitness;	
		int[] phenotype;
		for (BitvectorIndividual individual : individuals) {
			fitness = 0;
			phenotype = individual.getPhenotype();
	    	for (int i = 0; i < phenotype.length; i++) {
	    		if( phenotype[ i ] == 1 ) {
	    			fitness++;
	    		}
	    	}
	    	individual.setFitness( fitness / (double)phenotype.length);
		}
	 }
}