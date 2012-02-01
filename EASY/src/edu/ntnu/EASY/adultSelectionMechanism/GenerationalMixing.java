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
import java.util.LinkedList;
import java.util.List;

public class GenerationalMixing< T extends Comparable< ? super T >> {
	
	private int numAdults;

	public GenerationalMixing( int numAdults) {
		this.numAdults = numAdults;
	}

	public List< T > getAdults( List< T > children, List< T > adults ) {
		List< T> population = new LinkedList< T >();
		population.addAll( children );
		population.addAll( adults );
		Collections.sort( population );
		
		int numToDrop = population.size() - numAdults;
		
		return population.subList( numToDrop, population.size() );
	}
}
