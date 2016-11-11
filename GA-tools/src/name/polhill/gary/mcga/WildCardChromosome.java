/**
 * WildCardChromosome.java, name.polhill.gary.ga
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <!-- WildCardChromosome -->
 * 
 * Class containing WildCardGenes on zero or more locations of the Chromosome.
 * This is used for pattern-matching Chromosomes; genetic operators, costs and
 * comparison of WildCardChromosomes is meaningless, and will generate errors.
 * The size of the Chromosome is also meaningless if there are one or more
 * WildCardGenes.
 * 
 * @author Gary Polhill
 */
public class WildCardChromosome extends AbstractChromosome {

  /**
   * <!-- genes -->
   * 
   * Array of genes. This is stored as an unmodifiable list (since final Gene
   * genes[] would still mean elements of genes were modifiable)
   */
  private final List<Gene> genes;

  /**
   * <!-- nWildcards -->
   * 
   * Number of WildCardGenes in the array of genes
   */
  private final int nWildcards;

  /**
   * <!-- size -->
   * 
   * Size of the Chromosome. Meaningless if nWildcards > 0.
   */
  private final long size;

  /**
   * <!-- perturbable -->
   *
   * Store whether or not the Chromosome is perturbable
   */
  private boolean perturbable;

  /**
   * <!-- WildCardChromosome constructor -->
   * 
   */
  public WildCardChromosome(Chromosome a, Chromosome b) {
    if(a.nGenes() != b.nGenes()) {
      throw new RuntimeException("Unequal number of genes: (" + a.nGenes() + " and " + b.nGenes() + ")");
    }
    Gene[] genes = new Gene[a.nGenes()];
    int nWildcards = 0;
    long size = 0;
    for(int i = 0; i < genes.length; i++) {
      if(a.geneAt(i).equals(b.geneAt(i))) {
	genes[i] = a.geneAt(i);
	size += genes[i].size();
	if(genes[i] instanceof PerturbableGene) {
	  this.perturbable = true;
	}
      }
      else {
	genes[i] = WildCardGene.create();
	nWildcards++;
      }
    }
    this.nWildcards = nWildcards;
    this.size = size;
    this.genes = Collections.unmodifiableList(Arrays.asList(genes));
  }

  public WildCardChromosome(Chromosome a) {
    this(a, a);
  }
  
  private WildCardChromosome(Gene genes[], long size, int nWildcards, boolean perturbable) {
    this.nWildcards = nWildcards;
    this.size = size;
    this.genes = Collections.unmodifiableList(Arrays.asList(genes));
    this.perturbable = perturbable;
  }

  /**
   * <!-- randomise -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#randomise()
   */
  @Override
  public Chromosome randomise() {
    throw new RuntimeException();
  }

  /**
   * <!-- crossover -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#crossover(name.polhill.gary.mcga.Chromosome,
   *      long)
   */
  @Override
  public Chromosome crossover(Chromosome other, long point) {
    throw new RuntimeException();
  }

  /**
   * <!-- mutate -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#mutate(double)
   */
  @Override
  public Chromosome mutate(double pMutate) {
    throw new RuntimeException();
  }

  /**
   * <!-- getCost -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#getCost()
   */
  @Override
  public Cost[] getCost() {
    throw new RuntimeException();
  }

  /**
   * <!-- getCost -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#getCost(name.polhill.gary.mcga.CostFunction)
   */
  @Override
  public Cost[] getCost(CostFunction func) {
    throw new RuntimeException();
  }

  /**
   * <!-- calcCost -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#calcCost(name.polhill.gary.mcga.CostFunction)
   */
  @Override
  public Cost[] calcCost(CostFunction func) {
    throw new RuntimeException();
  }

  /**
   * <!-- comparableWith -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#comparableWith(name.polhill.gary.mcga.Chromosome)
   */
  @Override
  public boolean comparableWith(Chromosome other) {
    throw new RuntimeException();
  }

  /**
   * <!-- compareTo -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#compareTo(name.polhill.gary.mcga.Chromosome)
   */
  @Override
  public int compareTo(Chromosome other) {
    throw new RuntimeException();
  }

  /**
   * <!-- nWildcards -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#nWildcards()
   */
  @Override
  public long nWildcards() {
    return nWildcards;
  }

  /**
   * <!-- size -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#size()
   */
  @Override
  public long size() {
    if(nWildcards > 0) throw new RuntimeException();

    return size;
  }

  /**
   * <!-- geneAt -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#geneAt(int)
   */
  @Override
  public Gene geneAt(int i) {
    return genes.get(i);
  }

  /**
   * <!-- nGenes -->
   * 
   * @see name.polhill.gary.mcga.Chromosome#nGenes()
   */
  @Override
  public int nGenes() {
    return genes.size();
  }

  /**
   * <!-- clone -->
   * 
   * CLone the Chromosome
   * 
   * @see name.polhill.gary.mcga.AbstractChromosome#clone()
   */
  public WildCardChromosome clone() {
    return new WildCardChromosome(this);
  }

  /**
   * <!-- perturbable -->
   *
   * @see name.polhill.gary.mcga.Chromosome#perturbable()
   */
  @Override
  public boolean perturbable() {
    return perturbable;
  }
  
  @Override
  public Chromosome perturb(double pPerturb, double perturbVar) {
    Gene perturbed[] = new Gene[genes.size()];
    
    for(int i = 0; i < perturbed.length; i++) {
      Gene gene = genes.get(i);
      if(gene instanceof PerturbableGene) {
	perturbed[i] = ((PerturbableGene)gene).perturb(pPerturb, perturbVar);
      }
      else {
	perturbed[i] = gene.clone();
      }
    }
    
    return new WildCardChromosome(perturbed, size, nWildcards, perturbable);
  }
}
