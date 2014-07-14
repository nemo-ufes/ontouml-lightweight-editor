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

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.oled.draw.CompositeNode;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

/**
 * This class represents a reversible operation that sets a Label to a new
 * text.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque, John Guerson
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
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		DiagramEditor d = ((DiagramEditor)notification);
		
		String oldName = label.getNameLabelText();		
		label.setNameLabelText(text);
						
		if (parent instanceof ClassElement) 
		{
			Classifier element = (((ClassElement)parent).getClassifier());
			
			ProjectBrowser pb = ProjectBrowser.frame.getBrowserManager().getProjectBrowser();						
			// replace all references in constraints
			for(OCLDocument oclDoc: pb.getOCLDocuments())
			{
				String currentConstraints = oclDoc.getContent();
				String newConstraints = currentConstraints.replaceAll(oldName,text);
				oclDoc.setContent(newConstraints);
			}
			
			// update application accordingly
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromModification(element, false);
						
		}else if (parent instanceof BaseConnection){
			
		}
		
		elements.add(parent);		
				
		//notify
		if (d!=null) {
			d.notifyChange((List<DiagramElement>) elements, ChangeType.LABEL_TEXT_SET, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)d), this);
			for (UndoableEditListener l : ((DiagramEditor)d).editListeners)  l.undoableEditHappened(event);			
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
	public void undo() 
	{
		super.undo();
		
		label.setNameLabelText(oldText);
				
		if (parent instanceof ClassElement) 
		{
			Classifier element = (((ClassElement)parent).getClassifier());
						
			ProjectBrowser pb = ProjectBrowser.frame.getBrowserManager().getProjectBrowser();
			// replace all references in constraints
			for(OCLDocument oclDoc: pb.getOCLDocuments())
			{
				String currentConstraints = oclDoc.getContent();
				String newConstraints = currentConstraints.replaceAll(text,oldText);
				oclDoc.setContent(newConstraints);
			}
			
			// update application accordingly
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromModification(element, false);
						
		}else if (parent instanceof BaseConnection){
			
		}
				
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		elements.add(parent);
		notification.notifyChange(elements, ChangeType.LABEL_TEXT_SET, NotificationType.UNDO);					
	}
}
