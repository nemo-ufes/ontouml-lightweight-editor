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
import java.awt.geom.Line2D;
import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Meronymic;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.oled.draw.AbstractCompositeNode;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramOperations;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.DrawingContext.FontType;
import br.ufes.inf.nemo.oled.draw.GeometryUtil;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.LabelSource;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.draw.NullSelection;
import br.ufes.inf.nemo.oled.draw.Selection;
import br.ufes.inf.nemo.oled.draw.SimpleLabel;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement.ReadingDesign;

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
	private int assocLabelWidth; //the sum of the width of type/name/meta-property labels
	private double xpos;
	private double ypos;
	private BaseConnection association;
	private Line2D segment; // association segment in which this label is at
	private boolean editable;
	private transient DrawingContext context;
	public enum ReadingDirection { UNDEFINED, LEFT_RIGHT, RIGHT_LEFT, BOTTOM_UP, UP_BOTTOM };
	private ReadingDirection readingDirection = ReadingDirection.UNDEFINED;
	
	public void setSegment(Line2D segment)
	{
		this.segment = segment;
	}
		
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
	
	/** This is only needed if we choose to not move the labels in selection */
	public Selection getSelection(DiagramOperations operations) {
		return NullSelection.getInstance();
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
		assocLabelWidth = 0;
		if(typeLabel != null){
			int typeWidth = context.getFontMetrics(FontType.DEFAULT).stringWidth(getTypeLabelText());
			if(typeWidth> assocLabelWidth) assocLabelWidth = typeWidth;
		}	
		if(nameLabel != null){
			int nameWidth = context.getFontMetrics(FontType.DEFAULT).stringWidth(getNameLabelText());
			if(nameWidth> assocLabelWidth) assocLabelWidth = nameWidth;
		}
		if(metapropertyLabel != null){
			int metaWidth = context.getFontMetrics(FontType.DEFAULT).stringWidth(getMetaPropertyLabelText());
			if(metaWidth> assocLabelWidth) assocLabelWidth = metaWidth;
		}
		return assocLabelWidth;
	}
	
	public double getMaximumY2()
	{
		double labelY2=0;
		if(typeLabel != null){	
			if (association.showOntoUmlStereotype()) {			
				if(typeLabel.getAbsoluteY2()> labelY2) labelY2 = typeLabel.getAbsoluteY2();
			}
		}	
		if(nameLabel != null){
			if (((AssociationElement)association).showName() && getLabelText() != null) {			
				if(nameLabel.getAbsoluteY2()> labelY2) labelY2 = nameLabel.getAbsoluteY2();
			}
		}
		if(metapropertyLabel != null){
			if (association.getRelationship() instanceof Meronymic && ((AssociationElement)association).showMetaProperties()){
				if(metapropertyLabel.getAbsoluteY2()> labelY2) labelY2 = metapropertyLabel.getAbsoluteY2();
			}
		}
		return labelY2;
	}
	public double getMinimumY1()
	{
		double labelY1=1000;
		if(typeLabel != null){	
			if (association.showOntoUmlStereotype()) {
				if(typeLabel.getAbsoluteY1()< labelY1) labelY1 = typeLabel.getAbsoluteY1();
			}
		}	
		if(nameLabel != null){
			if (((AssociationElement)association).showName() && getLabelText() != null) {
				if(nameLabel.getAbsoluteY1()<labelY1) labelY1 = nameLabel.getAbsoluteY1();
			}
		}
		if(metapropertyLabel != null){
			if (association.getRelationship() instanceof Meronymic && ((AssociationElement)association).showMetaProperties()){
				if(metapropertyLabel.getAbsoluteY1()< labelY1) labelY1 = metapropertyLabel.getAbsoluteY1();
			}
		}
		return labelY1;
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
				
				if(((AssociationElement) association).getReadingDesign() != ReadingDesign.UNDEFINED) {
					drawDirection(drawingContext);
				}
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
	private void drawDirection(DrawingContext drawingContext) 
	{
		setReadingDirection(((AssociationElement) association).getReadingDesign());
		drawReadingDirection(context);		
	}

	/** 
	 * Set the reading direction i.e. left-right, right-left, bottom-up or up-bottom, according to the reading design
	 *  of the association i.e. according to the values to-source or to-destination
	 *  
	 *  @param readingDesign
	 */
	private void setReadingDirection(ReadingDesign readingDesign)
	{
		Node node1 = ((AssociationElement) association).getNode1();		
		Node node2 = ((AssociationElement) association).getNode2();
		Connection connection1 = ((AssociationElement) association).getConnection1();
		Connection connection2 = ((AssociationElement) association).getConnection2();
		
		double srcy1 = 0; double srcy2 = 0; double srcx1 = 0; double srcx2 = 0;
		double tgtx1 = 0; double tgtx2 = 0; double tgty1 = 0; double tgty2 = 0;  
		if(node1 !=null) { srcy1 = node1.getAbsoluteY1(); srcx1 = node1.getAbsoluteX1(); srcx2 = node1.getAbsoluteX2(); srcy2 = node1.getAbsoluteY2();}
		if(node2 !=null) { tgty1 = node2.getAbsoluteY1(); tgtx1 = node2.getAbsoluteX1(); tgtx2 = node2.getAbsoluteX2(); tgty2 = node2.getAbsoluteY2(); }
		if(connection1 !=null) { srcy1 = connection1.getAbsoluteY1(); srcx1 = connection1.getAbsoluteX1(); srcx2 = connection1.getAbsoluteX2(); srcy2 = connection1.getAbsoluteY2(); }
		if(connection2 !=null) { tgty1 = connection2.getAbsoluteY1(); tgtx1 = connection2.getAbsoluteX1(); tgtx2 = connection2.getAbsoluteX2(); tgty2 = connection2.getAbsoluteY2(); }
		
		Relationship rel = ((AssociationElement) association).getRelationship();
		Type srcType = ((Association)rel).getMemberEnd().get(0).getType();
		
		Type class1=null; Type class2=null; Type rel1=null; Type rel2=null;
		if(node1!=null) class1 = ((ClassElement)node1).getClassifier();
		if(node2!=null) class2 = ((ClassElement)node2).getClassifier();
		if(connection1!=null) rel1 = (Type)((AssociationElement)connection1).getRelationship();
		if(connection2!=null) rel2 = (Type)((AssociationElement)connection2).getRelationship();
		
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
			if(((AssociationElement) association).getReadingDesign() == ReadingDesign.DESTINATION) readingDirection = direction;
			else readingDirection = inverseDirection;			
		}
		if(rel1!=null && srcType.equals(rel1)) {
			if(((AssociationElement) association).getReadingDesign() == ReadingDesign.DESTINATION) readingDirection = direction;
			else readingDirection = inverseDirection;
		}
		if(class2!=null && srcType.equals(class2)) {
			if(((AssociationElement) association).getReadingDesign() == ReadingDesign.SOURCE) readingDirection = direction;
			else readingDirection = inverseDirection;
		}
		if(rel2!=null && srcType.equals(rel2)) {			
			if(((AssociationElement) association).getReadingDesign() == ReadingDesign.SOURCE) readingDirection = direction;
			else readingDirection = inverseDirection;
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
	
	private void drawTriangleUpBottom(DrawingContext drawingContext) {
		GeneralPath trianglePath = new GeneralPath();
		double height = nameLabel.getSize().getHeight() - 6;
		double x = nameLabel.getAbsCenterX(), y = getMaximumY2()+3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x + height / 2, y + 5);
		trianglePath.lineTo(x + height, y);
		trianglePath.closePath();
		drawingContext.draw(trianglePath, Color.BLACK);
	}
	
	private void drawTriangleBottomUp(DrawingContext drawingContext) {
		GeneralPath trianglePath = new GeneralPath();
		double height = nameLabel.getSize().getHeight() - 6;
		double x = nameLabel.getAbsCenterX() , y = getMinimumY1() - 3;
		trianglePath.moveTo(x, y);
		trianglePath.lineTo(x - height / 2, y-5);
		trianglePath.lineTo(x - height, y);
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
