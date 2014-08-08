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
import java.util.List;

import RefOntoUML.Association;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
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
	
	/**
	 * The direction to read the name.
	 */
	public enum ReadingDirection { UNDEFINED, LEFT_RIGHT, RIGHT_LEFT, BOTTOM_UP, UP_BOTTOM };
	
	private RelationType associationType = RelationType.ASSOCIATION;
	private ReadingDirection readingDirection = ReadingDirection.UNDEFINED;
	private Label multiplicity1Label;
	private Label multiplicity2Label;
	private Label role1Label;
	private Label subset1Label;
	private Label subset2Label;
	private Label redefine1Label;
	private Label redefine2Label;
	private Label role2Label;
	private AssociationLabel nameLabel;
	private boolean showMultiplicities, showName, showRoles, showSubsetting, showRedefining, showMetaProperties;
	
	/**
	 * Returns the prototype instance.
	 * @return the prototype instance
	 */
	public static AssociationElement getPrototype() { return prototype; }

	/**
	 * Constructor.
	 */
	private AssociationElement() {
		setConnection(new RectilinearConnection(this));
		setupMultiplicityLabels();
		setupRoleLabels();
		setupSubsettingLabels();
		setupRedefiningLabels();
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
	public boolean showSubsetting() {
		return showSubsetting;
	}
	public boolean showRedefining() {
		return showRedefining;
	}
	
	public boolean showMetaProperties() { return showMetaProperties; }
	
	/**
	 * Sets the showRoles property.
	 * @param flag the value of the showMultiplicities property
	 */
	public void setShowRoles(boolean flag) {
		showRoles = flag;		
	}
	
	public void setShowSubsetting(boolean flag){
		showSubsetting = flag;
	}
	
	public void setShowRedefining(boolean flag){
		showRedefining = flag;
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
		cloned.setupSubsettingLabels();
		cloned.setupRedefiningLabels();
		cloned.setupNameLabel();
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
	
	private void setupRedefiningLabels() {
		redefine1Label = new SimpleLabel();
		redefine1Label.setSource(new LabelSource() {
						
			private static final long serialVersionUID = 8971899878055731312L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				Association association = (Association) getRelationship();
				Property p = association.getMemberEnd().get(0);
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

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) { }
		});

		redefine2Label = new SimpleLabel();
		redefine2Label.setSource(new LabelSource() {
			
			private static final long serialVersionUID = 6014955370882528767L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				Association association = (Association) getRelationship();				
				Property p = association.getMemberEnd().get(1);
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

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) { }
		});
		
	}

	private void setupSubsettingLabels() {
		subset1Label = new SimpleLabel();
		subset1Label.setSource(new LabelSource() {
						
			private static final long serialVersionUID = 8971899878055731312L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				Association association = (Association) getRelationship();
				Property p = association.getMemberEnd().get(0);
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

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) { }
		});

		subset2Label = new SimpleLabel();
		subset2Label.setSource(new LabelSource() {
			
			private static final long serialVersionUID = 6014955370882528767L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				Association association = (Association) getRelationship();				
				Property p = association.getMemberEnd().get(1);
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

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) { }
		});		
	}
	
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

	public Label getSubsetting1Label() {return subset1Label; }
	public Label getSubsetting2Label() {return subset2Label; }
	
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
		if(subset1Label!=null)subset1Label.setParent(parent);
		if(subset2Label!=null)subset2Label.setParent(parent);
		if(redefine1Label!=null)redefine1Label.setParent(parent);
		if(redefine2Label!=null)redefine2Label.setParent(parent);
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

	public int getSubset1Width(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(subset1Label.getNameLabelText());
	}

	public int getSubset2Width(DrawingContext drawingContext)
	{
		return drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(subset2Label.getNameLabelText());
	}
	
	/**
	 * Draws the connection labels.
	 * @param drawingContext the DrawingContext
	 */
	private void drawLabels(DrawingContext drawingContext) {
		if (showMultiplicities) {			
			if (getNode1()!=null && multiplicity1Label.getParent()!=null)
				positionLabel(multiplicity1Label, getNode1(), getEndPoint1(), drawingContext, false);
			else if (getNode1()==null && multiplicity1Label.getParent()!=null)
				positionLabel(multiplicity1Label, getConnection1(), getEndPoint1(), drawingContext, false);
			if (getNode2()!=null && multiplicity2Label.getParent()!=null)
				positionLabel(multiplicity2Label, getNode2(), getEndPoint2(), drawingContext, false);
			else if(getNode2()==null && multiplicity2Label.getParent()!=null)
				positionLabel(multiplicity2Label, getConnection2(), getEndPoint2(), drawingContext, false);
			multiplicity1Label.draw(drawingContext);
			multiplicity2Label.draw(drawingContext);
		}		
		
		if (getNode1()!=null){
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
		
		if(role2Label!=null && showRoles) role1Label.draw(drawingContext);
		if(role2Label!=null && showRoles) role2Label.draw(drawingContext);		
		if(subset1Label!=null && showSubsetting)subset1Label.draw(drawingContext);
		if(subset2Label!=null && showSubsetting)subset2Label.draw(drawingContext);		
		if(redefine1Label!=null && showRedefining)redefine1Label.draw(drawingContext);
		if(redefine2Label!=null && showRedefining)redefine2Label.draw(drawingContext);
		
		positionNameLabel(drawingContext);		
		nameLabel.draw(drawingContext);
	}

	private void positionRedefiningLabel(Label label, Object endPointDiagramElement, Point2D endpoint, DrawingContext drawingContext) 
	{
		Direction direction=null;		
		if (endPointDiagramElement instanceof Node) direction = getPointDirection((Node)endPointDiagramElement, endpoint);
		else if (endPointDiagramElement instanceof Connection) direction = getPointDirection((Connection)endPointDiagramElement, endpoint);
		double x = 0, y = 0, marging = 10;
		double labelHeight = label.getSize().getHeight(); 
		double labelWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(label.getSource().getLabelText());
		switch (direction) {
			case NORTH:{
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
			case SOUTH:{
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
			case EAST:{
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
			default:{
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
	
	private void positionSubsettingLabel(Label label, Object endPointDiagramElement, Point2D endpoint, DrawingContext drawingContext) 
	{
		Direction direction=null;		
		if (endPointDiagramElement instanceof Node) direction = getPointDirection((Node)endPointDiagramElement, endpoint);
		else if (endPointDiagramElement instanceof Connection) direction = getPointDirection((Connection)endPointDiagramElement, endpoint);
		double x = 0, y = 0, marging = 10;
		double labelHeight = label.getSize().getHeight(); 
		double labelWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(label.getSource().getLabelText());
		switch (direction) {
			case NORTH:{
				if(showRoles()){
					x =	endpoint.getX() + marging;
					y = endpoint.getY() - (2*labelHeight + marging);
				}else{
					x =	endpoint.getX() + marging;
					y = endpoint.getY() - (labelHeight + marging);
				}
				break;
			}
			case SOUTH:{
				if(showRoles()){
					x =	endpoint.getX() + marging;
					y = endpoint.getY() + (labelHeight + marging);
				}else{
					x =	endpoint.getX() + marging;
					y = endpoint.getY() + marging;
				}
				break;
			}
			case EAST:{
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
			default:{
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
		int labelWidth=0;
		if(showOntoUmlStereotype() && nameLabel.getTypeLabelText() != null){
			int typeWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(nameLabel.getTypeLabelText());
			if(typeWidth> labelWidth) labelWidth = typeWidth;
		}	
		if(showName() && nameLabel.getNameLabelText() != null){
			int nameWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(nameLabel.getNameLabelText());
			if(nameWidth> labelWidth) labelWidth = nameWidth;
		}
		if(showMetaProperties() && nameLabel.getMetaPropertyLabelText() != null){
			int metaWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(nameLabel.getMetaPropertyLabelText());
			if(metaWidth> labelWidth) labelWidth = metaWidth;
		}
		// medium segment
		List<Line2D> segments = getSegments();
		if(segments.size()>0){
			Line2D middleSegment = getMiddleSegment();
			double x = (double) (middleSegment.getX2() + middleSegment.getX1() - labelWidth) / 2;
			double y = (double) (middleSegment.getY2() + middleSegment.getY1())/2;
			nameLabel.setAbsolutePos(x, y);			
			nameLabel.setSegment(middleSegment);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ReadingDirection getNameReadingDirection() {		
		return readingDirection; 
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNameReadingDirection(ReadingDirection dir) {
		readingDirection = dir;
	}	
}
