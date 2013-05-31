package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.ontouml.editor.model.EcoreHelper;
import br.ufes.inf.nemo.ontouml.editor.struct.Artifact;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.util.Resources;
import br.ufes.inf.nemo.ontouml.editor.util.Settings;

public class ProjectReader {

	private static ProjectReader instance = new ProjectReader();

	public static ProjectReader getInstance() {
		return instance;
	}
	
	public Project readProject(File file) throws Exception {

		boolean modelLoaded = false;
		
		ProjectImpl project = new ProjectImpl();
		project.setProjectFile(file);
		
		List<Artifact> artifacts = new ArrayList<Artifact>();
		
		ZipFile inFile = new ZipFile(file);	
		@SuppressWarnings("unchecked")
		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) inFile.entries();

		ZipEntry entry;
		while(entries.hasMoreElements()) {
			
			entry = entries.nextElement();
			InputStream in = inFile.getInputStream(entry);
			
			//Read the model and the project file
			if(entry.getName().equals(Settings.MODEL_DEFAULT_FILE.getValue()) && !modelLoaded)
			{
				Resource resource = EcoreHelper.createResource(); 				 
				resource.load(in, Collections.EMPTY_MAP);
				ModelArtifact artifact = new ModelArtifact(entry.getName(), resource);
				artifacts.add(artifact);
				modelLoaded = true;
			}
			else
			{
				ObjectInputStream obj = new ObjectInputStream(in);
				Artifact artifact = new BaseArtifactImpl(entry.getName(), obj.readObject());
				artifacts.add(artifact);
			}
			
			in.close();
		}
		
		inFile.close();
		
		if(!modelLoaded)
			throw new Exception(Resources.getString(("error.modelnotfound")));
		
		project.setArtifacts(artifacts);
		return project;
	}
	
}
