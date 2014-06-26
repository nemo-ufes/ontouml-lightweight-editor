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
package br.ufes.inf.nemo.oled.draw;

import java.awt.geom.Point2D;


/**
 * This class implements a single undoable operation to move a node.
 *
 * @author Wei-ju Wu
 */
public class MoveNodeOperation extends MoveOperation {

	private static final long serialVersionUID = -3369271938444554896L;
	private Node node;
	private CompositeNode newParent, originalParent;
	private Point2D targetPos;
	private Point2D originalPos;

	/**
	 * Constructor.
	 * @param aNode the node
	 * @param aNewParent the new parent
	 * @param aTargetPos the target position
	 */
	public MoveNodeOperation(Node aNode, CompositeNode aNewParent,
			Point2D aTargetPos) {
		node = aNode;
		newParent = aNewParent;
		targetPos = aTargetPos;
		originalParent = node.getParent();
		originalPos = new Point2D.Double(node.getAbsoluteX1(),
				node.getAbsoluteY1());
	}

	public Node getNode() {
		return node;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		if(originalParent != newParent) //Changed by aalbuquerque in 04/09/2011
		{
			if (originalParent != null) originalParent.removeChild(node);
			if (newParent != null) newParent.addChild(node);
		}
		
		node.setAbsolutePos(targetPos.getX(), targetPos.getY());
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		newParent.removeChild(node);
		originalParent.addChild(node);
		node.setAbsolutePos(originalPos.getX(), originalPos.getY());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		run();
	}
}
