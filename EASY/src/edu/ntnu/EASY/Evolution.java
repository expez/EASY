package edu.ntnu.EASY;

import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.individual.FitnessCalculator;
import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.selection.adult.AdultSelector;
import edu.ntnu.EASY.selection.parent.ParentSelector;

public class Evolution<GType, PType> {

	Population<GType, PType> population;
	FitnessCalculator<PType> fitCalc;
	AdultSelector<PType> adultSelector;
	ParentSelector<PType> parentSelector;
	Incubator<GType, PType> incubator;	

	Environment env;
	
	public Evolution(Population<GType, PType> population, FitnessCalculator<PType> fitCalc, AdultSelector<PType> adultSelector, ParentSelector<PType> parentSelector, Incubator<GType,PType> incubator){
		this.population = population;
		this.fitCalc = fitCalc;
		this.adultSelector = adultSelector;
		this.parentSelector = parentSelector;
		this.incubator = incubator;
	}
	
	@SuppressWarnings("unchecked")
	public void runEvolution(){
		

		Population<GType,PType> children = new Population<GType,PType>();
		Population<GType,PType> parents = new Population<GType,PType>();
		
		
		double maxFitness = 0.0;
		for(int generation = 1; generation <= env.maxGenerations && maxFitness < env.fitnessThreshold; generation++){
			fitCalc.setPopulation(population);
			for(Individual<GType,PType> individual : population){
				individual.calculateFitness(fitCalc);
			}
			
			parents = (Population<GType, PType>) parentSelector.select(population);

			children = incubator.makeChildren(parents);

			population = (Population<GType, PType>) adultSelector.select(population,children);
		}
	}
}
