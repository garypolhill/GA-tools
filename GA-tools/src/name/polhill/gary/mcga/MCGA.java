/*
 * name.polhill.gary.ga: MCGA.java Copyright
 * 
 * (C) 2008 Gary Polhill
 * 
 * This file is part of MCGA.
 * 
 * MCGA is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * MCGA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with GA. If not, see <http://www.gnu.org/licenses/>.
 */
package name.polhill.gary.mcga;

import java.util.HashSet;
import java.util.Set;

/**
 * MCGA
 * 
 * Class implementing a multi-criteria genetic algorithm. After constructing the
 * MCGA, call the step() method to run a single generation.
 * 
 * @author Gary Polhill
 */
public class MCGA {
  /**
   * The algorithm to use to select the next generation from the current one
   */
  Breeder breeder;

  /**
   * The cost function to use
   */
  CostFunction costFunction;

  /**
   * The current population
   */
  Chromosome[] population;

  /**
   * The current best solution found (may not necessarily be in the current
   * population if the breeder doesn't guarantee keeping it).
   */
  Set<Chromosome> best;

  /**
   * <!-- MCGA constructor -->
   * 
   * Private constructor
   * 
   * @param breeder
   * @param costFunction
   */
  private MCGA(Breeder breeder, CostFunction costFunction) {
    this.breeder = breeder;
    this.costFunction = costFunction;
    this.best = new HashSet<Chromosome>();
  }

  /**
   * Construct a GA by passing in a breeder, cost function and initial
   * population.
   * 
   * @param breeder
   *          The breeder algorithm to use
   * @param costFunction
   *          The cost function to use
   * @param population
   *          The initial population
   */
  public MCGA(Breeder breeder, CostFunction costFunction, Chromosome[] population) {
    this(breeder, costFunction);
    this.population = population;
  }

  /**
   * Construct a GA by passing in a breeder, cost function, population size and
   * gene factory
   * 
   * @param breeder
   *          The breeder algorithm to use
   * @param costFunction
   *          The cost function to use
   * @param popsize
   *          The size of population
   * @param factory
   *          A factory to use to build the population
   */

  public MCGA(Breeder breeder, CostFunction costFunction, int popsize, PopulationFactory factory) {
    this(breeder, costFunction);
    population = factory.buildPopulation(popsize);
  }

  /**
   * step
   * 
   * Run a single generation of the GA
   * 
   * @param maximise
   *          True if better solutions have higher costs, false otherwise
   * @return The best solution from the current generation (before breeding)
   */
  public Set<Chromosome> step(boolean maximise) {
    // Compute costs and find the set of 'best' genes in this population
    Set<Chromosome> best_genes = new HashSet<Chromosome>();
    best_genes.add(population[0]);
    population[0].calcCost(costFunction);
    for(int i = 1; i < population.length; i++) {
      population[i].calcCost(costFunction);
      boolean incomparable = true;
      for(Chromosome thisbest: best_genes) {
	if(population[i].comparableWith(thisbest)) {
	  incomparable = false;
	  if(maximise ? (population[i].compareTo(thisbest) > 0) : (population[i].compareTo(thisbest) < 0)) {
	    best_genes.remove(thisbest);
	    best_genes.add(population[i]);
	    break;
	  }
	  else break;
	}
      }
      if(incomparable) best_genes.add(population[i]);
    }

    // Breed the next population
    population = breeder.breed(population, maximise);

    // Update the saved population of 'best-ever' genes
    Set<Chromosome> addthis = new HashSet<Chromosome>();
    for(Chromosome thisbest: best_genes) {
      boolean incomparable = true;
      for(Chromosome bestsofar: best_genes) {
	if(thisbest.comparableWith(bestsofar)) {
	  incomparable = false;
	  if(maximise ? (thisbest.compareTo(bestsofar) > 0) : (thisbest.compareTo(bestsofar) < 0)) {
	    best.remove(bestsofar);
	    addthis.add(thisbest);
	  }
	}
      }
      if(incomparable) addthis.add(thisbest);
    }
    best.addAll(addthis);

    return best_genes;
  }

  /**
   * getBestSolution
   * 
   * This function returns a Pareto front of 'best' solutions
   * 
   * @return The best solution found in any generation
   */
  public Set<Chromosome> getBestSolution() {
    return best;
  }

  /**
   * <!-- difference -->
   *
   * @return The mean difference of the rest of the population from the first member
   */
  public double difference0() {
    double diff = 0.0;
    
    for(int i = 1; i < population.length; i++) {
      diff += population[0].difference(population[i]);
    }
    
    return diff / (double)population.length;
  }
  
  /**
   * <!-- difference -->
   *
   * (Potentially computationally expensive)
   *
   * @return The mean difference between all pairs of population members
   */
  public double difference() {
    double diff = 0.0;
    double n = 0.0;
    
    for(int i = 0; i < population.length - 1; i++) {
      for(int j = i + 1; j < population.length; j++) {
	diff += population[i].difference(population[j]);
	n += 1.0;
      }
    }
    
    return diff / n;
  }
}
