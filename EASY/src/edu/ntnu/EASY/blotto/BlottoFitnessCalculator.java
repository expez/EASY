package edu.ntnu.EASY.blotto;

import edu.ntnu.EASY.FitnessCalculator;
import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;

public class BlottoFitnessCalculator implements FitnessCalculator<double[]> {

	
	public double Rf;
	public double Lf;
	Population<?,double[]> population = null;
	
	public BlottoFitnessCalculator(double Rf, double Lf){
		this.Rf = Rf; 
		this.Lf = Lf;
	}
	
	@Override
	public void setPopulation(Population<?, double[]> population) {
		this.population = population; 
	}

	@Override
	public double calculate(double[] phenome) {
		double score = 0;
		double[] me = new double[phenome.length];
		double[] you = new double[phenome.length];
		for(Individual<?,double[]> individual : population){
			System.arraycopy(phenome,0,me,0,me.length);
			System.arraycopy(individual.getPhenome(),0,you,0,me.length);
			double meStrength = 1.0;
			double youStrength = 1.0;
			int won = 0;
			int lost = 0;
			for(int i = 0; i < me.length; i++){
				double checkWin = (me[i] * meStrength) - (you[i] * youStrength);
				if(checkWin > 0){
					won++;
					for(int j = i+1; j < me.length; j++){
						me[i] += Rf * (me[i] - you[i]);
					}
					youStrength -= Lf;
				} else if (checkWin < 0){
					lost++;
					for(int j = i+1; j < you.length; j++){
						you[i] += Rf * (you[i] - me[i]);
					}
					meStrength -= Lf;
				}
			}
			if(won > lost){
				score += 2; 
			} else if(won == lost){
				score += 1;
			}
		}
		return score / (population.size() * 2);
	}

}
