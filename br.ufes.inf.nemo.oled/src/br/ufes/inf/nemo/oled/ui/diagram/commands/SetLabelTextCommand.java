/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.ui.diagram.commands;

import java.util.ArrayList;
import java.util.List;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.draw.CompositeNode;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.ProjectTree;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

/**
 * This class represents a reversible operation that sets a Label to a new
 * text.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque
 * @version 1.0
 */
public class SetLabelTextCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = 5701807952287882396L;
	private Label label;
	private String text, oldText;
	private CompositeNode parent;

	/**
	 * Constructor.
	 * @param aLabel the Label
	 * @param aText the new text
	 */
	public SetLabelTextCommand(DiagramNotification aNotification, Label aLabel, String aText,  UmlProject project) {
		this.notification = aNotification;
		label = aLabel;
		text = aText;
		this.project = project;
		oldText = aLabel.getNameLabelText();
		if (aLabel.getParent()!=null) { parent = aLabel.getParent().getParent();} 
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		
		label.setNameLabelText(text);
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(label);
		notification.notifyChange(elements, ChangeType.LABEL_TEXT_SET, redo ? NotificationType.REDO : NotificationType.DO);
		
		ProjectBrowser.refreshTree(project);		
			
		if (parent instanceof ClassElement) 
		{
			Classifier element = (((ClassElement)parent).getClassifier());
			
			//Select the element in the Tree
			ProjectTree tree = ProjectBrowser.getProjectBrowserFor(ProjectBrowser.frame, project).getTree();
			tree.selectModelElement(element);
			
			//Update the element from the auto completion of the OCL editor
			//Update OntoUML Parser
			ProjectBrowser.frame.getInfoManager().getOcleditor().updateCompletion(element);			
			ProjectBrowser.getParserFor(project).updateElement(element);
			
		}else if (parent instanceof BaseConnection){
			
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
	@Override
	public void undo() {
		super.undo();
		label.setNameLabelText(oldText);
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(label);
		notification.notifyChange(elements, ChangeType.LABEL_TEXT_SET, NotificationType.UNDO);
					
	}
}
