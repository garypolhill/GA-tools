/*
 * name.polhill.gary.intga: IntGA.java Copyright (C) 2008 Macaulay Institute
 * 
 * This file is part of GA.
 * 
 * GA is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * GA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with GA. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contact information: Gary Polhill Macaulay Institute, Craigiebuckler,
 * Aberdeen. AB15 8QH. UK. g.polhill@macaulay.ac.uk
 */
package name.polhill.gary.mcga.intga;

import java.util.Set;

import name.polhill.gary.mcga.Chromosome;
import name.polhill.gary.mcga.MCGA;
import name.polhill.gary.mcga.KeepBestRankLotteryBreeder;

/**
 * IntGA
 * 
 * GA that has to find an integer from a bitstring of a given size
 * 
 * @author Gary Polhill
 * 
 */
public class IntGA {
  int nSteps;
  MCGA ga;
  IntCostFunction intfunc;
  KeepBestRankLotteryBreeder breeder;

  /**
   * main
   * 
   * Accessor for the program. Arguments are:
   * 
   * <ol>
   * <li>Number of generations to use</li>
   * <li>Size of population to use (suggest ~100)</li>
   * <li>Crossover probability (suggest 0.05-0.2)</li>
   * <li>Mutation probability (suggest 0.01-0.05)</li>
   * <li>Size of bitstring to use (number of bits--note that for too large a
   * bitstring, the cost will become infinite because it cannot be represented
   * in double-precision IEEE 754 floating point numbers. If all bitstrings have
   * infinite cost, there is no basis for selection and the GA will not perform
   * well until a mutation finds a non-infinite cost gene)</li>
   * <li>Value for the GA to find</li>
   * </ol>
   * 
   * @param args
   */
  public static void main(String[] args) {
    if(args.length < 6) {
      System.err.println("Arguments: <nSteps> <popSize> <pCrossover> <pMutate> <size> <values...>");
      System.exit(1);
    }

    int nSteps = Integer.parseInt(args[0]);
    int popSize = Integer.parseInt(args[1]);
    double pCrossover = Double.parseDouble(args[2]);
    double pMutate = Double.parseDouble(args[3]);
    int size = Integer.parseInt(args[4]);
    String[] values = new String[args.length - 5];
    for(int i = 0; i < values.length; i++) {
      values[i] = args[i + 5];
    }
    IntGA intga = new IntGA(nSteps, popSize, pCrossover, pMutate, size, values);
    intga.run();
    System.exit(0);
  }

  /**
   * <!-- IntGA constructor -->
   * 
   * @param nSteps
   * @param popSize
   * @param pCrossover
   * @param pMutate
   * @param size
   * @param value
   */
  public IntGA(int nSteps, int popSize, double pCrossover, double pMutate, int size, String[] values) {
    this.nSteps = nSteps;
    breeder = new KeepBestRankLotteryBreeder(pCrossover, pMutate, 1);
    intfunc = new IntCostFunction(values);
    IntGeneFactory factory = new IntGeneFactory(size, values.length);
    ga = new MCGA(breeder, intfunc, popSize, factory);
  }

  /**
   * <!-- run -->
   * 
   */
  public void run() {
    for(int i = 1; i < nSteps; i++) {
      Set<Chromosome> best_cost = ga.step(false);
      System.out.println("Generation " + i + ": Difference = " + ga.difference() + " best = "
	  + Chromosome.Tools.getCostString(best_cost));
    }
  }

}
