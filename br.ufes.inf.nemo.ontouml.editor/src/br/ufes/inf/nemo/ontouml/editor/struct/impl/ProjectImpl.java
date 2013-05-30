package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.ontouml.editor.adapter.ModelAdapter;
import br.ufes.inf.nemo.ontouml.editor.struct.Artifact;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectEvent;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectListener;

public class ProjectImpl implements Project {

	private transient List<Artifact> artifacts = new ArrayList<Artifact>();
	private List<ProjectListener> listeners; 
	
	public ModelAdapter getModel() {
		return null;
	}

	public void setModel(ModelAdapter modelAdapter) {

	}

	public void addArtifact(Artifact artifact) {
		artifacts.add(artifact);
		notifyLisneters("ARTIFACT_ADDED", artifact);
	}

	public List<Artifact> getAllArtifacts() {
		return null;
	}

	public void removeArtifact(Artifact artifact) {
		artifacts.remove(artifact);
		notifyLisneters("ARTIFACT_REMOVED", artifact);
	}

	public void removeAllArtifact() {

	}

	public File getProjectFile() {
		return null;
	}

	public void setProjectFile(File file) {

	}

	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}
	
	public void addProjectListener(ProjectListener projectListener) {
		listeners.add(projectListener);
	}

	public void removeProjectListener(ProjectListener projectListener) {
		listeners.remove(projectListener);
	}

	private void notifyLisneters(String event, Object data)
	{
		ProjectEvent projectEvent = new ProjectEventImpl(event, data, this);
		for (ProjectListener listener : listeners) {
			listener.notifyEvent(projectEvent);
		}
	}
}
