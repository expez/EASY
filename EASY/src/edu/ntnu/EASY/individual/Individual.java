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
public abstract class Individual implements Comparable<Individual>{

	private double fitness;

	private void growup() {};

	public void setFitness( double fitness ) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public int compareTo(Individual individual) {
		if( this.fitness == individual.getFitness() ) {
			return 0;
		}
		else if( this.fitness > individual.getFitness() ) {
			return 1;
		}
		return -1;
	}
}
