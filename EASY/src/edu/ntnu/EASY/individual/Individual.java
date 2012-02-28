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
package edu.ntnu.EASY.individual;

import edu.ntnu.EASY.FitnessCalculator;

public interface Individual<GType,PType> extends Comparable<Individual<?, PType>>{

	public double getFitness();
	
	public PType getPhenome();

	public GType getGenome();
	
	public void growUp();
	
	public double updateFitness(FitnessCalculator<PType> fitCalc);

	public int getAge();

	public void incrementAge();
}
