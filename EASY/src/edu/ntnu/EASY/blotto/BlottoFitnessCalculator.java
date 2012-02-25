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
