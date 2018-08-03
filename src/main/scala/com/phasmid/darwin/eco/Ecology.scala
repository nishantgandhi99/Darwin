/*
 * DARWIN Genetic Algorithms Framework Project.
 * Copyright (c) 2003, 2005, 2007, 2009, 2011, 2016, 2017. Phasmid Software
 *
 * Originally, developed in Java by Rubecula Software, LLC and hosted by SourceForge.
 * Converted to Scala by Phasmid Software and hosted by github at https://github.com/rchillyard/Darwin
 *
 *      This file is part of Darwin.
 *
 *      Darwin is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.phasmid.darwin.eco

import com.phasmid.darwin.Ecological
import com.phasmid.darwin.base._
import com.phasmid.darwin.genetics._
import com.phasmid.laScala.fp.FP.sequence
import com.phasmid.laScala.{OldRenderableCaseClass, Prefix}

/**
  * Created by scalaprof on 5/9/16.
  *
  * An ecology in which organisms can adapt and thrive (or not as the case may be).
  *
  * @param name        name by which to refer to this Ecology
  * @param factors     the factors present in this Ecology
  * @param fitnessFunc the fitness function
  * @param adapter     the adapter
  * @tparam T the trait type
  * @tparam X the eco-type
  */
case class Ecology[T, X](name: String, factors: Map[String, Factor], fitnessFunc: FitnessFunction[T, X], adapter: Adapter[T, X]) extends Identifying with Ecological[T, X] with Identifiable {

  /**
    * The apply method for this Ecology. For each Trait in the given Phenotype, we look up its corresponding Factor
    * and invoke the Adapter to create an Adaptation.
    *
    * Note that if the lookup fails, we simply ignore the trait without warning.
    *
    * @param phenotype the phenotype for which we want to measure the adaptation to this ecology
    * @return an Adaptatype
    */
  def apply(phenotype: Phenotype[T]): Adaptatype[X] = {
    val xats = for (t <- phenotype.traits; f <- factors.get(t.characteristic.name)) yield for (a <- adapter(f, t, fitnessFunc)) yield a
    Adaptatype(IdentifierStrUID("at", UID(phenotype.id)), sequence(xats).get)
  }

  override def toString: String = s"Ecology($name, $factors, $fitnessFunc, $adapter"

  override def render(indent: Int)(implicit tab: (Int) => Prefix): String = CaseIdentifiable.renderAsCaseClass(this.asInstanceOf[Ecology[Any, Any]])(indent)

}

case class Factor(name: String) extends Identifiable {
  override def render(indent: Int = 0)(implicit tab: (Int) => Prefix): String = if (indent > 0) name
  else OldRenderableCaseClass(this).render(indent)(tab)
}

