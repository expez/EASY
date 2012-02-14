package edu.ntnu.EASY;

import java.io.PrintStream;

public interface Report<GType,PType>  {

	public abstract void log(int generation,Population<GType, PType> population);

	public abstract void writeToStream(PrintStream out);
}
