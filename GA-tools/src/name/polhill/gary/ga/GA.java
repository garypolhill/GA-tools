/*
 * name.polhill.gary.ga: GA.java Copyright
 * 
 * (C) 2008 Gary Polhill
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
package name.polhill.gary.ga;

import java.util.HashMap;
import java.util.Map;

/**
 * GA
 * 
 * Class implementing a genetic algorithm. After constructing the GA, call the
 * step() method to run a single generation.
 * 
 * @author Gary Polhill
 * 
 */
public class GA {
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
  Gene[] population;

  /**
   * The current best solution found (may not necessarily be in the current
   * population if the breeder doesn't guarantee keeping it).
   */
  Gene best;

  /**
   * The cost of the best solution
   */
  double bestCost;

  /**
   * Map from current population members to costs
   */
  Map<Gene, Double> costs;

  private GA(Breeder breeder, CostFunction costFunction) {
    this.breeder = breeder;
    this.costFunction = costFunction;
  }

  /**
   * Construct a GA by passing in a breeder, cost function and initial
   * population.
   * 
   * @param breeder The breeder algorithm to use
   * @param costFunction The cost function to use
   * @param population The initial population
   */
  public GA(Breeder breeder, CostFunction costFunction, Gene[] population) {
    this(breeder, costFunction);
    this.population = population;
  }

  /**
   * Construct a GA by passing in a breeder, cost function, population size and
   * gene size
   * 
   * @param breeder The breeder algorithm to use
   * @param costFunction The cost function to use
   * @param popsize The size of population
   * @param genelength The size of the genes
   */

  public GA(Breeder breeder, CostFunction costFunction, int popsize,
      int genelength) {
    this(breeder, costFunction);
    population = new Gene[popsize];
    for(int i = 0; i < population.length; i++) {
      population[i] = new Gene(genelength);
      population[i].randomise();
    }
  }

  /**
   * step
   * 
   * Run a single generation of the GA
   * 
   * @param maximise True if better solutions have higher costs, false otherwise
   * @return The best solution from the current generation (before breeding)
   */
  public double step(boolean maximise) {
    costs = new HashMap<Gene, Double>();
    double best_cost = costFunction.cost(population[0]);
    Gene best_gene = population[0];
    costs.put(population[0], best_cost);
    for(int i = 1; i < population.length; i++) {
      double this_cost = costFunction.cost(population[i]);
      best_cost =
        maximise ? (this_cost > best_cost ? this_cost : best_cost)
                : (this_cost < best_cost ? this_cost : best_cost);
      if(this_cost == best_cost) best_gene = population[i];
      costs.put(population[i], this_cost);
    }
    if(maximise ? best_cost > bestCost : best_cost < bestCost) {
      bestCost = best_cost;
      best = best_gene;
    }
    population = breeder.breed(population, costs, maximise);
    return best_cost;
  }

  /**
   * getBestSolution
   * 
   * @return The best solution found in any generation
   */
  public Gene getBestSolution() {
    return best;
  }

  /**
   * getBestSolutionCost
   * 
   * @return The cost of the best solution in any generation
   */
  public double getBestSolutionCost() {
    return bestCost;
  }

  /**
   * getMeanCost
   * 
   * @return The mean cost of the last generation
   */
  public double getMeanCost() {
    double total = 0.0;
    for(double cost: costs.values()) {
      total += cost;
    }
    return total / (double)population.length;
  }

  /**
   * getCostVariance
   * 
   * @return The variance of the last generation
   */
  public double getCostVariance() {
    double mean = getMeanCost();
    double total = 0.0;
    for(double cost: costs.values()) {
      total += (cost - mean) * (cost - mean);
    }
    return total / (double)population.length;
  }

  /**
   * nDifferent
   * 
   * @return The number of bits in the genome for which there are different
   *         values in the population
   */
  public long nDifferent() {
    Gene and = population[0];
    for(int i = 1; i < population.length; i++) {
      and = and.and(population[i]);
    }
    return and.nZeros();
  }
}
