package br.ufes.inf.nemo.oled.ui.diagram.commands;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;

public class SetVisibilityCommand extends BaseDiagramCommand{

	private static final long serialVersionUID = -444736590798129291L;
	
	public DiagramEditor editor;
	public UmlProject project;
	public ArrayList<DiagramElement> selected = new ArrayList<DiagramElement>();	
	public enum Visibility { NAME, ENDPOINTS, STEREOTYPE, MULTIPLICITY }
	public Visibility visibility;
	public boolean value;
	
	public SetVisibilityCommand(DiagramNotification editorNotification, ArrayList<DiagramElement> selected, UmlProject project, Visibility visibility, boolean show) 
	{
		this.editor = (DiagramEditor)editorNotification;
		notification = editorNotification;
		this.project = project;		
		this.visibility = visibility;
		this.value = show;
		
		for(DiagramElement dElem: selected)
		{
			if(dElem instanceof BaseConnection){								
				this.selected.add(dElem);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();						
		if(notification!=null){
			ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();					
			for(DiagramElement dElem: selected)
			{
				if(dElem instanceof BaseConnection)
				{
					if(dElem instanceof AssociationElement){
						AssociationElement ce = (AssociationElement)dElem;					
						if(visibility==Visibility.NAME) ce.setShowName(!value);
						if(visibility==Visibility.STEREOTYPE) ce.setShowOntoUmlStereotype(!value);
						if(visibility==Visibility.MULTIPLICITY) ce.setShowMultiplicities(!value);
						if(visibility==Visibility.ENDPOINTS) ce.setShowRoles(!value);
					}else if(dElem instanceof GeneralizationElement){
						GeneralizationElement ce = (GeneralizationElement)dElem;					
						if(visibility==Visibility.NAME) ce.setShowName(!value);
					}						
					list.add(dElem);					
				}
			}			
			notification.notifyChange((List<DiagramElement>) list, ChangeType.VISIBILITY_CHANGED, NotificationType.UNDO);
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
	
	@Override
	public void run() {
		
		if(visibility==Visibility.NAME) editor.showName(value);
		if(visibility==Visibility.STEREOTYPE) editor.showStereotype(value);
		if(visibility==Visibility.MULTIPLICITY) editor.showMultiplicities(value);
		if(visibility==Visibility.ENDPOINTS) editor.showEndPoints(value);
				
		//notify
		if (notification!=null) {
			notification.notifyChange((List<DiagramElement>) selected, ChangeType.VISIBILITY_CHANGED, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)editor), this);
			for (UndoableEditListener l : ((DiagramEditor)editor).editListeners)  l.undoableEditHappened(event);			
		}	
		
	}
	
}
