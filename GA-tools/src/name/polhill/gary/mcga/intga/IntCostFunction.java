/*
 * name.polhill.gary.intga: IntCostFunction.java
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
 */
package name.polhill.gary.mcga.intga;

import java.math.BigInteger;

import name.polhill.gary.mcga.BitStringGene;
import name.polhill.gary.mcga.Chromosome;
import name.polhill.gary.mcga.Cost;
import name.polhill.gary.mcga.CostFunction;
import name.polhill.gary.mcga.DoubleCost;
import name.polhill.gary.mcga.Gene;
import name.polhill.gary.mcga.GrayCodeBitStringGene;

/**
 * IntCostFunction
 * 
 * Cost function based on the difference between the gene as a decoded Gray
 * number and an integer the GA has to find
 * 
 * @author Gary Polhill
 * 
 */
public class IntCostFunction implements CostFunction {
  private final BigInteger values[];

  public IntCostFunction(String args[]) {
    values = new BigInteger[args.length];

    for(int i = 0; i < args.length; i++) {
      values[i] = new BigInteger(args[i], 10);
    }
  }

  /**
   * <!-- cost -->
   * 
   * @param gene
   * @return
   */
  public DoubleCost cost(Gene gene, int i) {
    if(gene instanceof GrayCodeBitStringGene) {
      BitStringGene decode = ((GrayCodeBitStringGene)gene).inverseGrayCode();
      return new DoubleCost(Math.abs(decode.gene.subtract(values[i]).doubleValue()));
    }
    else {
      throw new RuntimeException("Invalid class for gene: " + gene.getClass());
    }
  }

  /**
   * <!-- cost -->
   * 
   * @see name.polhill.gary.mcga.CostFunction#cost(name.polhill.gary.mcga.Chromosome)
   */
  @Override
  public Cost[] cost(Chromosome chromosome) {
    int n = chromosome.nGenes();

    if(n != values.length) {
      throw new RuntimeException("Chromosome doesn't have the right number of genes (" + n + " rather than "
	  + values.length + ")");
    }

    DoubleCost[] cost = new DoubleCost[n];

    for(int i = 0; i < n; i++) {
      Gene gene = chromosome.geneAt(i);
      cost[i] = cost(gene, i);
    }
    return cost;
  }

}
