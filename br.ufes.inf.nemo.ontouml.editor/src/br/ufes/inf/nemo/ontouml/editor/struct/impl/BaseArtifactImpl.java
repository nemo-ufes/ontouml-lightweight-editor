package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.InputStream;

import br.ufes.inf.nemo.ontouml.editor.struct.Artifact;

public class BaseArtifactImpl implements Artifact {

	private String fileName;
	private InputStream inputStream;
	
	public BaseArtifactImpl(String fileName, InputStream inputStream) {
		this.fileName = fileName;
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
