package br.ufes.inf.nemo.ontouml.editor.struct;

import org.eclipse.emf.ecore.EObject;

public interface ModelArtifact extends Artifact{

	EObject getModel();
	
	void setModel(EObject model);
	
}
