/*
 * name.polhill.gary.ga: Cost.java Copyright (C) 2009 Macaulay Institute
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
 * <!-- Cost -->
 * 
 * Interface that Costs must all follow. The Cost is a generic and possibly
 * multidimensional measure.
 * 
 * @author Gary Polhill
 */
public interface Cost extends Comparable<Cost>, Cloneable {
  /**
   * <!-- comparableWith -->
   * 
   * Can this Cost be compared with the other?
   * 
   * @param other
   * @return
   */
  public boolean comparableWith(Cost other);

  /**
   * <!-- compareTo -->
   * 
   * Compare this Cost with another. This should be done in conjunction with the
   * comparableWith method to check that the Costs can be compared.
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Cost other);

  /**
   * <!-- clone -->
   * 
   * Copy the Cost
   * 
   * @return
   */
  public Cost clone();
}
