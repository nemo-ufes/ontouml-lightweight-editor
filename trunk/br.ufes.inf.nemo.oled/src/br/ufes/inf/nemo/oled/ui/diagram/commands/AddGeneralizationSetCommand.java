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
import java.util.Collection;
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import org.eclipse.emf.edit.command.AddCommand;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.oled.draw.CompositeElement;
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
public class AddGeneralizationSetCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = 2924451842640450250L;	
	@SuppressWarnings("unused")
	private CompositeElement parent;
	private RefOntoUML.Element genSet;
	private boolean addToDiagram;
	private RefOntoUML.Element eContainer;
	private ArrayList<DiagramElement> diagramGenList = new ArrayList<DiagramElement>();
	private ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
	
	public AddGeneralizationSetCommand(DiagramNotification editorNotification, CompositeElement parent, RefOntoUML.Element genSet, Collection<Generalization> generalizations, UmlProject project, RefOntoUML.Element eContainer) {
		this.parent = parent;
		this.project = project;
		this.eContainer = eContainer;
		this.notification = editorNotification;		
		if (notification==null) this.addToDiagram = false; else this.addToDiagram=true;		
		this.genSet = genSet;		
		this.generalizations .addAll(generalizations);
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
			undoFromModel(genSet,generalizations);
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromDeletion(genSet);
		}
		
		if(addToDiagram && diagramGenList.size()>0){						
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
			addToModel(genSet, generalizations);
			ProjectBrowser.frame.getDiagramManager().updateOLEDFromInclusion(genSet);			
		}
		
		if(addToDiagram && diagramGenList.size()>0){						
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
	
	public void undoFromModel(RefOntoUML.Element genSet,  ArrayList<Generalization> generalizations)
	{
//		System.out.println("Undoing = "+genSet);
		project.getEditingDomain().getCommandStack().undo();
		
		((GeneralizationSet)genSet).getGeneralization().removeAll(generalizations);
		for(Generalization gen: generalizations) {
			gen.getGeneralizationSet().remove((GeneralizationSet)genSet); 		
		}
	}
	
	public void addToModel(RefOntoUML.Element genSet,  ArrayList<Generalization> generalizations)
	{		
//		System.out.println("Adding = "+genSet);
		((GeneralizationSet)genSet).getGeneralization().addAll(generalizations);
		for(Generalization gen: generalizations) {
			gen.getGeneralizationSet().add((GeneralizationSet)genSet); 		
		}
		
		AddCommand cmd = new AddCommand(project.getEditingDomain(), ((RefOntoUML.Package)eContainer).getPackagedElement(), genSet);
		project.getEditingDomain().getCommandStack().execute(cmd);
	}

}
