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
package name.polhill.gary.mcga.gzga;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import name.polhill.gary.mcga.Chromosome;
import name.polhill.gary.mcga.Cost;
import name.polhill.gary.mcga.CostFunction;
import name.polhill.gary.mcga.DoubleArrayCost;

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

  /**
   * <!-- cost -->
   *
   * @see name.polhill.gary.mcga.CostFunction#cost(name.polhill.gary.mcga.Chromosome)
   */
  @Override
  public Cost[] cost(Chromosome gene) {
    ByteArrayOutputStream gzip = new ByteArrayOutputStream();
    ByteArrayOutputStream zip[] = new ByteArrayOutputStream[Deflater.BEST_COMPRESSION];
    for(int i = 0; i < zip.length; i++) {
      zip[i] = new ByteArrayOutputStream();
    }
    
    try {
      List<Double> list = new LinkedList<Double>();
      
      GZIPOutputStream gz = new GZIPOutputStream(gzip);
      for(int j = 0; j < gene.nGenes(); j++) {
	gz.write(gene.geneAt(j).toByteArray());
      }
      gz.close();
      
      list.add((double)gzip.size());
      
      for(int i = 0; i < zip.length; i++) {
	ZipOutputStream z = new ZipOutputStream(zip[i]);
	
	z.setLevel(i);
	
	for(int j = 0; j < gene.nGenes(); j++) {
	  z.putNextEntry(new ZipEntry(Integer.toString(j + 1)));
	  z.write(gene.geneAt(j).toByteArray());
	}
	z.close();
	
	list.add((double)zip[i].size());
      }
      
      return new Cost[] { new DoubleArrayCost(list.toArray(new Double[0])) };
    }
    catch(IOException e) {
      throw new Error(e);
    }
  }

}
