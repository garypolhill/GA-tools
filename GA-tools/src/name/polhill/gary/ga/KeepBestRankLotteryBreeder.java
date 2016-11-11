/*
 * name.polhill.gary.ga: LotteryBreeder.java
 * 
 * Copyright (C) 2008 Gary Polhill
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
 */
package name.polhill.gary.ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * KeepBestRankLotteryBreeder
 * 
 * A breeder issuing tickets in a lottery for a chance to propagate to the next
 * generation. Tickets are issued in rank order of cost, with the best genes
 * getting more tickets. Further, a specified number of the very best genes are
 * guaranteed getting copied to the next generation without genetic operators
 * being applied.
 * 
 * @author Gary Polhill
 * 
 */
public class KeepBestRankLotteryBreeder implements Breeder {

  private double pCrossover;
  private double pMutate;
  private int nBestKept;

  /**
   * Constructor passing in the crossover probability, mutation probability, and
   * the number of the best genes to propagate to the next generation
   * 
   * @param pCrossover Crossover probability
   * @param pMutate Mutate probability
   * @param nBestKept Number of best genes from the population to copy to the
   *          next generation without applying genetic operators
   */
  public KeepBestRankLotteryBreeder(double pCrossover, double pMutate,
      int nBestKept) {
    this.pCrossover = pCrossover;
    this.pMutate = pMutate;
    this.nBestKept = nBestKept;
  }

  /*
   * (non-Javadoc)
   * 
   * @see name.polhill.gary.ga.Breeder#breed(name.polhill.gary.ga.Gene[],
   *      java.util.Map)
   */
  public Gene[] breed(Gene[] population, final Map<Gene, Double> costs,
      final boolean maximise) {
    class CompareGenes implements Comparator<Gene> {

      /*
       * (non-Javadoc)
       * 
       * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
       */
      public int compare(Gene o1, Gene o2) {
        return Double.compare(costs.get(o1), costs.get(o2))
          * (maximise ? 1 : -1);
      }

    }
    List<Gene> sortedPop = new ArrayList<Gene>(population.length);
    for(Gene g: population) {
      sortedPop.add(g);
    }
    Collections.sort(sortedPop, new CompareGenes());
    Gene[] newpop = new Gene[population.length];
    for(int i = 0; i < nBestKept; i++) {
      newpop[i] = sortedPop.get(sortedPop.size() - (i + 1)).clone();
    }
    LinkedList<Integer> lottery = new LinkedList<Integer>();
    for(int i = 0; i < population.length; i++) {
      for(int j = 1; j <= i + 1; j++) {
        lottery.add(i);
      }
    }
    Collections.shuffle(lottery);
    for(int i = nBestKept; i < population.length; i++) {
      Gene mother = sortedPop.get(lottery.remove());
      Gene father = sortedPop.get(lottery.remove());
      Gene baby = mother.clone();
      if(Math.random() < pCrossover) {
        baby = baby.crossover(father, (int)(Math.random() * father.length));
      }
      if(pMutate > 0.0) {
        baby = baby.mutate(pMutate);
      }
      newpop[i] = baby;
    }
    return newpop;
  }

}
