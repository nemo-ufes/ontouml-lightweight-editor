package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.InputStream;

import br.ufes.inf.nemo.ontouml.editor.struct.DiagramArtifact;

public class DiagramArtifactImpl extends BaseArtifactImpl implements
		DiagramArtifact {

	public DiagramArtifactImpl(String fileName, InputStream inputStream) {
		super(fileName, inputStream);
	}

}
