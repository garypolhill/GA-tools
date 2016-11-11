/**
 * DoubleArrayCost.java, name.polhill.gary.mcga
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
 * <!-- DoubleArrayCost -->
 * 
 * A cost that is an array of numbers.
 * 
 * @author Gary Polhill
 */
public class DoubleArrayCost implements Cost {
  private final List<Double> values;

  /**
   * <!-- DoubleArrayCost constructor -->
   * 
   */
  public DoubleArrayCost(Double... values) {
    this.values = Collections.unmodifiableList(Arrays.asList(values));
  }

  /**
   * <!-- comparableWith -->
   * 
   * @see name.polhill.gary.mcga.Cost#comparableWith(name.polhill.gary.mcga.Cost)
   */
  @Override
  public boolean comparableWith(Cost other) {
    if(other instanceof DoubleArrayCost && ((DoubleArrayCost)other).values.size() == values.size()) {
      Double[] other_values = ((DoubleArrayCost)other).values.toArray(new Double[0]);
      Double[] this_values = values.toArray(new Double[0]);

      int dir = 0;
      for(int i = 0; i < this_values.length; i++) {
	if(dir == 0) dir = Double.compare(this_values[i], other_values[i]);
	else if(dir * Double.compare(this_values[i], other_values[i]) < 0) return false;
      }
      
      return true;
    }
    else if(other instanceof DoubleCost && values.size() == 1) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * <!-- compareTo -->
   * 
   * @see name.polhill.gary.mcga.Cost#compareTo(name.polhill.gary.mcga.Cost)
   */
  @Override
  public int compareTo(Cost other) {
    if(other instanceof DoubleArrayCost && ((DoubleArrayCost)other).values.size() == values.size()) {
      int dir = 0;
      
      for(int i = 0; i < values.size(); i++) {
	int new_dir = Double.compare(values.get(i), ((DoubleArrayCost)other).values.get(i));
	if(dir == 0) dir = new_dir;
	else if(dir * new_dir < 0) return 0;
      }
      
      return dir;
    }
    else if(other instanceof DoubleCost && values.size() == 1) {
      return Double.compare(values.get(0), ((DoubleCost)other).getValue());
    }
    else {
      return 0;
    }
  }

  @Override
  public DoubleArrayCost clone() {
    return new DoubleArrayCost(values.toArray(new Double[0]));
  }
  
  public String toString() {
    StringBuffer buff = new StringBuffer("[");
    
    for(Double value: values) {
      buff.append(" ");
      buff.append(value);
    }
    
    buff.append(" ]");
    
    return buff.toString();
  }
}
