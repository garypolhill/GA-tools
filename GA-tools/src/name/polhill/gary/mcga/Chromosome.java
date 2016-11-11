/*
 * name.polhill.gary.ga: Gene.java Copyright (C) 2009 Macaulay Institute
 * 
 * This file is part of MCGA.
 * 
 * MCGA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * MCGA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with MCGA. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contact information: Gary Polhill Macaulay Institute, Craigiebuckler,
 * Aberdeen. AB15 8QH. UK. g.polhill@macaulay.ac.uk
 */
package name.polhill.gary.mcga;

import java.util.Collection;

/**
 * <!-- Chromosome -->
 * 
 * Interface that all Chromosomes must follow. Chromosomes are arrays (or sets
 * or lists) of Genes.
 * 
 * @author Gary Polhill
 */
public interface Chromosome extends Cloneable, Comparable<Chromosome> {
  /**
   * <!-- randomise -->
   * 
   * Return a Chromosome with a randomised array of genes. By so doing, it
   * enables Chromosome classes to be immutable. However, calling this method on
   * mutable classes may cause it to change and return self.
   */
  public Chromosome randomise();

  /**
   * <!-- crossover -->
   * 
   * Return this Chromosome crossed over with the other at the specified point
   * (note that this point ranges over the size of the Chromosome, not the
   * number of Genes)
   * 
   * @param other
   * @param point
   * @return
   */
  public Chromosome crossover(Chromosome other, long point);

  /**
   * <!-- mutate -->
   * 
   * Mutate each Gene in the Chromosome with the specified probability
   * 
   * @param pMutate
   * @return
   */
  public Chromosome mutate(double pMutate);

  /**
   * <!-- getCost -->
   * 
   * Get the <i>stored</i> cost of the Chromosome (i.e. the cost computed at the
   * last call of calcCost())
   * 
   * @return
   */
  public Cost[] getCost();

  /**
   * <!-- getCost -->
   * 
   * Get the cost of the Chromosome using a particular cost function. <i>This
   * will not affect the stored cost</i>
   * 
   * @param func
   * @return
   */
  public Cost[] getCost(CostFunction func);

  /**
   * <!-- calcCost -->
   * 
   * Compute the <i>stored</i> cost of the Chromosome using the specified
   * function, and return ut.
   * 
   * @param func
   * @return
   */
  public Cost[] calcCost(CostFunction func);

  /**
   * <!-- comparableWith -->
   * 
   * Check if this Chromosome is comparable with the other
   * 
   * @param other
   * @return
   */
  public boolean comparableWith(Chromosome other);

  /**
   * <!-- compareTo -->
   * 
   * Compare this Chromosome with another with respect to cost
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Chromosome other);

  /**
   * <!-- clone -->
   * 
   * Clone the Chromosome
   * 
   * @return
   */
  public Chromosome clone();

  /**
   * <!-- nWildcards -->
   * 
   * Return the number of wildcards in the Chromosome. For most implementations,
   * this will simply be 0.
   * 
   * @return
   */
  public long nWildcards();

  /**
   * <!-- size -->
   * 
   * Return the size of the Chromosome -- this is not the same as the number of
   * Genes; it is the sum of their sizes
   * 
   * @return
   */
  public long size();

  /**
   * <!-- geneAt -->
   * 
   * Get the ith Gene (this kind of assumes that where Chromosomes comprise Sets
   * of Genes, there will be a consistent way to index set members).
   * 
   * @param i
   * @return
   */
  public Gene geneAt(int i);

  /**
   * <!-- nGenes -->
   * 
   * Get the number of Genes in the Chromosome.
   * 
   * @return
   */
  public int nGenes();

  /**
   * <!-- perturbable -->
   * 
   * @return <code>true</code> if the Chromosome contains perturbable genes
   */
  public boolean perturbable();

  /**
   * <!-- Chromosome.Tools -->
   * 
   * A singleton class implementing some tools that are useful with Chromosomes
   * or populations of Chromosomes
   * 
   * @author Gary Polhill
   */
  public class Tools {
    public static String getCostString(Collection<Chromosome> population) {
      StringBuffer buff = new StringBuffer();
      for(Chromosome chrom: population) {
	if(buff.length() > 0) buff.append(", ");
	Cost[] this_cost = chrom.getCost();
	buff.append("[");
	for(int j = 0; j < this_cost.length; j++) {
	  buff.append(" ");
	  buff.append(this_cost[j]);
	}
	buff.append(" ]");
      }

      return buff.toString();
    }
  }

  /**
   * <!-- perturb -->
   * 
   * Perturb the Chromosome. This will throw an exception if the Chromosome is
   * not perturbable
   * 
   * @param pPerturb
   * @param perturbVar
   * @return
   */
  public Chromosome perturb(double pPerturb, double perturbVar);

  /**
   * <!-- difference -->
   * 
   * Compute a measure of the difference between this Chromosome and another.
   * The result is 0.0 if they are identical, and 1.0 if they are completely
   * different.
   * 
   * @param other
   * @return
   */
  public double difference(Chromosome other);

}
