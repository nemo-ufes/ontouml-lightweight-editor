package br.ufes.inf.nemo.ontouml.editor.struct;


public interface ProjectEvent {

	String getEvent();
	
	Object getData();
	
	Project getSource();
}
