public abstract class Individual {

    Genotype genotype;
    double fitness;
       
    private abstract growup();

    public void setFitness( double fitness ) {
	this.fitness = fitness;
    }

    public double getFitness() {
	return fitness;
    }

    public abstract getPhenotype();
    

    
}
