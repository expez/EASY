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
package edu.ntnu.EASY.parentSelectionMechanism;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TournamentSelection< T extends Comparable< ? super T > > {

	private int tournamentSize;
	private int numParents;

	public TournamentSelection(int numParents, int tournamentSize ) {
		this.tournamentSize = tournamentSize;
		this.numParents = numParents;
	}
	
	/**
	 * Picks tournamentSize adults at random and pits them against eachother in a tournament. 
	 * The winner enters the list of parents returned from this method. 
	 * @param adults to participate in tournament selection in order to reproduce.
	 * @return A list, of size numParents, of adults ready to become parents.
	 */
	public List< T > getParents( List< T > adults) {
		List< T > adultsCopy = new LinkedList< T >( adults );
		List< T > parents = new LinkedList< T >();
		//Set of individuals to participate in a tournament. First prize is sex.
		List< T > tournamentRoster = new LinkedList< T >();
		
		while( parents.size() < numParents ) {
			tournamentRoster.clear();
			Collections.shuffle( adultsCopy );
			tournamentRoster.addAll( adultsCopy.subList( 0, tournamentSize ) );
			Collections.sort( tournamentRoster );
			parents.add( tournamentRoster.get( tournamentRoster.size() - 1) );
			adultsCopy.remove( tournamentRoster.get( tournamentRoster.size() - 1) );
		}
		return parents;
	}
}
