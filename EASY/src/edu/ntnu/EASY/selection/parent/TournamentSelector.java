/*Copyright (C) 2012 Lars Andersen, Tormund S. Haus.
larsan@stud.ntnu.no
tormunds@stud.ntnu.no

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

public class TournamentSelector<PType> implements ParentSelector<PType>{

	private int rank;
	private int numParents;

	public TournamentSelector(int rank, int numParents) {
		this.rank = rank;
		this.numParents = numParents;
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
		Population<GType, PType> tournamentRoster = new Population<GType, PType>(adults.getFitnessCalculator());
		Population<GType, PType> parents = new Population<GType, PType>(adults.getFitnessCalculator());
		
		while(parents.size() < numParents) {
			tournamentRoster.clear();
			adultsCopy.shuffle();
			//Add rank individuals to tournamentRoster.
			tournamentRoster.addAll(adultsCopy.getSubset(rank));
			//sort, descending order
			tournamentRoster.sort(true);
			parents.add(tournamentRoster.get(0));
			adultsCopy.drop(tournamentRoster.get(0));
		}
		return parents;
	}
}
