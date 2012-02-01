/*Copyright (C) 2012 Lars Andersen, Tormund S. Haus.
larsan@stud.ntnu.no
tormunds@ntnu.no

EASY is free software: you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
 
EASY is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.
 
You should have received a copy of the GNU General Public License
    along with EASY.  If not, see <http://www.gnu.org/licenses/>.*/
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
