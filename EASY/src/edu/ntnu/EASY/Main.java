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
import java.util.Arrays;

import edu.ntnu.EASY.incubator.BitvectorIncubator;
import edu.ntnu.EASY.incubator.BitvectorReplicator;
import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.individual.BitvectorIndividual;
import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.selection.adult.AdultSelector;
import edu.ntnu.EASY.selection.adult.FullGenerationalReplacement;
import edu.ntnu.EASY.selection.parent.FitnessProportionateSelection;
import edu.ntnu.EASY.selection.parent.ParentSelector;


public class Main {
    
	private	static int populationSize = 1000;
	private	static int maxGenerations = 10000;
	private	static double fitnessThreshold = 99;
	private	static double mutationRate = 0.05;
	private	static double crossoverRate = 0.05;
	private	static int bits = 40;
	private static int rank = 50;
	private static boolean elitsm = true;

    public static void main(String[] args) {
    	FitnessCalculator<int[]> fitCalc = IntegerArrayFitnessCalculators.ONE_MAX_FITNESS;
    	AdultSelector<int[]> adultSelector = new FullGenerationalReplacement<int[]>();
    	ParentSelector<int[]> parentSelector = new FitnessProportionateSelection<int[]>();
    	Incubator<int[], int[]> incubator = new BitvectorIncubator(new BitvectorReplicator(100));	
    	Evolution<int[],int[]> evo = new Evolution<int[], int[]>(fitCalc, adultSelector, parentSelector, incubator);

    	Environment env = new Environment();
    	env.populationSize = 1000;
    	env.maxGenerations = 100000;
    	env.fitnessThreshold = 35;
    	env.mutationRate = 0.005;
    	env.crossoverRate = 0.005;
    	env.numChildren = 1000;
    	env.numParents = 20;
    	env.elitism = 10;
    	
    	evo.runEvolution(env);
    }
    
    public static void foo(){
    	int[] best = {1,1,1,1};
    	int[] bad = {0,0,0,0};
    	int[] better = {1,0,0,1};
    	Population<int[],int[]> pop = new Population<int[],int[]>();
    	pop.add(new BitvectorIndividual(bad));
    	pop.add(new BitvectorIndividual(better));
    	pop.add(new BitvectorIndividual(best));
    	
    	FitnessCalculator<int[]> fitCalc = IntegerArrayFitnessCalculators.ONE_MAX_FITNESS;
    	
    	fitCalc.setPopulation(pop);
    	pop.sort(false);
    	for(Individual<int[],int[]> ind : pop){
    		ind.growUp();
    		ind.updateFitness(fitCalc);
    		System.out.printf("%s%n",Arrays.toString(ind.getGenome()));
    	}    	
    	
    	System.out.println("---");
    	
    	pop.sort(true);
    	for(Individual<int[],int[]> ind : pop){
    		ind.updateFitness(fitCalc);
    		System.out.printf("%s%n",Arrays.toString(ind.getGenome()));
    	}    	
    	
		
	}
}
