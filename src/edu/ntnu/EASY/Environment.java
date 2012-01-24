package edu.ntnu.EASY;

public class Environment {
    
    private FitnessEvaluator fitnessEvaluator;
    private AdultSelectionMechanism adultSelectionMechanism;
    private ParentSelectionMechanism parentSelectionMechanism;
    private ReproductionMechanism reproductionMechanism;

    public Environment(FitnessEvaluator fitnessEvaluator,
		       AdultSelectionMechanism adultSelectionMechanism,
		       ParentSelectionMechanism parentSelectionMechanism,
		       ReproductionMechanism reproductionMechanism) {

	this.fitnessEvaluator = fitnessEvaluator;
	this.adultSelectionMechanism = adultSelectionMechanism;
	this.parentSelectionMechanism = parentSelectionMechanism;
	this.reproductionMechanism = reproductionMechanism;
    }

    /**
     Updates the fitness values for population using whatever fitness evaluator
     is set.

     @param The current population.
     */
    public void evaluateFitness( Population population ) {
    
	fitnessEvaluator.updateFitness( population );
    } 
    
    /**
     Selects parents according to the parentSelectionMechanism.

     @param population the population to use for parent selection.

     @return Population of individuals fit enough to reproduce.
     */
    public Population selectParents( Population population ) {
	parentSelectionMechanism.getParents( population );
    }
    
    /**
       Selects adults according to the adultSelectionMechanism.

       @param population the population to use for adult selection.

       @return Population of individuals fit enough to become adults.
     */
    public Population selectAdults( Populuation population ) {
	adultSelectionMechanism.getAdults( population );
    } 

    /**
       Creates an offspring according the current reproduction
       mechanism.

       @param mom an individual to provide half of its gene pool for
       the offspring.
								
       @param dad an individual to provide half of its gene pool for
       the offspring.
     */
    public Individual getOffspring( Individual mom, Individual dad ) {
	reproductionMechanism.getOffspring( mom, dad );
    } 
}


