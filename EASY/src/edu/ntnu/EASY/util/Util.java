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
package edu.ntnu.EASY.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import edu.ntnu.EASY.Population;
import edu.ntnu.EASY.individual.Individual;


public class Util {

	public static final Random RNG = new Random();
	
	public static double log2(double x){
		return Math.log(x) / Math.log(2.0);
	}
	
	public static <GType,PType> double getFitnessVariance(Population<GType,PType> individuals, double mean) {
		double variance = 0;	
		for (Individual<GType, PType> individual : individuals) {
			variance += ( individual.getFitness() - mean) * ( individual.getFitness() - mean);
		}
		return variance / individuals.size();
	}
	
	public static String arrayToString(String format, double[] array) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.print("[");
		if(array.length > 0)
			pw.printf(format,array[0]);
		for(int i = 1; i < array.length; i++){
			pw.print(", ");
			pw.printf(format,array[i]);
		}
		pw.print("]");	
		return sw.toString();
	}

	public static void normalize(double[] a){
		double total = 0.0;
		for(double d : a){
			total += d;
		}
		for (int i = 0; i < a.length; i++) {
			a[i] /= total;
		}
	}
	
	public static double[] readTargetSpikeTrain( String filename ) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String trainingData = reader.readLine();
		String[] tokens = trainingData.trim().split("\\s+");
		double[] spikeTrain = new double[tokens.length];
		int i = 0;
		try{	
			for (i = 0; i < tokens.length; i++) {
				spikeTrain[i] = Double.valueOf(tokens[i]);
			}
		} catch (Exception e){
			System.out.printf("Shenanigans! %d [%s]%n",i,tokens[i]);
			System.exit(1);
		}
		return spikeTrain;
	}
}
