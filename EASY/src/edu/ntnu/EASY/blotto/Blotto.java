package edu.ntnu.EASY.blotto;

import java.io.FileNotFoundException;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Evolution;
import edu.ntnu.EASY.FitnessCalculator;
import edu.ntnu.EASY.Report;
import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.selection.adult.AdultSelector;
import edu.ntnu.EASY.selection.adult.FullGenerationalReplacement;
import edu.ntnu.EASY.selection.parent.FitnessProportionateSelection;
import edu.ntnu.EASY.selection.parent.ParentSelector;

public class Blotto {

	Environment env;
	
	public Blotto(){
		env = new Environment();
		env.populationSize = 20;
		env.maxGenerations = 500;
		env.fitnessThreshold = 35;
		env.mutationRate = 0.005;
		env.crossoverRate = 0.005;
		env.numChildren = 20;
		env.numParents = 10;
		env.elitism = 10;
	}
	
	public Report<double[],double[]> runBlottoEvolution(int B, double Rf, double Lf) {
		FitnessCalculator<double[]> fitCalc = new BlottoFitnessCalculator(Rf,Lf);
		AdultSelector<double[]> adultSelector = new FullGenerationalReplacement<double[]>();
		ParentSelector<double[]> parentSelector = new FitnessProportionateSelection<double[]>();
		Incubator<double[], double[]> incubator = new BlottoIncubator(new BlottoReplicator(B));	
		Evolution<double[],double[]> evo = new Evolution<double[], double[]>(fitCalc, adultSelector, parentSelector, incubator);

	
		Report<double[],double[]> report = new BlottoReport(env.maxGenerations);
		evo.runEvolution(env, report);
		return report;
	}
}
