package br.ufes.inf.nemo.ontouml.editor.struct;

import java.io.InputStream;

public interface Artifact {

	String getFileName();
	
	void setFileName(String fileName);
	
	InputStream getInputStream();
	
	void setInputStream(InputStream inputStream);
}
