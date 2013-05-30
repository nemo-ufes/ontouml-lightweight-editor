package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.InputStream;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.ontouml.editor.struct.ModelArtifact;

public class ModelArtifactImpl extends BaseArtifactImpl implements
		ModelArtifact {

	private EObject model;
	
	public ModelArtifactImpl(String fileName, InputStream inputStream) {
		super(fileName, inputStream);
	}

	public EObject getModel() {
		return model;
	}

	public void setModel(EObject model) {
		this.model = model;
	}

}
