package br.inf.ufes.nemo.oled.ui.diagram.commands;

import javax.swing.undo.AbstractUndoableEdit;

import br.inf.ufes.nemo.oled.model.UmlProject;
import br.inf.ufes.nemo.oled.util.Command;

public abstract class BaseDiagramCommand extends AbstractUndoableEdit implements
		Command {

	private static final long serialVersionUID = 733613330226013575L;
	protected UmlProject project;
	//private List<StatusListener> statusListeners = new ArrayList<StatusListener>();
	
	public UmlProject getProject() {
		return project;
	}
	
	/* CLEANUP
	 * public void addStatusListener(StatusListener listener)
	{
		statusListeners.add(listener);
	}
	
	public void removeStatusListener(StatusListener listener)
	{
		statusListeners.remove(listener);
	}
	
	public void notifyStatus(String status)
	{
		for (StatusListener item : statusListeners) {
			item.reportStatus(status);
		}
	}*/
}
