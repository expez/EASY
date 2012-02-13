package edu.ntnu.EASY;

import java.util.Arrays;

import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.selection.adult.AdultSelector;
import edu.ntnu.EASY.selection.parent.ParentSelector;

public class Evolution<GType, PType> {

	FitnessCalculator<PType> fitCalc;
	AdultSelector<PType> adultSelector;
	ParentSelector<PType> parentSelector;
	Incubator<GType, PType> incubator;	

	
	public Evolution(FitnessCalculator<PType> fitCalc, AdultSelector<PType> adultSelector, ParentSelector<PType> parentSelector, Incubator<GType,PType> incubator){
		this.fitCalc = fitCalc;
		this.adultSelector = adultSelector;
		this.parentSelector = parentSelector;
		this.incubator = incubator;
	}
	
	public void runEvolution(Environment env, Report<GType, PType> report){
		
		adultSelector.setEnvironment(env);
		parentSelector.setEnvironment(env);
		incubator.setEnvironment(env);
		
		Population<GType,PType> population = new Population<GType,PType>(fitCalc);
		for(int i = 0; i < env.populationSize; i++){
			Individual<GType,PType> ind = incubator.randomIndividual();
			ind.growUp();
			population.add(ind);
		}
		Population<GType,PType> children = new Population<GType,PType>(fitCalc);
		Population<GType,PType> parents = new Population<GType,PType>(fitCalc);
		
		double maxFitness = 0.0;
		for(int generation = 1; generation <= env.maxGenerations && maxFitness < env.fitnessThreshold; generation++){
			population.updateFitness();
			
			report.log(generation,population);
			System.out.printf("%d/%d - %d%n",generation,env.maxGenerations,population.size());
			
			parents = parentSelector.select(population);

			children = incubator.makeChildren(parents);
			for(Individual<GType,PType> child : children){
				child.growUp();
			}
			population = adultSelector.select(population,children);
		}
	}
}
