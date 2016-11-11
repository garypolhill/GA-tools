/**
 * DoubleCost.java, name.polhill.gary.mcga
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
package name.polhill.gary.mcga;

/**
 * <!-- DoubleCost -->
 * 
 * A cost class containing a single number
 *
 * @author Gary Polhill
 *
 */
public class DoubleCost implements Cost {
  private final double cost;

  /**
   * <!-- DoubleCost constructor -->
   *
   */
  public DoubleCost(double cost) {
    this.cost = cost;
  }

  /**
   * <!-- comparableWith -->
   *
   * @see name.polhill.gary.mcga.Cost#comparableWith(name.polhill.gary.mcga.Cost)
   */
  @Override
  public boolean comparableWith(Cost other) {
    return other instanceof DoubleCost ? true : false;
  }

  /**
   * <!-- compareTo -->
   *
   * @see name.polhill.gary.mcga.Cost#compareTo(name.polhill.gary.mcga.Cost)
   */
  @Override
  public int compareTo(Cost other) {
    if(!(other instanceof DoubleCost)) return 0;
    return Double.compare(cost, ((DoubleCost)other).cost);
  }

  
  /**
   * <!-- clone -->
   *
   * @see java.lang.Object#clone()
   */
  @Override
  public DoubleCost clone() {
    return new DoubleCost(cost);
  }
  
  /**
   * <!-- toString -->
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return Double.toString(cost);
  }
  
  /**
   * <!-- getValue -->
   *
   * @return the value
   */
  public double getValue() {
    return cost;
  }
}
