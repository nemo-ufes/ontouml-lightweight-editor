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
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.oled.draw.AbstractCompositeNode;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.DrawingContext.FontType;
import br.ufes.inf.nemo.oled.draw.GeometryUtil;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.LabelSource;
import br.ufes.inf.nemo.oled.draw.SimpleLabel;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement.ReadingDirection;

/**
 * This class implements a name nameLabel for an association. Associations have
 * special name labels which can display an arrow for reading directions.
 * 
 * @author Wei-ju Wu, John Guerson
 */
public class AssociationLabel extends AbstractCompositeNode implements Label,
		LabelSource {

	private static final long serialVersionUID = 4398704922594883346L;
	private Label typeLabel;
	private Label nameLabel;
	private Label metapropertyLabel;
	private double xpos;
	private double ypos;
	private BaseConnection association;
	private boolean editable;
	private transient DrawingContext context;
	
	/**
	 * Constructor.
	 */
	public AssociationLabel() {
		setTypeLabel(new SimpleLabel());
		setNameLabel(new SimpleLabel());
		setMetaPropertyLabel(new SimpleLabel());
	}

	/**
	 * Returns the wrapped nameLabel.
	 * 
	 * @return the wrapped nameLabel
	 */
	public Label getNameLabel() {
		return nameLabel;
	}

	public Label getMetaPropertyLabel()
	{
		return metapropertyLabel;
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

	public void setMetaPropertyLabel(Label aLabel)
	{
		metapropertyLabel = aLabel;
		metapropertyLabel.setSource(this);
		metapropertyLabel.setParent(this);
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

	public BaseConnection getAssociation()
	{
		return association;		
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
		if (nameLabel.getNameLabelText()!=null)
			return nameLabel.getNameLabelText();
		else
			return "";
	}

	public String getTypeLabelText() {
		if (typeLabel.getNameLabelText()!=null)
			return typeLabel.getNameLabelText();
		else
			return "";
	}
	
	public String getMetaPropertyLabelText()
	{
		if (metapropertyLabel.getNameLabelText()!=null)
			return metapropertyLabel.getNameLabelText();
		else
			return "";
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

	public void setMetaPropertyLabelText(String text)
	{
		metapropertyLabel.setNameLabelText(text);
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
		typeLabel.centerHorizontally();
		metapropertyLabel.centerHorizontally();
	}
	
	public int getTypeWidth()
	{
		return context.getFontMetrics(FontType.DEFAULT).stringWidth(getTypeLabelText());
	}

	public int getNameWidth()
	{
		return context.getFontMetrics(FontType.DEFAULT).stringWidth(getNameLabelText());
	}
	
	public int getMetaPropertyWidth()
	{
		return context.getFontMetrics(FontType.DEFAULT).stringWidth(getMetaPropertyLabelText());
	}
	
	public int getLabelWidth()
	{
		int labelWidth=0;
		if(typeLabel != null){
			int typeWidth = context.getFontMetrics(FontType.DEFAULT).stringWidth(getTypeLabelText());
			if(typeWidth> labelWidth) labelWidth = typeWidth;
		}	
		if(nameLabel != null){
			int nameWidth = context.getFontMetrics(FontType.DEFAULT).stringWidth(getNameLabelText());
			if(nameWidth> labelWidth) labelWidth = nameWidth;
		}
		if(metapropertyLabel != null){
			int metaWidth = context.getFontMetrics(FontType.DEFAULT).stringWidth(getMetaPropertyLabelText());
			if(metaWidth> labelWidth) labelWidth = metaWidth;
		}
		return labelWidth;
	}
	
	@Override
	public void setAbsolutePos(double xpos, double ypos) 
	{	
		super.setAbsolutePos(xpos, ypos);
		if (!GeometryUtil.getInstance().equals(xpos, getAbsoluteX1()) ||
		!GeometryUtil.getInstance().equals(ypos, getAbsoluteY1())) {
			this.xpos=xpos-getParent().getAbsoluteX1();
			this.ypos=ypos-getParent().getAbsoluteY1();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(DrawingContext drawingContext) {
		context = drawingContext;		
		if (association instanceof AssociationElement) {
			final AssociationElement assocElement = (AssociationElement) association;
			
			int nameWidth = 0;			
			int typeWidth = 0;
			int metaWidth = 0;			
			int labelWidth = getLabelWidth();
			
			if (assocElement.showOntoUmlStereotype()) typeWidth = getTypeWidth();
			if (assocElement.showName() && getLabelText() != null) nameWidth = getNameWidth();
			if (assocElement.getRelationship() instanceof Meronymic && assocElement.showMetaProperties()) metaWidth = getMetaPropertyWidth();
			
			if (assocElement.showOntoUmlStereotype()) {
				typeLabel.setSource(new LabelSource() {
					private static final long serialVersionUID = -5187481263917156632L;
					@Override
					public void setLabelText(String aText) { }					
					@Override
					public String getLabelText() {
						return association.getOntoUmlStereotype();
					}
				});
				
				if(assocElement.showName()){										
					typeLabel.setOrigin(xpos+((labelWidth-typeWidth)/2), ypos- drawingContext.getFontMetrics(FontType.DEFAULT).getHeight());					
				}else{
					typeLabel.setOrigin(xpos+((labelWidth-typeWidth)/2), ypos);
				}
				typeLabel.draw(drawingContext);
			}
			
			if (assocElement.showName() && getLabelText() != null) {				
				nameLabel.setSource(new LabelSource() {
					private static final long serialVersionUID = -5187481263917156632L;
					@Override
					public void setLabelText(String aText) {}					
					@Override
					public String getLabelText() {
						return assocElement.getAssociation().getName(); 
					}
				});

				nameLabel.setOrigin(xpos+((labelWidth-nameWidth)/2), ypos);
				
				nameLabel.draw(drawingContext);
				drawDirection(drawingContext);
			}
			
			if(assocElement.getRelationship() instanceof Meronymic && assocElement.showMetaProperties()){
				metapropertyLabel.setSource(new LabelSource() {
					private static final long serialVersionUID = -5187481263917156632L;
					@Override
					public void setLabelText(String aText) {}					
					@Override
					public String getLabelText() {
						Meronymic m = (Meronymic)assocElement.getRelationship();
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
				});		

				if(assocElement.showName() || assocElement.showOntoUmlStereotype()){										
					metapropertyLabel.setOrigin(xpos+((labelWidth-metaWidth)/2), ypos+ drawingContext.getFontMetrics(FontType.DEFAULT).getHeight());					
				}else{
					metapropertyLabel.setOrigin(xpos+((labelWidth-metaWidth)/2), ypos);
				}
				metapropertyLabel.draw(drawingContext);				
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
		}else if (readingDirection == ReadingDirection.UP_BOTTOM) {
			drawTriangleUpBottom(drawingContext);
		}else if (readingDirection == ReadingDirection.BOTTOM_UP) {
			drawTriangleBottomUp(drawingContext);
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

	private void drawTriangleBottomUp(DrawingContext drawingContext) {
		GeneralPath trianglePath = new GeneralPath();
		double height = nameLabel.getSize().getHeight() - 6;
		double x = nameLabel.getAbsoluteX1() - 3, y = nameLabel.getAbsoluteY1() + 3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x - 5, y + height / 2);
		trianglePath.lineTo(x, y + height);
		trianglePath.closePath();
		drawingContext.draw(trianglePath, Color.BLACK);
	}
	
	private void drawTriangleUpBottom(DrawingContext drawingContext) {
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
		if (association.getClassifier() == null || association.getClassifier().getName()==null)
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

	@Override
	public FontType getFontType() {
		return getFontType();
	}

}
