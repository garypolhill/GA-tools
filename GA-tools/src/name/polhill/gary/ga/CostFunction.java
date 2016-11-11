/*
 * name.polhill.gary.ga: CostFunction.java
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
package name.polhill.gary.ga;

/**
 * CostFunction
 * 
 * Interface for cost functions to implement.
 * 
 * @author Gary Polhill
 * 
 */
public interface CostFunction {

  /**
   * cost
   * 
   * Function returning the cost of a gene (population member)
   * 
   * @param gene The member of the population to compute the cost of
   * @return The cost
   */
  double cost(Gene gene);

}
