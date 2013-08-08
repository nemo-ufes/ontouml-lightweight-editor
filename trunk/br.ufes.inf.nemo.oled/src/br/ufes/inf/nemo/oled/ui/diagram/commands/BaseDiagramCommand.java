package br.ufes.inf.nemo.oled.ui.diagram.commands;

import javax.swing.undo.AbstractUndoableEdit;

import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.util.Command;

/**
 * Base class for all diagram commands
 * @author Antognoni Albuquerque
 * @version 1.0
 */
public abstract class BaseDiagramCommand extends AbstractUndoableEdit implements Command {
	private static final long serialVersionUID = 733613330226013575L;
	protected DiagramNotification notification;
	protected boolean redo = false;
	protected UmlProject project;
	
	public DiagramNotification getNotification() {
		return notification;
	}
	
	public UmlProject getProject() {
		return project;
	}
	
}
