package edu.ntnu.EASY;
import java.util.LinkedList;
import java.util.List;

import edu.ntnu.EASY.individual.BitvectorIndividual;
public class FullGenerationalReplacement {
    
    public FullGenerationalReplacement( ) {

    }

    /**
     Returns the combination of childrens and adults who may enter the
     next generation.

     @param Adults the adults from the previous generation.

     @param Children the children created during the last generation.
     
     @Return A list of individuals fit to enter the next generation.
     */
    public LinkedList< BitvectorIndividual > getAdults( List<BitvectorIndividual> children, List<BitvectorIndividual> adults ) {
    	return new LinkedList< BitvectorIndividual > (children);
    }
}
