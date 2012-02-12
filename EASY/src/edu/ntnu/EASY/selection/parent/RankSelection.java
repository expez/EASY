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
package edu.ntnu.EASY.selection.parent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RankSelection<T extends Comparable<? super T>> {

	private int rank;

	public RankSelection( int rank ) {
		this.rank = rank;
	}

	/**
	 * @param individuals List of individuals who are going to battle it out to become parents.
	 * @return list of RankSelection.Rank individuals.
	 */
	public List<T> getParents( List< T > individuals ) {

		//Copy the individuals
		List< T > individualsCopy = new LinkedList< T >( individuals );

		//Sort the individuals according to fitness.
		Collections.sort(individualsCopy);

		int numToDrop = 0;

		// Find number of individuals to drop from list.
		// We only want rank number of individuals to remain.
		while( numToDrop < individualsCopy.size() - rank ) {
			numToDrop++;
		}
		return individualsCopy.subList(numToDrop, individualsCopy.size());
	}
}

