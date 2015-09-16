package br.ufes.inf.nemo.sml2alloy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import sml2.SMLModel;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.AlloyPackage;
import br.ufes.inf.nemo.alloy.impl.AlloyPackageImpl;
import br.ufes.inf.nemo.alloy.util.AlloyResourceFactoryImpl;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.ontouml2alloy.transformer.Transformer;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyLibraryFiles;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyThemeFile;
import br.ufes.inf.nemo.sml2alloy.parser.SMLParser;
import br.ufes.inf.nemo.sml2alloy.util.SML2AlloyUtil;
import br.ufes.inf.nemo.sml2alloy.util.SituationLibraryFiles;

public class SML2Alloy
{		
	public SMLModel smlmodel;
	public AlloyFactory factory;
	
	public String dirPath;	
	public String alsPath;	
	public Resource alsresource;
	
	public SML2Alloy (SMLModel smlmodel, String alloyPath) 
	{
		alsPath = alloyPath;
		dirPath = alsPath.substring(0,alsPath.lastIndexOf(File.separator)+1);
		this.smlmodel = smlmodel;	
		factory = AlloyFactory.eINSTANCE;
	}
	
	/**
	 * This method performs the transformation from a SML Model 
	 * into an Alloy specification (.als). The Alloy libraries used 
	 * by this transformation are generated into the same folder of the 
	 * Alloy file, as well as the standard Alloy theme file. 
	 * 
	 * PS: In order to open the Analyzer stand-alone, we needed to extract 
	 * the Analyzer JAR file into that folder as well.
	 * 
	 */
	public String transform() throws Exception 
	{
		File f = new File(alsPath);
		if (!f.exists()) f.createNewFile();
		if (f.exists()) f.delete(); f.createNewFile();
		//f.deleteOnExit();
		
		AlloyThemeFile.generateAlloyThemeFile(dirPath);		

		AlloyLibraryFiles.generateLibraryFiles(dirPath);
		
		SituationLibraryFiles.generateLibraryFiles(dirPath);
		
		RefOntoUML.Package refmodel = this.smlmodel.getContextModel();
		//RefOntoUML.Package refmodel = importOntoUML();
		
		//Transform the OntoUML part
		OntoUMLParser ontoumlparser = new OntoUMLParser(refmodel);
		OntoUML2AlloyOptions options = new OntoUML2AlloyOptions();
				
		Transformer ontoumltransformer = new Transformer(ontoumlparser, factory, options);
		ontoumltransformer.run();
		
		//Transform the SML part
		SMLParser smlparser = new SMLParser(smlmodel, ontoumlparser);
		
		SML2AlloyTransformer smltransformer = new SML2AlloyTransformer(smlparser, factory, ontoumltransformer);
		smltransformer.run();
		
		createAlsResource();		
		alsresource.getContents().add(ontoumltransformer.module);		
		saveAlsResourceToFile(alsPath);
		
		return ontoumltransformer.module.toString()+smltransformer.rulecreator.rules;
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
	
	public static void main(String[] args)
	{
		String alloyPath = "C:\\Users\\Sobral\\AppData\\Local\\Temp\\Alloy\\alloymodel.als";
		SML2AlloyUtil util = new SML2AlloyUtil();
		SMLModel smlmodel = util.importSMLModel();
		
		SML2Alloy sml2alloy = new SML2Alloy(smlmodel, alloyPath);
		
		try
		{
//			util.printConstraints();
			Object[] result = util.verifySyntax(smlmodel);
			
			if ((boolean)result[0])
				System.out.println(sml2alloy.transform());
			
			else
				System.out.println(result[1]);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
