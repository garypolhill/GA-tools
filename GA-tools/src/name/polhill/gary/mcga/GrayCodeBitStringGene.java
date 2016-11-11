/*
 * name.polhill.gary.ga: GrayCodeBitStringGene.java Copyright (C) 2009 Macaulay
 * Institute
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

import java.math.BigInteger;

/**
 * GrayCodeBitStringGene
 * 
 * A Gene class implementing a BitStringGene that encodes an integer using Gray
 * coding, which avoids Hamming cliffs in mutations.
 * 
 * @author Gary Polhill
 */
public class GrayCodeBitStringGene extends BitStringGene {
  /**
   * Constructor to create an empty bitstring
   * @param size
   */
  public GrayCodeBitStringGene(int size) {
    super(size);
  }

  /**
   * Constructor to create a bitstring with specified data
   * @param size
   * @param data
   */
  public GrayCodeBitStringGene(int size, BigInteger data) {
    super(size, data);
  }

  /**
   * Constructor to create a bitstring with specified data
   * @param size
   * @param data
   */
  public GrayCodeBitStringGene(int size, byte[] data) {
    super(size, data);
  }

  /**
   * <!-- GrayCodeBitStringGene constructor -->
   * 
   * Constructor to create a bitstring as a copy of another
   *
   * @param other
   */
  public GrayCodeBitStringGene(BitStringGene other) {
    this(other.length, other.gene);
  }

  /**
   * <!-- grayCode -->
   * 
   * Gray code this gene. Gray code is an encoding of binary numbers such that
   * any two successive integers differ by one bit.
   * 
   * @return The result of Gray coding this gene
   */
  public BitStringGene grayCode() {
    return xor(rsh());
  }

  /**
   * <!-- inverseGrayCode -->
   * 
   * Inverse-Gray code this gene. Return a normally formatted binary number from
   * a Gray-coded gene.
   * 
   * @return The result of inverse-Gray coding this gene
   */
  public BitStringGene inverseGrayCode() {
    BitStringGene decode = new BitStringGene(length);
    decode.setBit(length - 1, getBit(length - 1));
    for(int i = length - 2; i >= 0; i--) {
      decode.setBit(i, decode.getBit(i + 1) ^ getBit(i));
    }
    return decode;
  }
 
  public GrayCodeBitStringGene clone() {
    return new GrayCodeBitStringGene(this);
  }
}
