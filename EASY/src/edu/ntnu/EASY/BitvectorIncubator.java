package edu.ntnu.EASY;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.ntnu.EASY.individual.BitvectorIndividual;


public class BitvectorIncubator {

    private double mutationRate;
    private double crossoverRate;
    Random random;
    
    public BitvectorIncubator( double mutationRate, double crossoverRate) {
    	
	this.mutationRate = mutationRate;
	this.crossoverRate = crossoverRate;
	random = new Random();
	
    };
    
    /**
     * @param parents List of individuals from which to create children
     * @param children number of children to create.
     * @return a list of children
     */
    public List<BitvectorIndividual> getChildren( List<BitvectorIndividual> parents, int children) {
    	List<BitvectorIndividual> childrenList = new LinkedList<BitvectorIndividual>();
    	Random random = new Random();
    	int momIndex;
    	int dadIndex;
    	BitvectorIndividual child;
    	while (childrenList.size() < children) {
			momIndex = random.nextInt( parents.size() );
			dadIndex = random.nextInt( parents.size() );
			child = getChild(parents.get( momIndex), parents.get( dadIndex ) );
			
			childrenList.add(child);
		}
    	return childrenList;
    }
    
    BitvectorIndividual getChild( BitvectorIndividual mom, BitvectorIndividual dad ) {
    	
	int[]  momsGenome = mom.getGenotype().getGenome();
	int[]  dadsGenome = dad.getGenotype().getGenome();
	int genomeLength = momsGenome.length;
	int[] childsGenome = new int[genomeLength];
	
	
	// Randomly crossover.
	if( random.nextDouble() <= crossoverRate) {
	    // Randomly find crossover point, between 0 (inclusive)
	    // and genomeLength (exclusive).
		int crossoverPoint = random.nextInt( genomeLength );
		childsGenome = crossover(momsGenome, dadsGenome, crossoverPoint);
	}

	// Length of the crossover section is determined randomly,
	// think this is OK for these individuals.
	
	BitvectorIndividual child = new BitvectorIndividual( genomeLength );
	
	//Set the genome we just made.
	child.getGenotype().setGenome( childsGenome );

	// Mutate.
	child.getGenotype().mutate(mutationRate);


	return child;
    }
    
	/**
	 * @param momsGenome an indidivuals genome. int[]
	 * @param dadsGenome an individuals genome. int[]
	 * @param crossoverPoint the point to crossover
	 * @return a new genome which is the copy of moms genes up to the crossoverPoint
	 * and the copy of dad's genes from then on out. int[]
	 */
    int[] crossover(int[] momsGenome, int[] dadsGenome, int crossoverPoint) {
    	int genomeLength = momsGenome.length;
    	int[]  childsGenome = new int[genomeLength];

    	// Copy mom's genes.
    	for (int i = 0; i < crossoverPoint; i++) {
    		childsGenome[ i ] = momsGenome[ i];
    	}
    	// Copy dad's genes.
    	for (int j = crossoverPoint; j < genomeLength; j++) {
    		childsGenome[ j ] = dadsGenome[ j ];
    	}
    	return childsGenome;
    }
}
