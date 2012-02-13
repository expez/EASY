package edu.ntnu.EASY;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ntnu.EASY.individual.Individual;

public class Population<GType, PType> implements Iterable<Individual<GType, PType>>{
	
	private List<Individual<GType, PType>> population;
	
	public Population() {
		this.population = new LinkedList<Individual<GType, PType>>();
	}
	
	public Population(List<Individual<GType,PType>> individuals) {
		this.population = new LinkedList<Individual<GType, PType>>(individuals);
	}
	
	public void add(Individual<GType, PType> individual ){
		population.add(individual);
	}

	public void addAll(Population<GType, PType> individuals){
		for(Individual<GType,PType> individual : individuals)
			population.add(individual);
	}
	
	public Individual<GType, PType> get(int index) {
		return population.get(index);
	}
	
	public int size() {
		return population.size();
	}
	
	public Iterator<Individual<GType, PType>> iterator() {
		return population.iterator();
	}

	public void drop(int n) {
		Collections.sort(population);
		for(int i = 0; i < n; i++){
			population.remove(0);
		}
	}
	
	public void sort(){
		sort(false);
	}
	
	public void sort(boolean desc){
		Collections.sort(population);
		if(desc)
			Collections.reverse(population);
	}
}
