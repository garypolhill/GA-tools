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

/**
 * <!-- Gene -->
 * 
 * Interface for Gene objects. Genes are datastructures that have a defined set
 * of options, and may appear in a Chromosome.
 * 
 * @author Gary Polhill
 */
public interface Gene extends Cloneable {
  /**
   * <!-- randomise -->
   * 
   * Return a randomised Gene. This may or may not change the Gene object (that
   * is, what is returned may be the caller with a new setting), depending on
   * whether the Gene is mutable.
   * 
   * @return
   */
  public Gene randomise();

  /**
   * <!-- crossover -->
   * 
   * Crossover this gene with the other at the specified point
   * 
   * @param other
   * @param point
   * @return
   */
  public Gene crossover(Gene other, int point);

  /**
   * <!-- mutate -->
   * 
   * Mutate the gene
   * 
   * @param pMutate
   * @return
   */
  public Gene mutate(double pMutate);

  /**
   * <!-- clone -->
   * 
   * Clone the gene
   * 
   * @return
   */
  public Gene clone();

  /**
   * <!-- difference -->
   * 
   * A measure of the difference between this gene and the argument. 1.0 means
   * completely different (or different class). 0.0 means equal.
   * 
   * @param other
   * @return A number between 0.0 and 1.0 representing how different this Gene
   *         is from the other.
   */
  public double difference(Gene other);

  /**
   * <!-- size -->
   * 
   * Return the size of the Gene
   * 
   * @return
   */
  public long size();

  /**
   * <!-- toByteArray -->
   * 
   * @return A byte array of the data in the gene
   */
  public byte[] toByteArray();
}
