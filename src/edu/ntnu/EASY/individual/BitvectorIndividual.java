package edu.ntnu.EASY.individual;
import edu.ntnu.EASY.genotype.BinaryGenotype;
public class BitvectorIndividual {

    private BinaryGenotype genotype;
    private int[] phenotype;
        
    public BitvectorIndividual( int length) {
	genotype = new BinaryGenotype( length );
	phenotype = new int[length];
    }

    public int[] getPhenotype() {
	return phenotype;
    } 

    private void growup() {
	phenotype = genotype.getGenome();
    }
}
