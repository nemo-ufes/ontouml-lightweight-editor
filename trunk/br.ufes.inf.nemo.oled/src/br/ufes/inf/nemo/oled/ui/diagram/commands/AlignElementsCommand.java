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
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class AlignElementsCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = 1L;
	
	public DiagramEditor editor;
	public UmlProject project;
	public ArrayList<DiagramElement> selected = new ArrayList<DiagramElement>();
	public enum Alignment { TOP, BOTTOM, LEFT, RIGHT, CENTER_VERTICAL, CENTER_HORIZONTAL }
	public Alignment direction;
	public ArrayList<Double> oldPosXList = new ArrayList<Double>();
	public ArrayList<Double> oldPosYList = new ArrayList<Double>();
	
	public AlignElementsCommand(DiagramNotification editorNotification, ArrayList<DiagramElement> selected, UmlProject project, Alignment direction) 
	{
		this.editor = (DiagramEditor)editorNotification;
		notification = editorNotification;
		this.project = project;		
		this.direction = direction;
		
		for(DiagramElement dElem: selected)
		{
			if(dElem instanceof ClassElement){
				ClassElement ce = (ClassElement)dElem;
				oldPosXList.add(ce.getAbsoluteX1());
				oldPosYList.add(ce.getAbsoluteY1());
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
					ce.setAbsolutePos(oldPosXList.get(i), oldPosYList.get(i));
					list.add(ce);
					i++;
				}
			}
			
			notification.notifyChange((List<DiagramElement>) list, ChangeType.ELEMENTS_ALIGNED, NotificationType.UNDO);
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
	public void run() {
		
		if(direction==Alignment.TOP) editor.alignTop();
		if(direction==Alignment.BOTTOM) editor.alignBottom();
		if(direction==Alignment.LEFT) editor.alignLeft();
		if(direction==Alignment.RIGHT) editor.alignRight();
		if(direction==Alignment.CENTER_VERTICAL) editor.alignCenterVertically();
		if(direction==Alignment.CENTER_HORIZONTAL) editor.alignCenterHorizontally();
		
		//notify
		if (notification!=null) {
			notification.notifyChange((List<DiagramElement>) selected, ChangeType.ELEMENTS_ALIGNED, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)editor), this);
			for (UndoableEditListener l : ((DiagramEditor)editor).editListeners)  l.undoableEditHappened(event);			
		}	
	}
}
