package br.ufes.inf.nemo.ontouml.editor.plugin;


public interface EditorEvent {
	
	String getEvent();
	
	Object getData();
	
	Editor getSource();

}
