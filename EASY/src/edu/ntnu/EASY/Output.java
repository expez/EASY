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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Output {

	private boolean isWriting;
	private List< String > outputList;
	private File file;


	public Output( String filename ) {
		isWriting = true;
		outputList = new LinkedList< String >();
		file = new File( filename );
	}
	
	/**
	 * Returns a boolean indicating whether or not the class is currently logging.
	 */
	public boolean isWriting() {
		return isWriting;
	}
	
	/**
	 * Toggles logging mode on or off.
	 * @param isLogging True to start logging, false to stop logging.
	 */
	public void setIsWriting( boolean isWriting ) {
		this.isWriting = isWriting;
	}
	
	/**
	 * Writes the output buffer to file.
	 */
	public void writeToFile( ) {
		//Do nothing if the write flag is false.
		if( !isWriting ) {
			return;
		}
		
		try {
			PrintWriter writer = new PrintWriter( new FileWriter( file, false ) );
			
			for (String string : outputList) {
				writer.println( string );
			}
			clear();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}
	
	/**
	 * @param string A string to append to the output buffer. The format for the line is <generation> <fitness> [ <variance> ] [ <other data> ]. 
	 * The entries shall be separated by tabs or spaces.
	 */
	public void appendLine( String string ) {
		outputList.add( string );
	}
	
	/**
	 * @param stringList A list of string elements to format into an output line. Each element contains no tabs or spaces.
	 */
	public void append( String[] stringList) {
		String lineBuffer = "";
		for (int i = 0; i < stringList.length; i++) {
			lineBuffer += stringList[ i ] + " ";
		}
		lineBuffer = lineBuffer.trim();
		appendLine( lineBuffer );
	}
	
	/**
	 * Deletes any stored content from the output buffer.
	 */
	public void clear() {
		outputList.clear();
	}
	
	/**
	 * Calls GNUPlot to plot the active data file.
	 */
	public void plot() {
		writeToFile();
		String setTitle = "set title \"Fitness plots for One Max\";";
		String setXlabel = "set xlabel \"Generations\";";
		String setYlabel = "set ylabel \"Fitness\";";
		String setTerm =  "set term png large size 800,600;";
		String setOutput = "set output \"" + file.getAbsolutePath() + ".png\";";
		String using1 = "'"+ file.getAbsolutePath()+"'" + " using 1:2 title \"Max fitness\" with linespoints,"; 
		String using2 = "'"+ file.getAbsolutePath()+"'"  + " using 1:3 title \"Average fitness\" with linespoints,";
		String using3 = "'"+ file.getAbsolutePath()+"'"  + " using 1:4 title\"Standard deviation\" with linespoints";
		String[] plotCommand = {"/usr/bin/gnuplot","-e", setTerm+setOutput+setTitle + setXlabel + setYlabel + "plot "+ using1 + using2 +using3};
		System.out.println(plotCommand);
		plot(plotCommand);
	}
	
	public static void plotBlotto(File file, int B, double Rf, double Lf) {
		
		String setTitle = String.format("set title \"Fitness plots [%2d,%.1f,%.1f]\";",B,Rf,Lf);
		String setXlabel = "set xlabel \"Generations\";";
		String setYlabel = "set ylabel \"Fitness\";";
		String setTerm =  "set term png large size 800,600;";
		String setOutput = "set output \"" + file.getAbsolutePath() + "-fitness.png\";";
		String using1 = "'"+ file.getAbsolutePath()+"'" + " using 1:2 title \"Max fitness\" with linespoints,"; 
		String using2 = "'"+ file.getAbsolutePath()+"'"  + " using 1:3 title \"Average fitness\" with linespoints,";
		String using3 = "'"+ file.getAbsolutePath()+"'"  + " using 1:4 title\"Standard deviation\" with linespoints";
		String[] plotFitness = {"/usr/bin/gnuplot", "-e", setTerm+setOutput+setTitle + setXlabel + setYlabel + "plot "+ using1 + using2 +using3};
		plot(plotFitness);

		setTitle = String.format("set title \"Average entropy plot [%2d,%.1f,%.1f]\";",B,Rf,Lf);
		setYlabel = "set ylabel \"Entropy\";";
		setOutput = "set output \"" + file.getAbsolutePath()+ "-entropy.png\";";
		using1 = "'"+ file.getAbsolutePath()+"'" + " using 1:5 title \"Average entropy\" with linespoints"; 
		String[] plotEntropy = {"/usr/bin/gnuplot", "-e", setTerm+setOutput+setTitle + setXlabel + setYlabel + "plot "+ using1};
		plot(plotEntropy);
	}

	private static void plot(String[] plotCommand) {
		String line;
		InputStream stderr = null;
		InputStream stdout = null;
		try {
			Process process = Runtime.getRuntime().exec(plotCommand);
			OutputStream stdin = process.getOutputStream();
			PrintStream ps = new PrintStream(stdin);
			ps.print(plotCommand);
			
			stderr = process.getErrorStream ();
			stdout = process.getInputStream ();
            process.waitFor();
            // clean up if any output in stdout
            BufferedReader brCleanUp =
              new BufferedReader (new InputStreamReader (stdout));
            while ((line = brCleanUp.readLine ()) != null) {
              System.out.println ("[Stdout] " + line);
            }
            brCleanUp.close();

            // clean up if any output in stderr
            brCleanUp =
              new BufferedReader (new InputStreamReader (stderr));
            while ((line = brCleanUp.readLine ()) != null) {
              System.out.println ("[Stderr] " + line);
            }
            brCleanUp.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
