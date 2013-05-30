package br.ufes.inf.nemo.ontouml.editor.struct;

import java.io.File;
import java.util.List;

import br.ufes.inf.nemo.ontouml.editor.adapter.ModelAdapter;

public interface Project {

	public ModelAdapter getModel();
	
	public void setModel(ModelAdapter modelAdapter);
	
	public void addArtifact(Artifact artifact);
	
	public List<Artifact> getAllArtifacts();
	
	public void removeArtifact(Artifact artifact);
	
	public void removeAllArtifact();
		
	public File getProjectFile();
	
	public void setProjectFile(File file);
	
	public void addProjectListener(ProjectListener projectListener);
	
	public void removeProjectListener(ProjectListener projectListener);
}
