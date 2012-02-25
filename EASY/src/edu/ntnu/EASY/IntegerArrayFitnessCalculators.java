/*Copyright (C) 2012 Lars Andersen, Tormund S. Haus.
larsan@stud.ntnu.no
tormunds@stud.ntnu.no

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


public class IntegerArrayFitnessCalculators {

    public static final FitnessCalculator<int[]> ONE_MAX_FITNESS = new FitnessCalculator<int[]>(){
    	{};

    	@Override
    	public void setPopulation(Population<?, int[]> population) {
    		
    	}
    
    	@Override
    	/**
       	 *	Fitness is equal to sum of 1s in the individual's phenotype.
       	 *	@param individual to get the fitness for.
       	 *	@return A double, in the interval [0,1], representing the fitness. 
    	 */
    	public double calculate(int[] phenome) {
    		double fitness = 0.0;	
    		for (int i = 0; i < phenome.length; i++) {
    			if( phenome[ i ] == 1 ) {
    				fitness++;
    			}
    		}
    		return fitness / phenome.length;
    	}
    };
}