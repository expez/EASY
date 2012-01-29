package edu.ntnu.EASY;

import edu.ntnu.EASY.individual.BitvectorIndividual;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class RankSelection {

	private int rank;

	public RankSelection( int rank ) {
		this.rank = rank;
	}

	/**
	 * @param individuals List of individuals who are going to battle it out to become parents.
	 * @return list of RankSelection.Rank individuals.
	 */
	public List<BitvectorIndividual> getParents( List<BitvectorIndividual> individuals ) {

		//Copy the individuals
		List<BitvectorIndividual> individualsCopy = new LinkedList<BitvectorIndividual>( individuals );

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

