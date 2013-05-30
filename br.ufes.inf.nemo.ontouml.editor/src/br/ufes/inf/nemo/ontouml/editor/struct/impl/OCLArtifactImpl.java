package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.InputStream;

import br.ufes.inf.nemo.ontouml.editor.struct.OCLArtifact;

public class OCLArtifactImpl extends BaseArtifactImpl implements OCLArtifact {

	public OCLArtifactImpl(String fileName, InputStream inputStream) {
		super(fileName, inputStream);
	}

}
