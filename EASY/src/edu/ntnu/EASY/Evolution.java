package edu.ntnu.EASY;

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
	
	public void runEvolution(Environment env){
		
		adultSelector.setEnvironment(env);
		parentSelector.setEnvironment(env);
		incubator.setEnvironment(env);
		
		Population<GType,PType> population = new Population<GType,PType>();
		for(int i = 0; i < env.populationSize; i++){
			Individual<GType,PType> ind = incubator.randomIndividual();
			ind.growUp();
			population.add(ind);
		}
		Population<GType,PType> children = new Population<GType,PType>();
		Population<GType,PType> parents = new Population<GType,PType>();
		
		double maxFitness = 0.0;
		for(int generation = 1; generation <= env.maxGenerations && maxFitness < env.fitnessThreshold; generation++){
			fitCalc.setPopulation(population);
			for(Individual<GType,PType> individual : population){
				individual.updateFitness(fitCalc);
			}
			population.sort(true);
			System.out.printf("Gen: %d, Fitness: %.2f%n",generation,population.get(0).getFitness());
			
			parents = parentSelector.select(population);

			children = incubator.makeChildren(parents);
			fitCalc.setPopulation(children);
			for(Individual<GType,PType> child : children){
				child.growUp();
				child.updateFitness(fitCalc);
			}
			population = adultSelector.select(population,children);
		}
	}
}
