package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.InputStream;

import br.ufes.inf.nemo.ontouml.editor.struct.AlloyArtifact;

public class AlloyArtifactImpl extends BaseArtifactImpl implements
		AlloyArtifact {

	public AlloyArtifactImpl(String fileName, InputStream inputStream) {
		super(fileName, inputStream);
	}

}
