/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
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

package br.ufes.inf.nemo.oled.ui.diagram.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Classifier;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Property;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.GeneralizationImpl;
import br.ufes.inf.nemo.oled.draw.CompositeElement;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramEditorNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramEditorNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.util.ModelHelper;


/**
 * This is an undoable creation command for a Connection.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque
 * @version 1.1
 */
public class AddConnectionCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = 2924451842640450250L;
	private DiagramElement element;
	private CompositeElement parent;
	private Classifier source;
	private Classifier target;

	/**
	 * Constructor.
	 * @param editorNotification a DiagramEditorNotification object
	 * @param structureDiagram the parent component
	 * @param conn the element created
	 * @param aTarget 
	 * @param aSource 
	 */
	public AddConnectionCommand(DiagramEditorNotification editorNotification, CompositeElement parent, UmlConnection conn, Classifier aSource, Classifier aTarget, UmlProject project) {
		this.parent = parent;
		this.project = project;
		this.notification = editorNotification;
		element = conn;
		source = aSource;
		target = aTarget;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();
				
		if (element instanceof BaseConnection) {
			BaseConnection connection = (BaseConnection) element;
			
			if (connection.getRelationship() instanceof GeneralizationImpl == false)
			{
				project.getEditingDomain().getCommandStack().undo();
			}	
			else
			{
				GeneralizationImpl generalization  = (GeneralizationImpl) connection.getRelationship();
	    		EcoreUtil.delete(generalization);
			}
		}
				
		parent.removeChild(element);
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(element);
		notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, NotificationType.UNDO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		redo = true;
		super.redo();
		run();
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
	    
		//Adds the element to the model
		if (element instanceof BaseConnection) {
			
			BaseConnection connection = (BaseConnection) element;
			
			if (connection.getRelationship() instanceof GeneralizationImpl)
			{
				GeneralizationImpl generalization  = (GeneralizationImpl) connection.getRelationship();
	    		generalization.setSpecific(source);
	    		generalization.setGeneral(target);
			}
			else if(connection.getRelationship() instanceof AssociationImpl)
			{
				AssociationImpl association  = (AssociationImpl) connection.getRelationship();
	    		Property node1Property = ModelHelper.getDefaultOwnedEnd(source);
	    		Property node2Property = ModelHelper.getDefaultOwnedEnd(target);
	    		
	    		//TODO Change this. 
	    		// Remove the if(association instanceof DirectedBinaryAssociationImpl...
	    		// 
	    		
	    		association.getOwnedEnd().add(node1Property);
	    		association.getOwnedEnd().add(node2Property);
	    		
	    		association.getMemberEnd().add(node1Property);
	    		association.getMemberEnd().add(node2Property);
	    		
	    		if(association instanceof DirectedBinaryAssociationImpl == false 
	    				&& association instanceof FormalAssociationImpl == false 
	    				&& association instanceof MaterialAssociation == false)
	    		{
		    		if(node1Property.getType() instanceof DataTypeImpl)
		    			association.getNavigableOwnedEnd().add(node1Property);
		    		
		    		if(node2Property.getType() instanceof DataTypeImpl)
		    			association.getNavigableOwnedEnd().add(node2Property);
	    		}
	    		
				AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), connection.getRelationship());
				project.getEditingDomain().getCommandStack().execute(cmd);
			}			
		}
		
		//Adds the element to the diagram
		parent.addChild(element);
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(element);
		notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, redo ? NotificationType.REDO : NotificationType.DO);
	}
}
