package edu.ntnu.EASY.blotto;

import edu.ntnu.EASY.FitnessCalculator;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;

public class BlottoFitnessCalculator implements FitnessCalculator<double[]> {

	Population<?,double[]> population = null;
	
	@Override
	public void setPopulation(Population<?, double[]> population) {
		this.population = population; 
	}

	@Override
	public double calculate(double[] phenome) {
		double score = 0;
		for(Individual<?,double[]> individual : population){
			double[] other = individual.getPhenome();
			int battlesWon = 0;
			int battlesLost = 0;
			for(int i = 0; i < phenome.length; i++){
				if(phenome[i] > other[i])
					battlesWon++;
				else if (phenome[i] < other[i])
					battlesLost++;
			}
			if(battlesWon > battlesLost){
				score += 2; 
			} else if(battlesWon == battlesLost){
				score += 1;
			}
		}
		return score / population.size();
	}

}
