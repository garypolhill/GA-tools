/**
 * AbstractPerturbableChromosome.java, name.polhill.gary.mcga
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <!-- AbstractPerturbableChromosome -->
 * 
 * Provide a default implementation for perturbable {@link Chromosome}s
 * (perturbable Chromosomes are defined as those containing perturbable Genes)
 * -- though at the cost of having to implement a constructor taking an array of
 * {@link Gene}s as argument.
 * 
 * @author Gary Polhill
 */
public abstract class AbstractPerturbableChromosome extends AbstractChromosome {

  protected AbstractPerturbableChromosome(Gene genes[]) {
    // deliberately blank -- subclasses will have to override to handle
    // initialisation from an array of genes
  }

  private static <T extends AbstractPerturbableChromosome> Chromosome createChromosome(T other, Gene genes[]) {
    try {
      Class<? extends AbstractPerturbableChromosome> chromosomeClass = other.getClass();
      Constructor<? extends AbstractPerturbableChromosome> constructor = chromosomeClass.getConstructor(Gene[].class);
      AbstractPerturbableChromosome instance = constructor.newInstance((Object[])genes);

      instance.initialiseFrom(other);

      return (Chromosome)constructor.newInstance((Object[])genes);
    }
    catch(SecurityException e) {
      throw new RuntimeException(e);
    }
    catch(NoSuchMethodException e) {
      // Since the constructor is explicitly declared above, this shouldn't ever
      // happen
      throw new RuntimeException("Panic!");
    }
    catch(IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
    catch(InstantiationException e) {
      throw new RuntimeException(e);
    }
    catch(IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    catch(InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * <!-- initialiseFrom -->
   * 
   * This method can be used by subclasses to initialise other variables in the
   * chromosome if there are any besides the gene array
   * 
   * @param other
   */
  protected abstract <T extends AbstractPerturbableChromosome> void initialiseFrom(T other);

  /**
   * <!-- perturbable -->
   * 
   * Return whether or not the chromosome has any perturbable genes
   * 
   * @see name.polhill.gary.mcga.AbstractChromosome#perturbable()
   */
  @Override
  public boolean perturbable() {
    int nGenes = nGenes();

    for(int i = 0; i < nGenes; i++) {
      if(geneAt(i) instanceof PerturbableGene) {
	return true;
      }
    }
    return false;
  }

  /**
   * <!-- perturb -->
   * 
   * Perturb the genes. If none are perturbable, this will simply return a
   * clone.
   * 
   * @see name.polhill.gary.mcga.AbstractChromosome#perturb(double, double)
   */
  @Override
  public Chromosome perturb(double pPerturb, double perturbVar) {
    Gene genes[] = new Gene[nGenes()];

    int nGenes = nGenes();

    for(int i = 0; i < nGenes; i++) {
      if(geneAt(i) instanceof PerturbableGene) {
	genes[i] = ((PerturbableGene)geneAt(i)).perturb(pPerturb, perturbVar);
      }
      else {
	genes[i] = geneAt(i).clone();
      }
    }

    return createChromosome(this, genes);
  }
}
