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
package edu.ntnu.EASY.blotto;

import java.io.PrintStream;

import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.Report;
import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.util.Util;

public class BlottoReport implements Report<double[],double[]>{

	Entry[] entries;
	
	public BlottoReport(int generations){
		entries = new Entry[generations + 1];
	}
	
	@Override
	public void log(int generation, Population<double[], double[]> population) {
		double max = 0.0, sd = 0.0;
		double average = 0.0;
		double entropy = 0.0;
		double[] best = population.get(0).getPhenome();
		for(Individual<double[],double[]> individual : population){
			average += individual.getFitness();
			entropy += calculateStrategyEntropy(individual.getPhenome());
			if(individual.getFitness() > max){
				max = individual.getFitness();
				best = individual.getPhenome();
			}
		}
		average /= population.size();
		sd = Math.sqrt(Util.getFitnessVariance(population, average));
		entropy /= population.size();
		entries[generation] = new Entry(max,average,sd,entropy,best);
		System.out.printf("%d/%d - %d%n",generation,entries.length - 1,population.size());
	}
	
	private double calculateStrategyEntropy(double[] p) {
		double h = 0.0;
		for(int i = 0; i < p.length; i++){
			if(0 < p[i])
				h -= (p[i] * Util.log2(p[i]));
		}
		return h;
	}

	public void writeFitnessPlot(PrintStream plot) {
		for(int i = 1; i < entries.length; i++)
			plot.printf("%d %f %f %f%n",i,entries[i].maxFitness,entries[i].averageFitness,entries[i].standardDeviation);
	}
	
	public void writeEntropyPlot(PrintStream plot) {
		for(int i = 1; i < entries.length; i++)
			plot.printf("%d %f%n",i,entries[i].averageStrategyEntropy);
	}
	
	@Override
	public void writeToStream(PrintStream out) {
		out.println("+-----+------+------+------+------+ ");
		out.println("| gen | best | mean |  sd  | avgH | best strategy");
		out.println("+-----+------+------+------+------+ ");
		for(int i = 1; i < entries.length; i++)
			out.printf("| %4d%s%n",i,entries[i]);
		out.println("+-----+------+------+------+------+ ");
	}
	
	static class Entry {
		
		private double maxFitness;
		private double averageFitness;
		private double standardDeviation;
		private double averageStrategyEntropy;
		private double[] bestPhenom;
		
		public Entry(double maxFitness,	double averageFitness,double standardDeviation, double averageStrategyEntropy, double[] bestPhenom){
			this.maxFitness = maxFitness;
			this.averageFitness = averageFitness;
			this.standardDeviation = standardDeviation;
			this.averageStrategyEntropy = averageStrategyEntropy;
			this.bestPhenom = bestPhenom;
		}
		
		public String toString(){
			return String.format("|%.4f|%.4f|%.4f|%.4f| - %s",maxFitness,averageFitness,standardDeviation,averageStrategyEntropy,Util.arrayToString("%.3f",bestPhenom));
		}
	}


}
