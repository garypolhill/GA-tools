/*
 * name.polhill.gary.mcga: PopulationFactory.java Copyright (C) 2009 Macaulay
 * Institute
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
 * <!-- PopulationFactory -->
 * 
 * Interface for classes implementing builders of populations to follow.
 * 
 * @author Gary Polhill
 */
public interface PopulationFactory {
  /**
   * <!-- buildPopulation -->
   * 
   * Build a population of Chromosomes
   * 
   * @param size
   * @return
   */
  public Chromosome[] buildPopulation(int size);
}
