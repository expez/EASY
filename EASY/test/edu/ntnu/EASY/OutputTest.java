package edu.ntnu.EASY;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class OutputTest {
	private static String filename; 
	private static Output output;
	private static File testFile;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		filename = "c:\\testfile";
		output = new Output( filename );
		testFile = new File( filename );
		testFile.delete();
	}

	@Test
	public void testAppend() {
		testFile.delete();
		String stringList[] = { "55", "0.77", "0.21" };
		output.append( stringList );
		output.writeToFile();
		
		String firstLine = "";
		try {
			BufferedReader reader = new BufferedReader( new FileReader( testFile ) );
			firstLine = reader.readLine();
			reader.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("55 0.77 0.21", firstLine);
	}
	
	@Test
	public void testAppendLine() { 
		testFile.delete();
		output.appendLine( "55 0.44 0.14" );
		output.appendLine( "123 0.33 0.11" );
		output.writeToFile();
		
		String firstLine = "";
		String secondLine = "";
		try {
			BufferedReader reader = new BufferedReader( new FileReader( testFile ) );
			firstLine = reader.readLine();
			secondLine = reader.readLine();
			reader.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals( "55 0.44 0.14", firstLine );
		assertEquals( "123 0.33 0.11", secondLine );
	}
	
	@Test
	public void testGnuplot() {
		testFile.delete();
		output.appendLine( "1 0.41" );
		output.appendLine( "2 0.43" );
		output.appendLine( "3 0.49" );
		output.appendLine( "4 0.55" );
		output.writeToFile();		
		
		output.plot();
	}
}
