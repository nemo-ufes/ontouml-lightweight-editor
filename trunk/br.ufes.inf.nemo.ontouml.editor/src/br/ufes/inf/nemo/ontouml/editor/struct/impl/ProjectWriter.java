package br.ufes.inf.nemo.ontouml.editor.struct.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.ontouml.editor.struct.Artifact;
import br.ufes.inf.nemo.ontouml.editor.struct.Project;
import br.ufes.inf.nemo.ontouml.editor.util.ConfigurationHelper;
import br.ufes.inf.nemo.ontouml.editor.util.Settings;

public class ProjectWriter {

	private static ProjectWriter instance = new ProjectWriter();

	public static ProjectWriter getInstance() {
		return instance;
	}
	
	public File writeProject(File file, Project project) throws IOException {

		File outFile = ConfigurationHelper.getFileWithExtension(file, ConfigurationHelper.getExtension());
		FileOutputStream dest = new FileOutputStream(outFile);
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
		
		for (Artifact artifact : project.getAllArtifacts()) {
			if(artifact instanceof ModelArtifact)
			{
				//Save the model as a resource inside the project file
				ZipEntry modelEntry = new ZipEntry(Settings.MODEL_DEFAULT_FILE.getValue());			
				out.putNextEntry(modelEntry);
				Resource resource = ((ModelArtifact)artifact).getResource();
				resource.save(out, Collections.EMPTY_MAP);
				out.closeEntry();
			}
			else
			{
				//Save the artifacts inside the project file
				ZipEntry projectEntry = new ZipEntry(artifact.getFileName());
				out.putNextEntry(projectEntry);
				ObjectOutputStream oos = new ObjectOutputStream(out);
				oos.writeObject(artifact.getObject()); 
				oos.flush();
				out.closeEntry();
			}
		}
				
		//Flushes and closes the zip file
		out.flush();
		out.finish();
		out.close();

		return outFile;
	}
}
