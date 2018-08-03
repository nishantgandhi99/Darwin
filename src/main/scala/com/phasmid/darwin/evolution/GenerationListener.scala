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

package com.phasmid.darwin.evolution

/**
  * Created by scalaprof on 7/26/17.
  *
  * @tparam Z the underlying type of evolvable organisms
  */
trait GenerationListener[Z] {
  /**
    * This method is called whenever the
    * {@link Evolvable} implementer
    * completes a new generation or when the
    * {@link Evolution} itself is
    * completely exhausted (has no more evolvables to work with).
    *
    * @param evolvable
    * the implementer of { @link Evolvable} which has completed a
    * generation or null if the { @link Evolution} itself has
    * completed.
    *
    * NOTE: implementers of this method must be prepared to accept
    * null as the parameter.
    */
  def onGeneration(evolvable: Evolvable[Z]): Unit

}
