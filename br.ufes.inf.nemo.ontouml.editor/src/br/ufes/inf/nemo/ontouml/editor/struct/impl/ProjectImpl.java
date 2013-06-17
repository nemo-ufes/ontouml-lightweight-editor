package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import RefOntoUML.Model;
import RefOntoUML.RefOntoUMLFactory;
import br.ufes.inf.nemo.ontouml.editor.model.EcoreHelper;
import br.ufes.inf.nemo.ontouml.editor.struct.Artifact;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectEvent;
import br.ufes.inf.nemo.ontouml.editor.struct.ProjectListener;
import br.ufes.inf.nemo.ontouml.editor.util.Settings;

public class ProjectImpl implements Project {

	private List<Artifact> artifacts = new ArrayList<Artifact>();
	private List<ProjectListener> listeners = new ArrayList<ProjectListener>(); 
	private File file;
	private boolean saveNeeded = true;

	public ProjectImpl()
	{
		Resource resource = EcoreHelper.createResource();
		ModelArtifact artifact = new ModelArtifact(Settings.MODEL_DEFAULT_FILE.getValue(), resource);
		
		Model model = RefOntoUMLFactory.eINSTANCE.createModel();
		model.setName("MyRoot");
		artifact.setModel(model);
		
		artifacts.add(artifact);
	}
	
	public void addArtifact(Artifact artifact) {
		artifacts.add(artifact);
		notifyLisneters("ARTIFACT_ADDED", artifact);
	}

	public List<Artifact> getAllArtifacts() {
		return artifacts;
	}

	public void removeArtifact(Artifact artifact) {
		artifacts.remove(artifact);
		notifyLisneters("ARTIFACT_REMOVED", artifact);
	}

	//TODO ver se é necessário
	public void removeAllArtifacts() {
		Artifact[] removed = (Artifact[]) artifacts.toArray();
		artifacts.clear();
		notifyLisneters("ARTIFACT_REMOVED", removed);
	}

	public EObject getModel() {
		for (Artifact artifact : artifacts) {
			if(artifact instanceof ModelArtifact)
			{
				return ((ModelArtifact)artifact).getModel();
			}
		}
		return null;
	}

	public void setModel(EObject model) {
		for (Artifact artifact : artifacts) {
			if(artifact instanceof ModelArtifact)
			{
				((ModelArtifact)artifact).setModel(model);
				notifyLisneters("ARTIFACT_CHANGED", artifact);
			}
		}
	}
	
	public File getProjectFile() {
		return file;
	}

	public void setProjectFile(File file) {
		this.file = file;
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
		
		saveNeeded = true;
	}

	public boolean isSaveNeeded() {
		return saveNeeded;
	}
	
	public void setSaveNeeded(boolean saveNeeded) {
		this.saveNeeded = saveNeeded;
	}
}
