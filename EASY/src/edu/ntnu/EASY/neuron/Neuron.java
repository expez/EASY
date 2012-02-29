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
package edu.ntnu.EASY.neuron;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Evolution;
import edu.ntnu.EASY.FitnessCalculator;
import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.selection.adult.AdultSelector;
import edu.ntnu.EASY.selection.adult.FullGenerationalReplacement;
import edu.ntnu.EASY.selection.adult.GenerationalMixing;
import edu.ntnu.EASY.selection.adult.Overproduction;
import edu.ntnu.EASY.selection.parent.ParentSelector;
import edu.ntnu.EASY.selection.parent.SigmaScaledSelector;
import edu.ntnu.EASY.selection.parent.StochasticTournamentSelector;
import edu.ntnu.EASY.selection.parent.TournamentSelector;
import edu.ntnu.EASY.util.Util;
import edu.ntnu.plotting.Plot;

public class Neuron {

	Environment env;
	
	public Neuron(){
		env = new Environment();
		env.populationSize = 1000;
		env.maxGenerations = 10000;
		env.fitnessThreshold = 2.0;
		env.mutationRate = 0.01;
		env.crossoverRate = 0.01;
		env.numChildren = 100;
		env.numParents = 200;
		env.elitism = 0;
		env.e = 0.6;
		env.rank = 6;
		env.maxAge = 10;
	}
	
	private FitnessCalculator<double[]> fitCalc;
	AdultSelector<double[]> adultSelector;
	ParentSelector<double[]> parentSelector;
	
	public NeuronReport runNeuronEvolution(double[] target) {
		fitCalc = new SpikeIntervalFitnessCalculator(target);
		adultSelector = new GenerationalMixing<double[]>(env.populationSize, env.maxAge, env.elitism);
		parentSelector = new StochasticTournamentSelector<double[]>(env.rank, env.numParents, env.e);
		Incubator<double[], double[]> incubator = new NeuronIncubator(new NeuronReplicator(env.mutationRate,env.crossoverRate), env.numChildren);	
		Evolution<double[],double[]> evo = new Evolution<double[], double[]>(fitCalc, adultSelector, parentSelector, incubator);

		NeuronReport report = new NeuronReport(env.maxGenerations);
		evo.runEvolution(env, report);
		return report;
	}
	
	public void writeEnv(PrintStream ps){
		ps.printf("env.fitcalc = %s%n",fitCalc.getClass());
		ps.printf("env.adult = %s%n",adultSelector.getClass());
		ps.printf("env.parent = %s%n",parentSelector.getClass());
		ps.printf("env.populationSize = %d%n",env.populationSize);
		ps.printf("env.maxGenerations = %d%n",env.maxGenerations);
		ps.printf("env.fitnessThreshold = %.2f%n",env.fitnessThreshold);
		ps.printf("env.mutationRate = %.2f%n",env.mutationRate);
		ps.printf("env.crossoverRate = %.2f%n",env.crossoverRate);
		ps.printf("env.numChildren = %d%n",env.numChildren);
		ps.printf("env.numParents = %d%n",env.numParents);
		ps.printf("env.elitism = %d%n",env.elitism);
		ps.printf("env.e = %.2f%n",env.e);
		ps.printf("env.rank = %d%n",env.rank);
		ps.printf("env.maxAge = %d%n",env.maxAge);
	}
	
	public static void main(String[] args) throws IOException {
		Neuron neuron = new Neuron();
		String train = "izzy-train4.dat";
		String dir = String.format("runs/%d",System.currentTimeMillis());
		double[] target = Util.readTargetSpikeTrain("training/" + train);
		new File(dir).mkdirs();
		long start = System.currentTimeMillis();
		NeuronReport neuronReport = neuron.runNeuronEvolution(target); 
		long stop = System.currentTimeMillis();
		
		double[] bestPhenome = neuronReport.getBestPhenome();
		Plot trains = Plot.newPlot("Trains")
			.setAxis("x","ms")
			.setAxis("y","activation")
			.with("bestPhenome",bestPhenome)
			.with("target",target)
			.make();
		
		double[] averageFitness = neuronReport.getAverageFitness();
		double[] bestFitness = neuronReport.getBestFitness();

		Plot fitness = Plot.newPlot("Fitness")
			.setAxis("x","generation")
			.setAxis("y","fitness")
			.with("average",averageFitness)
			.with("best",bestFitness)
			.make();
		
		trains.plot();
		fitness.plot();
		
		PrintStream log = new PrintStream(new FileOutputStream(dir + "/run.log"));
		PrintStream prop = new PrintStream(new FileOutputStream(dir + "/properties.log"));
		neuronReport.writeToStream(log);
		prop.printf("trainfile = %s%n",train);
		prop.printf("runtime (sec) = %d%n",(stop - start) / 1000);
		neuron.writeEnv(prop);
		trains.writeToFile(dir + "/trains-plot");
		fitness.writeToFile(dir + "/fitness-plot");
		
	}
}
