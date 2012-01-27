package edu.ntnu.EASY.fitnessEvaluator;

import edu.ntnu.EASY.individual.Individual;

public interface FitnessEvaluator {
    public double getFitness(Individual individual);
}
