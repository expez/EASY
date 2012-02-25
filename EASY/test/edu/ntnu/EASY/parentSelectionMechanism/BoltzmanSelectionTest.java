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
//package edu.ntnu.EASY.parentSelectionMechanism;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import edu.ntnu.EASY.OneMaxFitness;
//import edu.ntnu.EASY.individual.BitvectorIndividual;
//import edu.ntnu.EASY.parentSelectionMechanism.SigmaScaledSelection;
//
//public class BoltzmanSelectionTest {
//
//	private static List< BitvectorIndividual > adults;
//	private static List< BitvectorIndividual > parents;
//	private static int NUM_PARENTS = 71;
//	private static OneMaxFitness fitnessEvaluator;
//	private static BoltzmanSelection< BitvectorIndividual > boltzmanSelection;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		adults = new LinkedList< BitvectorIndividual >( BitvectorIndividual.getIndividuals( 233, 40 ) );
//		
//		fitnessEvaluator = new OneMaxFitness();
//		fitnessEvaluator.updateFitness( adults );
//		
//		boltzmanSelection = new BoltzmanSelection< BitvectorIndividual >( NUM_PARENTS );
//		
//		parents = boltzmanSelection.getParents( adults );
//	}
//
//	@Test
//	public void testAverageFitnessIncreased() {
//		double oldFitness = 0;
//		double newFitness = 0;
//		
//		for (BitvectorIndividual adult : adults) {
//			oldFitness += adult.getFitness();
//		}
//		
//		for (BitvectorIndividual parent : parents) {
//			newFitness += parent.getFitness();
//		}
//		
//		oldFitness /= adults.size();
//		newFitness /= parents.size();
//		
//		assertTrue( oldFitness <= newFitness );
//	}
//	
//	@Test
//	public void testNumParents() {
//		assertEquals( NUM_PARENTS, parents.size() );
//	}
//	
//	
//	@Test
//	public void testBoltzmanAverageFitnessIncreased() {
//		double oldFitness = 0;
//		double newFitness = 0;
//		
//		for (BitvectorIndividual adult : adults) {
//			oldFitness += adult.getFitness();
//		}
//		
//		for (BitvectorIndividual parent : parents) {
//			newFitness += parent.getFitness();
//		}
//		
//		oldFitness /= adults.size();
//		newFitness /= parents.size();
//		
//		assertTrue( oldFitness <= newFitness );
//	}
//}

