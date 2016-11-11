/**
 * AbstractChromosome.java, name.polhill.gary.ga
 * 
 * Copyright (C) The James Hutton Institute 2013
 * 
 * This file is part of MCGA
 * 
 * MCGA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * MCGA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MCGA. If not, see <http://www.gnu.org/licenses/>.
 */
package name.polhill.gary.mcga;

/**
 * <!-- AbstractChromosome -->
 * 
 * Utility class for Chromosomes to subclass from if they want
 * 
 * @author Gary Polhill
 */
public abstract class AbstractChromosome implements Chromosome {
  /**
   * <!-- ComparableReturn -->
   * 
   * Simple class to contain comparability information
   * 
   * @author Gary Polhill
   */
  protected static class ComparableReturn {
    public int comparison;
    public boolean comparable;

    ComparableReturn(int comparison, boolean comparable) {
      this.comparison = comparison;
      this.comparable = comparable;
    }
  }

  /**
   * <!-- cost -->
   * 
   * Store the cost of the chromosome
   */
  protected Cost[] cost;

  /**
   * <!-- AbstractChromosome constructor -->
   * 
   * Initialise the cost to <code>null</code>
   */
  public AbstractChromosome() {
    cost = null;
  }

  /**
   * <!-- getCost -->
   * 
   * Default implementation of {@link Chromosome#getCost()}
   * 
   * @see name.polhill.gary.mcga.Chromosome#getCost()
   */
  @Override
  public Cost[] getCost() {
    return cost;
  }

  /**
   * <!-- getCost -->
   * 
   * Default implementation of {@link Chromosome#getCost(CostFunction)}
   * 
   * @see name.polhill.gary.mcga.Chromosome#getCost(name.polhill.gary.mcga.CostFunction)
   */
  @Override
  public Cost[] getCost(CostFunction func) {
    return func.cost(this);
  }

  /**
   * <!-- calcCost -->
   * 
   * Default implementation of {@link Chromosome#calcCast(CostFunction)}
   * 
   * @see name.polhill.gary.mcga.Chromosome#calcCost(name.polhill.gary.mcga.CostFunction)
   */
  @Override
  public Cost[] calcCost(CostFunction func) {
    cost = func.cost(this);
    return cost;
  }

  /**
   * <!-- comparableWith -->
   * 
   * Default implementation of {@link Chromosome#comparableWith(Chromosome)}.
   * This does not check classes.
   * 
   * @see name.polhill.gary.mcga.Chromosome#comparableWith(name.polhill.gary.mcga.Chromosome)
   */
  @Override
  public boolean comparableWith(Chromosome other) {
    return compare(cost, other.getCost()).comparable;
  }

  /**
   * <!-- compareTo -->
   * 
   * Default implementation of {@link Chromosome#compareTo(Chromosome)}
   * 
   * @see name.polhill.gary.mcga.Chromosome#compareTo(name.polhill.gary.mcga.Chromosome)
   */
  @Override
  public int compareTo(Chromosome other) {
    return compare(cost, other.getCost()).comparison;
  }

  /**
   * <!-- match -->
   * 
   * Default match method (uses WildCardChromosome constructor)
   * 
   * @see name.polhill.gary.mcga.Chromosome#match(name.polhill.gary.mcga.Chromosome)
   */
  public Chromosome match(Chromosome other) {
    return new WildCardChromosome(this, other);
  }

  /**
   * <!-- nWildcards -->
   * 
   * Default implementation of {@link Chromosome#nWildcards()} -- assumes no wildcards
   * 
   * @see name.polhill.gary.mcga.Chromosome#nWildcards()
   */
  @Override
  public long nWildcards() {
    return 0;
  }

  /**
   * <!-- clone -->
   * 
   * Abstract clone method stops compiler whinging.
   * 
   * @see java.lang.Object#clone()
   */
  public abstract Chromosome clone();

  /**
   * <!-- compare -->
   * 
   * Utility function to compare two arrays of costs. Cost arrays are comparable
   * if they have the same length and each element of both arrays have the same
   * direction of comparability (<= 0 or >= 0).
   * 
   * @param cost1
   * @param cost2
   * @return ComparableReturn object
   */
  protected static ComparableReturn compare(Cost[] cost1, Cost[] cost2) {
    if(cost1 == null) throw new RuntimeException("Cannot compare null cost1");
    if(cost2 == null) throw new RuntimeException("Cannot compare null cost2");
    if(cost1.length != cost2.length) return new ComparableReturn(0, false);
    if(cost1.length == 0) return new ComparableReturn(0, true);

    if(!cost1[0].comparableWith(cost2[0])) return new ComparableReturn(0, false);
    int dir = cost1[0].compareTo(cost2[0]);

    for(int i = 1; i < cost1.length; i++) {
      if(!cost1[i].comparableWith(cost2[i])) return new ComparableReturn(0, false);
      int this_dir = cost1[i].compareTo(cost2[i]);

      // if one direction is negative and the other positive, then they are not
      // comparable
      if(dir * this_dir < 0) return new ComparableReturn(0, false);

      // If this direction is non-zero then the saved direction should be set to
      // it (if the saved direction is non-zero, then the previous check ensures
      // that the saved and current directions are the same)
      if(this_dir != 0) dir = this_dir;
    }

    return new ComparableReturn(dir, true);
  }
  
  /**
   * <!-- perturbable -->
   * 
   * Default implementation of {@link Chromosome#perturbable()} -- return <code>false</code>
   * 
   * @see name.polhill.gary.mcga.Chromosome#perturbable()
   */
  @Override
  public boolean perturbable() {
    return false;
  }

  /**
   * <!-- perturb -->
   * 
   * Implement a standard perturb operation that throws an exception
   * 
   * @see name.polhill.gary.mcga.Chromosome#perturb(double, double)
   */
  public Chromosome perturb(double pPerturb, double perturbVar) {
    throw new UnsupportedOperationException("Chromosome class " + getClass() + " does not support perturbation");
  }

  /**
   * <!-- difference -->
   * 
   * Default implementation of {@link Chromosome#difference(Chromosome)}.
   * Computes the mean difference between all the genes.
   * 
   * @see name.polhill.gary.mcga.Chromosome#difference(name.polhill.gary.mcga.Chromosome)
   */
  @Override
  public double difference(Chromosome other) {
    if(this.getClass() != other.getClass()) return 1.0;
    else if(this instanceof WildCardChromosome || other instanceof WildCardChromosome) return 0.0;
    else if(nGenes() != other.nGenes()) return 1.0;
    else {
      double diff = 0.0;

      for(int i = 0; i < nGenes(); i++) {
	diff += geneAt(i).difference(other.geneAt(i)) * geneAt(i).size();
      }

      return diff / size();
    }
  }

}