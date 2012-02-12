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
package edu.ntnu.EASY;
import java.util.List;

import edu.ntnu.EASY.individual.*;
import edu.ntnu.EASY.selection.adult.FullGenerationalReplacement;
import edu.ntnu.EASY.selection.parent.RankSelection;


public class Main {
    
	private	static int populationSize = 1000;
	private	static int maxGenerations = 10000;
	private	static double fitnessThreshold = 99;
	private	static double mutationRate = 0.05;
	private	static double crossoverRate = 0.05;
	private	static int bits = 40;
	private static int rank = 50;
	private static boolean elitsm = true;

	public void runEvolution(List<Individual> population, ){
		
	}
	
    public static void main(String[] args) {

    }
}