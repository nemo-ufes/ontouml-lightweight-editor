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

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import RefOntoUML.Association;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.oled.Main;
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
 * @author Wei-ju Wu, John Guerson
 */
public final class AssociationElement extends BaseConnection {

	private static final long serialVersionUID = 1866495594812659939L;
	private static AssociationElement prototype = new AssociationElement();
	private RelationType associationType = RelationType.ASSOCIATION;
	
	/** A direction of an end point relative to its connected node. */
	private enum Direction  { NORTH, SOUTH, EAST, WEST }
	
	/** The direction to read the name.*/
	public enum ReadingDesign { SOURCE, DESTINATION, UNDEFINED }
	private ReadingDesign readingDesign = ReadingDesign.UNDEFINED;
		
	/** The reading direction of the triangle */
	public enum ReadingDirection { UNDEFINED, LEFT_RIGHT, RIGHT_LEFT, BOTTOM_UP, UP_BOTTOM };
	private ReadingDirection readingDirection = ReadingDirection.UNDEFINED;
	
	private Label multiplicity1Label;
	private Label multiplicity2Label;
	private Label role1Label;
	private Label role2Label;
	private Label subset1Label;
	private Label subset2Label;
	private Label redefine1Label;
	private Label redefine2Label;
	private Label localNameLabel;
	private Label typeLabel;
	private Label metapropertyLabel;
	
	private boolean showMultiplicities, showName, showRoles, showSubsetting, showRedefining, showMetaProperties;
	
	/** Returns the prototype instance.  */
	public static AssociationElement getPrototype() { return prototype; }

	/** Constructor. */
	private AssociationElement() 
	{
		setConnection(new RectilinearConnection(this));
		setupMultiplicityLabels();
		setupRoleLabels();
		setupSubsettingLabels();
		setupRedefiningLabels();
		setupLocalNameLabel();	
		setupTypeLabel();		
		setupMetaPropertyLabel();
		showMultiplicities = true;
		showMetaProperties=true;		
	}
	
