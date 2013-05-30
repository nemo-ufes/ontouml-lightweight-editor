package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.File;

import br.ufes.inf.nemo.ontouml.editor.struct.Project;

public class ProjectReader {

	private static ProjectReader instance;
	
	private ProjectReader()
	{
		
	}
	
	public static ProjectReader getInstance()
	{
		if(instance == null)
			instance = new ProjectReader();
		return instance;
	}
	
	public Project readProject(File file)
	{
		return new ProjectImpl();
	}
	
}
