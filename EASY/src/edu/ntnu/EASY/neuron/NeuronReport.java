package edu.ntnu.EASY.neuron;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;

import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.Report;
import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.util.Util;

public final class NeuronReport implements Report<double[],double[]>{

	private Entry[] entries;
	
	private PrintWriter pw;
	
	public NeuronReport(int generations){
		entries = new Entry[generations + 1];
		try {
			pw = new PrintWriter(new FileWriter("genomes.log"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void log(int generation, Population<double[], double[]> population) {
		double average = 0.0;
		double bestFitness = population.get(0).getFitness();
		double[] bestGenome = population.get(0).getGenome();
		double[] bestPhenome = population.get(0).getPhenome();
		for(Individual<double[], double[]> individual : population){
			average += individual.getFitness();
			if(individual.getFitness() > bestFitness){
				bestFitness = individual.getFitness();
				bestGenome = individual.getGenome();
				bestPhenome = individual.getPhenome();
			}
			pw.printf("%s ",Util.arrayToString("%.2f",individual.getGenome()));
		}
		pw.println();
		System.out.printf("%d/%d - %f%n",generation,entries.length,bestFitness);
		entries[generation] = new Entry(bestGenome,bestPhenome,bestFitness,average / population.size());
		pw.flush();
	}

	@Override
	public void writeToStream(PrintStream out) {
		for (int i = 1; i < entries.length; i++) {
			out.printf("%4d | %.3f | %s%n",i,entries[i].averageFitness,Arrays.toString(entries[i].bestGenome));
		}
	}

	public double[] getBestPhenome() {
		return entries[entries.length - 1].bestPhenome;
	}


	public double[] getAverageFitness() {
		double[] tab = new double[entries.length];
		for(int i = 1; i < tab.length; i++){
			tab[i] = entries[i].averageFitness;
		}
		return tab;
	}

	public double[] getBestFitness() {
		double[] tab = new double[entries.length];
		for(int i = 1; i < tab.length; i++){
			tab[i] = entries[i].bestFitness;
		}
		return tab;
	}
	
	public static class Entry {
		
		private double[] bestGenome;
		private double[] bestPhenome;
		private double bestFitness;
		private double averageFitness;
		
		Entry(double[] bestGenome,double[] bestPhenome, double bestFitness, double averageFitness){
			this.bestFitness = bestFitness;
			this.averageFitness = averageFitness;
			this.bestGenome = bestGenome;
			this.bestPhenome = bestPhenome;
		}
		
	}


}
