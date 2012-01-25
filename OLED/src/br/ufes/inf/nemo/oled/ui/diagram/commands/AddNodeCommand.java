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

import br.ufes.inf.nemo.oled.draw.CompositeElement;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramEditorNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramEditorNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;


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
	private Node element;
	private CompositeElement parent;
	private double absx, absy;

	/**
	 * Constructor.
	 * @param editorNotification a DiagramEditorNotification object
	 * @param parent the parent component
	 * @param aNode the created element
	 * @param x the absolute x position
	 * @param y the absolute y position
	 */
	public AddNodeCommand(DiagramEditorNotification editorNotification, CompositeElement parent, Node aNode, double x, double y, UmlProject project) {
		this.parent = parent;
		this.project = project;
		this.notification = editorNotification;
		element = aNode;
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
		if(element instanceof ClassElement)
		{
			ClassElement classElement = (ClassElement) element;
			
			AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), classElement.getClassifier());
			project.getEditingDomain().getCommandStack().execute(cmd);
		}
		
		//Adds the element to the diagram
		parent.addChild(element);
		element.setAbsolutePos(absx, absy);
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(element);
		notification.notifyChange(elements, ChangeType.ELEMENTS_ADDED, redo ? NotificationType.REDO : NotificationType.DO);
	}
}
