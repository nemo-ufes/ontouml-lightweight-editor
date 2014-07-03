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

import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.draw.CompositeElement;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class implements a command to add nodes. It is introduced, because
 * AddElementCommand can not handle setting positions with nesting very
 * well.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque, John Guerson
 */
public class AddNodeCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = -3148409380703192555L;
	
	private CompositeElement parent;
	private DiagramElement diagramElement;
	private double absx, absy;	
	private RefOntoUML.Element element;
	private RefOntoUML.Element eContainer;	
	private boolean addToDiagram;

	public AddNodeCommand(DiagramNotification editorNotification, CompositeElement parent, RefOntoUML.Element element, double x, double y, UmlProject project, RefOntoUML.Element eContainer) 
	{
		this.parent = parent;
		this.project = project;
		this.notification = editorNotification;		
		if(notification==null) this.addToDiagram = false; else this.addToDiagram=true;
		this.element = element;		
		this.eContainer = eContainer;
		this.diagramElement = ModelHelper.getDiagramElementByEditor(element,(DiagramEditor)notification);		
		if(this.diagramElement==null) {
			if(element instanceof RefOntoUML.Class || element instanceof RefOntoUML.Association || element instanceof RefOntoUML.DataType || element instanceof RefOntoUML.Generalization)
			{				
				if(notification!=null)
					this.diagramElement = ((DiagramEditor)this.notification).getCreationHandler().createNode((RefOntoUML.Type)element, eContainer);				
			}
		}
		absx = x;
		absy = y;
	}

	@Override
	public void undo() 
	{		
		super.undo();
				
		if(element!=null){
//			System.out.println("Undoing = "+element);
			project.getEditingDomain().getCommandStack().undo();
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromDeletion(element);
		}
		
		if(addToDiagram && diagramElement != null){
			parent.removeChild(diagramElement);			
			ModelHelper.removeMapping(diagramElement);
		}		
		
		if(notification!=null){
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

	public void run() 
	{				
		ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
				
		if(element!=null){
			addToModel(element);
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromInclusion(element);
		}
		
		if(addToDiagram && diagramElement !=null){			
			addToDiagram(diagramElement,redo);
			ModelHelper.addMapping(element, ((ClassElement)diagramElement));
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
	
	private void addToDiagram (DiagramElement element, boolean redo)
	{
		//Adds the element to the diagram
		parent.addChild(element);
//		element.setParent((CompositeNode)parent);
		
		if(element instanceof Node) {
			
			((Node)element).setAbsolutePos(absx, absy);
			
			// show attributes compartment if necessary
			Classifier c = ((ClassElement)element).getClassifier();
			if (c instanceof RefOntoUML.Class){
				if (((RefOntoUML.Class)c).getOwnedAttribute().size()>0){
					((ClassElement)element).setShowAttributes(true);
				}
			}
			if (c instanceof RefOntoUML.DataType){
				if (((RefOntoUML.DataType)c).getOwnedAttribute().size()>0){
					((ClassElement)element).setShowAttributes(true);
				}
			}
		}		
	}
	private void addToModel(RefOntoUML.Element element)
	{
//		System.out.println("Adding = "+element);
		if(eContainer==null){
			
			if (!(project.getModel().getPackagedElement().contains(element)))
			{
				AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), element);
				project.getEditingDomain().getCommandStack().execute(cmd);
			}
			
		}else{
			if (eContainer instanceof RefOntoUML.Package)
			{
				if (!(((RefOntoUML.Package)eContainer).getPackagedElement().contains(element)))
				{					
					AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Package)eContainer).getPackagedElement(), element);
					project.getEditingDomain().getCommandStack().execute(cmd);
				}
				
			}else if (eContainer instanceof RefOntoUML.Element && element instanceof RefOntoUML.Comment)
			{
				if (!(((RefOntoUML.Element)eContainer).getOwnedComment().contains(element)))
				{
					AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Element)eContainer).getOwnedComment(), element);
					project.getEditingDomain().getCommandStack().execute(cmd);
				}
				
			}else if (eContainer instanceof RefOntoUML.Classifier && element instanceof RefOntoUML.Constraintx)
			{
				if (!(((RefOntoUML.Constraintx)element).getConstrainedElement().contains((RefOntoUML.Classifier)eContainer)))						
				{
					AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Constraintx)element).getConstrainedElement(), (RefOntoUML.Classifier)eContainer);
					project.getEditingDomain().getCommandStack().execute(cmd);
				}
				
			}else if (eContainer instanceof RefOntoUML.Class && element instanceof RefOntoUML.Property)
			{
				if (!(((RefOntoUML.Class)eContainer).getOwnedAttribute().contains(element)))
				{
					AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Class)eContainer).getOwnedAttribute(), element);
					project.getEditingDomain().getCommandStack().execute(cmd);
				}
				
			}else if (eContainer instanceof RefOntoUML.DataType && element instanceof RefOntoUML.Property)
			{
				if (!(((RefOntoUML.DataType)eContainer).getOwnedAttribute().contains(element)))
				{
					AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.DataType)eContainer).getOwnedAttribute(), element);
					project.getEditingDomain().getCommandStack().execute(cmd);
				}
			}			
		}		
	}	
}
