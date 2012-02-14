package edu.ntnu.EASY;

import edu.ntnu.EASY.incubator.BitvectorIncubator;
import edu.ntnu.EASY.incubator.BitvectorReplicator;
import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.selection.adult.AdultSelector;
import edu.ntnu.EASY.selection.adult.FullGenerationalReplacement;
import edu.ntnu.EASY.selection.parent.FitnessProportionateSelector;
import edu.ntnu.EASY.selection.parent.ParentSelector;

public class Bitvector {
	Environment env;
	
	public Bitvector(){
		env = new Environment();
		env.populationSize = 20;
		env.maxGenerations = 500;
		env.fitnessThreshold = 2.0;
		env.mutationRate = 0.005;
		env.crossoverRate = 0.005;
		env.numChildren = 20;
		env.numParents = 10;
		env.elitism = 10;
	}
	
	public Report<int[],int[]> runBitvectorEvolution(int bits) {
		FitnessCalculator<int[]> fitCalc = IntegerArrayFitnessCalculators.ONE_MAX_FITNESS;
		AdultSelector<int[]> adultSelector = new FullGenerationalReplacement<int[]>(env.elitism);
		ParentSelector<int[]> parentSelector = new FitnessProportionateSelector<int[]>(env.numParents);
		Incubator<int[],int[]> incubator = new BitvectorIncubator(new BitvectorReplicator(bits, env.mutationRate,env.crossoverRate), env.numChildren);	
		Evolution<int[],int[]> evo = new Evolution<int[], int[]>(fitCalc, adultSelector, parentSelector, incubator);
		
	
		Report<int[],int[]> report = new BasicReport();
		evo.runEvolution(env, report);
		return report;
	}
}
