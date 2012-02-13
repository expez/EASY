package edu.ntnu.EASY.util;

import java.util.Random;

import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;


public class Util {

	public static final Random RNG = new Random();
	
	public static <GType,PType> double getFitnessVariance(Population<GType,PType> individuals, double mean) {
	double variance = 0;	
	for (Individual<GType, PType> individual : individuals) {
		variance += ( individual.getFitness() - mean) * ( individual.getFitness() - mean);
	}
	return variance / individuals.size();
}

	
}
