package br.ufes.inf.nemo.ontouml.editor.plugin;

import br.ufes.inf.nemo.ontouml.editor.struct.Project;

public interface EditorFactory extends ApplicationPlugin {
	
	Editor getEditor(Object artifact, Project project);
	
	String getSupportedArtifactDesciption();
	
	String getSupportedArtifactExtension();
			
}
