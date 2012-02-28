package edu.ntnu.EASY.neuron;

import java.util.LinkedList;
import java.util.List;

import edu.ntnu.EASY.FitnessCalculator;
import edu.ntnu.EASY.Population;

public class AggreagateFitnessCalculator<PType> implements FitnessCalculator<PType> {

	List<FitnessCalculator<PType>> calculators = new LinkedList<FitnessCalculator<PType>>();
	
	public AggreagateFitnessCalculator<PType> addCalculator(FitnessCalculator<PType> calculator){
		calculators.add(calculator);
		return this;
	}
	
	@Override
	public void setPopulation(Population<?,PType> population) {
		for(FitnessCalculator<PType> calculator : calculators){
			calculator.setPopulation(population);
		}
	}

	@Override
	public double calculate(PType phenome) {
		double fitness = 0.0;
		for(FitnessCalculator<PType> calculator : calculators){
			double f = calculator.calculate(phenome);
			fitness += f;
		}
		return fitness / calculators.size();
	}

}
