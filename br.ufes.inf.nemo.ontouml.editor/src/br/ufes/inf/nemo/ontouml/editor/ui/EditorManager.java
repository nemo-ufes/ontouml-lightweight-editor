package br.ufes.inf.nemo.ontouml.editor.ui;

import javax.swing.JTabbedPane;

import br.ufes.inf.nemo.ontouml.editor.plugin.Editor;
import br.ufes.inf.nemo.ontouml.editor.ui.diagram.ClassDiagramEditor;

@SuppressWarnings("serial")
public class EditorManager extends JTabbedPane{

	public EditorManager()
	{
		super();
		
		Editor editor = new ClassDiagramEditor();
		addTab("Teste", editor.getUIComponent());
	}
	
	public void cut()
	{
		//Do something...
	}
	
	public void copy()
	{
		//Do something...		
	}
	
	public void paste()
	{
		//Do something...		
	}
	
	public void undo()
	{
		//Do something...		
	}
	
	public void redo()
	{
		//Do something...		
	}
	
}
