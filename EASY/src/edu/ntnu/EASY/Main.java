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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import edu.ntnu.EASY.blotto.Blotto;

public class Main {
    
    public static void main(String[] args) {
    	int[] Bs = {5,10,20};
    	double[] Rfs = {1.0,0.5,0.0};
    	double[] Lfs = {1.0,0.5,0.0};
    	
    	Blotto blotto = new Blotto();
    	
    	for(int B : Bs){
    		for(double Rf : Rfs){
    			for(double Lf : Lfs){
    				double start = System.currentTimeMillis();
    				Report<double[],double[]> report = blotto.runBlottoEvolution(B,Rf,Lf);
    				double stop = System.currentTimeMillis();
    				String filename = String.format("log/blotto-%d-%.2f-%.2f.txt",B,Rf,Lf);
    				try {
						PrintStream out = new PrintStream(new FileOutputStream(filename));
						out.printf("# Blotto run, B: %d, Rf: %.2f, Lf: %.2f - %.2f sec %n",B,Rf,Lf,(stop-start)/1000);
						report.writeToStream(out);
					} catch (FileNotFoundException e) {
						System.err.printf("File not found: %s%n",filename);
					}
    			}
    		}
    	}
    }
}
