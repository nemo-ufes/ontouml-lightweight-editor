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
package br.ufes.inf.nemo.oled.umldraw.structure;

import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import RefOntoUML.NamedElement;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.oled.draw.CompositeNode;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.DiagramOperations;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.LineConnectMethod;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.draw.Selection;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnectionSelection;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * UML specific connections wrap the connections defined in the draw package and
 * specify how they should be rendered, possibly with additions of decorations
 * like arrow heads.
 * 
 * @author Wei-ju Wu, John Guerson
 */
public class BaseConnection implements UmlConnection, Adapter {

	private static final long serialVersionUID = 4796693000723361980L;
	private transient Relationship relationship;
	protected Connection connection;
	private String ontoUmlStereotype;
	private boolean showOntoUmlStereotype = false;
	private String relationshipUUID; 
	
	/**
	 * Makes the Constructor protected to prevent direct instantiation.
	 */
	protected BaseConnection() {
	}
		
	/**
	 * {@inheritDoc}
	 */
	public boolean isTreeStyle() {
		return connection.isTreeStyle();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isDashed() {
		return connection.isDashed();
	}

	/**
	 * {@inheritDoc}
	 */
	public LineConnectMethod getConnectMethod() {
		return connection.getConnectMethod();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone() {
		BaseConnection cloned = null;
		try {
			cloned = (BaseConnection) super.clone();
			if (relationship != null) {
				cloned.relationship = ModelHelper.clone(relationship);
				cloned.relationship.eAdapters().add(cloned);
			}
			if (connection != null) {
				cloned.connection = (Connection) connection.clone();				
			}
		} catch (CloneNotSupportedException ignore) {
			ignore.printStackTrace();
		}
		return cloned;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setRelationship(Relationship aRelationship) {
		relationship = aRelationship;
		
		if(relationship.eResource() != null)
			relationshipUUID = ModelHelper.getUUIDFromElement(relationship);
		
		if (relationship != null) {
			relationship.eAdapters().add(this);
			ontoUmlStereotype = ModelHelper.getClassAsStereotype(relationship);			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Relationship getRelationship() {
		
		//In case of deserialization, attempts to retrieve the element from model
		if(relationship == null && relationshipUUID != null)
		{
			RefOntoUML.Package model = ((StructureDiagram)getDiagram()).getRootPackage();
			relationship = (Relationship) ModelHelper.getElementByUUID(model, relationshipUUID);
		}
		
		return relationship;
	}

	/**
	 * Sets the Connection object.
	 * 
	 * @param aConnection
	 *            the Connection object
	 */
	public void setConnection(Connection aConnection) {
		connection = aConnection;
	}

	/**
	 * Returns the wrapped connection object.
	 * 
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * {@inheritDoc}
	 */
	public NamedElement getClassifier() {
		return null;
	}

	/**
	 * Returns the Node 0 element.
	 * 
	 * @return node 0
	 */
	public Node getNode1() {
		return connection.getNode1();
	}

	/**
	 * Sets the node 0 element.
	 * 
	 * @param aNode
	 *            the node 0 element
	 */
	public void setNode1(Node aNode) {
		connection.setNode1(aNode);
	}

	/**
	 * Returns the Node 1 element.
	 * 
	 * @return node 1
	 */
	public Node getNode2() {
		return connection.getNode2();
	}

	/**
	 * Sets the node 1 element of the connection.
	 * 
	 * @param aNode
	 *            the node 1 element
	 */
	public void setNode2(Node aNode) {
		connection.setNode2(aNode);
	}

	public void setConnection1(Connection c){
		connection.setConnection1(c);
	}
	
	public void setConnection2(Connection c){
		connection.setConnection2(c);
	}
	
	public Connection getConnection1(){
		return connection.getConnection1();
	}
	
	public Connection getConnection2(){
		return connection.getConnection2();
	}
	
	/**
	 * Draws the connection.
	 * 
	 * @param drawingContext
	 *            the DrawingContext
	 */
	public void draw(DrawingContext drawingContext) {
		connection.draw(drawingContext);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Point2D> getPoints() {
		return connection.getPoints();
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPoints() {
		connection.resetPoints();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Line2D> getSegments() {
		return connection.getSegments();
	}

	private double length(double x1,double y1,double x2,double y2){
        double length_in_pixel;
        double x=Math.pow((x2-x1), 2);
        double y=Math.pow((y2-y1), 2);
        length_in_pixel = Math.sqrt(x+y);
        return length_in_pixel;
    }
	
	public Line2D getMiddleSegment()
	{
		List<Line2D> segments = getSegments();
		if(segments.size()>0){
			Line2D middleSegment = segments.get(segments.size() / 2);
			double middleLength = length(middleSegment.getP1().getX(),middleSegment.getP1().getY(),middleSegment.getP2().getX(),middleSegment.getP2().getY());						
			if(segments.size()%2==0){				
				for(Line2D l: segments){
					double x = length(l.getP1().getX(),l.getP1().getY(),l.getP2().getX(),l.getP2().getY());
					if(x>middleLength) { middleLength=x; middleSegment=l; }
				}
			}
			return middleSegment;
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Line2D getSegmentAtPoint(double xcoord, double ycoord) {
		return connection.getSegmentAtPoint(xcoord, ycoord);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean contains(double x, double y) {
		return connection.contains(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean intersects(Rectangle2D bounds) {
		return connection.intersects(bounds);
	}

	/**
	 * {@inheritDoc}
	 */
	public Rectangle2D getAbsoluteBounds() {
		return connection.getAbsoluteBounds();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return relationship == null ? super.toString() : relationship
				.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isVisible(Rectangle2D bounds) {
		return connection.isVisible(bounds);
	}

	/**
	 * {@inheritDoc}
	 */
	public CompositeNode getParent() {
		return connection.getParent();
	}

	public void setShowOntoUmlStereotype(boolean showOntoUmlStereotype) {
		this.showOntoUmlStereotype = showOntoUmlStereotype;
	}

	public boolean showOntoUmlStereotype() {
		return showOntoUmlStereotype;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isAncestor(DiagramElement element) {
		return connection.isAncestor(element);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setParent(CompositeNode aParent) {
		connection.setParent(aParent);
	}

	/**
	 * {@inheritDoc}
	 */
	public void recalculateSize(DrawingContext drawingContext) {
		connection.recalculateSize(drawingContext);
	}

	/**
	 * {@inheritDoc}
	 */
	public Selection getSelection(DiagramOperations operations) {
		return new UmlConnectionSelection(this,
				connection.getSelection(operations));
	}

	/**
	 * {@inheritDoc}
	 */
	public Label getLabelAt(double mx, double my) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isValid() {
		return connection.isValid();
	}

	/**
	 * {@inheritDoc}
	 */
	public void invalidate() {
		connection.invalidate();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setIsDashed(boolean flag) {
		connection.setIsDashed(flag);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPoints(List<Point2D> thePoints) {
		connection.setPoints(thePoints);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public AffineTransform calculateRotationInEndPoint1() {
		return connection.calculateRotationInEndPoint1();
	}

	/**
	 * {@inheritDoc}
	 */
	public AffineTransform calculateRotationInEndPoint2() {
		return connection.calculateRotationInEndPoint2();
	}

	/**
	 * {@inheritDoc}
	 */
	public Point2D getEndPoint1() {
		return connection.getEndPoint1();
	}

	/**
	 * {@inheritDoc}
	 */
	public Point2D getEndPoint2() {
		return connection.getEndPoint2();
	}

	/**
	 * {@inheritDoc}
	 */
	public void copyData(Connection conn) {
		connection.copyData(conn);
	}

	// *************************************************************************
	// ***** Nesting
	// ********************

	/**
	 * {@inheritDoc}
	 */
	public boolean isNestable() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean canNestElements() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setOntoUmlStereotype(String ontoUmlStereotype) {
		this.ontoUmlStereotype = ontoUmlStereotype;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getOntoUmlStereotype() {
		return ontoUmlStereotype;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Notifier getTarget() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAdapterForType(Object arg0) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyChanged(Notification arg0) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTarget(Notifier arg0) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Diagram getDiagram() {
		return connection.getDiagram();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	 public void addedToDiagram(Diagram diagram) {
		 relationshipUUID = ModelHelper.getUUIDFromElement(relationship);
	 }

	public String getRelationshipUUID() {
		return relationshipUUID;
	}

	/**
	 * {@inheritDoc}
	 */
	public Collection<? extends Connection> getConnections() {
		return connection.getConnections();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addConnection(Connection conn) { connection.addConnection(conn); }

	/**
	 * {@inheritDoc}
	 */
	public void removeConnection(Connection conn) { connection.removeConnection(conn); }

	@Override
	public double getAbsCenterX() {
		return connection.getAbsCenterX();		
	}

	@Override
	public double getAbsCenterY() {
		return connection.getAbsCenterY();
	}

	@Override
	public void calculateIntersection(Line2D line, Point2D intersectionPoint) {
		connection.calculateIntersection(line, intersectionPoint);		
	}

	@Override
	public boolean intersects(Line2D line) {
		return connection.intersects(line);		
	}

	@Override
	public double getAbsoluteX2() {		
		return connection.getAbsoluteX2();
	}

	@Override
	public double getAbsoluteX1() {
		return connection.getAbsoluteX1();
	}

	@Override
	public double getAbsoluteY2() {
		return connection.getAbsoluteY2();
	}

	@Override
	public double getAbsoluteY1() {
		return connection.getAbsoluteY1();
	}

	@Override
	public void setConnections(Collection<? extends Connection> connList) {
		connection.setConnections(connList);		
	}

}
