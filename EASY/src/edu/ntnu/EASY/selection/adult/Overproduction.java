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
package edu.ntnu.EASY.selection.adult;

import edu.ntnu.EASY.Environment;
import edu.ntnu.EASY.Population;

public class Overproduction<PType> extends AdultSelector<PType> {

	/**
	 * 
	 * @param numAdults The number of individuals to return when getAdults(T a, T b) is called..
	 */
	private Environment env;
	
	
	public Overproduction() {
	}
	
	/**
	 * All individiduals are assumed to have updated fitness values when this function is called.
	 * 
	 * @param children A list of children from previous generation.
	 * @param adults The list of adults from previous generation.
	 * @return A The list of individuals fit to enter the current generation.
	 */
	@Override
	public <GType> Population<GType, PType> select(Population<GType, PType> adults, Population<GType, PType> children) {
		children.drop(children.size() - env.numAdults);
		return children;
	}
}
