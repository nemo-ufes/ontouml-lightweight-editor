package br.ufes.inf.nemo.ontouml.editor.plugin;

import java.util.List;

import br.ufes.inf.nemo.ontouml.editor.struct.Project;

public interface EditorFactory extends ApplicationPlugin {
	
	Editor getEditor(Object artifact, Project project);
	
	List<String> getSupportedArtifactExtensions();
			
}
