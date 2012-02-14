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
public class JavaPlotTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] tab1 = new double[5][2];
        tab1[0][0] = 0.0000;
        tab1[0][1] = 2.0000;
        tab1[1][0] = 1.0000;
        tab1[1][1] = 5.0000;
        tab1[2][0] = 2.0000;
        tab1[2][1] = 6.0000;
        tab1[3][0] = 3.0000;
        tab1[3][1] = 8.0000;
        tab1[4][0] = 4.0000;
        tab1[4][1] = 10.0000;
        
        double[][] tab2 = new double[5][2];
        tab2[0][0] = 0.0000;
        tab2[0][1] = 3.0000;
        tab2[1][0] = 1.0000;
        tab2[1][1] = 5.0000;
        tab2[2][0] = 2.0000;
        tab2[2][1] = 2.0000;
        tab2[3][0] = 3.0000;
        tab2[3][1] = 4.0000;
        tab2[4][0] = 4.0000;
        tab2[4][1] = 11.0000;

        JavaPlotTest.newPlot().with("s1",tab1).with("s2",tab2).plot();
    }
    
    public static PlotBuilder newPlot(){
    	return new PlotBuilder();
    }
    
    public static class PlotBuilder{
    	
    	private static PlotStyle plotStyle = new PlotStyle();
    	
    	static {
    		plotStyle.setStyle(Style.LINES);
    		plotStyle.setLineWidth(1);
    	}
    	
    	
    	JavaPlot plot;
    	
    	private PlotBuilder(){
    		plot = new JavaPlot();
    	}
    	
    	public PlotBuilder with(String title, double[][] dataset){
            DataSetPlot ds = new DataSetPlot(dataset);
            ds.setPlotStyle(plotStyle);
            ds.setTitle(title);
            plot.addPlot(ds);
            return this;
    	}
    	
    	public JavaPlot plot(){
            plot.newGraph();
    		plot.plot();
    		return plot;
    	}
    	
    }
}
