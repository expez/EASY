package edu.ntnu.EASY.adultSelectionMechanism;

import java.util.Collections;
import java.util.List;

public class Overproduction< T extends Comparable< ? super T > > {

	/**
	 * 
	 * @param numAdults The number of individuals to return when getAdults(T a, T b) is called..
	 */
	
	private int numAdults;
	public Overproduction( int numAdults) {
		this.numAdults = numAdults;
	}
	
	/**
	 * All individiduals are assumed to have updated fitness values when this function is called.
	 * 
	 * @param children A list of children from previous generation.
	 * @param adults The list of adults from previous generation.
	 * @return A The list of individuals fit to enter the current generation.
	 */
	public List< T > getAdults( List< T > children, List< T > adults ) {
		Collections.sort( children );
		
		int numToDrop = children.size() - numAdults;
		
		return children.subList( numToDrop, children.size() );
	}
}
