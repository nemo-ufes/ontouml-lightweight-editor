package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import br.ufes.inf.nemo.ontouml.editor.struct.Artifact;

public class BaseArtifactImpl implements Artifact {

	private String fileName;
	private Object object;
	
	public BaseArtifactImpl(String fileName, Object object) {
		super();
		this.fileName = fileName;
		this.object = object;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
		
}
