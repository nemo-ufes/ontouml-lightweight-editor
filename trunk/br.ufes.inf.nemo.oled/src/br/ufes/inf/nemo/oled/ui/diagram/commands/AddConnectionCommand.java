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
package br.ufes.inf.nemo.oled.ui.diagram.commands;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.GeneralizationImpl;
import br.ufes.inf.nemo.oled.draw.CompositeElement;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This is an undoable creation command for a Connection.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque, John Guerson
 */
public class AddConnectionCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = 2924451842640450250L;	
	private CompositeElement parent;
	private DiagramElement diagramElement;	
	private RefOntoUML.Element relationship;
	private Classifier source;
	private Classifier target;
	private EObject eContainer;	
	private boolean addToDiagram;

	public AddConnectionCommand(DiagramNotification editorNotification, CompositeElement parent, RefOntoUML.Element relationship, Classifier aSource, Classifier aTarget, UmlProject project, EObject eContainer) {
		this.parent = parent;
		this.project = project;
		this.notification = editorNotification;
		
		if (notification==null) this.addToDiagram = false; 
		else this.addToDiagram=true;
		
		this.relationship = relationship;		
		source = aSource;
		target = aTarget;
		
		this.eContainer = eContainer;
		
		diagramElement = ModelHelper.getDiagramElementByEditor(relationship,(DiagramEditor)notification);
	}

	@Override
	public void undo() 
	{
		super.undo();
						
		if (relationship!=null){
//			System.out.println("Undoing ="+relationship);
			project.getEditingDomain().getCommandStack().undo();
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromDeletion(relationship);
		}
		
		if(addToDiagram && diagramElement!=null){				
			parent.removeChild(diagramElement);
			ModelHelper.removeMapping(diagramElement);
			
			List<DiagramElement> elements = new ArrayList<DiagramElement>();
			elements.add(diagramElement);
			notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, NotificationType.UNDO);			
		}	
	}

	@Override
	public void redo() 
	{
		redo = true;
		super.redo();
		run();
	}

	public void run() {	    
					
		ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
		
		addToModel();		
		ProjectBrowser.frame.getDiagramManager().updateOLEDFromInclusion(relationship);
		
		if(addToDiagram && diagramElement != null)
		{			
			addToDiagram(redo);
			ModelHelper.addMapping(relationship, diagramElement);	
			list.add(diagramElement);
		}
		
		DiagramEditor d = ((DiagramEditor)notification);
		//notify
		if (d!=null) {
			d.notifyChange((List<DiagramElement>) list, ChangeType.ELEMENTS_ADDED, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)d), this);
			for (UndoableEditListener l : ((DiagramEditor)d).editListeners)  l.undoableEditHappened(event);			
		}
	}
		
	@SuppressWarnings("unused")
	private void addToDiagram (boolean redo)
	{
		//set sides on the diagram element
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
		}
		
		//add to diagram
		parent.addChild(diagramElement);
//		diagramElement.setParent((CompositeNode)parent);
		
		// bug in designing. not best solution, but it works.
		Relationship relationship = ((UmlConnection)diagramElement).getRelationship();		
		if (source instanceof Relationship || target instanceof Relationship)  diagramElement.invalidate();		
	}
	
	private void addToModel()
	{			
//		System.out.println("Adding = "+relationship);
		if (relationship instanceof Association){
			
			//set sides on the element
			Property p1 = ((Association)relationship).getMemberEnd().get(0);
			Property p2 = ((Association)relationship).getMemberEnd().get(1);			
			if(source!=null) {
				p1.setType(source);
				p1.setName(source.getName().trim().toLowerCase());
			}			
			if(target!=null) {
				p2.setType(target);
				p2.setName(target.getName().trim().toLowerCase());
			}
			
			// add to model
			if(eContainer==null){
				AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), relationship);
				project.getEditingDomain().getCommandStack().execute(cmd);
			}else{				
				AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Package)eContainer).getPackagedElement(), relationship);
				project.getEditingDomain().getCommandStack().execute(cmd);
			}			
		}
		if (relationship instanceof Generalization)
		{	
			//set sides on the element
			((Generalization)relationship).setSpecific(source);
			((Generalization)relationship).setSpecific(target);
			
			//add to model
			if(source!=null){
				AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Classifier)source).getGeneralization(), (RefOntoUML.Generalization)relationship);
				project.getEditingDomain().getCommandStack().execute(cmd);				
			}
						
		}		
	}	
}

