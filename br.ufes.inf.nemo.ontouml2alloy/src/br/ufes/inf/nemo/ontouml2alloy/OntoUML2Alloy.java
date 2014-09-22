package br.ufes.inf.nemo.ontouml2alloy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl;
import br.ufes.inf.nemo.alloy.util.AlloyResourceFactoryImpl;
import br.ufes.inf.nemo.ontouml2alloy.transformer.Transformer;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyLibraryFiles;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyThemeFile;

public class OntoUML2Alloy {
		
	public OntoUMLParser ontoparser;
	public AlloyFactory factory;
	public Transformer transformer;
	public OntoUML2AlloyOptions options;
	
	public String dirPath;	
	public String alsPath;	
	public Resource alsresource;
	
	/**
	 * Constructor
	 */
	public OntoUML2Alloy (OntoUMLParser refparser, String alloyPath, OntoUML2AlloyOptions opt) 
	{
		alsPath = alloyPath;
		dirPath = alsPath.substring(0,alsPath.lastIndexOf(File.separator)+1);
		ontoparser = refparser;	
		factory = AlloyFactory.eINSTANCE;
		options = opt;
	}
	
	/**
	 * This method performs the transformation from a RefOntoUML Model 
	 * into an Alloy specification (.als). The Alloy libraries used 
	 * by this transformation are generated into the same folder of the 
	 * Alloy file, as well as the standard Alloy theme file. 
	 * 
	 * PS: In order to open the Analyzer stand-alone, we needed to extract 
	 * the Analyzer JAR file into that folder as well.
	 * 
	 */
	public String transform () throws Exception 
	{
		File f = new File(alsPath);
		if (!f.exists()) f.createNewFile();
		if (f.exists()) f.delete(); f.createNewFile();
		f.deleteOnExit();
		
		AlloyThemeFile.generateAlloyThemeFile(dirPath);		

		AlloyLibraryFiles.generateLibraryFiles(dirPath);				
				
		transformer = new Transformer(ontoparser, factory, options);
		transformer.run();
		
		createAlsResource();		
		alsresource.getContents().add(transformer.module);		
		saveAlsResourceToFile(alsPath);
		
		return transformer.module.toString();
	}
	
	private void createAlsResource() 
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new AlloyResourceFactoryImpl() );
		resourceSet.getPackageRegistry().put(AlloyPackage.eNS_URI,AlloyPackage.eINSTANCE);
		AlloyPackageImpl.init();
		alsresource = resourceSet.createResource(URI.createURI("models/out.xmi"));
	}
	
	private void saveAlsResourceToFile(String alsPath) 
	{		
		try{			
			FileWriter fstream = new FileWriter(alsPath);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(alsresource.getContents().get(0).toString());			
			out.close();
		  }catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
		  }		
	}	
}
