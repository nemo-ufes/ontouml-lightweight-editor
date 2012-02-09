package br.ufes.inf.nemo.oled.ui;

import br.ufes.inf.nemo.oled.draw.Diagram;

public interface Editor {

	public enum EditorNature
	{
		ONTOUML,
		TEXT,
		INSTANCE_VISUALIZER,
		READ_ONLY
	}
	
	public boolean isSaveNeeded();
	
	abstract EditorNature getEditorNature();
	
	abstract Diagram getDiagram();
	
}
