package br.ufes.inf.nemo.ontouml.editor.struct;

import java.io.File;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface Project {
	
	public void addArtifact(Artifact artifact);
	
	public List<Artifact> getAllArtifacts();
	
	public void removeArtifact(Artifact artifact);
	
	public void removeAllArtifacts();
	
	public EObject getModel();
	
	public File getProjectFile();
	
	public boolean isSaveNeeded();
	
	public void setProjectFile(File file);
	
	public void addProjectListener(ProjectListener projectListener);
	
	public void removeProjectListener(ProjectListener projectListener);
}
