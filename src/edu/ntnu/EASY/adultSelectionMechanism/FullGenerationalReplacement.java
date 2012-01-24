package edu.ntnu.EASY.adultSelectionMechanism;
import edu.ntnu.EASY.Population;
public class FullGenerationalReplacement {
    private Population children;
    
    public FullGenerationalReplacement( Population children ) {
	this.children = children;
    }
    //All children of previous generation get to become adults.
    public Population getAdults() {
	return children;
    }
}
