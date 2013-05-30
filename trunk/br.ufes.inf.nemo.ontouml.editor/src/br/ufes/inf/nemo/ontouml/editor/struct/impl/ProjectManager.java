package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.File;

import br.ufes.inf.nemo.ontouml.editor.struct.Project;


public class ProjectManager {
	
	private Project project;
	
	public void newProject()
	{
		project = new ProjectImpl();
		System.out.println("New Project...");
	}
	
	public void newArtifact()
	{
		System.out.println("New Artifact...");
		//Do something
	}
	
	public void openProject(String path)
	{
		project = ProjectReader.getInstance().readProject(new File(path));
		System.out.println("Open Project..." + path);
	}

	public void saveProjectAs()
	{
		//Do something
		System.out.println("Save Project as...");
	}
	
	public void saveProject()
	{
		//Do something
		System.out.println("Save Project...");
	}

	public Project getProject() {
		return project;
	}
}
