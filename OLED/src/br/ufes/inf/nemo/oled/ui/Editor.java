package br.ufes.inf.nemo.oled.ui;

public interface Editor {

	public enum EditorNature
	{
		ONTOUML,
		TEXT,
		ALLOY_SIMULATION,
		READ_ONLY
	}
	
	public boolean isSaveNeeded();
	
	abstract EditorNature getEditorNature();
}
