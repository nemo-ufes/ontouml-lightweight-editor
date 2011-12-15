/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.inf.ufes.nemo.oled.umldraw.shared;

import br.inf.ufes.nemo.oled.draw.DiagramOperations;
import br.inf.ufes.nemo.oled.draw.Node;
import br.inf.ufes.nemo.oled.draw.NodeSelection;


/**
 * This class implements a DiagramSelection. Diagrams can be resized, but
 * not moved.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class DiagramSelection extends NodeSelection {

  /**
   * Constructor.
   * @param anEditor the editor
   * @param aNode the node
   */
  public DiagramSelection(DiagramOperations anEditor, Node aNode) {
    super(anEditor, aNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void moveSelection(double diffx, double diffy) { }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resizeNw(double diffx, double diffy) { }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resizeNe(double diffx, double diffy) { }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resizeSw(double diffx, double diffy) { }
}
