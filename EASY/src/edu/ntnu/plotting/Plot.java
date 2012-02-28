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
package edu.ntnu.plotting;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.FileTerminal;
import com.panayotis.gnuplot.terminal.GNUPlotTerminal;

/**
 * on NetBeans add library -> jar -> 
 * package namepackage;
 *
 * import com.panayotis.gnuplot.JavaPlot;
 * import com.panayotis.gnuplot.plot.DataSetPlot;
 * import com.panayotis.gnuplot.style.PlotStyle;
 * import com.panayotis.gnuplot.style.Style;
 * @author mkwest
 *
 */
public class Plot {

    JavaPlot plot;
    
    private Plot(JavaPlot plot){
    	this.plot = plot;
    }
    
	public void plot(){
		plot.plot();
	}
	
	public void writeToFile(String filename){
		GNUPlotTerminal old = plot.getTerminal();
		FileTerminal terminal = new FileTerminal("png",filename + ".png");
		terminal.set("size","800, 600");
		plot.setTerminal(terminal);
		plot.plot();
		plot.setTerminal(old);
	}
    
    public static PlotBuilder newPlot(String title){
    	return new PlotBuilder(title);
    }
    
    public static class PlotBuilder{
    	
    	private static PlotStyle plotStyle = new PlotStyle();
    	
    	static {
    		plotStyle.setStyle(Style.LINES);
    		plotStyle.setLineWidth(1);
    	}
    	
    	JavaPlot plot;
    	
    	private PlotBuilder(String title){
    		plot = new JavaPlot();
    		plot.setTitle(title);
    	}
    	
    	public Plot make() {
    		return new Plot(plot);
    	}

		public PlotBuilder setAxis(String axis, String label){
    		plot.getAxis(axis).setLabel(label);
    		return this;
    	}
    	
		public PlotBuilder with(String title, double[] dataset){
    		double[][] ds = new double[dataset.length][2];
            for(int i = 0; i < ds.length; i++){
            	ds[i][0] = i;            	
            	ds[i][1] = dataset[i];
            }
    		return with(title,ds);
    	}
    	
    	public PlotBuilder with(String title, double[][] dataset){
            DataSetPlot ds = new DataSetPlot(dataset);
            ds.setPlotStyle(plotStyle);
            ds.setTitle(title);
            plot.addPlot(ds);
            return this;
    	}
    }
}
