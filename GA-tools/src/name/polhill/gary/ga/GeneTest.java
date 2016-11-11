/* name.polhill.gary.ga: GeneTest.java
 * Copyright (C) 2008  Macaulay Institute
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
 * Contact information:
 *   Gary Polhill
 *   Macaulay Institute, Craigiebuckler, Aberdeen. AB15 8QH. UK.
 *   g.polhill@macaulay.ac.uk
 */
package name.polhill.gary.ga;

import java.math.BigInteger;

import junit.framework.TestCase;

/**
 * GeneTest
 * @author Gary Polhill
 *
 */
public class GeneTest extends TestCase {

  /**
   * @param name
   */
  public GeneTest(String name) {
    super(name);
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#crossover(name.polhill.gary.ga.Gene, int)}.
   */
  public void testCrossover() {
    Gene gene1 = new Gene(20);
    assertEquals("00000000000000000000", gene1.toString());
    Gene gene2 = new Gene(20, new BigInteger("11111111111111111111", 2));
    Gene gene3 = gene1.crossover(gene2, 10);
    Gene gene4 = gene2.crossover(gene1, 10);
    assertEquals("00000000000000000000", gene1.toString());
    assertEquals("11111111111111111111", gene2.toString());
    assertEquals("11111111110000000000", gene3.toString());
    assertEquals("00000000001111111111", gene4.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#mutate(double)}.
   */
  public void testMutate() {
    Gene gene1 = new Gene(20, new BigInteger("0000000000000000000", 2));
    Gene gene2 = gene1.mutate(1.0);
    assertFalse(gene1.toString().equals(gene2.toString()));
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#and(name.polhill.gary.ga.Gene)}.
   */
  public void testAnd() {
    Gene gene1 = new Gene(4, new BigInteger("0011", 2));
    Gene gene2 = new Gene(4, new BigInteger("0101", 2));
    Gene gene3 = gene1.and(gene2);
    assertEquals("0001", gene3.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#xor(name.polhill.gary.ga.Gene)}.
   */
  public void testXor() {
    Gene gene1 = new Gene(4, new BigInteger("0011", 2));
    Gene gene2 = new Gene(4, new BigInteger("0101", 2));
    Gene gene3 = gene1.xor(gene2);
    assertEquals("0110", gene3.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#rsh()}.
   */
  public void testRsh() {
    Gene gene1 = new Gene(20, new BigInteger("11111111111111111111", 2));
    Gene gene2 = gene1.rsh().rsh().rsh();
    assertEquals("00011111111111111111", gene2.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#grayCode()}.
   */
  public void testGrayCode() {
    Gene gene1 = new Gene(20, new BigInteger("87315", 10));
    Gene gene2 = gene1.grayCode();
    Gene gene3 = gene2.inverseGrayCode();
    assertEquals(gene1.toString(), gene3.toString());
    Gene gene4 = (new Gene(20, new BigInteger("87316", 10))).grayCode();
    assertEquals(1, gene4.xor(gene2).nOnes());
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#setBit(int, boolean)}.
   */
  public void testSetBit() {
    Gene gene1 = new Gene(20, new BigInteger("00000000000000000000", 2));
    gene1.setBit(10, true);
    assertEquals("00000000010000000000", gene1.toString());
    gene1.setBit(10, false);
    assertEquals("00000000000000000000", gene1.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#getBit(int)}.
   */
  public void testGetBit() {
    Gene gene1 = new Gene(20, new BigInteger("01010101010101010101", 2));
    for(int i = 0; i < gene1.length; i++) {
      if(i % 2 == 0) assertTrue(gene1.getBit(i));
      else assertFalse(gene1.getBit(i));
    }
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#nOnes()}.
   */
  public void testNOnes() {
    Gene gene1 = new Gene(20, new BigInteger("00010001000100010001", 2));
    assertEquals(5, gene1.nOnes());
  }

  /**
   * Test method for {@link name.polhill.gary.ga.Gene#nZeros()}.
   */
  public void testNZeros() {
    Gene gene1 = new Gene(20, new BigInteger("00010001000100010001", 2));
    assertEquals(15, gene1.nZeros());
  }

}
