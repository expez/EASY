package edu.ntnu.EASY.reproductionMechanism;
import java.util.Random;

import edu.ntnu.EASY.individual.BitvectorIndividual;


public class BitvectorIncubator {

    private double mutationRate;
    private double crossoverRate;
    Random random;
    
    public BitvectorIncubator( double mutationRate,
			   double crossoverRate) {
	this.mutationRate = mutationRate;
	this.crossoverRate = crossoverRate;
	random = new Random();
    };

    public BitvectorIndividual getChild( BitvectorIndividual mom, BitvectorIndividual dad ) {
	int[]  momsGenome = mom.getGenotype().getGenome();
	int[]  dadsGenome = dad.getGenotype().getGenome();
	int genomeLength = momsGenome.length;
	int[]  childsGenome = new int[genomeLength];
	
	// Randomly crossover.
	if( random.nextDouble() <= crossoverRate) {

	    // Randomly find crossover point, between 0 (inclusive)
	    // and genomeLength (exclusive) + 1.
	    int crossoverPoint = random.nextInt(genomeLength + 1);

	    // Copy mom's genes.
	    for (int i = 0; i < crossoverPoint; i++) {
		childsGenome[ i ] = momsGenome[ i];
	    }
	    // Copy dad's genes.
	    for (int j = crossoverPoint; j < genomeLength; j++) {
		childsGenome[ j ] = dadsGenome[ j ];
	    }
	}

	// Length of the crossover section is determined randomly,
	// think this is OK for these indidividuals.
	
	BitvectorIndividual child = new BitvectorIndividual( genomeLength );

	//Set the genome we just made.
	child.getGenotype().setGenome( childsGenome );

	return child;
    }
}
