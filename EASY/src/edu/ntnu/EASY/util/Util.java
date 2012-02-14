package edu.ntnu.EASY.util;

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

	
}
