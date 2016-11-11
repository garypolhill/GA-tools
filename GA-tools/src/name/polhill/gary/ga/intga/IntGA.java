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
package name.polhill.gary.ga.intga;

import name.polhill.gary.ga.GA;
import name.polhill.gary.ga.KeepBestRankLotteryBreeder;

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
  GA ga;
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
    if(args.length != 6 && args.length != 7) {
      System.err
          .println("Arguments: <nSteps> <popSize> <pCrossover> <pMutate> <size> <value> [<Gray Code?>]");
    }
    IntGA intga =
      new IntGA(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double
          .parseDouble(args[2]), Double.parseDouble(args[3]), Integer
          .parseInt(args[4]), args[5]);
    if(args.length == 7) {
      IntCostFunction.GRAY_CODE = new Boolean(args[6]);
    }
    intga.run();
    System.exit(0);
  }

  public IntGA(int nSteps, int popSize, double pCrossover, double pMutate,
      int size, String value) {
    this.nSteps = nSteps;
    breeder = new KeepBestRankLotteryBreeder(pCrossover, pMutate, 1);
    intfunc = new IntCostFunction(value);
    ga = new GA(breeder, intfunc, popSize, size);
  }

  public void run() {
    for(int i = 1; i < nSteps; i++) {
      double best_cost = ga.step(false);
      System.out.println("Generation " + i + ": Minimum difference = "
        + best_cost + ", mean " + ga.getMeanCost() + ", variance "
        + ga.getCostVariance() + ", diversity " + ga.nDifferent());
    }
  }

}
