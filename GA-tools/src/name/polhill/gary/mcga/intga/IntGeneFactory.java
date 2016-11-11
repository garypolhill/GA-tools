/**
 * IntGeneFactory.java, name.polhill.gary.intga
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
package name.polhill.gary.mcga.intga;

import name.polhill.gary.mcga.BitStringChromosome;
import name.polhill.gary.mcga.Chromosome;
import name.polhill.gary.mcga.GrayCodeBitStringGene;
import name.polhill.gary.mcga.PopulationFactory;

/**
 * <!-- IntGeneFactory -->
 *
 * @author Gary Polhill
 *
 */
public class IntGeneFactory implements PopulationFactory {
  private final int geneLength;
  private final int nGenes;

  /**
   * <!-- IntGeneFactory constructor -->
   *
   */
  public IntGeneFactory(int geneLength, int nGenes) {
    this.geneLength = geneLength;
    this.nGenes = nGenes;
  }

  /**
   * <!-- buildPopulation -->
   *
   * @see name.polhill.gary.mcga.PopulationFactory#buildPopulation(int)
   */
  @Override
  public Chromosome[] buildPopulation(int size) {
    BitStringChromosome population[] = new BitStringChromosome[size];
    
    for(int i = 0; i < size; i++) {
      GrayCodeBitStringGene gene[] = new GrayCodeBitStringGene[nGenes];
      
      for(int j = 0; j < nGenes; j++) {
	gene[j] = new GrayCodeBitStringGene(geneLength);
	gene[j].randomise();
      }
      
      population[i] = new BitStringChromosome(gene);
    }
    
    return population;
  }

}
