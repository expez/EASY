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

import edu.ntnu.EASY.Population;

public class TournamentSelector<PType> extends ParentSelector<PType>{

	public TournamentSelector() {

	}
	
	/**
	 * Picks tournamentSize adults at random and pits them against each other in a tournament. 
	 * The winner enters the population of parents returned from this method. 
	 * @param adults to participate in tournament selection in order to reproduce.
	 * @return A population, of size numParents, of adults ready to become parents.
	 */

	@Override
	public <GType> Population<GType, PType> select(Population<GType, PType> adults) {
		Population<GType, PType> adultsCopy = adults.copy();
		Population<GType, PType> tournamentRoster = new Population<GType, PType>();
		Population<GType, PType> parents= new Population<GType, PType>();
		
		while(parents.size() < env.numParents) {
			tournamentRoster.clear();
			adultsCopy.shuffle();
			//Add rank individuals to tournamentRoster.
			tournamentRoster.addAll(adultsCopy.getSubset(env.rank));
			//sort, descending order
			tournamentRoster.sort(true);
			parents.add(tournamentRoster.get(0));
			adultsCopy.drop(tournamentRoster.get(0));
		}
		return parents;
	}
}
