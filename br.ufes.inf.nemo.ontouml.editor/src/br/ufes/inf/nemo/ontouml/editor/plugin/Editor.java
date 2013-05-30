package br.ufes.inf.nemo.ontouml.editor.plugin;

import java.util.List;

import javax.swing.JComponent;

public interface Editor {

	JComponent getUIComponent();
			
	List<Class<? extends Tool>> getTools();
	
	boolean isSaveNeeded();
	
	boolean canUndo();
	
	boolean canRedo();
	
	boolean undo();
	
	boolean redo();
	
	void registerApplicationHandler(ApplicationHandler applicationHandler);
	
	void addEditorListener(EditorListener editorlistener);
	
	void removeEditorListener(EditorListener editorlistener);
	
}
