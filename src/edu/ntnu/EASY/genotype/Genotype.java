package edu.ntnu.EASY;

interface Genotype {
    
    public Phenotype toPhenotype();

    public void mutate();
}
