package br.ufes.inf.nemo.ontouml.editor.plugin;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.ontouml.editor.struct.Project;

public interface ApplicationHandler {
	
	EObject getSelectedElement();
	
	Project getSelectedProject();
	
	Object getSelectedAftifact();
			
	void showStatus(String status);
	
}
