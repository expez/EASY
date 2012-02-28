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
package edu.ntnu.EASY;

public class Environment {

	public int populationSize = 100;
	public int maxGenerations = 1000;
	public double fitnessThreshold = 1;
	public double mutationRate = 0.01;
	public double crossoverRate = 0.01;
	public int numChildren;
	public int numParents;
	public int elitism;
	public int numAdults;
	public int rank;
	public int tournamentSize;
	public double e;
	public int maxAge;
}