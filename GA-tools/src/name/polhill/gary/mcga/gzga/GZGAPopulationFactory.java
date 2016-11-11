/**
 * GZGAPopulationFactory.java, name.polhill.gary.gzga
 *
 * Copyright (C) The James Hutton Institute 2013
 *
 * This file is part of MCGA
 *
 * MCGA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MCGA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MCGA.  If not, see <http://www.gnu.org/licenses/>.
 */
package name.polhill.gary.mcga.gzga;

import name.polhill.gary.mcga.BitStringChromosome;
import name.polhill.gary.mcga.BitStringGene;
import name.polhill.gary.mcga.Chromosome;
import name.polhill.gary.mcga.PopulationFactory;

/**
 * <!--  -->
 *
 * @author Gary Polhill
 *
 */
public class GZGAPopulationFactory implements PopulationFactory {
  
  private final int geneSize;

  /**
   * <!-- GZGAPopulationFactory constructor -->
   *
   */
  public GZGAPopulationFactory(int geneSize) {
    this.geneSize = geneSize;
  }

  /**
   * <!-- buildPopulation -->
   *
   * @see name.polhill.gary.mcga.PopulationFactory#buildPopulation(int)
   */
  @Override
  public Chromosome[] buildPopulation(int size) {
    BitStringChromosome population[] = new BitStringChromosome[size];
    
    for(int i = 0; i < population.length; i++) {
      population[i] = new BitStringChromosome(new BitStringGene[] { new BitStringGene(geneSize) });
    }
    
    return population;
  }

}
