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


public abstract class AbstractIndividual<GType, PType> implements Individual<GType, PType>{

	protected GType genome;
	protected PType phenome;
	
	private double fitness;
	int age;

	public AbstractIndividual(GType genome){
		this.genome = genome;
		this.age = 0;
	}

	public GType getGenome(){
		return genome;
	}
	
	public PType getPhenome(){
		return phenome;
	}
	
	public abstract void growUp();

	public double getFitness() {
		return fitness;
	}
	
	public double updateFitness(FitnessCalculator<PType> fitCalc){
		if(phenome == null) {
			growUp();
		}
		return fitness = fitCalc.calculate(phenome);
	}

	public int compareTo(Individual<?, PType> that) {
		if(this.fitness > that.getFitness())
			return 1;
		if(this.fitness < that.getFitness())
			return -1;
		return 0;
	}

	@Override
	public int getAge() {
		return age;
	}
	
	public void incrementAge() {
		age++;
	}
}
