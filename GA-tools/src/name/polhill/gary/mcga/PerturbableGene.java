/**
 * PerturbableGene.java, name.polhill.gary.mcga
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

import java.util.Random;

/**
 * <!-- PerturbableGene -->
 * 
 * Genes that can be perturbed using a normal distribution
 * 
 * @author Gary Polhill
 * 
 */
public interface PerturbableGene extends Gene {

  /**
   * <!-- perturb -->
   * 
   * Perturb the gene using a distribution with the specified variance
   * 
   * @param probability
   * @param variance
   * @return perturbed Gene
   */
  public Gene perturb(double probability, double variance);

  /**
   * <!-- PerturbableGene -->
   * 
   * Class containing tools to help with perturbation
   * 
   * @author Gary Polhill
   */
  public class Tools {
    private static Random rng = new Random();
    public static final int RESAMPLE_MAX = 100;

    private Tools() {
      // empty constructor
    }

    public static double sampleNormal(double variance) {
      return rng.nextGaussian() * Math.sqrt(variance);
    }
    
    public static double resampleTruncatedNormal(double variance, double minimum, double maximum, double fallback) {
      return resampleTruncatedNormal(variance, minimum, maximum, fallback, RESAMPLE_MAX);
    }
    
    public static double resampleTruncatedNormal(double variance, double minimum, double maximum, double fallback, int max) {
      if(minimum > maximum) {
	throw new IllegalArgumentException("Minimum " + minimum + " is more than maximum " + maximum);
      }
      for(int i = 0; i < max; i++) {
	double sample = sampleNormal(variance);
	if(sample >= minimum && sample <= maximum) return sample;
      }
      return fallback;
    }

    public static double sampleTruncatedNormal(double variance, double minimum, double maximum) {
      if(minimum > maximum) {
	throw new IllegalArgumentException("Minimum " + minimum + " is more than maximum " + maximum);
      }
      double sample = sampleNormal(variance);
      if(sample < minimum) sample = minimum;
      if(sample > maximum) sample = maximum;
      return sample;
    }
  }
}
