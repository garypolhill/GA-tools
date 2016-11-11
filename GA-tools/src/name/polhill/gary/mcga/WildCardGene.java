/* name.polhill.gary.ga: WildCardGene.java
 * Copyright (C) 2009  Macaulay Institute
 *
 * This file is part of MCGA.
 *
 * MCGA is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 *
 * MCGA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with MCGA. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * Contact information:
 *   Gary Polhill
 *   Macaulay Institute, Craigiebuckler, Aberdeen. AB15 8QH. UK.
 *   g.polhill@macaulay.ac.uk
 */
package name.polhill.gary.mcga;

/**
 * WildCardGene
 * @author Gary Polhill
 *
 */
public final class WildCardGene extends AbstractGene implements Gene {
  private static WildCardGene singleton = new WildCardGene();
  
  private WildCardGene() {
    
  }
  
  public static WildCardGene create() {
    return singleton;
  }

  /* (non-Javadoc)
   * @see name.polhill.gary.ga.Gene#compareTo(name.polhill.gary.ga.Gene)
   */
  public int compareTo(Gene other) {
    return 0;
  }

  /* (non-Javadoc)
   * @see name.polhill.gary.ga.Gene#crossover(name.polhill.gary.ga.Gene, int)
   */
  public Gene crossover(Gene other, int point) {
    throw new RuntimeException();
  }

  /* (non-Javadoc)
   * @see name.polhill.gary.ga.Gene#matches(name.polhill.gary.ga.Gene)
   */
  public boolean matches(Gene other) {
    return true;
  }

  /* (non-Javadoc)
   * @see name.polhill.gary.ga.Gene#mutate(double)
   */
  public Gene mutate(double mutate) {
    throw new RuntimeException();
  }

  /* (non-Javadoc)
   * @see name.polhill.gary.ga.Gene#randomise()
   */
  public Gene randomise() {
    throw new RuntimeException();
  }

  /* (non-Javadoc)
   * @see name.polhill.gary.ga.Gene#size()
   */
  public long size() {
    throw new RuntimeException();
  }

  public WildCardGene clone() {
    return singleton;
  }

  /**
   * <!-- toByteArray -->
   *
   * @see name.polhill.gary.mcga.Gene#toByteArray()
   */
  @Override
  public byte[] toByteArray() {
    return new byte[] {(byte)0};
  }

  /**
   * <!-- differenceSameClass -->
   *
   * @see name.polhill.gary.mcga.AbstractGene#differenceSameClass(name.polhill.gary.mcga.AbstractGene)
   */
  @Override
  protected double differenceSameClass(AbstractGene other) {
    return 0.0;
  }
}
