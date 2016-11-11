/*
 * name.polhill.gary.gzga: GZGA.java
 * 
 * Copyright (C) 2008 Macaulay Institute
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
package name.polhill.gary.mcga.gzga;

import java.util.Set;

import name.polhill.gary.mcga.Chromosome;
import name.polhill.gary.mcga.MCGA;
import name.polhill.gary.mcga.RankLotteryBreeder;

/**
 * GZGA
 * 
 * A GA trying to find a bitstring that is difficult to compress. Since any
 * random bitstring is difficult to compress, we pass in a population of all-0
 * bitstings to the GA initially. This GA therefore 'finds' random bitstrings...
 * 
 * @author Gary Polhill
 * 
 */
public class GZGA {
  int nSteps;
  MCGA ga;
  GZipCostFunction gzipfunc;
  RankLotteryBreeder breeder;

  /**
   * main
   * 
   * Entry point to run the GZGA. Arguments are:
   * 
   * <ol>
   * <li>Number of generations to use</li>
   * <li>Size of population to use</li>
   * <li>Crossover probability to use (suggest around 0.05-0.2)</li>
   * <li>Mutation probability to use (suggest around 0.01-0.05)</li>
   * <li>Number of bytes in the bitstring</li>
   * </ol>
   * 
   * @param args
   *          Arguments to the program--see above.
   */
  public static void main(String[] args) {
    if(args.length != 5) {
      System.err.println("Arguments: <nSteps> <popSize> <pCrossover> <pMutate> <size>");
    }
    GZGA gzga = new GZGA(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]),
	Double.parseDouble(args[3]), Integer.parseInt(args[4]) * 8);
    gzga.run();
    System.exit(0);
  }

  public GZGA(int nSteps, int popSize, double pCrossover, double pMutate, int size) {
    this.nSteps = nSteps;
    breeder = new RankLotteryBreeder(pCrossover, pMutate);
    gzipfunc = new GZipCostFunction();
    GZGAPopulationFactory factory = new GZGAPopulationFactory(size);
    ga = new MCGA(breeder, gzipfunc, popSize, factory);
  }

  public void run() {
    for(int i = 1; i < nSteps; i++) {
      Set<Chromosome> best_cost = ga.step(true);
      System.out.println("Generation " + i + ": diversity " + ga.difference0() + ", costs = "
	  + Chromosome.Tools.getCostString(best_cost));
    }
  }
}
