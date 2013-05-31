package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class ModelArtifact extends BaseArtifactImpl {

	private Resource resource;
		
	public ModelArtifact(String fileName, Resource resource) {
		super(fileName, resource);
		this.resource = resource;  
	}
	
	public EObject getModel() {
		return resource.getContents().get(0);
	}

	public void setModel(EObject model) {
		resource.getContents().clear();
		resource.getContents().add(model);
	}

	public Resource getResource()
	{
		return resource;
	}
	
	public void setResource(Resource resource)
	{
		this.resource = resource;
	}
	
}
