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

/**
 * RankLotteryBreeder
 * 
 * A breeder that sorts the population in order of cost, and issues tickets in a
 * lottery for breeding selection based on their rank in the ordering.
 * 
 * @author Gary Polhill
 * 
 */
public class RankLotteryBreeder extends KeepBestRankLotteryBreeder {

  public RankLotteryBreeder(double pCrossover, double pMutate) {
    this(pCrossover, pMutate, 0);
  }

  private RankLotteryBreeder(double pCrossover, double pMutate, int nBest) {
    super(pCrossover, pMutate, nBest);
  }

}
