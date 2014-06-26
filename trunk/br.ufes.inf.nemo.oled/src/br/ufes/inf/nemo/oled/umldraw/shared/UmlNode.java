/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.umldraw.shared;

import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.model.RelationEndType;
import br.ufes.inf.nemo.oled.model.RelationType;

/**
 * A specialized interface that is used for Node allElements that have an
 * associated UML model element.
 * 
 * @author Wei-ju Wu
 */
public interface UmlNode extends Node, UmlDiagramElement {

  /**
   * Determines whether this Node accepts a connection of the specified type
   * with the specified node and the specified end type. The <i>with</i>
   * parameter can be omitted.
   * @param associationType the AssociationType for the connection
   * @param as the association end type, can be UNSPECIFIED, SOURCE OR TARGET
   * @param with the other end node if available
   * @return true if connection is accepted, false otherwise
   */
  boolean acceptsConnection(RelationType associationType,
                            RelationEndType as, UmlNode with);
}
