/*
 * name.polhill.gary.ga: BitStringChromosome.java Copyright (C) 2009 Macaulay
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
 * BitStringChromosome
 * 
 * A Chromosome containing an array of BitStringGenes
 * 
 * @author Gary Polhill
 */
public class BitStringChromosome extends AbstractChromosome implements Chromosome {

  /**
   * <!-- genes -->
   * 
   * Genes in the Chromosome
   */
  BitStringGene[] genes;

  /**
   * <!-- size -->
   * 
   * Size of the Chromosome -- this is the sum of the sizes of the genes.
   */
  final long size;

  /**
   * <!-- BitStringChromosome constructor -->
   * 
   * Constructor for an empty BitStringChromosome with a given number of genes
   * of the same size
   * 
   * @param ngenes
   * @param genelength
   */
  public BitStringChromosome(int ngenes, int genelength) {
    genes = new BitStringGene[ngenes];
    size = (long)ngenes * (long)genelength;
    for(int i = 0; i < genes.length; i++) {
      genes[i] = new BitStringGene(genelength);
    }
  }

  /**
   * <!-- BitStringChromosome constructor -->
   * 
   * Constructor for an empty BitStringChromosome with gene sizes specified as
   * per array. (I'm not sure what I had in mind when creating this constructor.
   * It seems to be the same as BitStringChromosome(int[]).)
   * 
   * @param genelengths
   */
  public BitStringChromosome(Integer[] genelengths) {
    genes = new BitStringGene[genelengths.length];
    long size = 0;
    for(int i = 0; i < genes.length; i++) {
      size += genelengths[i];
      genes[i] = new BitStringGene(genelengths[i]);
    }
    this.size = size;
  }

  /**
   * <!-- BitStringChromosome constructor -->
   * 
   * Constructor for an empty BitStringChromosome with gene sizes specified as
   * per array
   * 
   * @param genelengths
   */
  public BitStringChromosome(int[] genelengths) {
    genes = new BitStringGene[genelengths.length];
    long size = 0;
    for(int i = 0; i < genes.length; i++) {
      size += genelengths[i];
      genes[i] = new BitStringGene(genelengths[i]);
    }
    this.size = size;
  }

  /**
   * <!-- BitStringChromosome constructor -->
   * 
   * Constructor for an array of BitStringGenes that has already been created.
   * 
   * @param genes
   */
  public BitStringChromosome(BitStringGene[] genes) {
    this.genes = genes;
    long size = 0;
    for(int i = 0; i < genes.length; i++) {
      size += genes[i].size();
    }
    this.size = size;
  }

  /**
   * <!-- BitStringChromosome constructor -->
   * 
   * Construct this Chromosome as a clone of the other
   * 
   * @param other
   */
  public BitStringChromosome(BitStringChromosome other) {
    genes = new BitStringGene[other.genes.length];
    long size = 0;
    for(int i = 0; i < genes.length; i++) {
      size += other.genes[i].size();
      genes[i] = other.genes[i].clone();
    }
    this.size = size;
    if(other.cost != null) {
      cost = new Cost[other.cost.length];
      for(int i = 0; i < cost.length; i++) {
	cost[i] = other.cost[i].clone();
      }
    }
  }

  public boolean sameConfiguration(Chromosome other) {
    if(!(other instanceof BitStringChromosome)) return false;
    else {
      BitStringChromosome bsother = (BitStringChromosome)other;

      if(bsother.nGenes() != nGenes()) return false;

      for(int i = 0; i < genes.length; i++) {
	if(bsother.geneAt(i).size() != genes[i].size()) return false;
      }

      return true;
    }
  }

  /**
   * <!-- crossover -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#crossover(name.polhill.gary.mcga.Chromosome,
   *      long)
   */
  public Chromosome crossover(Chromosome other, long point) {
    if(!(other instanceof BitStringChromosome)) {
      throw new RuntimeException("Cannot crossover a BitStringChromosome with a chromosome of a different class ("
	  + other.getClass() + ")");
    }
    
    if(!sameConfiguration(other)) {
      throw new RuntimeException("Cannot crossover chromosomes of different sizes");
    }
    
    // Handle quick cases
    
    if(point <= 0) return other.clone();
    if(point >= size) return clone();
    
    // Initialise crossover

    BitStringChromosome bsother = (BitStringChromosome)other;
    BitStringChromosome crossover = bsother.clone();
    
    // Find the crossover gene, filling in this chromosome from the left
    
    long pointfind = 0;
    int point_i;
    for(point_i = 0; point_i < genes.length; point_i++) {
      pointfind += genes[point_i].size();
      if(point <= pointfind) break;
      else crossover.genes[point_i] = genes[point_i].clone(); 
    }
    
    // Crossover within the crossover gene
    
    pointfind -= genes[point_i].size();
    crossover.genes[point_i] = crossover.genes[point_i].crossover(genes[point_i], (int)(point - pointfind));
    
    return crossover;
  }

  /**
   * <!-- mutate -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#mutate(double)
   */
  public Chromosome mutate(double pMutate) {
    BitStringChromosome chromosome = clone();
    for(int i = 0; i < genes.length; i++) {
      chromosome.genes[i] = chromosome.genes[i].mutate(pMutate);
    }
    return chromosome;
  }

  /**
   * <!-- randomise -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#randomise()
   */
  public Chromosome randomise() {
    for(int i = 0; i < genes.length; i++) {
      genes[i].randomise();
    }

    return this;
  }

  /**
   * <!-- size -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#size()
   */
  public long size() {
    return size;
  }

  /**
   * <!-- clone -->
   * 
   * @see name.polhill.gary.mcga.AbstractChromosome#clone()
   */
  public BitStringChromosome clone() {
    return new BitStringChromosome(this);
  }

  /**
   * <!-- geneAt -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#geneAt(int)
   */
  public Gene geneAt(int i) {
    return genes[i];
  }

  /**
   * <!-- nGenes -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#nGenes()
   */
  public int nGenes() {
    return genes.length;
  }
}
