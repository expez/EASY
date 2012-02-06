//*Copyright (C) 2012 Lars Andersen, Tormund S. Haus.
//larsan@stud.ntnu.no
//tormunds@ntnu.no
//
//EASY is free software: you can redistribute it and/or modify it
//under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
// 
//EASY is distributed in the hope that it will be useful, but
//WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//General Public License for more details.
// 
//You should have received a copy of the GNU General Public License
//    along with EASY.  If not, see <http://www.gnu.org/licenses/>.*/
package edu.ntnu.EASY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Output {

	private boolean isWriting;
	private String filename;
	private List< String > outputList;

	public Output( String filename ) {
		isWriting = true;
		this.filename = filename;
		outputList = new LinkedList< String >();
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
		//Do nothing if the write flag is false or file not set.
		if( !isWriting ) {
			return;
		}
		
		try {
			PrintWriter writer = new PrintWriter( new FileWriter( new File( filename), true ) );
			
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
}
