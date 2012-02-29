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
package edu.ntnu.EASY;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.individual.Individual;

public class Population<GType, PType> implements Iterable<Individual<GType, PType>>{
	
	private FitnessCalculator<PType> fitCalc;
	private List<Individual<GType, PType>> individuals;
	
	public Population(FitnessCalculator<PType> fitCalc) {
		this.fitCalc = fitCalc;
		this.individuals = new LinkedList<Individual<GType, PType>>();
	}
	
	public Population(Population<GType, PType> population) {
		this.individuals = new LinkedList<Individual<GType, PType>>();
		this.fitCalc = population.fitCalc;
		for (Individual<GType, PType> individual : population) {
			this.individuals.add(individual);
		}
	}
	
	public static <GType, PType> Population<GType, PType> getRandomPopulation(Incubator<GType, PType> incubator,
				FitnessCalculator<PType> fitCalc, 
				int numIndividuals) {
		Population<GType, PType> population= new Population<GType, PType>(fitCalc);
			while(population.size() < numIndividuals) {
				population.add(incubator.randomIndividual());
		}
		return population;
	}
	
	public Population(List<Individual<GType,PType>> individuals) {
		this.individuals = new LinkedList<Individual<GType, PType>>(individuals);
	}
	
	public void add(Individual<GType, PType> individual ){
		individuals.add(individual);
	}

	public void addAll(Population<GType, PType> individuals){
		for(Individual<GType,PType> individual : individuals)
			this.individuals.add(individual);
	}
	
	public Individual<GType, PType> get(int index) {
		return individuals.get(index);
	}
	
	public int size() {
		return individuals.size();
	}
	
	public Iterator<Individual<GType, PType>> iterator() {
		return individuals.iterator();
	}

	public void drop(int n) {
		Collections.sort(individuals);
		for(int i = 0; i < n; i++){
			individuals.remove(0);
		}
	}
	
	public void sort(){
		sort(false);
	}
	
	public void sort(boolean desc){
		Collections.sort(individuals);
		if(desc)
			Collections.reverse(individuals);
	}
	
	public Population<GType, PType> copy() {
		return new Population<GType, PType>(this);
	}
	
	public void clear() {
		individuals.clear();
	}
	
	public void shuffle() {
		Collections.shuffle(individuals);
	}

	/**
	 * @param Size of list to return
	 * @return Returns a population of the first size individuals from the current population.
	 */
	public Population<GType, PType> getSubset(int size) {
		Population<GType, PType> copy = copy();
		while( size < copy.size() ) {
			copy.individuals.remove(copy.size() - 1 );
		}
		return copy;
	}
	
	public void updateFitness(){
		fitCalc.setPopulation(this);
		for(Individual<GType,PType> individual : individuals){
			individual.updateFitness(fitCalc);
		}
	}

	public FitnessCalculator<PType> getFitnessCalculator() {
		return fitCalc;
	}

	public void incrementAge() {
		for (Individual<GType, PType> ind : individuals) {
			ind.incrementAge();
		}
		
	}

	public void remove(int i) {
		individuals.remove(i);
		
	}
	
	public void remove(Individual<GType, PType> ind) {
		individuals.remove(ind);
	}
}
