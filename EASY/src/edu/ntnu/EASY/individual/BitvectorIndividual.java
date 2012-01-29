package edu.ntnu.EASY.individual;
import java.util.LinkedList;

import edu.ntnu.EASY.BinaryGenotype;
public class BitvectorIndividual extends Individual{

    private BinaryGenotype genotype;
    private int[] phenotype;
        
    public BitvectorIndividual( int length ) {
    
    	genotype = new BinaryGenotype( length );
    	phenotype = new int[length];
    }

    public int[] getPhenotype() {
    	phenotype = genotype.getGenome();
    	return phenotype;
    } 

    public BinaryGenotype getGenotype() {
    	return genotype;
    }

    /**
       Returns a list of bitvectorindividuals.

       @param numIndividuals number of individuals to create.

       @param length of bitvector.

       @return a list of BitvectorIndividuals
     */
    public static LinkedList<BitvectorIndividual> getIndividuals( int numIndividuals, int length ) {
    	LinkedList< BitvectorIndividual > list = new LinkedList< BitvectorIndividual >();

    	for (int i = 0; i < numIndividuals; i++) {
    		list.add( new BitvectorIndividual( length ) );
    	}

    	return list;
    } 
}
