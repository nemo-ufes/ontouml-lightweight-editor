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
import java.util.HashMap;
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import org.eclipse.emf.edit.command.DeleteCommand;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;

/**
 * @author John Guerson
 */
public class DeleteGeneralizationSetCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = 2924451842640450250L;	
	private RefOntoUML.Element genSet;
	private ArrayList<DiagramElement> diagramGenList = new ArrayList<DiagramElement>();
	private ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
	
	public DeleteGeneralizationSetCommand(DiagramNotification editorNotification, RefOntoUML.Element genSet, UmlProject project) {		
		this.project = project;
		this.notification = editorNotification;		
		this.genSet = genSet;		
		this.generalizations.addAll(((RefOntoUML.GeneralizationSet)genSet).getGeneralization());
		if(generalizations!=null && notification!=null){
			for(DiagramElement dElem: ((DiagramEditor)notification).getDiagram().getChildren()){
				if(dElem instanceof GeneralizationElement){
					GeneralizationElement genElem = (GeneralizationElement)dElem;
					if(generalizations.contains((Generalization)genElem.getRelationship())){ 
						diagramGenList.add(genElem);
					}
				}
			}
		}
	}
	
	@Override
	public void undo()
	{
		super.undo();
		
		ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
		
		if(genSet!=null){
			undoGeneralizationSet((RefOntoUML.GeneralizationSet)genSet);			
		}
		
		if(diagramGenList.size()>0){						
			for(DiagramElement genElem: diagramGenList){
				Generalization gen = (Generalization)((GeneralizationElement)genElem).getRelationship();
				ProjectBrowser.frame.getDiagramManager().updateOLEDFromModification(gen,false);
				list.add(genElem);
			}
		}		
		
		if(notification!=null){
			notification.notifyChange(diagramGenList, ChangeType.ELEMENTS_MODIFIED, NotificationType.UNDO);
		}
	}
	
	@Override
	public void redo() 
	{
		redo = true;
		super.redo();
		run();
	}

	@Override
	public void run() {
		ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
		
		if(genSet!=null){
			deleteGeneralizationSet((RefOntoUML.GeneralizationSet)genSet);						
		}
		
		if(diagramGenList.size()>0){						
			for(DiagramElement genElem: diagramGenList){
				Generalization gen = (Generalization)((GeneralizationElement)genElem).getRelationship();
				ProjectBrowser.frame.getDiagramManager().updateOLEDFromModification(gen,false);
				list.add(genElem);
			}
		}		
		
		DiagramEditor d = ((DiagramEditor)notification);
		//notify
		if (d!=null) {
			d.notifyChange((List<DiagramElement>) list, ChangeType.ELEMENTS_MODIFIED, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)d), this);
			for (UndoableEditListener l : ((DiagramEditor)d).editListeners)  l.undoableEditHappened(event);			
		}
		
	}
	
	
	// deleted generalization sets and its dependent generalizations
	private HashMap<Generalization, GeneralizationSet> decoupledGenSetMap = new HashMap<Generalization,GeneralizationSet>();
	
	private void deleteGeneralizationSet(GeneralizationSet elem)
	{
		//decouple generalization sets and its generalizations before deletion
		for(Generalization gen: ((GeneralizationSet)elem).getGeneralization())
		{				
			if(gen!=null) decoupledGenSetMap.put(gen,elem);			
		}			
		((GeneralizationSet)elem).getGeneralization().removeAll(decoupledGenSetMap.keySet());		
		for(Generalization gen: decoupledGenSetMap.keySet()) {
			gen.getGeneralizationSet().remove(elem);
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromModification(gen, false);
		}
		
		delete(elem);		
	}
	
	private void delete (RefOntoUML.Element elem)
	{			
//		System.out.println("Deleting = "+elem);
		DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(project.getEditingDomain(), elem);
		project.getEditingDomain().getCommandStack().execute(cmd);
		ProjectBrowser.frame.getDiagramManager().updateOLEDFromDeletion(elem);
	}
	
	private void undoGeneralizationSet(GeneralizationSet elem)
	{
		undo(elem);
		
		//couple generalization set and its generalizations again
		((GeneralizationSet)elem).getGeneralization().addAll(decoupledGenSetMap.keySet());		
		for(Generalization gen: decoupledGenSetMap.keySet()) {
			gen.getGeneralizationSet().add(elem);
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromModification(gen, false);
		}
	}
	
	private void undo (RefOntoUML.Element elem)
	{		
//		System.out.println("Undoing = "+elem);
		project.getEditingDomain().getCommandStack().undo();
		ProjectBrowser.frame.getDiagramManager().updateOLEDFromInclusion(elem);
	}
}