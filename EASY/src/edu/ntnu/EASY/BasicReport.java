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

import java.io.PrintStream;
import java.util.Arrays;

import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.util.Util;

public class BasicReport implements Report<int[], int[]> {
	
	Output output;
	public BasicReport() {
		this.output = new Output("oneMaxOut");
	}

	@Override
	public void log(int generation, Population<int[], int[]> population) {
		double bestFitness = population.get(0).getFitness();
		int[] bestPhenome = population.get(0).getPhenome();
		double average = 0.0;
		for(Individual<int[],int[]> ind : population){
			if(bestFitness < ind.getFitness()){
				bestFitness = ind.getFitness();
				bestPhenome = ind.getPhenome();
			}
			average += ind.getFitness();
		}
		average /= population.size();
		double sd = Math.sqrt(Util.getFitnessVariance(population, average));
		System.out.printf("%4d: %.2f, %s%n",generation,bestFitness,Arrays.toString(bestPhenome));
		output.appendLine(generation + " " + bestFitness + " " + average + " " + sd);
		if(generation == 100)
			output.plot();
	}	

	@Override
	public void writeToStream(PrintStream out) {

	}

}
