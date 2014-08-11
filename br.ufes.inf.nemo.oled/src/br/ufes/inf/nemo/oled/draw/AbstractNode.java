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

import java.awt.geom.Dimension2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;

/**
 * This class implements an abstract Node class.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque
 */
public abstract class AbstractNode implements Node {

	private static final long serialVersionUID = -8643725646833660531L;
	protected Point2D origin = new Point2D.Double(0, 0);
	private Dimension2D size = new DoubleDimension(40, 40);
	private Dimension2D minimumSize = new DoubleDimension(40, 40);
	private CompositeNode parent;
	private Collection<NodeChangeListener> changeListeners =
		new ArrayList<NodeChangeListener>();
	private List<Connection> connections = new ArrayList<Connection>();
	private transient NodeSelection selection;

	/**
	 * Writes the instance variables to the stream.
	 * @param stream an ObjectOutputStream
	 * @throws IOException if I/O error occured
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.writeObject(origin);
		stream.writeObject(size);
		stream.writeObject(minimumSize);
		stream.writeObject(parent);
		if (selection != null) changeListeners.remove(selection);
		stream.writeObject(changeListeners);
		if (selection != null) changeListeners.add(selection);
		stream.writeObject(connections);
	}

	/**
	 * Reset the transient values for serialization.
	 * @param stream an ObjectInputStream
	 * @throws IOException if I/O error occured
	 * @throws ClassNotFoundException if class was not found
	 */
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream stream)
	throws IOException, ClassNotFoundException, InvalidClassException {
		origin = (Point2D) stream.readObject();
		size = (Dimension2D) stream.readObject();
		minimumSize = (Dimension2D) stream.readObject();
		parent = (CompositeNode) stream.readObject();
		changeListeners = (List<NodeChangeListener>) stream.readObject();
		connections = (List<Connection>) stream.readObject();
		selection = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone() {
		try {
			AbstractNode node = (AbstractNode) super.clone();
			node.origin = (Point2D) origin.clone();
			node.size = (Dimension2D) size.clone();
			node.minimumSize = (Dimension2D) minimumSize.clone();
			node.connections = new ArrayList<Connection>();
			node.changeListeners = new ArrayList<NodeChangeListener>();
			// just copy the the parent to avoid the recursion
			node.selection = null; // do not copy the selection
			return node;
		} catch (CloneNotSupportedException ignore) {
			ignore.printStackTrace();
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public CompositeNode getParent() { return parent; }
	
	/**
	 * {@inheritDoc}
	 */
	public void setParent(CompositeNode aParent) { parent = aParent; }

	//Helper method to walk the nodes hierarchy, gathering all parents
	private void getAllParents(Set<CompositeNode> allParents){
		if(parent != null)
		{
			allParents.add(parent);
			((AbstractNode) parent).getAllParents(allParents);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Diagram getDiagram() { 
		Set<CompositeNode> parents = new HashSet<CompositeNode>();
		getAllParents(parents);
		
		for (CompositeNode item : parents) {
			if(item instanceof StructureDiagram)
				return (Diagram) item;
		}
		
		return null; 
	}
	
	/**
	 * {@inheritDoc}
	 */
	 public void addedToDiagram(Diagram diagram) {
	 
	 }
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isAncestor(DiagramElement element) {
		CompositeNode tmp = getParent();
		while (tmp != null) {
			if (tmp == element) return true;
			tmp = tmp.getParent();
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public Point2D getOrigin() { return origin; }

	/**
	 * {@inheritDoc}
	 */
	public void setOrigin(double xpos, double ypos) {
		origin.setLocation(xpos, ypos);
	}

	/**
	 * {@inheritDoc}
	 */
	public double getAbsoluteX1() {
		return parent.getAbsoluteX1() + getOrigin().getX();
	}

	/**
	 * {@inheritDoc}
	 */
	public double getAbsoluteY1() {
		return parent.getAbsoluteY1() + getOrigin().getY();
	}

	/**
	 * {@inheritDoc}
	 */
	public Dimension2D getMinimumSize() {
		return minimumSize;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setMinimumSize(double width, double height) {
		minimumSize.setSize(width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	public Dimension2D getSize() { return size; }

	/**
	 * {@inheritDoc}
	 */
	public void setSize(double w, double h) {
		setSizePlain(w, h);
		invalidate();
		notifyNodeResized();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setWidth(double width) {
		setSize(width, getSize().getHeight());
	}

	/**
	 * {@inheritDoc}
	 */
	public void setHeight(double height) {
		setSize(getSize().getWidth(), height);
	}

	/**
	 * Sets the internal size, without notification and without invalidating
	 * the object.
	 * @param width the width the width
	 * @param height the height the height
	 */
	protected void setSizePlain(double width, double height) {
		size.setSize(width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean contains(double xcoord, double ycoord) {
		double absx = getAbsoluteX1(), absy = getAbsoluteY1();
		return xcoord >= absx && xcoord <= absx + getSize().getWidth() &&
		ycoord >= absy && ycoord <= absy + getSize().getHeight();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setAbsolutePos(double xpos, double ypos) {
		// Only change the values if position was changed
		if (!GeometryUtil.getInstance().equals(xpos, getAbsoluteX1()) ||
				!GeometryUtil.getInstance().equals(ypos, getAbsoluteY1())) {
			origin.setLocation(xpos - parent.getAbsoluteX1(),
					ypos - parent.getAbsoluteY1());
			notifyNodeMoved();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public double getAbsCenterX() {
		return getAbsoluteX1() + getSize().getWidth() / 2;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getAbsCenterY() {
		return getAbsoluteY1() + getSize().getHeight() / 2;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isVisible(Rectangle2D clipBounds) {
		return clipBounds.intersects(DrawingShapeFactory.getInstance().createRect2d(
				getAbsoluteX1(), getAbsoluteY1(), getSize().getWidth(),
				getSize().getHeight()));
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean intersects(Line2D line) {
		return getAbsoluteBounds().intersectsLine(line);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean intersects(Rectangle2D bounds) {
		return getAbsoluteBounds().intersects(bounds);
	}

	/**
	 * Returns the absolute bounding box for this node.
	 * @return the absolute bounds for this node
	 */
	public Rectangle2D getAbsoluteBounds() {
		return new Rectangle2D.Double(getAbsoluteX1(), getAbsoluteY1(),
				getSize().getWidth(), getSize().getHeight());
	}

	/**
	 * {@inheritDoc}
	 */
	public void calculateIntersection(Line2D line, Point2D intersectionPoint) {

		// check every of the four sides
		Line2D side = new Line2D.Double();

		// top line
		side.setLine(getAbsoluteX1(), getAbsoluteY1(),
				getAbsoluteX1() + getSize().getWidth(), getAbsoluteY1());
		if (line.intersectsLine(side)) {
			GeometryUtil.getInstance().computeLineIntersection(line, side,
					intersectionPoint);
			return;
		}
		// right line
		side.setLine(getAbsoluteX1() + getSize().getWidth(), getAbsoluteY1(),
				getAbsoluteX1() + getSize().getWidth(),
				getAbsoluteY1() + getSize().getHeight());
		if (line.intersectsLine(side)) {
			GeometryUtil.getInstance().computeLineIntersection(line, side,
					intersectionPoint);
			return;
		}
		// bottom line
		side.setLine(getAbsoluteX1(), getAbsoluteY1() + getSize().getHeight(),
				getAbsoluteX1() + getSize().getWidth(),
				getAbsoluteY1() + getSize().getHeight());
		if (line.intersectsLine(side)) {
			GeometryUtil.getInstance().computeLineIntersection(line, side,
					intersectionPoint);
			return;
		}
		// left line
		side.setLine(getAbsoluteX1(), getAbsoluteY1(), getAbsoluteX1(),
				getAbsoluteY1() + getSize().getHeight());
		if (line.intersectsLine(side)) {
			GeometryUtil.getInstance().computeLineIntersection(line, side,
					intersectionPoint);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Selection getSelection(DiagramOperations operations) {
		if (selection == null) {
			selection = new NodeSelection(operations, this);
		} else {
			selection.updateDimensions();
		}
		return selection;
	}

	/**
	 * A method for testing purposes only. Invocations to the Selection can then
	 * be registered.
	 * @param aSelection the testing selection
	 */
	protected void setSelection(NodeSelection aSelection) {
		selection = aSelection;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getAbsoluteX2() {
		return getAbsoluteX1() + getSize().getWidth();
	}

	/**
	 * {@inheritDoc}
	 */
	public double getAbsoluteY2() {
		return getAbsoluteY1() + getSize().getHeight();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addNodeChangeListener(NodeChangeListener l) {
		changeListeners.add(l);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeNodeChangeListener(NodeChangeListener l) {
		changeListeners.remove(l);
	}

	/**
	 * Returns this object's NodeChangeListeners. Can be overridden.
	 * @return the change listeners
	 */
	protected Collection<NodeChangeListener> getNodeChangeListeners() {
		return changeListeners;
	}

	/**
	 * {@inheritDoc}
	 */
	public void invalidate() { }

	/**
	 * {@inheritDoc}
	 */
	public boolean isValid() { return true; }

	/**
	 * Notifies the listeners that this node has moved.
	 */
	protected void notifyNodeMoved() {
		for (NodeChangeListener l : getNodeChangeListeners()) {
			l.nodeMoved(this);
		}
	}

	/**
	 * Notifies all listeners that this Node was resized.
	 */
	protected void notifyNodeResized() {
		for (NodeChangeListener l : getNodeChangeListeners()) {
			l.nodeResized(this);
		}
	}

	// *************************************************************************
	// ***** Connections
	// ********************

	/**
	 * {@inheritDoc}
	 */
	public Collection<? extends Connection> getConnections() {
		return connections;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addConnection(Connection conn) { connections.add(conn); }

	/**
	 * {@inheritDoc}
	 */
	public void removeConnection(Connection conn) { connections.remove(conn); }

	// *************************************************************************
	// ***** Nesting
	// ********************

	/**
	 * {@inheritDoc}
	 */
	public boolean isNestable() { return false; }

	/**
	 * {@inheritDoc}
	 */
	public boolean canNestElements() { return false; }
}
