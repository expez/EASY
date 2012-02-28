package edu.ntnu.EASY.selection.parent;

import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.util.Util;

public class StochasticTournamentSelector<PType> implements ParentSelector<PType>{
	
	private int rank;
	private int numParents;
	private double intervals[];
	/**
	 * Tournament selection where the probability of adding the individual 
	 * with best fitness in the tournamen to the parent list is e.
	 * Second best individual e*(1-e)
	 * third best			  e*(1-e)^2 etc
	 * @param rank size of the tournament
	 * @param numParents
	 * @param e probability to add the best individual to parent list
	 */
	public StochasticTournamentSelector(int rank, int numParents, double e) {
		this.rank = rank;
		this.numParents = numParents;
		this.intervals = new double[rank];
	
		intervals[0] += e;
		for(int i = 1; i<rank; i++) {
			intervals[i] = intervals[i-1]+ e*Math.pow(1-e,i);
		}	
	}

	@Override
	public <GType> Population<GType, PType> select(Population<GType, PType> adults) {

		Population<GType, PType> adultsCopy = adults.copy();
		Population<GType, PType> tournamentRoster = new Population<GType, PType>(adults.getFitnessCalculator());
		Population<GType, PType> parents = new Population<GType, PType>(adults.getFitnessCalculator());
		double random;
		//TODO: If numParents has to be quite a bit lower than numAdults or this won't work. Add a fix.
		while(parents.size() < numParents) {
			tournamentRoster.clear();
			adultsCopy.shuffle();
			//Add rank individuals to tournamentRoster.
			tournamentRoster.addAll(adultsCopy.getSubset(rank));
			//sort, descending order
			tournamentRoster.sort(true);
			random = Util.RNG.nextDouble();
			for(int i = 0; i < intervals.length; i++) {
				if(random  < intervals[i]) {
					parents.add(tournamentRoster.get(i));
					adultsCopy.remove(tournamentRoster.get(i));
					break;
				}
			}
		}
		return parents;
	}
}

