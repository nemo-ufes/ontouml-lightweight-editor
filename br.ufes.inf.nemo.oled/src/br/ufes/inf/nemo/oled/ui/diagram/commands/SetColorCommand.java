package br.ufes.inf.nemo.oled.ui.diagram.commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class SetColorCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = 1L;
	
	public DiagramEditor editor;
	public UmlProject project;
	public ArrayList<DiagramElement> selected = new ArrayList<DiagramElement>();
	public ArrayList<Color> oldColorList = new ArrayList<Color>();
	public Color color;
	
	public SetColorCommand(DiagramNotification editorNotification, ArrayList<DiagramElement> selected, UmlProject project, Color color)
	{
		this.editor = (DiagramEditor)editorNotification;
		notification = editorNotification;
		this.project = project;		
		this.color = color;
		
		for(DiagramElement dElem: selected)
		{
			if(dElem instanceof ClassElement){
				ClassElement ce = (ClassElement)dElem;
				oldColorList.add(ce.getBackgroundColor());
				this.selected.add(ce);
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
		
			int i =0;
			for(DiagramElement dElem: selected)
			{
				if(dElem instanceof ClassElement)
				{
					ClassElement ce = (ClassElement)dElem;
					ce.setBackgroundColor(oldColorList.get(i));
					list.add(ce);
					i++;
				}
			}
			
			notification.notifyChange((List<DiagramElement>) list, ChangeType.ELEMENTS_COLORED, NotificationType.UNDO);
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
		
		for(DiagramElement dElem: selected)
		{
			if(dElem instanceof ClassElement)
			{
				ClassElement ce = (ClassElement)dElem;
				ce.setBackgroundColor(color);				
			}
		}
	
		//notify
		if (notification!=null) {
			notification.notifyChange((List<DiagramElement>) selected, ChangeType.ELEMENTS_COLORED, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)editor), this);
			for (UndoableEditListener l : ((DiagramEditor)editor).editListeners)  l.undoableEditHappened(event);			
		}	
	}
}
