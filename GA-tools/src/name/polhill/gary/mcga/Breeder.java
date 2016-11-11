/*
 * name.polhill.gary.ga: Selector.java
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
package name.polhill.gary.mcga;

/**
 * An interface for all breeder classes to implement. A genetic algorithm can be
 * sensitive to the algorithm used to select the next population.
 * 
 * @author Gary Polhill
 * 
 */
public interface Breeder {

  /**
   * breed
   * 
   * Breed method, containing the algorithm for selecting the next generation
   * 
   * @param population The current population
   * @param maximise True if higher cost is better, false if lower cost is
   *          better
   * @return The next generation
   */
  Chromosome[] breed(Chromosome[] population, boolean maximise);

}