	/** {@inheritDoc} */
	@Override
	public Object clone() 
	{
		AssociationElement cloned = (AssociationElement) super.clone();
		// readjust the multiplicity labels, they do not point to the correct
		// label sources after a clone()
		cloned.setupMultiplicityLabels();
		cloned.setupRoleLabels();
		cloned.setupSubsettingLabels();
		cloned.setupRedefiningLabels();
		cloned.setupLocalNameLabel();
		cloned.setupTypeLabel();
		cloned.setupMetaPropertyLabel();
		cloned.showMultiplicities = showMultiplicities;
		cloned.showName = showName;
		cloned.showRoles = showRoles;
		cloned.showSubsetting = showSubsetting;
		cloned.showRedefining = showRedefining;
		cloned.multiplicity1Label.setParent(multiplicity1Label.getParent());
		cloned.multiplicity2Label.setParent(multiplicity2Label.getParent());
		cloned.role1Label.setParent(role1Label.getParent());
		cloned.role2Label.setParent(role2Label.getParent());
		cloned.subset1Label.setParent(subset1Label.getParent());
		cloned.subset2Label.setParent(subset2Label.getParent());
		cloned.redefine1Label.setParent(redefine1Label.getParent());
		cloned.redefine2Label.setParent(redefine2Label.getParent());				
		cloned.localNameLabel.setParent(localNameLabel.getParent());
		cloned.typeLabel.setParent(typeLabel.getParent());
		cloned.metapropertyLabel.setParent(metapropertyLabel.getParent());
		return cloned;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setParent(CompositeNode parent) {
		//TODO Verificar se a connection usa esse composite node
		super.setParent(parent);
		multiplicity1Label.setParent(parent);
		multiplicity2Label.setParent(parent);
		role1Label.setParent(parent);
		role2Label.setParent(parent);
		if(subset1Label!=null)subset1Label.setParent(parent);
		if(subset2Label!=null)subset2Label.setParent(parent);
		if(redefine1Label!=null)redefine1Label.setParent(parent);
		if(redefine2Label!=null)redefine2Label.setParent(parent);
		if(localNameLabel!=null) localNameLabel.setParent(parent);
		if(typeLabel!=null)typeLabel.setParent(parent);
		if(metapropertyLabel!=null)metapropertyLabel.setParent(parent);
	}
	
	/** Position properly all labels */
	public void positionAllLabels(DrawingContext drawingContext )
	{		
		if (showMultiplicities) 
		{			
			if (getNode1()!=null && multiplicity1Label.getParent()!=null) positionLabel(multiplicity1Label, getNode1(), getEndPoint1(), drawingContext, false);
			else if (getNode1()==null && multiplicity1Label.getParent()!=null) positionLabel(multiplicity1Label, getConnection1(), getEndPoint1(), drawingContext, false);
			
			if (getNode2()!=null && multiplicity2Label.getParent()!=null) positionLabel(multiplicity2Label, getNode2(), getEndPoint2(), drawingContext, false);
			else if(getNode2()==null && multiplicity2Label.getParent()!=null) positionLabel(multiplicity2Label, getConnection2(), getEndPoint2(), drawingContext, false);			
		}
		if (getNode1()!=null)
		{
			if(role1Label!=null && showRoles) positionLabel(role1Label, getNode1(), getEndPoint1(), drawingContext, true);
			if(subset1Label!=null && showSubsetting) positionSubsettingLabel(subset1Label, getNode1(), getEndPoint1(), drawingContext);			
			if(redefine1Label!=null && showRedefining) positionRedefiningLabel(redefine1Label, getNode1(), getEndPoint1(), drawingContext);
		}else{
			if(role1Label!=null && showRoles) positionLabel(role1Label, getConnection1(), getEndPoint1(), drawingContext, true);
			if(subset1Label!=null && showSubsetting) positionSubsettingLabel(subset1Label, getConnection1(), getEndPoint1(), drawingContext);
			if(redefine1Label!=null && showRedefining) positionRedefiningLabel(redefine1Label, getNode1(), getEndPoint1(), drawingContext);
		}
		
		if (getNode2()!=null){
			if(role2Label!=null && showRoles) positionLabel(role2Label, getNode2(), getEndPoint2(), drawingContext, true);
			if(subset2Label!=null && showSubsetting)positionSubsettingLabel(subset2Label, getNode2(), getEndPoint2(), drawingContext);
			if(redefine2Label!=null && showRedefining) positionRedefiningLabel(redefine2Label, getNode1(), getEndPoint1(), drawingContext);
		}else{
			if(role2Label!=null && showRoles) positionLabel(role2Label, getConnection2(), getEndPoint2(), drawingContext, true);
			if(subset2Label!=null && showSubsetting) positionSubsettingLabel(subset2Label, getConnection2(), getEndPoint2(), drawingContext);
			if(redefine2Label!=null && showRedefining) positionRedefiningLabel(redefine2Label, getNode1(), getEndPoint1(), drawingContext);
		}
		
		if(localNameLabel!=null && showName) positionLocalNameLabel(drawingContext);				
		if(typeLabel!=null && showOntoUmlStereotype()) positionTypeLabel(drawingContext);
		if(metapropertyLabel!=null && showMetaProperties) positionMetaPropertyLabel(drawingContext);	
	}
	
	/** Draws the connection labels. */
	private void drawLabels(DrawingContext drawingContext) 
	{
		positionAllLabels(drawingContext);
		
		if(multiplicity1Label != null && showMultiplicities) multiplicity1Label.draw(drawingContext);
		if(multiplicity2Label != null && showMultiplicities) multiplicity2Label.draw(drawingContext);
		if(role2Label!=null && showRoles) role1Label.draw(drawingContext);
		if(role2Label!=null && showRoles) role2Label.draw(drawingContext);		
		if(subset1Label!=null && showSubsetting)subset1Label.draw(drawingContext);
		if(subset2Label!=null && showSubsetting)subset2Label.draw(drawingContext);		
		if(redefine1Label!=null && showRedefining)redefine1Label.draw(drawingContext);
		if(redefine2Label!=null && showRedefining)redefine2Label.draw(drawingContext);				
		if(localNameLabel!=null && showName) localNameLabel.draw(drawingContext);
		if(typeLabel!=null && showOntoUmlStereotype()) typeLabel.draw(drawingContext);
		if(metapropertyLabel!=null && showMetaProperties) metapropertyLabel.draw(drawingContext);
	}
	
	/** Sets the multiplicity label sources. */
	private void setupMultiplicityLabels() 
	{
		multiplicity1Label = new SimpleLabel();
		multiplicity1Label.setSource(new LabelSource() {
			
			private static final long serialVersionUID = 124766466850619305L;
			
			/** {@inheritDoc} */
			public String getLabelText() 
			{
				Association association = (Association) getRelationship();
				return ModelHelper.getMultiplicityString(association.getMemberEnd().get(0));
			}
			
			/** {@inheritDoc} */
			public void setLabelText(String aText) { }
		});

		multiplicity2Label = new SimpleLabel();
		multiplicity2Label.setSource(new LabelSource() {
			
			private static final long serialVersionUID = 6012955370882528767L;
			
			/** {@inheritDoc} */
			public String getLabelText() 
			{
				Association association = (Association) getRelationship();
				return ModelHelper.getMultiplicityString(association.getMemberEnd().get(1));
			}
			
			/** {@inheritDoc} */
			public void setLabelText(String aText) { }
		});
	}
	
	/** Get the redefining string */
	private String getRedefiningString(Property p)
	{
		String str = new String();
		if(p!=null){									
			if (p.getRedefinedProperty().size()>0){
				str = "{ redefines ";
				int i=0;
				for(Property property: p.getRedefinedProperty()){
					if(i<p.getRedefinedProperty().size()-1) str += property.getName()+",";
					else str += property.getName();
					i++;
				}
				str += " }";
			}
		}
		return str;	
	}
	
	/** Sets the Redefining label sources. */
	private void setupRedefiningLabels() 
	{
		redefine1Label = new SimpleLabel();
		redefine1Label.setSource(new LabelSource() {
						
			private static final long serialVersionUID = 8971899878055731312L;

			/** {@inheritDoc} */
			public String getLabelText() 
			{
				Association association = (Association) getRelationship();
				Property p = association.getMemberEnd().get(0);				
				return getRedefiningString(p);				
			}

			/** {@inheritDoc} */
			public void setLabelText(String aText) { }
		});

		redefine2Label = new SimpleLabel();
		redefine2Label.setSource(new LabelSource() {
			
			private static final long serialVersionUID = 6014955370882528767L;

			/** {@inheritDoc} */
			public String getLabelText() 
			{
				Association association = (Association) getRelationship();				
				Property p = association.getMemberEnd().get(1);				
				return getRedefiningString(p);				
			}

			/** {@inheritDoc} */
			public void setLabelText(String aText) { }
		});		
	}

	/** Get the subsetting string */
	private String getSubsettingString(Property p)
	{
		String str = new String();
		if(p!=null){									
			if (p.getSubsettedProperty().size()>0){
				str = "{ subsets ";
				int i=0;
				for(Property property: p.getSubsettedProperty()){
					if(i<p.getSubsettedProperty().size()-1) str += property.getName()+",";
					else str += property.getName();
					i++;
				}
				str += " }";
			}
		}
		return str;	
	}
			
	/** Sets the Subsetting label sources. */
	private void setupSubsettingLabels() 
	{
		subset1Label = new SimpleLabel();
		subset1Label.setSource(new LabelSource() {
						
			private static final long serialVersionUID = 8971899878055731312L;

			/** {@inheritDoc} */
			public String getLabelText() 
			{
				Association association = (Association) getRelationship();
				Property p = association.getMemberEnd().get(0);
				return getSubsettingString(p);			
			}

			/** {@inheritDoc} */
			public void setLabelText(String aText) { }
		});

		subset2Label = new SimpleLabel();
		subset2Label.setSource(new LabelSource() {
			
			private static final long serialVersionUID = 6014955370882528767L;

			/** {@inheritDoc} */
			public String getLabelText() 
			{
				Association association = (Association) getRelationship();				
				Property p = association.getMemberEnd().get(1);
				return getSubsettingString(p);				
			}

			/** {@inheritDoc} */
			public void setLabelText(String aText) { }
		});		
	}

	/** Sets the role label sources. */
	private void setupRoleLabels() 
	{
		role1Label = new SimpleLabel();
		role1Label.setSource(new LabelSource() {
			
			private static final long serialVersionUID = 8971899878055731312L;

			/** {@inheritDoc} */
			public String getLabelText() 
			{
				Association association = (Association) getRelationship();
				String role = association.getMemberEnd().get(0).getName(); 
				return role != null ? role : "";
			}

			/** {@inheritDoc} */
			public void setLabelText(String aText) { }
		});

		role2Label = new SimpleLabel();
		role2Label.setSource(new LabelSource() {

			private static final long serialVersionUID = 6014955370882528767L;

			/** {@inheritDoc} */
			public String getLabelText() 
			{
				Association association = (Association) getRelationship();
				String role = association.getMemberEnd().get(1).getName();
				return role != null ? role : "";
			}

			/** {@inheritDoc} */
			public void setLabelText(String aText) { }
		});
		
	}
	
	/** Sets the name label. */
	public void setupLocalNameLabel() 
	{		
		localNameLabel = new SimpleLabel();		
		localNameLabel.setSource(new LabelSource() {
			
			private static final long serialVersionUID = -4124277770032234968L;

			@Override
			public String getLabelText() { return ((Association)getRelationship()).getName(); }
			
			@Override
			public void setLabelText(String aText) {}
		});
	}
	
	/** Sets the stereotype label. */
	public void setupTypeLabel() 
	{		
		typeLabel = new SimpleLabel();		
		typeLabel.setSource(new LabelSource() {
			
			private static final long serialVersionUID = -6122649758554356164L;

			@Override
			public String getLabelText() { return getOntoUmlStereotype(); }
			
			@Override
			public void setLabelText(String aText) { }
		});			
	}
	
	private String getMetaPropertyString(Meronymic m)
	{
		ArrayList<String> result = new ArrayList<String>();
		if (m.isIsEssential()) result.add("essential");
		if (m.isIsInseparable()) result.add("inseparable");
		if (m.isIsImmutablePart()) result.add("immutablePart");
		if (m.isIsImmutableWhole()) result.add("immutableWhole");
		String str =  new String();
		if (result.size()>0){						
			str +="{"; 
			int i=0;
			for(String s: result){
				if(i==result.size()-1) str += s;
				else str += s+",";
				i++;
			}
			str += "}";
		}						
		return str;
	}
	
	/** Sets up the meta properties label */
	public void setupMetaPropertyLabel()
	{
		metapropertyLabel = new SimpleLabel();
		metapropertyLabel.setSource(new LabelSource() {
								
			private static final long serialVersionUID = -2578940157526321262L;

			@Override
			public String getLabelText() {
				if(getRelationship() instanceof Meronymic){
					Meronymic m = (Meronymic)getRelationship();
					return getMetaPropertyString(m);
				}else{
					return "";
				}
			}
			
			@Override
			public void setLabelText(String aText) {}
		});		
	}
		
	/** Returns the value of the showName property.  */
	public boolean showName() { return showName; }

	/** Sets the showName property. */
	public void setShowName(boolean flag) { showName = flag; }
	
	/** Sets the showMetaProperties property. */
	public void setShowMetaProperties(boolean flag) { showMetaProperties = flag; }
		
	/** Returns the value of the showMultiplicities property. */
	public boolean showMultiplicities() { return showMultiplicities; }

	/** Sets the showMultiplicities property. */
	public void setShowMultiplicities(boolean flag) { showMultiplicities = flag; }

	/** Returns the value of the showRoles property. */
	public boolean showRoles() { return showRoles; }
	
	/** Returns the value of the showSubsetting property. */
	public boolean showSubsetting() { return showSubsetting; }
	
	/** Returns the value of the showRedefining property. */
	public boolean showRedefining() { return showRedefining; }
	
	/** Returns the value of the showMetaProperties property. */
	public boolean showMetaProperties() { return showMetaProperties; }
	
	/** Sets the showRoles property. */
	public void setShowRoles(boolean flag) { showRoles = flag; }

	/** Sets the showSubsetting property. */
	public void setShowSubsetting(boolean flag){ showSubsetting = flag; }
	
	/** Sets the showRedefining property. */
	public void setShowRedefining(boolean flag){ showRedefining = flag; }
	
	/** Returns the name label. */
	public Label getLocalNameLabel() { return localNameLabel; }
	
	/** Returns the type label. */
	public Label getTypeLabel() { return typeLabel; }

	/** Returns the meta-property label. */
	public Label getMetaPropertyLabel() { return metapropertyLabel; }
	
	/** Returns the multiplicity label for element 1. */
	public Label getMultiplicity1Label() { return multiplicity1Label; }

	/** Returns the multiplicity label for element 2. */
	public Label getMultiplicity2Label() { return multiplicity2Label; }

	/** Returns the role label for element 1. */
	public Label getRole1Label() { return role1Label; }

	/** Returns the role label for element 2. */
	public Label getRole2Label() { return role2Label; }

	/** Returns the subsetting label for element 1. */
	public Label getSubsetting1Label() { return subset1Label; }
	
	/** Returns the subsetting label for element 2. */
	public Label getSubsetting2Label() { return subset2Label; }
	
	/** Returns the AssociationType. */
	public RelationType getAssociationType() { return associationType; }

	/** Sets the AssociationType. */
	public void setAssociationType(RelationType anAssociationType) { associationType = anAssociationType; }

	/** Gets the association relationship*/
	public Association getAssociation() { return (Association) getRelationship(); }

	/** Sets the reading design */
	public void setReadingDesign(ReadingDesign direction) { this.readingDesign = direction; }

	/** Gets the reading design */	
	public ReadingDesign getReadingDesign() { return readingDesign; }	
	
	/** Gets the subsetting label width of element 1 */	
	public int getSubset1Width(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(subset1Label.getNameLabelText());
	}

	/** Gets the subsetting label width of element 2 */
	public int getSubset2Width(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(subset2Label.getNameLabelText());
	}
	
	/** Gets the redefining label width of element 1 */
	public int getRedefining1Width(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(redefine1Label.getNameLabelText());
	}

	/** Gets the redefining label width of element 2 */
	public int getRedefining2Width(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(redefine2Label.getNameLabelText());
	}
	
	/** Gets the name label width */
	public int getNameWidth(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(localNameLabel.getNameLabelText());
	}
	
	/** Gets the stereotype label width */
	public int getTypeWidth(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(typeLabel.getNameLabelText());
	}
	
	/** Gets the meta property label width */
	public int getMetaPropertyWidth(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(metapropertyLabel.getNameLabelText());
	}
	
	/** Determines the direction the point is relative to the node. */
	private Direction getPointDirection(Node node, Point2D point) 
	{
		if (point.getX() >= node.getAbsoluteX2()) return Direction.EAST;		
		if (point.getX() <= node.getAbsoluteX1()) return Direction.WEST;		
		if (point.getY() <= node.getAbsoluteY1()) return Direction.NORTH;		
		return Direction.SOUTH;
	}

	/** Determines the direction the point is relative to the node. */
	private Direction getPointDirection(Connection c, Point2D point) 
	{
		if (point.getX() >= c.getAbsCenterX()) return Direction.EAST;		
		if (point.getX() <= c.getAbsCenterX()) return Direction.WEST;		
		if (point.getY() <= c.getAbsCenterY()) return Direction.NORTH;		
		return Direction.SOUTH;
	}
	
	/** {@inheritDoc} */
	@Override
	public void draw(DrawingContext drawingContext) 
	{
		super.draw(drawingContext);
		
		//First, draw the line
		if (associationType == RelationType.DERIVATION) drawingContext.setStrokeType(StrokeType.DASHED_BOLD);
		
		//Then, draw decorations
		if (associationType == RelationType.DERIVATION) drawCircle(drawingContext, calculateRotationInEndPoint2(), true);		
		else if (associationType == RelationType.COMPONENTOF) {
			
			if((Meronymic)getRelationship()!=null){
				drawParthood(drawingContext, calculateRotationInEndPoint1(), ((Meronymic)getRelationship()).isIsShareable(), null);
			}else{
				Main.printErrLine("Trying to draw a memberOf decoration... null relationship!");
			}
		}		
		else if (associationType == RelationType.MEMBEROF) {
			
			if((Meronymic)getRelationship()!=null){
				drawParthood(drawingContext, calculateRotationInEndPoint1(), ((Meronymic)getRelationship()).isIsShareable(), "M");
			}else{
				Main.printErrLine("Trying to draw a memberOf decoration... null relationship!");
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

		drawNavigabilityArrows(drawingContext);
		
		drawLabels(drawingContext);
				
		drawDirection(drawingContext);		
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unused")
	private void drawRombus(DrawingContext drawingContext, AffineTransform rotationTransform, boolean filled) 
	{
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
	
	/** {@inheritDoc} */
	private void drawCircle(DrawingContext drawingContext, AffineTransform rotationTransform, boolean filled) 
	{
		//The circle goes on the target
		Point2D endpoint = getEndPoint2();
		float x = (float) endpoint.getX(), y = (float) endpoint.getY();
		Shape shape = new Ellipse2D.Float(x - 3.0f, y - 3.0f, 6.0f, 6.0f);
		GeneralPath circle = new GeneralPath(shape);
		circle.transform(rotationTransform);
		Color fillColor = filled ? Color.BLACK : Color.WHITE;	
		drawingContext.draw(circle, fillColor);
	}

	/** {@inheritDoc} */
	private void drawParthood(DrawingContext drawingContext, AffineTransform rotationTransform, boolean shareable, String content) 
	{
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
		if(content != null) drawingContext.draw(rombus, fillColor, content.charAt(0), fontColor, FontType.SMALL);
		else drawingContext.draw(rombus, fillColor);
	}

	/** {@inheritDoc} */
	private void drawNavigabilityArrows(DrawingContext drawingContext) 
	{		
		boolean navigableSource = getAssociation().getNavigableOwnedEnd().contains(getAssociation().getOwnedEnd().get(0));
		boolean navigableTarget = getAssociation().getNavigableOwnedEnd().contains(getAssociation().getOwnedEnd().get(1));		
		if(navigableSource ^ navigableTarget){
			if (navigableSource) drawArrow(drawingContext, getEndPoint1(), calculateRotationInEndPoint1());			
			if (navigableTarget) drawArrow(drawingContext, getEndPoint2(), calculateRotationInEndPoint2());			
		}
	}

	/** Draws the arrow. */
	private void drawArrow(DrawingContext drawingContext, Point2D endpoint, AffineTransform rotationTransform) 
	{
		new SimpleArrowTip().draw(drawingContext, endpoint, rotationTransform);
	}
	
	/** Draws the direction triangle. */
	private void drawDirection(DrawingContext drawingContext) 
	{		
		if(localNameLabel!=null)
		{
			ReadingDesign d = getReadingDesign();
			setReadingDirection(d);
			if(getReadingDesign() != ReadingDesign.UNDEFINED) {
				drawReadingDirection(drawingContext);
			}
		}
	}
	
	/** Draw the reading direction */
	private void drawReadingDirection(DrawingContext drawingContext)
	{
		if (readingDirection == ReadingDirection.LEFT_RIGHT) {
			drawTriangleLeftRight(drawingContext);
		} else if (readingDirection == ReadingDirection.RIGHT_LEFT) {
			drawTriangleRightLeft(drawingContext);
		}else if (readingDirection == ReadingDirection.UP_BOTTOM) {
			drawTriangleUpBottom(drawingContext);
		}else if (readingDirection == ReadingDirection.BOTTOM_UP) {
			drawTriangleBottomUp(drawingContext);
		}
	}

	/** Private method reusable in the setting of the reading direction */
	private void setReadingDirection(Type srcType, Type class1, Type class2, Type rel1, Type rel2, ReadingDirection direction, ReadingDirection inverseDirection)
	{
		if(class1!=null && srcType.equals(class1)) {
			if(getReadingDesign() == ReadingDesign.DESTINATION) readingDirection = direction;
			else readingDirection = inverseDirection;			
		}
		if(rel1!=null && srcType.equals(rel1)) {
			if(getReadingDesign() == ReadingDesign.DESTINATION) readingDirection = direction;
			else readingDirection = inverseDirection;
		}
		if(class2!=null && srcType.equals(class2)) {
			if(getReadingDesign() == ReadingDesign.SOURCE) readingDirection = direction;
			else readingDirection = inverseDirection;
		}
		if(rel2!=null && srcType.equals(rel2)) {			
			if(getReadingDesign() == ReadingDesign.SOURCE) readingDirection = direction;
			else readingDirection = inverseDirection;
		}
	}
	
	/** Draws the triangle facing to the right. */
	private void drawTriangleLeftRight(DrawingContext drawingContext) 
	{
		GeneralPath trianglePath = new GeneralPath();
		double height = localNameLabel.getSize().getHeight() - 6;
		double x = localNameLabel.getAbsoluteX2() + 3, y = localNameLabel.getAbsoluteY1() + 3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x + 5, y + height / 2);
		trianglePath.lineTo(x, y + height);
		trianglePath.closePath();
		drawingContext.draw(trianglePath, Color.BLACK);
	}

	/** Draws the triangle facing to the bottom. */
	private void drawTriangleUpBottom(DrawingContext drawingContext) 
	{
		GeneralPath trianglePath = new GeneralPath();
		double height = localNameLabel.getSize().getHeight() - 6;
		double x = localNameLabel.getAbsCenterX(), y = getMaximumY2()+3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x + height / 2, y + 5);
		trianglePath.lineTo(x + height, y);
		trianglePath.closePath();
		drawingContext.draw(trianglePath, Color.BLACK);
	}
	
	/** Draws the triangle facing to the top. */
	private void drawTriangleBottomUp(DrawingContext drawingContext) 
	{
		GeneralPath trianglePath = new GeneralPath();
		double height = localNameLabel.getSize().getHeight() - 6;
		double x = localNameLabel.getAbsCenterX() , y = getMinimumY1() - 3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x - height / 2, y-5);
		trianglePath.lineTo(x - height, y);
		trianglePath.closePath();
		drawingContext.draw(trianglePath, Color.BLACK);
	}
	
	/** Draws the triangle facing to the left. */
	private void drawTriangleRightLeft(DrawingContext drawingContext) 
	{
		GeneralPath trianglePath = new GeneralPath();
		double height = localNameLabel.getSize().getHeight() - 6;
		double x = localNameLabel.getAbsoluteX1() - 3, y = localNameLabel.getAbsoluteY1() + 3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x - 5, y + height / 2);
		trianglePath.lineTo(x, y + height);
		trianglePath.closePath();
		drawingContext.draw(trianglePath, Color.BLACK);
	}

	private double getMinimumY1()
	{
		double labelY1=1000;
		if(typeLabel != null){	
			if (showOntoUmlStereotype()) {
				if(typeLabel.getAbsoluteY1()< labelY1) labelY1 = typeLabel.getAbsoluteY1();
			}
		}	
		if(localNameLabel != null){
			if (showName()) {
				if(localNameLabel.getAbsoluteY1()<labelY1) labelY1 = localNameLabel.getAbsoluteY1();
			}
		}
		if(metapropertyLabel != null){
			if (getRelationship() instanceof Meronymic && showMetaProperties()){
				if(metapropertyLabel.getAbsoluteY1()< labelY1) labelY1 = metapropertyLabel.getAbsoluteY1();
			}
		}
		return labelY1;
	}
	
	private double getMaximumY2()
	{
		double labelY2=0;
		if(typeLabel != null){	
			if (showOntoUmlStereotype()) {			
				if(typeLabel.getAbsoluteY2()> labelY2) labelY2 = typeLabel.getAbsoluteY2();
			}
		}	
		if(localNameLabel != null){
			if (showName()) {			
				if(localNameLabel.getAbsoluteY2()> labelY2) labelY2 = localNameLabel.getAbsoluteY2();
			}
		}
		if(metapropertyLabel != null){
			if (getRelationship() instanceof Meronymic && showMetaProperties()){
				if(metapropertyLabel.getAbsoluteY2()> labelY2) labelY2 = metapropertyLabel.getAbsoluteY2();
			}
		}
		return labelY2;
	}
	
	/** 
	 * Set the reading direction i.e. left-right, right-left, bottom-up or up-bottom, according to the reading design
	 *  of the association i.e. according to the values to-source or to-destination
	 *  
	 *  @param readingDesign
	 */
	private void setReadingDirection(ReadingDesign readingDesign)
	{
		Node node1 = getNode1();		
		Node node2 = getNode2();
		Connection connection1 = getConnection1();
		Connection connection2 = getConnection2();
		
		double srcy1 = 0; double srcy2 = 0; double srcx1 = 0; double srcx2 = 0;
		double tgtx1 = 0; double tgtx2 = 0; double tgty1 = 0; double tgty2 = 0;  
		if(node1 !=null) { srcy1 = node1.getAbsoluteY1(); srcx1 = node1.getAbsoluteX1(); srcx2 = node1.getAbsoluteX2(); srcy2 = node1.getAbsoluteY2();}
		if(node2 !=null) { tgty1 = node2.getAbsoluteY1(); tgtx1 = node2.getAbsoluteX1(); tgtx2 = node2.getAbsoluteX2(); tgty2 = node2.getAbsoluteY2(); }
		if(connection1 !=null) { srcy1 = connection1.getAbsoluteY1(); srcx1 = connection1.getAbsoluteX1(); srcx2 = connection1.getAbsoluteX2(); srcy2 = connection1.getAbsoluteY2(); }
		if(connection2 !=null) { tgty1 = connection2.getAbsoluteY1(); tgtx1 = connection2.getAbsoluteX1(); tgtx2 = connection2.getAbsoluteX2(); tgty2 = connection2.getAbsoluteY2(); }
		
		Relationship rel = getRelationship();
		Type srcType = ((Association)rel).getMemberEnd().get(0).getType();
		
		Type class1=null; Type class2=null; Type rel1=null; Type rel2=null;
		if(node1!=null) class1 = ((ClassElement)node1).getClassifier();
		if(node2!=null) class2 = ((ClassElement)node2).getClassifier();
		if(connection1!=null) rel1 = (Type)((AssociationElement)connection1).getRelationship();
		if(connection2!=null) rel2 = (Type)((AssociationElement)connection2).getRelationship();
		
		Line2D segment = getMiddleSegment();
		if(segment==null) { setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.UNDEFINED,ReadingDirection.UNDEFINED); return; }
		
		// vertical aligned		
		if((srcx2>=tgtx1 && srcx2 <= tgtx2)|| (srcx1>=tgtx1 && srcx1<= tgtx2))
		{
			if(segment.getY1()> segment.getY2()) setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.BOTTOM_UP,ReadingDirection.UP_BOTTOM);				 
			else setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.UP_BOTTOM,ReadingDirection.BOTTOM_UP);
		}
		// horizontal aligned		
		else if((srcy2>=tgty1 && srcy2 <= tgty2)|| (srcy1>=tgty1 && srcy1<= tgty2))
		{
			if(segment.getX1()> segment.getX2()) setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.RIGHT_LEFT,ReadingDirection.LEFT_RIGHT);				 
			else setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.LEFT_RIGHT,ReadingDirection.RIGHT_LEFT);
		}
		// first at east and second at west
		else if(segment.getX1() >= segment.getX2()) 
		{			
			if(segment.getY1() > segment.getY2()) {
				if((srcx1 - tgtx2)>(srcy1-tgty2)){
					setReadingDirection(srcType, class1, class2, rel1, rel2, ReadingDirection.RIGHT_LEFT, ReadingDirection.LEFT_RIGHT);
				}else{
					setReadingDirection(srcType, class1, class2, rel1, rel2, ReadingDirection.BOTTOM_UP, ReadingDirection.UP_BOTTOM);
				}						
			} else {
				if((srcx1 - tgtx2)>(tgty1-srcy2)){
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.RIGHT_LEFT,ReadingDirection.LEFT_RIGHT);	
				}else{
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.UP_BOTTOM,ReadingDirection.BOTTOM_UP);
				}
			}
		} 
		// first at west and second at east
		else if(segment.getX1() < segment.getX2())
		{
			if(segment.getY1() < segment.getY2()) {
				if((srcx1 - tgtx2)>(srcy1-tgty2)){
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.LEFT_RIGHT,ReadingDirection.RIGHT_LEFT);
				}else{
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.UP_BOTTOM,ReadingDirection.BOTTOM_UP);
				}
			} else {
				if((tgtx1 - srcx2)>(srcy1-tgty2)){
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.LEFT_RIGHT,ReadingDirection.RIGHT_LEFT);
				}else{
					setReadingDirection(srcType, class1, class2, rel1, rel2, ReadingDirection.BOTTOM_UP, ReadingDirection.UP_BOTTOM);
				}
			}
		}
		// first at south and second at north
		else if(segment.getY1() >= segment.getY2()) 
		{
			if(segment.getX1() > segment.getX2()) {
				if((srcx1 - tgtx2)>(srcy1-tgty2)){
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.RIGHT_LEFT,ReadingDirection.LEFT_RIGHT);
				}else{
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.BOTTOM_UP,ReadingDirection.UP_BOTTOM);
				}
			} else {
				if((tgtx1 - srcx2)>(srcy1-tgty2)){
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.LEFT_RIGHT,ReadingDirection.RIGHT_LEFT);
				}else{
					setReadingDirection(srcType, class1, class2, rel1, rel2, ReadingDirection.BOTTOM_UP, ReadingDirection.UP_BOTTOM);
				}
			}
		}
		// first at north and second at south
		else if (segment.getY1() < segment.getY2()) 
		{
			if(segment.getX1() < segment.getX2()) {
				if((tgtx1 - srcx2)>(tgty1-srcy2)){
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.LEFT_RIGHT,ReadingDirection.RIGHT_LEFT);
				}else{
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.UP_BOTTOM,ReadingDirection.BOTTOM_UP);
				}
			} else {
				if((srcx1 - tgtx2)>(tgty1-srcy2)){
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.RIGHT_LEFT,ReadingDirection.LEFT_RIGHT);					
				}else{
					setReadingDirection(srcType,class1,class2,rel1,rel2,ReadingDirection.UP_BOTTOM,ReadingDirection.BOTTOM_UP);
				}
			}
		} 
	}
	
	/** Sets the position for the name label. */
	private void positionLocalNameLabel(DrawingContext drawingContext) 
	{
		int labelWidth= getNameWidth(drawingContext);		
		// medium segment
		List<Line2D> segments = getSegments();
		if(segments.size()>0)
		{
			Line2D middleSegment = getMiddleSegment();
			double x = (double) (middleSegment.getX2() + middleSegment.getX1() - labelWidth) / 2;
			double y = (double) (middleSegment.getY2() + middleSegment.getY1())/2;			
			localNameLabel.setAbsolutePos(x, y);			
		}
	}
	
	/** Sets the position for the stereotype label. */
	private void positionTypeLabel(DrawingContext drawingContext) 
	{
		int labelWidth= getTypeWidth(drawingContext);		
		// medium segment
		List<Line2D> segments = getSegments();
		if(segments.size()>0)
		{
			Line2D middleSegment = getMiddleSegment();
			double x = (double) (middleSegment.getX2() + middleSegment.getX1() - labelWidth) / 2;
			double y = (double) (middleSegment.getY2() + middleSegment.getY1())/2;			
			if(showName()){										
				typeLabel.setAbsolutePos(x, y- drawingContext.getFontMetrics(FontType.DEFAULT).getHeight());					
			}else{
				typeLabel.setAbsolutePos(x, y);
			}					
		}
	}
	
	/** Sets the position for the meta-properties label. */
	private void positionMetaPropertyLabel(DrawingContext drawingContext) 
	{
		int labelWidth= getMetaPropertyWidth(drawingContext);		
		// medium segment
		List<Line2D> segments = getSegments();
		if(segments.size()>0)
		{
			Line2D middleSegment = getMiddleSegment();
			double x = (double) (middleSegment.getX2() + middleSegment.getX1() - labelWidth) / 2;
			double y = (double) (middleSegment.getY2() + middleSegment.getY1())/2;			
			if(showName() || showOntoUmlStereotype()){										
				metapropertyLabel.setAbsolutePos(x, y+ drawingContext.getFontMetrics(FontType.DEFAULT).getHeight());					
			}else{
				metapropertyLabel.setAbsolutePos(x, y);
			}								
		}
	}
	
	/** Position redefining label */
	private void positionRedefiningLabel(Label label, Object endPointDiagramElement, Point2D endpoint, DrawingContext drawingContext) 
	{
		Direction direction=null;		
		if (endPointDiagramElement instanceof Node) direction = getPointDirection((Node)endPointDiagramElement, endpoint);
		else if (endPointDiagramElement instanceof Connection) direction = getPointDirection((Connection)endPointDiagramElement, endpoint);
		double labelHeight = label.getSize().getHeight(); 
		double labelWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(label.getSource().getLabelText());
		double x = 0, y = 0, marging = 10;		
		switch (direction){
			case NORTH:
			{
				if(showRoles() & showSubsetting()){
					x =	endpoint.getX() + marging;
					y = endpoint.getY() - (3*labelHeight + marging);
				}else if((showRoles() && !showSubsetting())||(!showRoles() && showSubsetting())){
					x =	endpoint.getX() + marging;
					y = endpoint.getY() - (2*labelHeight + marging);
				}else{
					x =	endpoint.getX() + marging;
					y = endpoint.getY() - (labelHeight + marging);
				}
				break;
			}
			case SOUTH:
			{				
				if(showRoles() & showSubsetting()){
					x =	endpoint.getX() + marging;
					y = endpoint.getY() + (2*labelHeight + marging);
				}else if((showRoles() && !showSubsetting())||(!showRoles() && showSubsetting())){
					x =	endpoint.getX() + marging;
					y = endpoint.getY() + (labelHeight + marging);
				}else{
					x =	endpoint.getX() + marging;
					y = endpoint.getY() + marging;
				}
				break;
			}
			case EAST:
			{
				if(showRoles() & showSubsetting()){
					x = endpoint.getX() + marging;
					y = endpoint.getY() - (3*labelHeight + marging);
				}else if((showRoles() && !showSubsetting())||(!showRoles() && showSubsetting())){
					x = endpoint.getX() + marging;								
					y = endpoint.getY() - (2*labelHeight + marging);
				}else{
					x = endpoint.getX() + marging;								
					y = endpoint.getY() - (labelHeight + marging);
				}
				break;
			}
			case WEST:			
			default:
			{
				//west
				if(showRoles() & showSubsetting()){
					x = endpoint.getX() - (labelWidth + marging);
					y = endpoint.getY() - (3*labelHeight + marging);
				}else if((showRoles() && !showSubsetting())||(!showRoles() && showSubsetting())){
					x = endpoint.getX() - (labelWidth + marging);								
					y = endpoint.getY() - (2*labelHeight + marging);
				}else{
					x = endpoint.getX() - (labelWidth + marging);								
					y = endpoint.getY() - (labelHeight + marging);
				}
				break;
			}				
		}
		label.setAbsolutePos(x, y);
	}
	
	/** Position subsetting label */
	private void positionSubsettingLabel(Label label, Object endPointDiagramElement, Point2D endpoint, DrawingContext drawingContext) 
	{
		Direction direction=null;		
		if (endPointDiagramElement instanceof Node) direction = getPointDirection((Node)endPointDiagramElement, endpoint);
		else if (endPointDiagramElement instanceof Connection) direction = getPointDirection((Connection)endPointDiagramElement, endpoint);
		double labelHeight = label.getSize().getHeight(); 
		double labelWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(label.getSource().getLabelText());
		double x = 0, y = 0, marging = 10;		
		switch (direction){
			case NORTH:
			{
				if(showRoles()){
					x =	endpoint.getX() + marging;
					y = endpoint.getY() - (2*labelHeight + marging);
				}else{
					x =	endpoint.getX() + marging;
					y = endpoint.getY() - (labelHeight + marging);
				}
				break;
			}
			case SOUTH:
			{
				if(showRoles()){
					x =	endpoint.getX() + marging;
					y = endpoint.getY() + (labelHeight + marging);
				}else{
					x =	endpoint.getX() + marging;
					y = endpoint.getY() + marging;
				}
				break;
			}
			case EAST:
			{
				if(showRoles()){
					x = endpoint.getX() + marging;								
					y = endpoint.getY() - (2*labelHeight + marging);
				}else{
					x = endpoint.getX() + marging;								
					y = endpoint.getY() - (labelHeight + marging);
				}
				break;
			}
			case WEST:			
			default:
			{
				//west
				if(showRoles()){
					x = endpoint.getX() - (labelWidth + marging);								
					y = endpoint.getY() - (2*labelHeight + marging);
				}else{
					x = endpoint.getX() - (labelWidth + marging);								
					y = endpoint.getY() - (labelHeight + marging);
				}
				break;
			}				
		}
		label.setAbsolutePos(x, y);
	}
	
	/** Positions a label relative to an endpoint. */
	private void positionLabel(Label label, Object endPointDiagramElement, Point2D endpoint, DrawingContext drawingContext, boolean roleLabel) 
	{
		Direction direction=null;
		if (endPointDiagramElement instanceof Node) direction = getPointDirection((Node)endPointDiagramElement, endpoint);
		else if (endPointDiagramElement instanceof Connection) direction = getPointDirection((Connection)endPointDiagramElement, endpoint);
		double labelHeight = label.getSize().getHeight(); 
		double labelWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(label.getSource().getLabelText());
		double x = 0, y = 0, marging = 10;				
		switch (direction) {
		case NORTH:
		{
			if(roleLabel) {
				x =	endpoint.getX() + marging;
				y = endpoint.getY() - labelHeight - marging;
			}else{
				x =	endpoint.getX() - labelWidth - marging;
				y = endpoint.getY() - labelHeight - marging;
			}
			break;
		}
		case SOUTH:
		{
			if(roleLabel) {
				x =	endpoint.getX() + marging;
				y = endpoint.getY() + marging;
			}else{
				x =	endpoint.getX() - labelWidth - marging;
				y = endpoint.getY() + marging;
			}
			break;
		}
		case EAST:
		{
			if(roleLabel){
				x = endpoint.getX() + marging;
				y = endpoint.getY() - labelHeight - marging;
			}else{
				x = endpoint.getX() + marging;
				y = endpoint.getY() + marging;
			}
			break;
		}
		case WEST:
		default:
		{
			if(roleLabel){
				x = endpoint.getX() - labelWidth - marging;
				y = endpoint.getY() - labelHeight - marging;
			}else{
				x = endpoint.getX() - labelWidth - marging;
				y = endpoint.getY() + marging;
			}
			break;
		}
		}
		label.setAbsolutePos(x, y);
	}
	
	/** {@inheritDoc} */
	@Override
	public Label getLabelAt(double xcoord, double ycoord) { return null; }
	
