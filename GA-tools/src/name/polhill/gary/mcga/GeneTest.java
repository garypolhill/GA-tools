/*
 * name.polhill.gary.ga: GeneTest.java Copyright (C) 2008 Macaulay Institute
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
 * Contact information: Gary Polhill Macaulay Institute, Craigiebuckler,
 * Aberdeen. AB15 8QH. UK. g.polhill@macaulay.ac.uk
 */
package name.polhill.gary.mcga;

import java.math.BigInteger;

import junit.framework.TestCase;

/**
 * GeneTest
 * 
 * Test for the BitStringGene class
 * 
 * @author Gary Polhill
 */
public class GeneTest extends TestCase {

  /**
   * @param name
   */
  public GeneTest(String name) {
    super(name);
  }

  /**
   * Test method for
   * {@link name.polhill.gary.mcga.BitStringGene#crossover(name.polhill.gary.mcga.BitStringGene, int)}
   * .
   */
  public void testCrossover() {
    BitStringGene gene1 = new BitStringGene(20);
    assertEquals("00000000000000000000", gene1.toString());
    BitStringGene gene2 =
      new BitStringGene(20, new BigInteger("11111111111111111111", 2));
    BitStringGene gene3 = gene1.crossover(gene2, 10);
    BitStringGene gene4 = gene2.crossover(gene1, 10);
    assertEquals("00000000000000000000", gene1.toString());
    assertEquals("11111111111111111111", gene2.toString());
    assertEquals("11111111110000000000", gene3.toString());
    assertEquals("00000000001111111111", gene4.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.mcga.BitStringGene#mutate(double)}.
   */
  public void testMutate() {
    BitStringGene gene1 =
      new BitStringGene(20, new BigInteger("0000000000000000000", 2));
    BitStringGene gene2 = gene1.mutate(1.0);
    assertFalse(gene1.toString().equals(gene2.toString()));
  }

  /**
   * Test method for
   * {@link name.polhill.gary.mcga.BitStringGene#and(name.polhill.gary.mcga.BitStringGene)}
   * .
   */
  public void testAnd() {
    BitStringGene gene1 = new BitStringGene(4, new BigInteger("0011", 2));
    BitStringGene gene2 = new BitStringGene(4, new BigInteger("0101", 2));
    BitStringGene gene3 = gene1.and(gene2);
    assertEquals("0001", gene3.toString());
  }

  /**
   * Test method for
   * {@link name.polhill.gary.mcga.BitStringGene#xor(name.polhill.gary.mcga.BitStringGene)}
   * .
   */
  public void testXor() {
    BitStringGene gene1 = new BitStringGene(4, new BigInteger("0011", 2));
    BitStringGene gene2 = new BitStringGene(4, new BigInteger("0101", 2));
    BitStringGene gene3 = gene1.xor(gene2);
    assertEquals("0110", gene3.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.mcga.BitStringGene#rsh()}.
   */
  public void testRsh() {
    BitStringGene gene1 =
      new BitStringGene(20, new BigInteger("11111111111111111111", 2));
    BitStringGene gene2 = gene1.rsh().rsh().rsh();
    assertEquals("00011111111111111111", gene2.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.mcga.BitStringGene#grayCode()}.
   */
  public void testGrayCode() {
    GrayCodeBitStringGene gene1 =
      new GrayCodeBitStringGene(20, new BigInteger("87315", 10));
    GrayCodeBitStringGene gene2 = new GrayCodeBitStringGene(gene1.grayCode());
    BitStringGene gene3 = gene2.inverseGrayCode();
    assertEquals(gene1.toString(), gene3.toString());
    BitStringGene gene4 =
      (new GrayCodeBitStringGene(20, new BigInteger("87316", 10))).grayCode();
    assertEquals(1, gene4.xor(gene2).nOnes());
  }

  /**
   * Test method for
   * {@link name.polhill.gary.mcga.BitStringGene#setBit(int, boolean)}.
   */
  public void testSetBit() {
    BitStringGene gene1 =
      new BitStringGene(20, new BigInteger("00000000000000000000", 2));
    gene1.setBit(10, true);
    assertEquals("00000000010000000000", gene1.toString());
    gene1.setBit(10, false);
    assertEquals("00000000000000000000", gene1.toString());
  }

  /**
   * Test method for {@link name.polhill.gary.mcga.BitStringGene#getBit(int)}.
   */
  public void testGetBit() {
    BitStringGene gene1 =
      new BitStringGene(20, new BigInteger("01010101010101010101", 2));
    for(int i = 0; i < gene1.length; i++) {
      if(i % 2 == 0) assertTrue(gene1.getBit(i));
      else
        assertFalse(gene1.getBit(i));
    }
  }

  /**
   * Test method for {@link name.polhill.gary.mcga.BitStringGene#nOnes()}.
   */
  public void testNOnes() {
    BitStringGene gene1 =
      new BitStringGene(20, new BigInteger("00010001000100010001", 2));
    assertEquals(5, gene1.nOnes());
  }

  /**
   * Test method for {@link name.polhill.gary.mcga.BitStringGene#nZeros()}.
   */
  public void testNZeros() {
    BitStringGene gene1 =
      new BitStringGene(20, new BigInteger("00010001000100010001", 2));
    assertEquals(15, gene1.nZeros());
  }

}
