package br.ufes.inf.nemo.oled.ui.diagram;

import javax.swing.JComponent;


public abstract class BaseEditor extends JComponent implements Editor {

	private static final long serialVersionUID = -5942413080525420849L;
			
	public boolean isSaveNeeded()
	{
		return false;
	}

	public abstract EditorNature getEditorNature();
}