//  /** If is allowable to move the labels, than we need to reposition the labels at every move in the association*/
//	public void repositionLabels(List<Point2D> oldpoints, List<Point2D> newpoints)
//	{
//		if (oldpoints.size()<0) return;
//		if (newpoints.size()<0) return;
//		
//		double sourceX = oldpoints.get(0).getX();
//		double sourceY = oldpoints.get(0).getY();
//		double targetX = oldpoints.get(oldpoints.size()-1).getX();
//		double targetY = oldpoints.get(oldpoints.size()-1).getY();
//		double newSourceX = newpoints.get(0).getX();
//		double newSourceY = newpoints.get(0).getY();
//		double newTargetX = newpoints.get(newpoints.size()-1).getX();
//		double newTargetY = newpoints.get(newpoints.size()-1).getY();
//		
//		double mult1X1 = multiplicity1Label.getAbsoluteX1();
//		double mult1Y1 = multiplicity1Label.getAbsoluteY1();
//		double mult2X1 = multiplicity2Label.getAbsoluteX1();
//		double mult2Y1 = multiplicity2Label.getAbsoluteY1();
//		
//		double newposSrcX = 0;
//		double newposTgtX = 0;
//		double newposSrcY = 0;
//		double newposTgtY = 0;
//		
//		if(sourceX > newSourceX) newposSrcX = mult1X1-(sourceX-newSourceX);
//		if(sourceX < newSourceX) newposSrcX = mult1X1+(newSourceX-sourceX);
//		if(targetX > newTargetX) newposTgtX = mult2X1-(targetX-newTargetX);
//		if(targetX < newTargetX) newposTgtX = mult2X1+(newTargetX-targetX);
//		
//		if(sourceY > newSourceY) newposSrcY = mult1Y1-(sourceY-newSourceY);
//		if(sourceY < newSourceY) newposSrcY = mult1Y1+(newSourceY-sourceY);
//		if(targetY > newTargetY) newposTgtY = mult2Y1-(targetY-newTargetY);
//		if(targetY < newTargetY) newposTgtY = mult2Y1+(newTargetY-targetY);
//		
//		multiplicity1Label.setAbsolutePos(newposSrcX, newposSrcY);
//		multiplicity2Label.setAbsolutePos(newposTgtX, newposTgtY);
//		
//		super.setPoints(newpoints);
//		multiplicity1Label.invalidate();
//		multiplicity2Label.invalidate();		
//	}

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
	
}
