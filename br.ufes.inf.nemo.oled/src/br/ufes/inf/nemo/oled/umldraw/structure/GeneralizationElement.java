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
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import br.ufes.inf.nemo.oled.draw.CompositeNode;
import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.DrawingContext.FontType;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.RectilinearConnection;
import br.ufes.inf.nemo.oled.draw.SimpleLabel;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlLabelFormatter;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlModelElementLabelSource;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * An generalization connection.
 *
 * @author Antognoni Albuquerque, John Guerson
 */
public final class GeneralizationElement extends BaseConnection {

	private static final long serialVersionUID = 3261336029815928290L;
	private static GeneralizationElement prototype = new GeneralizationElement();
	private Label nameLabel;
	private boolean showName = true;

	//When dealing with a generalization (model element) which has been serialized and deserialized, 
	//not all properties are loaded, like eContainer (although the generalization element has a UUID)
	//which are essential for changing/deleting the generalization.
	//Maybe it's because it is seen as a feature of its container and so, should be 
	//accessed only through it, not directly. As a workaround, we keep track of the 
	//generalization specific and the general.
	
	private String specificUUID;
	private String generalUUID;
	
	/**
	 * Returns the prototype instance.
	 * @return the prototype instance
	 */
	public static GeneralizationElement getPrototype() { return prototype; }

	@Override
	public Object clone() {
		GeneralizationElement cloned = (GeneralizationElement) super.clone();
		cloned.setupNameLabel();
		cloned.nameLabel.setParent(nameLabel.getParent());
		return cloned;
	}
	
	public Generalization getGeneralization()
	{
		return (Generalization) getRelationship();
	}
	
	public Classifier getSpecific()
	{
		RefOntoUML.Package model = ((StructureDiagram)getDiagram()).getRootPackage();
		return (Classifier) ModelHelper.getElementByUUID(model, specificUUID);
	}
	
	public Classifier getGeneral()
	{
		RefOntoUML.Package model = ((StructureDiagram)getDiagram()).getRootPackage();
		return (Classifier) ModelHelper.getElementByUUID(model, generalUUID);
	}
	
	/**
	 * Private constructor.
	 */
	private GeneralizationElement() {
		setConnection(new RectilinearConnection(this));
		setIsDashed(false);
		setupNameLabel();
	}

	/**
	 * Sets the name label.
	 */
	public void setupNameLabel()
	{
		nameLabel = new SimpleLabel();
		if(getGeneralization() != null)
			if(getGeneralization().getGeneralizationSet().size() > 0)
			{
				nameLabel.setSource(new UmlModelElementLabelSource((StructureDiagram)getDiagram(),getGeneralization()));
			}
	}
	
	/**
	 * Returns the name label.
	 * @return the name label
	 */
	public Label getNameLabel() { return nameLabel; }
	
	@Override
	public void setParent(CompositeNode parent) {
		super.setParent(parent);
		nameLabel.setParent(parent);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(DrawingContext drawingContext) {
		super.draw(drawingContext);
		drawInheritanceArrow(drawingContext, calculateRotationInEndPoint2());
		if(getGeneralization().getGeneralizationSet().size() > 0 && showName)
			drawNameLabel(drawingContext);
	}

	/**
	 * Draws the fat inheritance arrow.
	 * @param drawingContext the drawing context
	 * @param rotationTransform the rotation transform
	 */
	private void drawInheritanceArrow(DrawingContext drawingContext,
			AffineTransform rotationTransform) {
		Point2D endpoint = getEndPoint2();
		double x = endpoint.getX(), y = endpoint.getY();
		GeneralPath arrow = new GeneralPath();
		arrow.moveTo(x - 11, y - 7);
		arrow.lineTo(x, y);
		arrow.lineTo(x - 11, y + 7);
		arrow.closePath();
		arrow.transform(rotationTransform);
		drawingContext.draw(arrow, Color.WHITE);
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}

	public boolean showName() {
		return showName;
	}
	
	/**
	 * Draws the connection labels.
	 * @param drawingContext the DrawingContext
	 */
	private void drawNameLabel(DrawingContext drawingContext) {
		
		if(nameLabel.getSource() == null && getGeneralization().getGeneralizationSet().size() > 0)
		{
			nameLabel.setSource(new UmlModelElementLabelSource((StructureDiagram)getDiagram(),getGeneralization()));
		}
		
		positionNameLabel(drawingContext);
		nameLabel.draw(drawingContext);
	}
	
	/**
	 * Sets the position for the name label.
	 */
	private void positionNameLabel(DrawingContext drawingContext) {

		String labelText = UmlLabelFormatter.getLabelTextFor(((UmlModelElementLabelSource)nameLabel.getSource()).getNamedElement());
		int labelWidth = drawingContext.getFontMetrics(FontType.DEFAULT).stringWidth(labelText);			
		
		List<Line2D> segments = getSegments();
		Line2D middlesegment = segments.get(segments.size() / 2);
		int x = (int) (middlesegment.getX2() + middlesegment.getX1() - labelWidth) / 2;
		int y = (int) (middlesegment.getY2() + middlesegment.getY1()) / 2;
		
		nameLabel.setAbsolutePos(x, y);
	}
	
	@Override
	public String toString()
	{
		return ModelHelper.getStereotype(getRelationship())+" "+((RefOntoUML.Generalization)getRelationship()).getSpecific().getName()+" -> "+((RefOntoUML.Generalization)getRelationship()).getGeneral().getName();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	 public void addedToDiagram(Diagram diagram) {
		 super.addedToDiagram(diagram);
		 
		 generalUUID = ModelHelper.getUUIDFromElement(getGeneralization().getGeneral());
		 specificUUID = ModelHelper.getUUIDFromElement(getGeneralization().getSpecific());
	 }
}
