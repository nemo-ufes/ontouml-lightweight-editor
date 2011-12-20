package br.ufes.inf.nemo.oled.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import br.ufes.inf.nemo.oled.model.UmlProject;

public class OLEDStore {

	enum FileType
	{
		MODEL,
		PROJECT,
		SIMULATION,
		SIMULATION_OUTPUT
	}
	
	/**
	 * Returns an input stream to the model file.
	 */
	public static InputStream getFile(String path, FileType type) throws IOException
	{
		ZipFile zf = null;
		if(!path.endsWith(getStoreExtension()))
			throw new IOException("File type not supported");
			
		try {
			
			zf = new ZipFile(path);
			ZipEntry entry = zf.getEntry(getFileName(type));
			InputStream in = zf.getInputStream(entry);
						
			return in;
			
		} finally {
			zf.close();
		}
	}
	
	public static String getFileName(FileType type)
	{
		switch (type) {
		case MODEL: 
			return "model.ontouml";
		case PROJECT: 
			return "project.dat";
		case SIMULATION: 
			return "simulation.als";
		case SIMULATION_OUTPUT: 
			return "simulation_output.xml";
		default:
			return null;
		}
	}
	
	public static String getStoreExtension()
	{
		return ".oled";
	}

	
	public static void save(UmlProject project, String path) {
		
		
	}
	
}
