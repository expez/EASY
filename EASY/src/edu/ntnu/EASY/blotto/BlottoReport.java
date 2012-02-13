package edu.ntnu.EASY.blotto;

import java.io.PrintStream;
import java.util.Arrays;

import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.Report;

public class BlottoReport extends Report<double[],double[]>{

	
	@Override
	public void log(int generation, Population<double[], double[]> population) {
		double max = 0.0, average = 0.0, sd = 0.0;
		for(Individual<PType,GType> individual : population){
			
		}
		
	}
	

	@Override
	public void writeToStream(PrintStream out) {
		// TODO Auto-generated method stub
		
	}
	
	
	static class Entry {
		private double maxFitness;
		private double averageFitness;
		private double standardDeviation;
		private double averageStrategyEntropy;
		private double[] bestPhenom;
		
		public String toString(){
			return String.format("|%.4f|%.4f|%.4f|%.4f| - %s",maxFitness,averageFitness,standardDeviation,averageStrategyEntropy,Arrays.toString(bestPhenom));
		}
	}
}
