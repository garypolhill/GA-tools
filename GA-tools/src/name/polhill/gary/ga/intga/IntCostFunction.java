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
 * 
 */
package name.polhill.gary.ga.intga;

import java.math.BigInteger;

import name.polhill.gary.ga.CostFunction;
import name.polhill.gary.ga.Gene;

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
  public static boolean GRAY_CODE = true;

  public BigInteger value;

  public IntCostFunction(String arg) {
    value = new BigInteger(arg, 10);
  }

  /*
   * (non-Javadoc)
   * 
   * @see name.polhill.gary.ga.CostFunction#cost(name.polhill.gary.ga.Gene)
   */
  public double cost(Gene gene) {
    Gene decode = GRAY_CODE ? gene.inverseGrayCode() : gene.clone();
    return Math.abs(decode.gene.subtract(value).doubleValue());
  }
  
  public static void main(String[] args) {
    if(args.length != 3) {
      System.err.println("usage: <value> <length> <max>");
      return;
    }
    IntCostFunction cf = new IntCostFunction(args[0]);
    BigInteger max = new BigInteger(args[2], 10);
    int size = new Integer(args[1]);
    for(BigInteger i = BigInteger.ZERO; i.compareTo(max) <= 0; i = i.add(BigInteger.ONE)) {
      Gene gene = new Gene(size, i);
      gene = gene.grayCode();
      double cost = cf.cost(gene);
      System.out.println("i = " + i + "; cost = " + cost);
    }
  }

}
