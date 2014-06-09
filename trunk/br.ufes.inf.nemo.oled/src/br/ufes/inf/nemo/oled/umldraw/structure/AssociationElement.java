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
package br.ufes.inf.nemo.oled.umldraw.structure;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

import RefOntoUML.Association;
import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.oled.draw.CompositeNode;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.DrawingContext.FontType;
import br.ufes.inf.nemo.oled.draw.DrawingContext.StrokeType;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.LabelSource;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.draw.RectilinearConnection;
import br.ufes.inf.nemo.oled.draw.SimpleArrowTip;
import br.ufes.inf.nemo.oled.draw.SimpleLabel;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.util.ModelHelper;



/**
 * This class implements an association connection. The association connection
 * can be configured to display either one of an unspecified association,
 * an aggregation or a composition, they mainly differ by the displayed
 * decorations.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public final class AssociationElement extends BaseConnection {

	private static final long serialVersionUID = 1866495594812659939L;
	private static AssociationElement prototype = new AssociationElement();
	
	/**
	 * The direction to read the name.
	 */
	public enum ReadingDirection { UNDEFINED, LEFT_RIGHT, RIGHT_LEFT };
	
	private RelationType associationType = RelationType.ASSOCIATION;
	private ReadingDirection readingDirection = ReadingDirection.UNDEFINED;
	private Label multiplicity1Label;
	private Label multiplicity2Label;
	private Label role1Label;
	private Label role2Label;
	private AssociationLabel nameLabel;
	private boolean showMultiplicities, showName, showRoles, showMetaProperties;
	
	/**
	 * Returns the prototype instance.
	 * @return the prototype instance
	 */
	public static AssociationElement getPrototype() { return prototype; }

	/**
	 * Constructor.
	 */
	private AssociationElement() {
		setConnection(new RectilinearConnection());
		setupMultiplicityLabels();
		setupRoleLabels();
		setupNameLabel();
		
		showMultiplicities = true;
		showMetaProperties=true;
	}

	/**
	 * Returns the value of the showName property.
	 * @return the value of the showName property
	 */
	public boolean showName() { return showName; }

	/**
	 * Sets the showName property.
	 * @param flag the value of the showName property
	 */
	public void setShowName(boolean flag) { showName = flag; }

	public void setShowMetaProperties(boolean flag) { showMetaProperties = flag; }
		
	/**
	 * Returns the value of the showMultiplicities property.
	 * @return the value of the showMultiplicities property
	 */
	public boolean showMultiplicities() { return showMultiplicities; }

	/**
	 * Sets the showMultiplicities property.
	 * @param flag the value of the showMultiplicities property
	 */
	public void setShowMultiplicities(boolean flag) { showMultiplicities = flag; }


	/**
	 * Returns the value of the showRoles property.
	 * @return the value of the showRoles property
	 */
	public boolean showRoles() {
		return showRoles;
	}
	
	public boolean showMetaProperties() { return showMetaProperties; }
	
	/**
	 * Sets the showRoles property.
	 * @param flag the value of the showMultiplicities property
	 */
	public void setShowRoles(boolean flag) {
		showRoles = flag;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone() {
		AssociationElement cloned = (AssociationElement) super.clone();
		// readjust the multiplicity labels, they do not point to the correct
		// label sources after a clone()
		cloned.setupMultiplicityLabels();
		cloned.setupRoleLabels();
		cloned.setupNameLabel();
		cloned.showMultiplicities = showMultiplicities;
		cloned.showName = showName;
		cloned.showRoles = showRoles;
		cloned.multiplicity1Label.setParent(multiplicity1Label.getParent());
		cloned.multiplicity2Label.setParent(multiplicity2Label.getParent());
		cloned.role1Label.setParent(role1Label.getParent());
		cloned.role2Label.setParent(role2Label.getParent());
		cloned.nameLabel.setParent(nameLabel.getParent());		
		return cloned;
	}

	/**
	 * Sets the name label.
	 */
	public void setupNameLabel() {
		nameLabel = new AssociationLabel();
		nameLabel.setAssociation(this);
	}

	/**
	 * Sets the multiplicity label sources.
	 */
	private void setupMultiplicityLabels() {
		multiplicity1Label = new SimpleLabel();
		multiplicity1Label.setSource(new LabelSource() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 124766466850619305L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				Association association = (Association) getRelationship();
				return ModelHelper.getMultiplicityString(association.getMemberEnd().get(0));
			}

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) { }
		});

		multiplicity2Label = new SimpleLabel();
		multiplicity2Label.setSource(new LabelSource() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6012955370882528767L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				Association association = (Association) getRelationship();
				return ModelHelper.getMultiplicityString(association.getMemberEnd().get(1));
			}

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) { }
		});
	}

	/**
	 * Sets the role label sources.
	 */
	private void setupRoleLabels() {
		role1Label = new SimpleLabel();
		role1Label.setSource(new LabelSource() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 8971899878055731312L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				Association association = (Association) getRelationship();
				String role = association.getMemberEnd().get(0).getName(); 
				return role != null ? role : "";
			}

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) { }
		});

		role2Label = new SimpleLabel();
		role2Label.setSource(new LabelSource() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6014955370882528767L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				Association association = (Association) getRelationship();
				String role = association.getMemberEnd().get(1).getName();
				return role != null ? role : "";
			}

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) { }
		});
		
	}
	
	/**
	 * Returns the name label.
	 * @return the name label
	 */
	public Label getNameLabel() { return nameLabel; }

	/**
	 * Returns the multiplicity label for element 1.
	 * @return the multiplicity label for element 1
	 */
	public Label getMultiplicity1Label() { return multiplicity1Label; }

	/**
	 * Returns the multiplicity label for element 2.
	 * @return the multiplicity label for element 2
	 */
	public Label getMultiplicity2Label() { return multiplicity2Label; }

	/**
	 * Returns the role label for element 1.
	 * @return the role label for element 1
	 */
	public Label getRole1Label() { return role1Label; }

	/**
	 * Returns the role label for element 2.
	 * @return the role label for element 2
	 */
	public Label getRole2Label() { return role2Label; }

	/**
	 * Returns the AssociationType.
	 * @return the AssociationType
	 */
	public RelationType getAssociationType() { return associationType; }

	/**
	 * Sets the AssociationType.
	 * @param anAssociationType the AssociationType
	 */
	public void setAssociationType(RelationType anAssociationType) {
		associationType = anAssociationType;
	}

	public Association getAssociation()
	{
		return (Association) getRelationship();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setParent(CompositeNode parent) {//TODO Verificar se a connection usa esse composite node
		super.setParent(parent);
		multiplicity1Label.setParent(parent);
		multiplicity2Label.setParent(parent);
		role1Label.setParent(parent);
		role2Label.setParent(parent);
		nameLabel.setParent(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(DrawingContext drawingContext) {
		super.draw(drawingContext);
		
		//Frist, draw the line
		if (associationType == RelationType.DERIVATION) {
			drawingContext.setStrokeType(StrokeType.DASHED_BOLD);
		}
		
		//Then, draw decorations
		if (associationType == RelationType.DERIVATION) {
			
			drawCircle(drawingContext, calculateRotationInEndPoint2(), true);
		}

		else if (associationType == RelationType.COMPONENTOF) {

			if((Meronymic)getRelationship()!=null){
				drawParthood(drawingContext, calculateRotationInEndPoint1(), ((Meronymic)getRelationship()).isIsShareable(), null);
			}else{
				System.err.println("Trying to draw a memberOf decoration... null relationship!");
			}
		}
		
		else if (associationType == RelationType.MEMBEROF) {
			if((Meronymic)getRelationship()!=null){
				drawParthood(drawingContext, calculateRotationInEndPoint1(), ((Meronymic)getRelationship()).isIsShareable(), "M");
			}else{
				System.err.println("Trying to draw a memberOf decoration... null relationship!");
			}
		}

		else if (associationType == RelationType.SUBQUANTITYOF) {
			if((Meronymic)getRelationship()!=null){
				drawParthood(drawingContext, calculateRotationInEndPoint1(), ((Meronymic)getRelationship()).isIsShareable(), "Q");
			}else{
				System.err.println("Trying to draw a subQuantityOf decoration... null relationship!");
			}
		}

		else if (associationType == RelationType.SUBCOLLECTIONOF) {
			
			if((Meronymic)getRelationship()!=null){
				drawParthood(drawingContext, calculateRotationInEndPoint1(), ((Meronymic)getRelationship()).isIsShareable(), "C");
			}else{
				System.err.println("Trying to draw a subCollectionOf decoration... null relationship!");
			}
		}

		//Then, draw navegability arrows and labels
		drawNavigabilityArrows(drawingContext);
		drawLabels(drawingContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Label getLabelAt(double xcoord, double ycoord) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unused")
	private void drawRombus(DrawingContext drawingContext, AffineTransform rotationTransform, boolean filled) {
		Point2D endpoint = getEndPoint1();
		double x = endpoint.getX(), y = endpoint.getY();
		GeneralPath rombus = new GeneralPath();
		rombus.moveTo(x - 18, y);
		rombus.lineTo(x - 9, y - 7);
		rombus.lineTo(x, y);
		rombus.lineTo(x - 9, y + 7);
		rombus.closePath();
		rombus.transform(rotationTransform);
		Color fillColor = filled ? Color.BLACK : Color.WHITE;
		drawingContext.draw(rombus, fillColor);
	}

	private void drawCircle(DrawingContext drawingContext,
			AffineTransform rotationTransform, boolean filled) {

		//The circle goes on the target
		Point2D endpoint = getEndPoint2();
		float x = (float) endpoint.getX(), y = (float) endpoint.getY();
		Shape shape = new Ellipse2D.Float(x - 3.0f, y - 3.0f, 6.0f, 6.0f);
		GeneralPath circle = new GeneralPath(shape);
		circle.transform(rotationTransform);
		Color fillColor = filled ? Color.BLACK : Color.WHITE;	
		drawingContext.draw(circle, fillColor);
	}

	private void drawParthood(DrawingContext drawingContext, AffineTransform rotationTransform, boolean shareable, String content) {
		Point2D endpoint = getEndPoint1();
		double x = endpoint.getX(), y = endpoint.getY();
		GeneralPath rombus = new GeneralPath();
		rombus.moveTo(x - 18, y);
		rombus.lineTo(x - 9, y - 7);
		rombus.lineTo(x, y);
		rombus.lineTo(x - 9, y + 7);
		rombus.closePath();
		rombus.transform(rotationTransform);
		Color fillColor = shareable ? Color.WHITE : Color.BLACK;
		Color fontColor = shareable ? Color.BLACK : Color.WHITE; //The opposite
		if(content != null)
			drawingContext.draw(rombus, fillColor, content.charAt(0), fontColor, FontType.SMALL);
		else
			drawingContext.draw(rombus, fillColor);
	}

	/**
	 * Draws the navigability arrows.
	 * @param drawingContext the DrawingContext
	 */
	private void drawNavigabilityArrows(DrawingContext drawingContext) {
		
		boolean navigableSource = getAssociation().getNavigableOwnedEnd().contains(getAssociation().getOwnedEnd().get(0));
		boolean navigableTarget = getAssociation().getNavigableOwnedEnd().contains(getAssociation().getOwnedEnd().get(1));
		
		if(navigableSource ^ navigableTarget)
		{
			if (navigableSource) {
				drawArrow(drawingContext, getEndPoint1(), calculateRotationInEndPoint1());
			}
			if (navigableTarget) {
				drawArrow(drawingContext, getEndPoint2(), calculateRotationInEndPoint2());
			}
		}
	}

	/**
	 * Draws the arrow.
	 * @param drawingContext the drawing context
	 * @param endpoint the end point to draw the arrow at
	 * @param rotationTransform the rotation
	 */
	private void drawArrow(DrawingContext drawingContext, Point2D endpoint,
			AffineTransform rotationTransform) {
		new SimpleArrowTip().draw(drawingContext, endpoint, rotationTransform);
	}

	/**
	 * Draws the connection labels.
	 * @param drawingContext the DrawingContext
	 */
	private void drawLabels(DrawingContext drawingContext) {
		if (showMultiplicities) {
			if (getNode1()!=null)
				positionLabel(multiplicity1Label, getNode1(), getEndPoint1(), drawingContext, false);
			else
				positionLabel(multiplicity1Label, getConnection1(), getEndPoint1(), drawingContext, false);
			if (getNode2()!=null)
				positionLabel(multiplicity2Label, getNode2(), getEndPoint2(), drawingContext, false);
			else
				positionLabel(multiplicity2Label, getConnection2(), getEndPoint2(), drawingContext, false);
			multiplicity1Label.draw(drawingContext);
			multiplicity2Label.draw(drawingContext);
		}
		if (showRoles) {
			if (getNode1()!=null)
				positionLabel(role1Label, getNode1(), getEndPoint1(), drawingContext, true);
			else
				positionLabel(role1Label, getConnection1(), getEndPoint1(), drawingContext, true);
			if (getNode2()!=null)
				positionLabel(role2Label, getNode2(), getEndPoint2(), drawingContext, true);
			else
				positionLabel(role2Label, getConnection2(), getEndPoint2(), drawingContext, true);
			role1Label.draw(drawingContext);
			role2Label.draw(drawingContext);
		}
		positionNameLabel(drawingContext);
		nameLabel.draw(drawingContext);
	}

	/**
	 * Positions a label relative to an endpoint.
	 * @param label the label
	 * @param node the node
	 * @param endpoint the end point
	 */
	private void positionLabel(Label label, Object endPointDiagramElement, Point2D endpoint, DrawingContext drawingContext, boolean roleLabel) {
		Direction direction=null;
		if (endPointDiagramElement instanceof Node)
			direction = getPointDirection((Node)endPointDiagramElement, endpoint);
		else if (endPointDiagramElement instanceof Connection)
			direction = getPointDirection((Connection)endPointDiagramElement, endpoint);
				
		double x = 0, y = 0, marging = 10, labelHeight = label.getSize().getHeight(), 
			labelWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(label.getSource().getLabelText());
				
		switch (direction) {
			case NORTH:
				if(roleLabel)
				{
					x =	endpoint.getX() + marging;
					y = endpoint.getY() - labelHeight - marging;
				}
				else
				{
					x =	endpoint.getX() - labelWidth - marging;
					y = endpoint.getY() - labelHeight - marging;
				}
				break;
			case SOUTH:
				if(roleLabel)
				{
					x =	endpoint.getX() + marging;
					y = endpoint.getY() + marging;
				}
				else
				{
					x =	endpoint.getX() - labelWidth - marging;
					y = endpoint.getY() + marging;
				}
				break;
			case EAST:
				if(roleLabel)
				{
					x = endpoint.getX() + marging;
					y = endpoint.getY() - labelHeight - marging;
				}
				else
				{
					x = endpoint.getX() + marging;
					y = endpoint.getY() + marging;
				}
				break;
			case WEST:
			default:
				if(roleLabel)
				{
					x = endpoint.getX() - labelWidth - marging;
					y = endpoint.getY() - labelHeight - marging;
				}
				else
				{
					x = endpoint.getX() - labelWidth - marging;
					y = endpoint.getY() + marging;
				}
				break;
		}
		
		label.setAbsolutePos(x, y);
	}

	@Override
	public String toString()
	{
		if(getRelationship() instanceof RefOntoUML.Association){
			return "<<"+ModelHelper.getStereotype(getRelationship())+">> "+((RefOntoUML.Association)getRelationship()).getName();
		}else if (getRelationship() instanceof RefOntoUML.Generalization){
			return ModelHelper.getStereotype(getRelationship())+" "+((RefOntoUML.Generalization)getRelationship()).getSpecific()+" -> "+((RefOntoUML.Generalization)getRelationship()).getGeneral();
		}else if (getRelationship()==null){
			return "null";
		}
		return "!UNKNOWN";
	}
	
	/**
	 * A direction of an end point relative to its connected node.
	 */
	private enum Direction  { NORTH, SOUTH, EAST, WEST }

	/**
	 * Determines the direction the point is relative to the node.
	 * @param node the node
	 * @param point the point
	 * @return the direction
	 */
	private Direction getPointDirection(Node node, Point2D point) {
		if (point.getX() >= node.getAbsoluteX2()) {
			return Direction.EAST;
		}
		if (point.getX() <= node.getAbsoluteX1()) {
			return Direction.WEST;
		}
		if (point.getY() <= node.getAbsoluteY1()) {
			return Direction.NORTH;
		}
		return Direction.SOUTH;
	}

	/**
	 * Determines the direction the point is relative to the node.
	 * @param c the node
	 * @param point the point
	 * @return the direction
	 */
	private Direction getPointDirection(Connection c, Point2D point) {
		if (point.getX() >= c.getAbsCenterX()) {
			return Direction.EAST;
		}
		if (point.getX() <= c.getAbsCenterX()) {
			return Direction.WEST;
		}
		if (point.getY() <= c.getAbsCenterY()) {
			return Direction.NORTH;
		}
		return Direction.SOUTH;
	}
	
	/**
	 * Sets the position for the name label.
	 */
	private void positionNameLabel(DrawingContext drawingContext) {
		int labelWidth = 0;

		if(showOntoUmlStereotype() && nameLabel.getTypeLabelText() != null)
			labelWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(nameLabel.getTypeLabelText());

		// medium segment
		List<Line2D> segments = getSegments();		
		if(segments.size()>0){
			Line2D middlesegment = segments.get(segments.size() / 2);
			int x = (int) (middlesegment.getX2() + middlesegment.getX1() - labelWidth) / 2;
			int y = (int) (middlesegment.getY2() + middlesegment.getY1()) / 2;
			nameLabel.setAbsolutePos(x, y);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ReadingDirection getNameReadingDirection() { return readingDirection; }

	/**
	 * {@inheritDoc}
	 */
	public void setNameReadingDirection(ReadingDirection dir) {
		readingDirection = dir;
	}
}
