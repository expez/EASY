package edu.ntnu.EASY;

import java.io.PrintStream;
import java.util.Arrays;

import edu.ntnu.EASY.individual.Individual;

public class BasicReport implements Report<int[], int[]> {

	@Override
	public void log(int generation, Population<int[], int[]> population) {
		double bestFitness = population.get(0).getFitness();
		int[] bestPhenome = population.get(0).getPhenome();
		for(Individual<int[],int[]> ind : population){
			if(bestFitness < ind.getFitness()){
				bestFitness = ind.getFitness();
				bestPhenome = ind.getPhenome();
			}
		}
		System.out.printf("%4d: %.2f, %s%n",generation,bestFitness,Arrays.toString(bestPhenome));
	}

	@Override
	public void writeToStream(PrintStream out) {

	}

}
