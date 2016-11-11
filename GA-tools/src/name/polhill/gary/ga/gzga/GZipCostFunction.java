/* name.polhill.gary.gzga: GZipCostFunction.java
 * 
 * Copyright (C) 2008  Gary Polhill
 *
 * This file is part of GA.
 *
 * GA is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 *
 * GA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with GA. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 */
package name.polhill.gary.ga.gzga;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import name.polhill.gary.ga.CostFunction;
import name.polhill.gary.ga.Gene;

/**
 * GZipCostFunction
 * 
 * Cost function based on the number of bytes in a compression of the bitstring
 * using the gzip compression algorithm
 * 
 * @author Gary Polhill
 *
 */
public class GZipCostFunction implements CostFunction {

  /* (non-Javadoc)
   * @see name.polhill.gary.ga.CostFunction#cost(name.polhill.gary.ga.Gene)
   */
  public double cost(Gene gene) {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try {
      GZIPOutputStream zip = new GZIPOutputStream(output);
      zip.write(gene.gene.toByteArray());
      zip.close();
      return (double)output.size();
    }
    catch(IOException e) {
      throw new Error(e);
    }
  }

}
