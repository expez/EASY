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
package edu.ntnu.EASY;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.ntnu.EASY.blotto.Blotto;
import edu.ntnu.EASY.blotto.BlottoReport;
import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.neuron.NeuronIncubator;
import edu.ntnu.EASY.neuron.NeuronReplicator;
import edu.ntnu.EASY.neuron.NeuronReport;
import edu.ntnu.EASY.neuron.SpikeIntervalFitnessCalculator;
import edu.ntnu.EASY.neuron.SpikeTimeFitnessCalculator;
import edu.ntnu.EASY.neuron.WaveformFitnessCalculator;
import edu.ntnu.EASY.selection.adult.AdultSelector;
import edu.ntnu.EASY.selection.adult.FullGenerationalReplacement;
import edu.ntnu.EASY.selection.adult.GenerationalMixing;
import edu.ntnu.EASY.selection.adult.Overproduction;
import edu.ntnu.EASY.selection.parent.BoltzmanSelector;
import edu.ntnu.EASY.selection.parent.FitnessProportionateSelector;
import edu.ntnu.EASY.selection.parent.ParentSelector;
import edu.ntnu.EASY.selection.parent.RankSelector;
import edu.ntnu.EASY.selection.parent.SigmaScaledSelector;
import edu.ntnu.EASY.selection.parent.StochasticTournamentSelector;
import edu.ntnu.EASY.selection.parent.TournamentSelector;
import edu.ntnu.EASY.util.Util;
import edu.ntnu.plotting.Plot;

public class Main {

	private static final Options options = new Options();
	private static final String USAGE = "java -jar easy.jar [pgtmckoePA]\n" +
											"ParentSelectors:\n" +
											"\t(1) Boltzman (1)\n" +
											"\t(2) FitnessProportionate\n" +
											"\t(3) Rank\n" +
											"\t(3) SigmaScaled\n" +
											"\t(4) Tournament\n" +
											"\t(5) StochasticTournament\n\n" +
											"AdultSelectors:\n" +
											"\t(1) FullGenerationalReplacement\n" +
											"\t(2) GenerationalMixing\n" +
											"\t(3) Overproduction\n\n" +
											"FitnessCalculators:\n" +
											"\t(1) SpikeTimeDistanceMetric\n" +
											"\t(2) SpikeIntervalDistanceMetric\n" +
											"\t(3) WaveformDistanceMetric\n";
											
	static {
		options.addOption("p","population",true,"The population size of the system")
				.addOption("g","generations",true,"How many generations to simulate")
				.addOption("t","target",true,"The target spike train")
				.addOption("m","mutation",true,"The mutation rate of the system.")
				.addOption("c","crossover",true,"The crossover rate of the system.")
				.addOption("b","children",true,"Number of children produced every cycle.")
				.addOption("f","parents",true,"Number of parents selected for mating")
				.addOption("e","elitsm",true,"How many of the fittest individuals that skip selection")
				.addOption("P","parent-select",true,"Which parent selection strategy to use.")
				.addOption("A","adult-select",true,"Which adult selection strategy to use.")
				.addOption("C","calculator",true,"What fitness calculator to use")
				.addOption("?",false,"Print help")
				.addOption("h","help",false,"Print help")
				.addOption("o","output-files",true,"Prefix for the outputfiles (*-fit.png, *-train.png, *.log)")
				.addOption("r","rank",true,"The rank used is selection.");
	}
	
