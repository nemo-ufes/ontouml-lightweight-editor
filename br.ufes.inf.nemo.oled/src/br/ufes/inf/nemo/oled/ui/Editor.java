package br.ufes.inf.nemo.oled.ui;

import org.eclipse.emf.edit.provider.IDisposable;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.UmlProject;

public interface Editor extends IDisposable {

	public enum EditorNature
	{
		ONTOUML,
		TEXT,
		HTML,
		INSTANCE_VISUALIZER,
		READ_ONLY
	}
	
	public boolean isSaveNeeded();
	
	abstract EditorNature getEditorNature();
	
	abstract UmlProject getProject();
	
	abstract Diagram getDiagram();
	
}
