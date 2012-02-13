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

public class FullGenerationalReplacement<PType> implements AdultSelector<PType> {
    
	Environment env = null;
	
    /**
     Returns the combination of childrens and adults who may enter the
     next generation.

     @param Adults the adults from the previous generation.

     @param Children the children created during the last generation.
     
     @Return A population of individuals fit to enter the next generation.
     */
	@Override
	public <GType> Population<GType, PType> select(Population<GType, PType> adults, Population<GType, PType> children) {
		return children;
	}

	@Override
	public void setEnvironment(Environment env) {
		this.env = env;
	}
}
