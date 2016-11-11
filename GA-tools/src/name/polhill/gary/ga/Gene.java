/*
 * name.polhill.gary.ga: Gene.java
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

import java.math.BigInteger;
import java.util.Random;

/**
 * Gene
 * 
 * A gene class using BigInteger to store the genome. A better implementation
 * would abstract the Gene class from the GA too (not all GAs encode solutions
 * using bitstrings).
 * 
 * @author Gary Polhill
 * 
 */
public class Gene implements Cloneable {
  public BigInteger gene;
  public int length;
  Random randomiser;

  /**
   * Constructor using a specified size. The gene will be initialised to zero.
   * 
   * @param size The size of the gene.
   */
  public Gene(int size) {
    length = size;
    randomiser = new Random();
    gene = new BigInteger(size, randomiser);
    gene = gene.multiply(BigInteger.ZERO);
  }

  /**
   * Constructor using a specified size and initial data value
   * 
   * @param size The size of the gene
   * @param data The data to store in it
   */
  public Gene(int size, byte[] data) {
    this(size);
    gene = gene.or(new BigInteger(data));
  }

  /**
   * Constructor using a specified size and BigInteger initial data value
   * 
   * @param size The size of the gene
   * @param data The data to store in it
   */
  public Gene(int size, BigInteger data) {
    this(size);
    gene = gene.or(data);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#clone()
   */
  public Gene clone() {
    return new Gene(length, gene);
  }

  /**
   * randomise
   * 
   * Randomise the contents of the gene with equal probability of each bit being
   * set to 1 or 0.
   */
  public void randomise() {
    gene = new BigInteger(length, randomiser);
  }

  /**
   * crossover
   * 
   * Return a gene that is the crossover of this gene with the argument at the
   * specified point
   * 
   * @param other The gene to crossover this gene with
   * @param point The point at which to cross over
   * @return The resulting gene
   */
  public Gene crossover(Gene other, int point) {
    BigInteger crossover = new BigInteger(gene.toByteArray());
    for(int i = point; i < other.length; i++) {
      if(other.gene.testBit(i)) crossover = crossover.setBit(i);
      else
        crossover = crossover.clearBit(i);
    }
    return new Gene(length, crossover);
  }

  /**
   * mutate
   * 
   * Return a gene that is a mutation of this one. Each bit has a pMutate
   * probability of being set to a <i>random</i> value. Thus the expected
   * number of bits that differ between this gene and the returned gene is
   * pMutate * length / 2.
   * 
   * @param pMutate The mutation probability of each bit
   * @return The result of the mutation
   */
  public Gene mutate(double pMutate) {
    BigInteger mutation = new BigInteger(gene.toByteArray());
    for(int i = 0; i < length; i++) {
      if(randomiser.nextDouble() < pMutate) {
        if(randomiser.nextBoolean()) mutation = mutation.setBit(i);
        else
          mutation = mutation.clearBit(i);
      }
    }
    return new Gene(length, mutation);
  }

  /**
   * and
   * 
   * Return a bitwise AND of this gene with the argument
   * 
   * @param other The other gene to AND this gene with
   * @return The bitwise AND of this gene with the argument
   */
  public Gene and(Gene other) {
    BigInteger and = new BigInteger(gene.toByteArray());
    return new Gene(length, and.and(other.gene));
  }

  /**
   * xor
   * 
   * Return a bitwise XOR of this gene with the argument
   * 
   * @param other The other gene to XOR this gene with
   * @return The bitwise XOR of this gene with the argument
   */
  public Gene xor(Gene other) {
    BigInteger xor = new BigInteger(gene.toByteArray());
    return new Gene(length, xor.xor(other.gene));
  }

  /**
   * rsh
   * 
   * Right shift the bits by one bit
   * 
   * @return The resulting gene
   */
  public Gene rsh() {
    return rsh(1);
  }

  /**
   * rsh
   * 
   * Return the result of right-shifting the bits in this gene by the specified
   * number of bits
   * 
   * @param i The number of bits by which to shift
   * @return The resulting gene
   */
  public Gene rsh(int i) {
    BigInteger rsh = new BigInteger(gene.toByteArray());
    return new Gene(length, rsh.shiftRight(i));
  }

  /**
   * grayCode
   * 
   * Gray code this gene. Gray code is an encoding of binary numbers such that any
   * two successive integers differ by one bit.
   *
   * @return The result of Gray coding this gene
   */
  public Gene grayCode() {
    return xor(rsh());
  }
  
  /**
   * inverseGrayCode
   * 
   * Inverse-Gray code this gene. Return a normally formatted binary number from
   * a Gray-coded gene.
   *
   * @return The result of inverse-Gray coding this gene
   */
  public Gene inverseGrayCode() {
    Gene decode = new Gene(length);
    decode.setBit(length - 1, getBit(length - 1));
    for(int i = length - 2; i >= 0; i--) {
      decode.setBit(i, decode.getBit(i + 1) ^ getBit(i));
    }
    return decode;
  }

  /**
   * setBit
   * 
   * Set the specified bit of this gene to the value given
   *
   * @param bit The bit to set (0..length - 1)
   * @param value The value to set it to (true == 1; false == 0)
   */
  public void setBit(int bit, boolean value) {
    if(value) gene = gene.setBit(bit);
    else
      gene = gene.clearBit(bit);
  }
  
  /**
   * getBit
   *
   * @param bit The bit to get (0..length - 1)
   * @return The value of that bit
   */
  public boolean getBit(int bit) {
    return gene.testBit(bit);
  }

  /**
   * nOnes
   *
   * @return The number of ones in this bitstring
   */
  public int nOnes() {
    int ones = 0;
    for(int i = 0; i < length; i++) {
      if(gene.testBit(i)) ones++;
    }
    return ones;
  }

  /**
   * nZeros
   *
   * @return The number of zeros in this bitstring
   */
  public long nZeros() {
    int zeros = 0;
    for(int i = 0; i < length; i++) {
      if(!gene.testBit(i)) zeros++;
    }
    return zeros;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString() {
    StringBuffer geneStr = new StringBuffer(gene.toString(2));
    while(geneStr.length() < length) {
      geneStr = (new StringBuffer("0")).append(geneStr);
    }
    return geneStr.toString();
  }
}
