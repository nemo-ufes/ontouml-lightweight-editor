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
package br.inf.ufes.nemo.oled.umldraw.structure;

import java.awt.Color;
import java.awt.geom.GeneralPath;

import RefOntoUML.Generalization;
import br.inf.ufes.nemo.oled.draw.AbstractCompositeNode;
import br.inf.ufes.nemo.oled.draw.DrawingContext;
import br.inf.ufes.nemo.oled.draw.DrawingContext.FontType;
import br.inf.ufes.nemo.oled.draw.Label;
import br.inf.ufes.nemo.oled.draw.LabelSource;
import br.inf.ufes.nemo.oled.draw.SimpleLabel;
import br.inf.ufes.nemo.oled.umldraw.shared.UmlModelElementLabelSource;
import br.inf.ufes.nemo.oled.umldraw.structure.AssociationElement.ReadingDirection;

/**
 * This class implements a name nameLabel for an association. Associations have
 * special name labels which can display an arrow for reading directions.
 * 
 * @author Wei-ju Wu
 * @version 1.0
 */
public class AssociationLabel extends AbstractCompositeNode implements Label,
		LabelSource {

	private static final long serialVersionUID = 4398704922594883346L;
	private Label typeLabel;
	private Label nameLabel;
	private BaseConnection association;
	private boolean editable;
	
	/**
	 * Constructor.
	 */
	public AssociationLabel() {
		setTypeLabel(new SimpleLabel());
		setNameLabel(new SimpleLabel());
	}

	/**
	 * Returns the wrapped nameLabel.
	 * 
	 * @return the wrapped nameLabel
	 */
	public Label getNameLabel() {
		return nameLabel;
	}

	/**
	 * Sets a Label. This method is exposed for unit testing.
	 * 
	 * @param aLabel
	 *            the nameLabel
	 */
	public void setNameLabel(Label aLabel) {
		nameLabel = aLabel;
		nameLabel.setSource(this);
		nameLabel.setParent(this);
	}

	/**
	 * Returns the wrapped nameLabel.
	 * 
	 * @return the wrapped nameLabel
	 */
	public Label getTypeLabel() {
		return typeLabel;
	}

	/**
	 * Sets a Label. This method is exposed for unit testing.
	 * 
	 * @param aLabel
	 *            the nameLabel
	 */
	public void setTypeLabel(Label aLabel) {
		typeLabel = aLabel;
		typeLabel.setSource(this);
		typeLabel.setParent(this);
	}

	/**
	 * Sets the association.
	 * 
	 * @param assoc
	 *            the AssociationElement
	 */
	public void setAssociation(BaseConnection assoc) {
		association = assoc;
	}

	/**
	 * {@inheritDoc}
	 */
	public Label getLabelAt(double mx, double my) {
		if (contains(mx, my))
			return this;
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSource(LabelSource aSource) {
	}

	/**
	 * {@inheritDoc}
	 */
	public LabelSource getSource() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getNameLabelText() {
		return nameLabel.getNameLabelText();
	}

	public String getTypeLabelText() {
		return typeLabel.getNameLabelText();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNameLabelText(String text) {
		nameLabel.setNameLabelText(text);
	}

	public void setTypeLabelText(String text) {
		typeLabel.setNameLabelText(text);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setFontType(DrawingContext.FontType aFontType) {
		nameLabel.setFontType(aFontType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void centerHorizontally() {
		nameLabel.centerHorizontally();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(DrawingContext drawingContext) {

		if (association instanceof AssociationElement) {
			AssociationElement assocElement = (AssociationElement) association;

			if (assocElement.showOntoUmlStereotype()) {
				// Classifier stereotype =
				// ((UmlRelation)association.getModelElement()).getStereotype();
				//typeLabel.setSource(new SimpleLabelSource());

				typeLabel.setSource(new LabelSource() {

					private static final long serialVersionUID = -5187481263917156632L;

					@Override
					public void setLabelText(String aText) { }
					
					@Override
					public String getLabelText() {
						return association.getOntoUmlStereotype();
					}
				});

				// Calculate offset for vertical alignment of the stereotype
				if (typeLabel.getOrigin().getY() == nameLabel.getOrigin()
						.getY())
					typeLabel.setOrigin(typeLabel.getOrigin().getX(), typeLabel
							.getOrigin().getY()
							- drawingContext.getFontMetrics(FontType.DEFAULT)
									.getHeight());

				if(association.showOntoUmlStereotype())
					typeLabel.draw(drawingContext);
			}

			if (assocElement.showName() && getLabelText() != null) {
				nameLabel.draw(drawingContext);
				drawDirection(drawingContext);
			}

		}

		else if (association instanceof GeneralizationElement) {
			Generalization generalization = ((GeneralizationElement) association).getGeneralization();	
			if(generalization.getGeneralizationSet().size() > 0)
			{
				typeLabel.setSource(new UmlModelElementLabelSource(generalization.getGeneralizationSet().get(0)));
				typeLabel.draw(drawingContext);
			}
		}

	}

	/**
	 * Draws the direction triangle.
	 * 
	 * @param drawingContext
	 *            the drawing context
	 */
	private void drawDirection(DrawingContext drawingContext) {
		ReadingDirection readingDirection = ((AssociationElement) association)
				.getNameReadingDirection();

		if (readingDirection == ReadingDirection.LEFT_RIGHT) {
			drawTriangleLeftRight(drawingContext);
		} else if (readingDirection == ReadingDirection.RIGHT_LEFT) {
			drawTriangleRightLeft(drawingContext);
		}
	}

	/**
	 * Draws the triangle facing to the right.
	 * 
	 * @param drawingContext
	 *            the drawing context
	 */
	private void drawTriangleLeftRight(DrawingContext drawingContext) {
		GeneralPath trianglePath = new GeneralPath();
		double height = nameLabel.getSize().getHeight() - 6;
		double x = nameLabel.getAbsoluteX2() + 3, y = nameLabel.getAbsoluteY1() + 3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x + 5, y + height / 2);
		trianglePath.lineTo(x, y + height);
		trianglePath.closePath();
		drawingContext.draw(trianglePath, Color.BLACK);
	}

	/**
	 * Draws the triangle facing to the left.
	 * 
	 * @param drawingContext
	 *            the drawing context
	 */
	private void drawTriangleRightLeft(DrawingContext drawingContext) {
		GeneralPath trianglePath = new GeneralPath();
		double height = nameLabel.getSize().getHeight() - 6;
		double x = nameLabel.getAbsoluteX1() - 3, y = nameLabel.getAbsoluteY1() + 3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x - 5, y + height / 2);
		trianglePath.lineTo(x, y + height);
		trianglePath.closePath();
		drawingContext.draw(trianglePath, Color.BLACK);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getLabelText() {
		if (association.getClassifier() == null)
			return "";
		return association.getClassifier().getName();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLabelText(String aText) {
		if (association.getClassifier() != null) {
			association.getClassifier().setName(aText);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
