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
import java.awt.geom.Dimension2D;

import RefOntoUML.Classifier;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Property;
import RefOntoUML.impl.ClassImpl;
import RefOntoUML.impl.DataTypeImpl;
import br.ufes.inf.nemo.oled.draw.AbstractCompositeNode;
import br.ufes.inf.nemo.oled.draw.Compartment;
import br.ufes.inf.nemo.oled.draw.Compartment.Alignment;
import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.draw.DoubleDimension;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.DrawingContext.FontType;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.LabelSource;
import br.ufes.inf.nemo.oled.draw.SimpleLabel;
import br.ufes.inf.nemo.oled.model.RelationEndType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlModelElementLabelSource;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlNode;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class represents a Class element in the editor. It is responsible for
 * rendering the information in the editor.
 * 
 * @author Wei-ju Wu, John Guerson
 */
public final class ClassElement extends AbstractCompositeNode implements
		LabelSource, UmlNode  {

	private transient Classifier classData;
	private String classUUID; 
	
	private static final long serialVersionUID = 8767029215902619069L;
	private Compartment mainCompartment;
	private Compartment attributesCompartment;
	private Compartment operationsCompartment;
	private Label mainLabel;
	private Label ontoUmlLabel;
	private String ontoUmlStereotype;
	private boolean showOperations = false, showAttributes = false, showStereotypes = true;
	private static ClassElement prototype = new ClassElement();

	/**
	 * Returns the prototype instance.
	 * 
	 * @return the prototype instance
	 */
	public static ClassElement getPrototype() {
		return prototype;
	}
	
	/**
	 * Private constructor.
	 */
	private ClassElement() {
		mainCompartment = new Compartment();
		attributesCompartment = new Compartment();
		operationsCompartment = new Compartment();
		mainLabel = new SimpleLabel();
		mainLabel.setSource(this);
		mainLabel.setEditable(true);
		mainCompartment.addLabel(mainLabel);
		ontoUmlLabel = new SimpleLabel();
		mainCompartment.addLabel(ontoUmlLabel);
		mainCompartment.setParent(this);
		attributesCompartment.setParent(this);
		attributesCompartment.setAlignment(Alignment.LEFT);
		operationsCompartment.setParent(this);
		operationsCompartment.setAlignment(Alignment.LEFT);
		
		setupOntoUmlLabelSource();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone() {
		ClassElement cloned = (ClassElement) super.clone();
		if (classData != null) {
			//cloned.classData = RefOntoUMLHelper.clone(classData);
			//cloned.classData.eAdapters().add(cloned);
			
			cloned.setClassifier(ModelHelper.clone(classData));
		}
		cloned.mainLabel = (Label) mainLabel.clone();
		cloned.mainLabel.setSource(cloned);
		cloned.mainLabel.setEditable(mainLabel.isEditable());
		cloned.ontoUmlLabel = (Label) ontoUmlLabel.clone();
		cloned.ontoUmlStereotype = ontoUmlStereotype;		
		cloned.mainCompartment = (Compartment) mainCompartment.clone();
		cloned.mainCompartment.setParent(cloned);
		cloned.mainCompartment.removeAllLabels();
		cloned.mainCompartment.addLabel(cloned.ontoUmlLabel);
		cloned.mainCompartment.addLabel(cloned.mainLabel);
		cloned.attributesCompartment = (Compartment) attributesCompartment.clone();
		cloned.attributesCompartment.setParent(cloned);
		cloned.operationsCompartment = (Compartment) operationsCompartment.clone();
		cloned.operationsCompartment.setParent(cloned);
		
		cloned.setupOntoUmlLabelSource();
		
		return cloned;
	}
 
	public void setBackgroundColor(Color color)
	{
		mainCompartment.setBackground(color);
		attributesCompartment.setBackground(color);
		operationsCompartment.setBackground(color);
		invalidate();
	}
	
	public Color getBackgroundColor()
	{
		return mainCompartment.getBackground();
	}
	
	public void changeStereotypeTo(String stereotype)
	{
		ontoUmlStereotype = stereotype;
		setupOntoUmlLabelSource();
		invalidate();
	}
	
	/***
	 * Compares two elements, returns true if tey hold the same data, false otherwise
	 * @param snapshot
	 * @return
	 */
	public Boolean compareTo(ClassElement snapshot)
	{
		boolean ret = true;
		
		ret &= snapshot.getClassifier().getName().equals(classData.getName());
		ret &= snapshot.showAttributes() && showAttributes;
		ret &= snapshot.showOperations() && showOperations;
		
		return ret;
	}
	
	public void copyDataTo(ClassElement elm)
	{
		elm.setShowAttributes(showAttributes);
		elm.setShowOperations(showOperations);
		elm.setShowStereotypes(showStereotypes);
		elm.setSize(getSize().getWidth(), getSize().getHeight());
			
		//invalidate();
	}
	
	private void setupOntoUmlLabelSource()
	{
		ontoUmlLabel.setSource(new LabelSource() {

			private static final long serialVersionUID = 124766466850619305L;

			/**
			 * {@inheritDoc}
			 */
			public String getLabelText() {
				return ontoUmlStereotype;
			}

			/**
			 * {@inheritDoc}
			 */
			public void setLabelText(String aText) {
			}
		});
	}
	
	/**
	 * Returns the main label for testing purposes.
	 * 
	 * @return the main label
	 */
	public Label getMainLabel() {
		return mainLabel;
	}

	/**
	 * Returns the main compartment for testing purposes.
	 * 
	 * @return the main compartment
	 */
	public Compartment getMainCompartment() {
		return mainCompartment;
	}

	/**
	 * Sets the main compartment for testing purposes.
	 * 
	 * @param aCompartment
	 *            the compartment to set
	 */
	public void setMainCompartment(Compartment aCompartment) {
		mainCompartment = aCompartment;
	}

	/**
	 * Returns the attributes compartment for testing purposes.
	 * 
	 * @return the attributes compartment
	 */
	public Compartment getAttributesCompartment() {
		return attributesCompartment;
	}

	/**
	 * Sets the attributes compartment for testing purposes.
	 * 
	 * @param aCompartment
	 *            the compartment to set
	 */
	public void setAttributesCompartment(Compartment aCompartment) {
		attributesCompartment = aCompartment;
	}

	/**
	 * Returns the operations compartment for testing purposes.
	 * 
	 * @return the operations compartment
	 */
	public Compartment getOperationsCompartment() {
		return operationsCompartment;
	}

	/**
	 * Sets the operations compartment for testing purposes.
	 * 
	 * @param aCompartment
	 *            the compartment to set
	 */
	public void setOperationsCompartment(Compartment aCompartment) {
		operationsCompartment = aCompartment;
	}

	/**
	 * Sets the model element.
	 * 
	 * @param aModelElement
	 *            the model element
	 */
	public void setClassifier(Classifier classifier) {
		classData = classifier;
		
		if(classifier.eResource() != null)
			classUUID = ModelHelper.getUUIDFromElement(classifier);
		
		if (classData != null) {			
			ontoUmlStereotype = ModelHelper.getClassAsStereotype(classData);
		}
		reinitMainCompartment();
		reinitAttributesCompartment();
	}

	/**
	 * {@inheritDoc}
	 */
	public Classifier getClassifier() {
		
		//In case of deserialization, attempts to retrieve the element from model
		if(classData == null && classUUID != null)
		{
			RefOntoUML.Package model = ((StructureDiagram)getDiagram()).getRootPackage();
			classData = (Classifier) ModelHelper.getElementByUUID(model, classUUID);
		}
		
		return classData;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	 public void addedToDiagram(Diagram diagram) {
		 classUUID = ModelHelper.getUUIDFromElement(classData);
	 }
	
	/**
	 * Sets the showAttributes flag.
	 * 
	 * @param flag
	 *            true to show the attributes compartment, false otherwise
	 */
	public void setShowAttributes(boolean flag) {
		if (showAttributes && !flag) {
			setHeight(getSize().getHeight()	- attributesCompartment.getSize().getHeight());
		}
		showAttributes = flag;
		invalidate();
	}

	/**
	 * Returns the value of the showAttributes attribute.
	 * 
	 * @return the value of the showAttributes attribute
	 */
	public boolean showAttributes() {
		return showAttributes;
	}

	/**
	 * Sets the showOperations flag.
	 * 
	 * @param flag
	 *            true to show the operations compartment, false otherwise
	 */
	public void setShowOperations(boolean flag) {
		if (showOperations && !flag) {
			setHeight(getSize().getHeight()
					- attributesCompartment.getSize().getHeight());
		}
		showOperations = flag;
		invalidate();
	}

	/**
	 * Returns the value of the showOperations attribute.
	 * 
	 * @return the value of the showOperations attribute
	 */
	public boolean showOperations() {
		return showOperations;
	}

	/**
	 * Sets the showStereotypes flag.
	 * 
	 * @param flag
	 *            the value
	 */
	public void setShowStereotypes(boolean flag) {
		if (showStereotypes && !flag) {
			setHeight(getSize().getHeight()
					- mainCompartment.getSize().getHeight());
		}
		showStereotypes = flag;
		reinitMainCompartment();
		//mainCompartment.addLabel(ontoUmlLabel);
		//mainCompartment.addLabel(mainLabel);
		invalidate();
	}

	/**
	 * Returns the value of the showStereotypes attribute.
	 * 
	 * @return the status of the showStereotypes attribute
	 */
	public boolean showStereotypes() {
		return showStereotypes;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getLabelText() {
		if (getClassifier()==null) return "";
		else return getClassifier().getName();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLabelText(String aText) {
		getClassifier().setName(aText);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(DrawingContext drawingContext) {
		if (!isValid()) {
			mainLabel.setFontType(getMainLabelFontType());
			recalculateSize(drawingContext);
		}

		mainCompartment.draw(drawingContext);
		
		if (showAttributes)
			attributesCompartment.draw(drawingContext);
		if (showOperations)
			operationsCompartment.draw(drawingContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void invalidate() {
		mainCompartment.invalidate();
		attributesCompartment.invalidate();
		operationsCompartment.invalidate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid() {
		boolean result = mainCompartment.isValid();
		if (showAttributes) {
			result &= attributesCompartment.isValid();
		}
		if (showOperations) {
			result &= operationsCompartment.isValid();
		}
		return result;
	}

	/**
	 * Returns the main label font type. Dependent whether the class is abstract
	 * or not.
	 * 
	 * @return the FontType for the main label
	 */
	private FontType getMainLabelFontType() {
		return (classData.isIsAbstract()) ? FontType.ABSTRACT_ELEMENT
				: FontType.ELEMENT_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	public Label getLabelAt(double mx, double my) {
		Label label = mainCompartment.getLabelAt(mx, my);
		if (label == null && showAttributes) {
			label = attributesCompartment.getLabelAt(mx, my);
		}
		if (label == null && showOperations) {
			label = operationsCompartment.getLabelAt(mx, my);
		}
		return label;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean acceptsConnection(RelationType associationType,
			RelationEndType as, UmlNode with) {
		return true;
	}

	/**
	 * Reinitializes the main compartment with the contained labels.
	 */
	public void reinitMainCompartment() {
		mainCompartment.removeAllLabels();
		mainCompartment.addLabel(ontoUmlLabel);
		mainCompartment.addLabel(mainLabel);
	}

	public void reinitAttributesCompartment() 
	{		
		attributesCompartment.removeAllLabels();
		if(getClassifier() instanceof ClassImpl) {
			ClassImpl aclass = (ClassImpl) getClassifier(); 
			for (Property property : aclass.getOwnedAttribute()) {
 				Label label = new SimpleLabel();				
				label.setSource(new UmlModelElementLabelSource((StructureDiagram)getDiagram(),property));
				attributesCompartment.addLabel(label);
			}
		} else if(getClassifier() instanceof Enumeration) 
		{
			Enumeration aclass = (Enumeration) getClassifier(); 
			for (EnumerationLiteral literal : aclass.getOwnedLiteral()) {
				Label label = new SimpleLabel();				
				label.setSource(new UmlModelElementLabelSource((StructureDiagram)getDiagram(),literal));
				attributesCompartment.addLabel(label);
			}
		} else if((getClassifier() instanceof DataTypeImpl)) 
		{
			DataTypeImpl dataType = (DataTypeImpl) getClassifier();
			for (Property property : dataType.getOwnedAttribute()) {
				Label label = new SimpleLabel();
				label.setSource(new UmlModelElementLabelSource((StructureDiagram)getDiagram(),property));
				attributesCompartment.addLabel(label);
			}	
		}		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return"<<"+ModelHelper.getStereotype(getClassifier())+">> "+getClassifier().getName();
	}

	// ************************************************************************
	// ****** Size calculation
	// *********************************

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dimension2D getMinimumSize() {
		return new DoubleDimension(calculateMinimumWidth(),
				calculateMinimumHeight());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void recalculateSize(DrawingContext drawingContext) {
		recalculateMainCompartment(drawingContext);
		recalculateAttributesCompartment(drawingContext);
		recalculateOperationsCompartment(drawingContext);
		double totalWidth = calculateTotalWidth();
		// do not invalidate the sub allElements
		setSizePlain(totalWidth, calculateTotalHeight());
		// adjust main compartment, in case the other compartments made this
		// element wider
		mainCompartment.setWidth(totalWidth);
		attributesCompartment.setWidth(totalWidth);
		resizeLastCompartmentToFit();
		notifyNodeResized();
	}

	/**
	 * Recalculates the main compartment.
	 * 
	 * @param drawingContext
	 *            the DrawingContext
	 */
	private void recalculateMainCompartment(DrawingContext drawingContext) {
		mainLabel.setFontType(getMainLabelFontType());
		mainCompartment.recalculateSize(drawingContext);
	}

	/**
	 * Recalculates the attributes compartment.
	 * 
	 * @param drawingContext
	 *            the DrawingContext
	 */
	private void recalculateAttributesCompartment(DrawingContext drawingContext) {
		attributesCompartment.recalculateSize(drawingContext);
		attributesCompartment.setOrigin(0, getMainCompartmentHeight());
	}

	/**
	 * Recalculates the operations compartment.
	 * 
	 * @param drawingContext
	 *            the DrawingContext
	 */
	private void recalculateOperationsCompartment(DrawingContext drawingContext) {
		operationsCompartment.recalculateSize(drawingContext);
		operationsCompartment.setOrigin(0, getOperationsY());
	}

	/**
	 * Returns the y position for the operations compartment.
	 * 
	 * @return the y position for the operations compartment
	 */
	private double getOperationsY() {
		double result = getMainCompartmentHeight();
		if (showAttributes)
			result += attributesCompartment.getSize().getHeight();
		return result;
	}

	/**
	 * Determines the total height of this element.
	 * 
	 * @return the total height
	 */
	private double calculateTotalHeight() {
		double compartmentHeightSum = getMainCompartmentHeight();
		if (showAttributes) {
			compartmentHeightSum += attributesCompartment.getMinimumSize()
					.getHeight();
		}
		if (showOperations) {
			compartmentHeightSum += operationsCompartment.getMinimumSize()
					.getHeight();
		}

		if (compartmentHeightSum > getSize().getHeight() && !showAttributes
				&& !showOperations) {
			return getSize().getHeight();
		}
		return Math.max(compartmentHeightSum, getSize().getHeight());
	}

	/**
	 * Determines the total width of this element.
	 * 
	 * @return the total width
	 */
	private double calculateTotalWidth() {
		double maxwidth = Math.max(mainCompartment.getSize().getWidth(),
				getSize().getWidth());
		if (showAttributes) {
			maxwidth = Math.max(maxwidth, attributesCompartment.getSize()
					.getWidth());
		}
		if (showOperations) {
			maxwidth = Math.max(maxwidth, operationsCompartment.getSize()
					.getWidth());
		}
		return maxwidth;
	}

	/**
	 * Calculates the minimum width of this element.
	 * 
	 * @return the minimum width
	 */
	private double calculateMinimumWidth() {
		double minimumWidth = mainCompartment.getMinimumSize().getWidth();
		if (showAttributes) {
			minimumWidth = Math.max(minimumWidth, attributesCompartment
					.getMinimumSize().getWidth());
		}
		if (showOperations) {
			minimumWidth = Math.max(minimumWidth, operationsCompartment
					.getMinimumSize().getWidth());
		}
		return minimumWidth;
	}

	/**
	 * Calculates the minimum height of this element.
	 * 
	 * @return the minimum height
	 */
	private double calculateMinimumHeight() {
		double minimumHeight = mainCompartment.getMinimumSize().getHeight();
		if (showAttributes) {
			minimumHeight += attributesCompartment.getMinimumSize().getHeight();
		}
		if (showOperations) {
			minimumHeight += operationsCompartment.getMinimumSize().getHeight();
		}
		return minimumHeight;
	}

	/**
	 * Resizes the last visible compartment to fit within the total height.
	 */
	private void resizeLastCompartmentToFit() {
		Compartment lastCompartment = getLastVisibleCompartment();
		double diffHeight = getSize().getHeight() - getCompartmentHeightSum();
		lastCompartment.setHeight(lastCompartment.getSize().getHeight()
				+ diffHeight);
	}

	/**
	 * Returns the last visible compartment.
	 * 
	 * @return the last visible compartment
	 */
	private Compartment getLastVisibleCompartment() {
		Compartment lastCompartment = mainCompartment;
		if (showAttributes)
			lastCompartment = attributesCompartment;
		if (showOperations)
			lastCompartment = operationsCompartment;
		return lastCompartment;
	}

	/**
	 * Returns the sum of compartment heights.
	 * 
	 * @return the sum of compartment heights
	 */
	private double getCompartmentHeightSum() {
		double result = mainCompartment.getSize().getHeight();
		if (showAttributes) {
			result += attributesCompartment.getSize().getHeight();
		}
		if (showOperations) {
			result += operationsCompartment.getSize().getHeight();
		}
		return result;
	}

	/**
	 * Returns the height of the compartment.
	 * 
	 * @return the height of the compartment
	 */
	private double getMainCompartmentHeight() {
		return mainCompartment.getSize().getHeight();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNestable() {
		return true;
	}
}

