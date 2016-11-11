/**
 * AbstractGene.java, name.polhill.gary.mcga
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
 * <!-- AbstractGene -->
 *
 * @author Gary Polhill
 *
 */
public abstract class AbstractGene implements Gene {

  @Override
  public double difference(Gene other) {
    if((this instanceof WildCardGene) || (other instanceof WildCardGene)) {
      return 0.0;
    }
    else if(this.getClass().equals(other.getClass())) {
      return differenceSameClass((AbstractGene)other);
    }
    else {
      return 1.0;
    }
  }
  
  protected abstract double differenceSameClass(AbstractGene other);
  
  @Override
  public abstract AbstractGene clone();

}
