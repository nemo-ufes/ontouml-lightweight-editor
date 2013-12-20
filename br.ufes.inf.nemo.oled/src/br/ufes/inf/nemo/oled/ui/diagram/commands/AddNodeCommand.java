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

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.draw.CompositeElement;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.ProjectTree;
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
	@SuppressWarnings("unused")
	private RefOntoUML.Element element;
	private RefOntoUML.Package eContainer;
	private Node diagramElement;
	private CompositeElement parent;	
	private double absx, absy;
	private boolean addToModel;
	private boolean addToDiagram;
	
	/**
	 * Constructor.
	 * @param editorNotification a ModelNotification object
	 * @param parent the parent component
	 * @param aNode the created element
	 * @param x the absolute x position
	 * @param y the absolute y position
	 */
	public AddNodeCommand(DiagramNotification editorNotification, CompositeElement parent, RefOntoUML.Element element, double x, double y, UmlProject project, boolean addToModel, boolean addToDiagram, RefOntoUML.Package eContainer) {
		this.parent = parent;
		this.project = project;
		this.notification = editorNotification;
		this.addToModel = addToModel;
		this.addToDiagram = addToDiagram;
		this.element = element;		
		this.eContainer = eContainer;
		this.diagramElement = (Node)ModelHelper.getDiagramElement(element);
		absx = x;
		absy = y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();
		
		project.getEditingDomain().getCommandStack().undo();
		parent.removeChild(diagramElement);
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(diagramElement);
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
	public void run() 
	{		
		if (addToDiagram && !addToModel) return;
				
		if(diagramElement instanceof ClassElement)
		{
			ClassElement classElement = (ClassElement) diagramElement;
			
			if(addToModel){
				addToModel(classElement.getClassifier());
			}
			
			if(addToDiagram){
				addToDiagram(diagramElement,redo);
			}
		}
						
		//triggers the search for errors and warnings in the model
		ProjectBrowser.frame.getDiagramManager().searchWarnings();
		ProjectBrowser.frame.getDiagramManager().searchErrors();
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
		if(element instanceof Node) ((Node)element).setAbsolutePos(absx, absy);		
				
		Classifier classifier = ((ClassElement)element).getClassifier();
		
		ModelHelper.addMapping(classifier, ((ClassElement)element));
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(element);
		
		if(notification!=null)notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, redo ? NotificationType.REDO : NotificationType.DO);
	}
	
	/**
	 * Add a element to the model instance behind the scenes and updates the application accordingly.
	 * @param elem
	 */
	public void addToModel(RefOntoUML.Element element)
	{
		if(eContainer==null){
			AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), element);
			project.getEditingDomain().getCommandStack().execute(cmd);
		}else{
			AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Package)eContainer).getPackagedElement(), element);
			project.getEditingDomain().getCommandStack().execute(cmd);
		}
		
		ProjectBrowser.getParserFor(project).addElement(element);
		
		//============ Updating application... ==============
		
		//FIXME - Do not rebuild the tree, only update it!
		ProjectBrowser.rebuildTree(project);
		
		//Select the element in the Tree
		ProjectTree tree = ProjectBrowser.getProjectBrowserFor(ProjectBrowser.frame, project).getTree();
		tree.selectModelElement(element);
		
		//Include this element in the Auto Completion of OCL Editor
		if (element instanceof RefOntoUML.Type){
			ProjectBrowser.frame.getInfoManager().getOcleditor().addCompletion((RefOntoUML.Type)element);
		}
	}
	
}
