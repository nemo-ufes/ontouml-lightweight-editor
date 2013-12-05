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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.AggregationKind;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.CharacterizationImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.DerivationImpl;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.GeneralizationImpl;
import RefOntoUML.impl.MaterialAssociationImpl;
import RefOntoUML.impl.MediationImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.componentOfImpl;
import br.ufes.inf.nemo.oled.draw.CompositeElement;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectTree;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
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
	 * @param editorNotification a ModelNotification object
	 * @param structureDiagram the parent component
	 * @param conn the element created
	 * @param aTarget 
	 * @param aSource 
	 */
	public AddConnectionCommand(DiagramNotification editorNotification, CompositeElement parent, UmlConnection conn, Classifier aSource, Classifier aTarget, UmlProject project) {
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
												
	    		Property node1Property, node2Property;
	    		
	    		node1Property = ModelHelper.getDefaultOwnedEnd(source, 1, 1);
	    		
	    		//If the association is a ComponentOf, set the default cardinality to 2..*, to help in validation
	    		if(association instanceof componentOfImpl)
	    			node2Property = ModelHelper.getDefaultOwnedEnd(target, 2, -1);
	    		else
	    			node2Property = ModelHelper.getDefaultOwnedEnd(target, 1, 1);
	    		
	    		if(association instanceof MeronymicImpl)
	    		{
	    			if(((Meronymic)association).isIsShareable())
	    			{
	    				node1Property.setAggregation(AggregationKind.SHARED);
	    			}
	    			else
	    			{
	    				node1Property.setAggregation(AggregationKind.COMPOSITE);
	    			}	
	    		}
	    		
	    		String node1Name = node1Property.getType().getName();
	    		
	    		if(node1Name==null || node1Name.trim().isEmpty())
	    			node1Name = "source";
	    		else
	    			node1Name = node1Name.trim().toLowerCase();
	    		
	    		String node2Name = node2Property.getType().getName();
	    		
	    		if(node2Name==null || node2Name.trim().isEmpty())
	    			node2Name = "target";
	    		else
	    			node2Name = node2Name.trim().toLowerCase();
	    		
	    		node1Property.setName(node1Name);
	    		node2Property.setName(node2Name);
	    		
	    		association.getOwnedEnd().add(node1Property);
	    		association.getOwnedEnd().add(node2Property);
	    		
	    		association.getMemberEnd().add(node1Property);
	    		association.getMemberEnd().add(node2Property);
	    		
	    		if(association instanceof DirectedBinaryAssociationImpl || association instanceof FormalAssociationImpl || association instanceof MaterialAssociationImpl)
	    		{
	    			association.getNavigableOwnedEnd().add(node1Property);
	    			association.getNavigableOwnedEnd().add(node2Property);
	    			
	    			//If the association is Mediation or Characterization, set target readonly to help in validation
	    			if(association instanceof MediationImpl || association instanceof CharacterizationImpl || association instanceof DerivationImpl)
	    			{
	    				node2Property.setIsReadOnly(true);
	    			}
	    		}
	    		else
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
		
		EObject refElem=null;
		if (element instanceof AssociationElement){
			
			//Update the project tree: FIXME every modification creates a new tree
			refElem = ((AssociationElement)element).getAssociation();
			ProjectBrowser.getParserFor(project).addElement(refElem);
			ProjectBrowser.rebuildTree(project);
			//Select this element in the tree
			ProjectTree tree = ProjectBrowser.getProjectBrowserFor(ProjectBrowser.frame, project).getTree();
			tree.selectModelElement(refElem);
			//Include this element in the Auto Completion of OCL Editor
			ProjectBrowser.frame.getInfoManager().getOcleditor().addCompletion((RefOntoUML.Association)refElem);
			
		}else if (element instanceof GeneralizationElement){
			
			//Update the project tree: FIXME every modification creates a new tree
			refElem = ((GeneralizationElement)element).getGeneralization();
			ProjectBrowser.rebuildTree(project);
		}		
		
		//triggers the search for errors and warnings in the model
		ProjectBrowser.frame.getDiagramManager().searchWarnings();
		ProjectBrowser.frame.getDiagramManager().searchErrors();
	}
}
