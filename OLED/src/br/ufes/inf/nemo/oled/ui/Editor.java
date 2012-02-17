package br.ufes.inf.nemo.oled.ui;

import org.eclipse.emf.edit.provider.IDisposable;

import br.ufes.inf.nemo.oled.draw.Diagram;

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
	
	abstract Diagram getDiagram();
	
}