    public static void main(String[] args) {

    	CommandLine cl = null;
    	CommandLineParser clp = new BasicParser();
    	HelpFormatter hf = new HelpFormatter();
    	try {
    		cl = clp.parse(options,args);
    	} catch (ParseException e) {
    		e.printStackTrace();
    		hf.printHelp(USAGE,options);
    		System.exit(1);
    	}

    	if(cl.hasOption('h') || cl.hasOption('?')){
    		hf.printHelp(USAGE,options);
    		System.exit(0);
    	}
    	
    	Environment env = new Environment();
    	
		env.populationSize = Integer.parseInt(cl.getOptionValue('p',"200"));
		env.maxGenerations = Integer.parseInt(cl.getOptionValue('g',"1000"));
		env.fitnessThreshold = 2.0;
		env.mutationRate = Double.parseDouble(cl.getOptionValue('m',"0.01"));
		env.crossoverRate = Double.parseDouble(cl.getOptionValue('c',"0.01"));
		env.numChildren = Integer.parseInt(cl.getOptionValue('b',"200"));
		env.numParents = Integer.parseInt(cl.getOptionValue('f',"20"));
		env.rank = Integer.parseInt(cl.getOptionValue('r',"10"));
		env.elitism = Integer.parseInt(cl.getOptionValue('e',"3"));
    	
		ParentSelector<double[]> parentSelector = null;
		int parent = Integer.parseInt(cl.getOptionValue('P',"1"));
		switch(parent){
		case 1:
			parentSelector = new BoltzmanSelector<double[]>(env.numParents);
			break;
		case 2:
			parentSelector = new FitnessProportionateSelector<double[]>(env.numParents);
			break;
		case 3:
			parentSelector = new RankSelector<double[]>(env.rank);
			break;
		case 4:
			parentSelector = new SigmaScaledSelector<double[]>(env.numParents);
			break;
		case 5:
			parentSelector = new TournamentSelector<double[]>(env.rank, env.numParents);
			break;
		case 6:
			parentSelector = new StochasticTournamentSelector<double[]>(env.rank, env.numParents,0.3);
			break;
		default:
			System.out.printf("No such parent selector: %d%n",parent);
			hf.printHelp(USAGE,options);
			System.exit(1);
		}
		
		AdultSelector<double[]> adultSelector = null;
		int adult = Integer.parseInt(cl.getOptionValue('A',"1"));
		switch(adult){
		case 1:
			adultSelector = new FullGenerationalReplacement<double[]>(env.elitism);
			break;
		case 2:
			adultSelector = new GenerationalMixing<double[]>(env.numAdults, 10, env.elitism);
			break;
		case 3:
			adultSelector = new Overproduction<double[]>(env.numAdults,env.elitism);
			break;
		default:
			System.out.printf("No such adult selector: %d%n",adult);
			hf.printHelp(USAGE,options);
			System.exit(1);
		}

		String targetFile = cl.getOptionValue('t',"target.dat");
			
		double[] target = null;
		try {
			target = Util.readTargetSpikeTrain(targetFile);
		} catch (IOException e) {
			System.out.printf("Couldn't read target file: %s%n",targetFile);
			hf.printHelp(USAGE,options);
			System.exit(1);
		}

		FitnessCalculator<double[]> fitCalc = null;
		int calc = Integer.parseInt(cl.getOptionValue('C',"1"));
		switch(calc){
		case 1:
			fitCalc = new SpikeTimeFitnessCalculator(target);
			break;
		case 2:
			fitCalc = new SpikeIntervalFitnessCalculator(target);
			break;
		case 3:
			fitCalc = new WaveformFitnessCalculator(target);
			break;
		default:
			System.out.printf("No such fitness calculator: %d%n",calc);
			hf.printHelp(USAGE,options);
			System.exit(1);
		}
		
		String output = cl.getOptionValue('o',"neuron");
		
		Incubator<double[], double[]> incubator = new NeuronIncubator(new NeuronReplicator(env.mutationRate,env.crossoverRate), env.numChildren);	
		
		Evolution<double[],double[]> evo = new Evolution<double[], double[]>(fitCalc, adultSelector, parentSelector, incubator);

		
		NeuronReport report = new NeuronReport(env.maxGenerations);
		evo.runEvolution(env, report);
		
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(output + ".log"));
			report.writeToStream(ps);
		} catch (FileNotFoundException e) {
			System.out.printf("Could not write to %s.log%n",output);
		}
		
		double[] bestPhenome = report.getBestPhenome();
		Plot train = Plot.newPlot("Neuron")
			.setAxis("x","ms")
			.setAxis("y","activation")
			.with("bestPhenome",bestPhenome)
			.with("target",target)
			.make();
		
		double[] averageFitness = report.getAverageFitness();
		double[] bestFitness = report.getBestFitness();

		Plot fitness = Plot.newPlot("Fitness")
			.setAxis("x","generation")
			.setAxis("y","fitness")
			.with("average",averageFitness)
			.with("best",bestFitness)
			.make();
		
		train.plot();
		fitness.plot();
		train.writeToFile(output + "-train");
		fitness.writeToFile(output + "-fitness");
    }
    
    public static void blotto(){
    	Blotto blotto = new Blotto();
    	
    	int[] Bs = {5,20};
    	double[] Rfs = {1.0,0.5,0.0};
    	double[] Lfs = {1.0,0.5,0.0};

    	for(int B : Bs){
    		for(double Rf : Rfs){
    			for(double Lf : Lfs){
    				double start = System.currentTimeMillis();
    				BlottoReport report = blotto.runBlottoEvolution(B,Rf,Lf);
    				double stop = System.currentTimeMillis();
    				String filename = String.format("log/blotto-%d-%.1f-%.1f",B,Rf,Lf);
    				try {
    					PrintStream log = new PrintStream(new FileOutputStream(filename + ".log"));
    					log.printf("# Blotto run, B: %d, Rf: %.2f, Lf: %.2f - %.2f sec %n",B,Rf,Lf,(stop-start)/1000);
    					report.writeToStream(log);
    					File plotfile = new File(filename + ".plot");
    					PrintStream plotStream = new PrintStream(new FileOutputStream(plotfile));
    					report.writePlot(plotStream);
    					Output.plotBlotto(plotfile,B,Rf,Lf);
    				} catch (FileNotFoundException e) {
    					System.err.printf("File not found: %s%n",filename);
    				}
    			}
    		}
    	}
    }
}
