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

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.GeneralizationImpl;
import br.ufes.inf.nemo.oled.draw.CompositeElement;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.ProjectTree;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
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
	
	private CompositeElement parent;
	private DiagramElement diagramElement;
	
	private RefOntoUML.Element relationship;
	private Classifier source;
	private Classifier target;
	private EObject eContainer;
	
	private boolean addToModel;
	private boolean addToDiagram;

	/**
	 * Constructor.
	 * @param editorNotification a ModelNotification object
	 * @param structureDiagram the parent component
	 * @param conn the element created
	 * @param aTarget 
	 * @param aSource 
	 */
	public AddConnectionCommand(DiagramNotification editorNotification, CompositeElement parent, RefOntoUML.Element relationship, Classifier aSource, Classifier aTarget, UmlProject project, boolean addToModel, boolean addToDiagram, EObject eContainer) {
		this.parent = parent;
		this.project = project;
		this.notification = editorNotification;
		this.addToModel = addToModel;
		this.addToDiagram = addToDiagram;
		this.relationship = relationship;
		this.eContainer = eContainer;
		diagramElement = ModelHelper.getDiagramElement(relationship);
		source = aSource;
		target = aTarget;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();
				
		if (relationship!=null){
			if (relationship instanceof GeneralizationImpl == false){
				project.getEditingDomain().getCommandStack().undo();
			} else {
				GeneralizationImpl generalization  = (GeneralizationImpl)relationship;
	    		EcoreUtil.delete(generalization);
			}
		}
		
		if(diagramElement!=null){				
			parent.removeChild(diagramElement);
			List<DiagramElement> elements = new ArrayList<DiagramElement>();
			elements.add(diagramElement);
			notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, NotificationType.UNDO);	
		}		
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
	    
		if (addToDiagram && !addToModel){
			return;
		}
		
		if(addToModel && relationship!=null){			
			addToModel(relationship);			
		}
		
		if(addToDiagram && diagramElement != null){
			
			if (diagramElement instanceof BaseConnection) {				
				BaseConnection connection = (BaseConnection) diagramElement;				
				if (connection.getRelationship() instanceof GeneralizationImpl)
				{
					GeneralizationImpl generalization  = (GeneralizationImpl) connection.getRelationship();
		    		generalization.setSpecific(source);
		    		generalization.setGeneral(target);
				}
				else if(connection.getRelationship() instanceof AssociationImpl)
				{
					AssociationImpl association  = (AssociationImpl) connection.getRelationship();
					association.getMemberEnd().get(0).setType(source);
					association.getMemberEnd().get(1).setType(target);				
				}
				addToDiagram(diagramElement,redo);
			}			
		}
				
		ProjectBrowser.frame.getDiagramManager().searchWarnings();
		ProjectBrowser.frame.getDiagramManager().searchErrors();
		ProjectBrowser.frame.getDiagramManager().updateUI();
	}
		
	/**
	 * Add a element to the diagram (not to the model instance behind the scenes). In fact, the element instance already exists inside the diagram element.
	 * @param element
	 * @param redo
	 */
	public void addToDiagram (DiagramElement element, boolean redo)
	{
		//Adds the element to the diagram
		parent.addChild(element);
		
		Relationship relationship = ((UmlConnection)element).getRelationship();
		
		if (relationship instanceof Derivation) element.invalidate(); // bug in designing. not best solution, but works.
		
		ModelHelper.addMapping(relationship, element);
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(element);
		notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, redo ? NotificationType.REDO : NotificationType.DO);
	}
	
	/**
	 * Add a element to the model instance behind the scenes and updates the application accordingly.
	 * @param elem
	 */
	public void addToModel(RefOntoUML.Element element)
	{			
		if (element instanceof Association){

			if(eContainer==null){
				AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), element);
				project.getEditingDomain().getCommandStack().execute(cmd);
			}else{				
				AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Package)eContainer).getPackagedElement(), element);
				project.getEditingDomain().getCommandStack().execute(cmd);
			}

			ProjectBrowser.getParserFor(project).addElement(element); 
						
			Property p1 = ((Association)element).getMemberEnd().get(0);
			Property p2 = ((Association)element).getMemberEnd().get(1);
			
			ProjectBrowser.getParserFor(project).addElement(p1); 	
			ProjectBrowser.getParserFor(project).addElement(p2);			
		}
		
		if (element instanceof Generalization){			
			((Generalization) element).setSpecific(source);
			((Generalization) element).setGeneral(target);
		}
		
		//============ Updating application... ==============
		
		//FIXME - Do not rebuild the tree, only update it!
		ProjectBrowser.rebuildTree(project);
		
		//Select this element in the tree
		ProjectTree tree = ProjectBrowser.getProjectBrowserFor(ProjectBrowser.frame, project).getTree();
		tree.selectModelElement(element);
		
		if (element instanceof RefOntoUML.Association){
			//	Include this element in the Auto Completion of OCL Editor
			ProjectBrowser.frame.getInfoManager().getOcleditor().addCompletion((RefOntoUML.Association)element);
		}
		
	}
}
