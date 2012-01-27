package edu.ntnu.EASY;
import edu.ntnu.EASY.adultSelectionMechanism.AdultSelectionMechanism;
import edu.ntnu.EASY.fitnessEvaluator.FitnessEvaluator;
import edu.ntnu.EASY.individual.*;
import edu.ntnu.EASY.parentSelectionMechanism.ParentSelectionMechanism;
public class Environment {
    
    private FitnessEvaluator fitnessEvaluator;
    private AdultSelectionMechanism adultSelectionMechanism;
    private ParentSelectionMechanism parentSelectionMechanism;
    
    public Environment(FitnessEvaluator fitnessEvaluator,
		       AdultSelectionMechanism adultSelectionMechanism,
		       ParentSelectionMechanism parentSelectionMechanism) {

	this.fitnessEvaluator = fitnessEvaluator;
	this.adultSelectionMechanism = adultSelectionMechanism;
	this.parentSelectionMechanism = parentSelectionMechanism;
    }

    /**
     Updates the fitness values for population using whatever fitness evaluator
     is set.

     @param The current population.
     */
    public void updateFitness( Individual individual ) {
    
	double fitness = fitnessEvaluator.getFitness( individual );
	individual.setFitness( fitness );
    } 
    
    /**
     Selects parents according to the parentSelectionMechanism.

     @param population the population to use for parent selection.

     @return Population of individuals fit enough to reproduce.
     */
    public Population selectParents( Population population ) {
	return parentSelectionMechanism.getParents( population );
    }
    
    /**
       Selects adults according to the adultSelectionMechanism.

       @param parents the parents who possibly survive in competition
       with the children

       @param children the children who might live to be adults.
       
       @return Population of individuals fit enough to become adults.
       */
    public Population selectAdults( Population children, Population parents) {
	return adultSelectionMechanism.getAdults( children, parents );
    } 

    /**
       Creates an offspring according the current reproduction
       mechanism.

       @param mom an individual to provide half of its gene pool for
       the offspring.
								
       @param dad an individual to provide half of its gene pool for
       the offspring.
     */
    // public Individual getChild( Individual mom, Individual dad ) {
    // 	reproductionMechanism.getChild( mom, dad );
    // } 
}


