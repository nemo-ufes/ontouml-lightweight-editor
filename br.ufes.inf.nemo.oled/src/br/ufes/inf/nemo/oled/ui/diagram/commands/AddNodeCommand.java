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

import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Comment;
import RefOntoUML.Constraintx;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.draw.CompositeElement;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Node;
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
 * @author Wei-ju Wu, Antognoni Albuquerque
 * @version 1.1
 */
public class AddNodeCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = -3148409380703192555L;
	
	private CompositeElement parent;
	private DiagramElement diagramElement;
	private double absx, absy;
	
	private RefOntoUML.Element element;
	private RefOntoUML.Element eContainer;		
	
	private boolean addToDiagram;
	
	/**
	 * Constructor.
	 * @param editorNotification a ModelNotification object
	 * @param parent the parent component
	 * @param aNode the created element
	 * @param x the absolute x position
	 * @param y the absolute y position
	 */
	public AddNodeCommand(DiagramNotification editorNotification, CompositeElement parent, RefOntoUML.Element element, double x, double y, UmlProject project, RefOntoUML.Element eContainer) {
		this.parent = parent;
		this.project = project;
		this.notification = editorNotification;		
		if(notification==null) this.addToDiagram = false; else this.addToDiagram=true;
		this.element = element;		
		this.eContainer = eContainer;
		this.diagramElement = ModelHelper.getDiagramElementByEditor(element,(DiagramEditor)notification);
		if(this.diagramElement==null) {
			if(!(element instanceof GeneralizationSet) && !(element instanceof Comment) && !(element instanceof Constraintx))
				this.diagramElement = ((DiagramEditor)this.notification).getCreationHandler().createNode((RefOntoUML.Type)element, eContainer);
		}
		absx = x;
		absy = y;
	}

	/**
	 * {@inheritDoc} - UNDO
	 */
	@Override
	public void undo() {
		super.undo();
	
		if(element!=null){
			project.getEditingDomain().getCommandStack().undo();
		}
		
		if(diagramElement != null){
			parent.removeChild(diagramElement);	
			
			List<DiagramElement> elements = new ArrayList<DiagramElement>();
			elements.add(diagramElement);
			notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, NotificationType.UNDO);	
		}		
	}

	/**
	 * {@inheritDoc} - REDO
	 */
	@Override
	public void redo() {
		redo = true;
		super.redo();
		run();
	}

	/**
	 * {@inheritDoc} - RUN
	 */
	public void run() 
	{				
		addToModel(element);		
		
		if(addToDiagram && diagramElement !=null){						
			addToDiagram(diagramElement,redo);
			if (ModelHelper.getDiagramElementByEditor(element,(DiagramEditor)notification)==null) ModelHelper.addMapping(element, ((ClassElement)diagramElement));
		}
		
		ProjectBrowser.frame.getDiagramManager().updateOLEDFromInclusion(element);		
	}	
	
	/**
	 * Add a element to the diagram (not to the model instance behind the scenes). In fact, the element instance already exists inside the diagram element.
	 * @param element
	 * @param redo
	 */
	private void addToDiagram (DiagramElement element, boolean redo)
	{
		//Adds the element to the diagram
		parent.addChild(element);
//		element.setParent((CompositeNode)parent);
		if(element instanceof Node) ((Node)element).setAbsolutePos(absx, absy);		
								
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(element);
		
		if(notification!=null) notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, redo ? NotificationType.REDO : NotificationType.DO);
	}
	
	/**
	 * Add a element to the model instance behind the scenes and updates the application accordingly.
	 * It only adds the element if it does not exists in the container.
	 * 
	 * @param elem
	 */
	private void addToModel(RefOntoUML.Element element)
	{
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
