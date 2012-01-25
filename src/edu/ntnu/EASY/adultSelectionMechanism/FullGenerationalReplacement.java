package edu.ntnu.EASY.adultSelectionMechanism;
import edu.ntnu.EASY.Population;
public class FullGenerationalReplacement implements AdultSelectionMechanism{
    
    public FullGenerationalReplacement( ) {

    }

    /**
     Returns the combination of childrens and adults who may enter the
     next generation.

     @param Adults the adults from the previous generation.

     @param Children the children created during the last generation.
     
     @Return A population of individuals fit to enter the next generation.
     */
    public Population getAdults( Population children, Population Adults ) {
	return children;
    }
}
