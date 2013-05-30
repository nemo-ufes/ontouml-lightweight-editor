package br.ufes.inf.nemo.ontouml.editor.plugin;

import br.ufes.inf.nemo.ontouml.editor.adapter.ElementAdapter;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;

public interface ApplicationHandler {
	
	ElementAdapter getSelectedElement();
	
	Project getSelectedProject();
	
	Object getSelectedAftifact();
			
	void showStatus(String status);
	
}
