package edu.ntnu.EASY.reproductionMechanism;

import edu.ntnu.EASY.individual.Individual;
public interface ReproductionMechanism {
    
    public Individual getChild( Individual mom, Individual dad);
}
