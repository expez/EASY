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

import edu.ntnu.EASY.blotto.BlottoFitnessCalculator;
import edu.ntnu.EASY.blotto.BlottoIncubator;
import edu.ntnu.EASY.blotto.BlottoReplicator;
import edu.ntnu.EASY.incubator.BitvectorIncubator;
import edu.ntnu.EASY.incubator.BitvectorReplicator;
import edu.ntnu.EASY.incubator.Incubator;
import edu.ntnu.EASY.individual.BitvectorIndividual;
import edu.ntnu.EASY.individual.Individual;
import edu.ntnu.EASY.selection.adult.AdultSelector;
import edu.ntnu.EASY.selection.adult.FullGenerationalReplacement;
import edu.ntnu.EASY.selection.parent.FitnessProportionateSelector;
import edu.ntnu.EASY.selection.parent.ParentSelector;
d
import edu.ntnu.EASY.blotto.Blotto;

public class Main {
    
    public static void main(String[] args) {
//    	int[] Bs = {5,10,20};
//    	double[] Rfs = {1.0,0.5,0.0};
//    	double[] Lfs = {1.0,0.5,0.0};

    	int[] Bs = {5,20};
    	double[] Rfs = {1.0,0.0};
    	double[] Lfs = {1.0,0.0};
    	
    	
    	Blotto blotto = new Blotto();
    	
    	for(int B : Bs){
    		for(double Rf : Rfs){
    			for(double Lf : Lfs){
    				Report<double[],double[]> report = blotto.runBlottoEvolution(B,Rf,Lf);
    				String filename = String.format("blotto-%d-%.2f-%.2f.txt",B,Rf,Lf);
    				try {
						PrintStream out = new PrintStream(new FileOutputStream(filename));
						report.writeToStream(out);
					} catch (FileNotFoundException e) {
						System.err.printf("File not found: %s%n",filename);
					}
    			}
    		}
    	}
    }
}
