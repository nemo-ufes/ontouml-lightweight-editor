package br.ufes.inf.nemo.ocl.pivot.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.OCL;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.model.OCLstdlib;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.PivotOCLUtil;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2Ecore;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2EcoreOption;
import br.ufes.inf.nemo.ontouml2ecore.OntoUML2EcoreUtil;

public class PivotOCLParser {
	
	private PivotEnvironmentFactory pivotEnvFactory;
	private OCL ocl;
	private MetaModelManager metamodelManager;
	private HashMap <RefOntoUML.Element, EModelElement> emap = new HashMap<RefOntoUML.Element, EModelElement>();
	
	private RefOntoUML.Package ontoRoot; //OntoUML
	private OntoUMLParser refparser;
	private String refFile;
	
	private EPackage ecoreRoot; //Ecore
	private String ecorePath;
	private String ecoreFile;	
		
	private ArrayList<Constraint> constraintlist = new ArrayList<Constraint>();	 //OCL
	private String oclContent;
	private String oclPath;		
	
	private String tempDirPath; // used to store all the necessary artifacts 
	
	private void setupOCL(ResourceSet resourceSet)
	{	
		OCLstdlib.install();		
		org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
    	org.eclipse.ocl.examples.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();    	
    	org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();
    	
		OCL.initialize(resourceSet);
	}
		
	/**
	 * Constructor
	 * 
	 * @param refRoot: OntoUML package root
	 * @param tempDirPath: directory used to store all the generated artifacts necessary for parsing OCL. If null or empty
	 *        the directory to be used will be probably "MyDocuments".
	 */
	public PivotOCLParser (RefOntoUML.Package refRoot, String tempDirPath)
	{
		this.tempDirPath = tempDirPath;
		this.ontoRoot = refRoot;
		if (tempDirPath==null || tempDirPath.isEmpty())	this.tempDirPath = PivotOCLUtil.getDefaultDirectoryPath()+File.separator;

		if (refRoot.getName()==null || refRoot.getName().isEmpty()) refFile = "default.refontouml";
		else refFile = refRoot.getName()+".refontouml";
		
		ecoreFile = refFile.replace(".refontouml",".ecore");
		ecorePath = this.tempDirPath+ecoreFile;
				
		// convert OntoUML to Ecore
		Resource ecoreResource = OntoUML2Ecore.convertToEcore(ontoRoot, ecorePath, new OntoUML2EcoreOption(true, true));

		// this map contains the elements mapping from the ontouml conversion to ecore
		emap = OntoUML2Ecore.getOntoUML2EcoreMap();
		
		// this parser contains all the informations of the OntoUML model
		refparser = OntoUML2Ecore.getOntoUMLParser();
		
		// configure OCL from resource set resultant of the conversion to ecore
		setupOCL(ecoreResource.getResourceSet());
		
		// put Ecore in package registry lookup
		ecoreRoot = (EPackage) ecoreResource.getContents().get(0);			
	
		metamodelManager = new MetaModelManager();
		pivotEnvFactory = new PivotEnvironmentFactory(ecoreResource.getResourceSet().getPackageRegistry(), metamodelManager);
	    ocl = OCL.newInstance(pivotEnvFactory);	
	    
	}
			
	/**
	 * Constructor
	 * 
	 * @param refAbsPath: OntoUML model path
	 * @param tempDirPath: directory used to store all the generated artifacts necessary for parsing OCL. If null or empty
	 *        the directory to be used will be the OntoUML model path.
	 *        
	 * @throws IOException
	 */
	public PivotOCLParser (String refAbsPath, String tempDirPath) throws IOException
	{		
		this.tempDirPath = tempDirPath;
		if (tempDirPath==null || tempDirPath.isEmpty())	this.tempDirPath = refAbsPath.substring(0, refAbsPath.lastIndexOf(File.separator));
	
		refFile = refAbsPath.substring(refAbsPath.lastIndexOf(File.separator)+1);

		if (refAbsPath.contains(".refontouml"))
		{
			ecoreFile = refFile.replace(".refontouml",".ecore");
			ecorePath = this.tempDirPath+ecoreFile;
			
			Resource refResource = PivotOCLUtil.readOntoUML(refAbsPath);
			ontoRoot = (RefOntoUML.Package)refResource.getContents().get(0);
		
			// convert OntoUML to Ecore
			Resource ecoreResource = OntoUML2Ecore.convertToEcore(ontoRoot, ecorePath, new OntoUML2EcoreOption(true, true));

			// this map contains the elements mapping from the ontouml conversion to ecore
			emap = OntoUML2Ecore.getOntoUML2EcoreMap();
			
			// this parser contains all the informations of the OntoUML model
			refparser = OntoUML2Ecore.getOntoUMLParser();
			
			// configure OCL from resource set resultant of the conversion to ecore
			setupOCL(ecoreResource.getResourceSet());
			
			// put Ecore in package registry lookup
			ecoreRoot = (EPackage) ecoreResource.getContents().get(0);			
		
			metamodelManager = new MetaModelManager();
			pivotEnvFactory = new PivotEnvironmentFactory(ecoreResource.getResourceSet().getPackageRegistry(), metamodelManager);
		    ocl = OCL.newInstance(pivotEnvFactory);	  
		    
		} else if (refAbsPath.contains(".ecore")) { // actually, the argument is an Ecore (*.ecore) model...
	
			ecoreFile = refFile;
			ecorePath = this.tempDirPath+ecoreFile;
			
			ecoreRoot = OntoUML2EcoreUtil.readEcore(ecorePath);
									
			setupOCL(ecoreRoot.eResource().getResourceSet());
			
			metamodelManager = new MetaModelManager();
			pivotEnvFactory = new PivotEnvironmentFactory(ecoreRoot.eResource().getResourceSet().getPackageRegistry(), metamodelManager);
		    ocl = OCL.newInstance(pivotEnvFactory);		    
		}				  	    
   	}	    
				
	/**
	 * Parse OCL content.
	 * 
	 * @param oclContent: String containing all the OCL constraints in the form of Complete OCL documents
	 * 
	 * @throws ParserException
	 * @throws IOException
	 */
	public void parse(String oclContent) throws ParserException, IOException
	{
		this.oclPath = ecorePath.replace(".ecore", ".ocl");
		oclContent = processOCLContent(oclContent);
		this.oclContent = oclContent;
		PivotOCLUtil.writeFile(this.oclContent, oclPath);
		
		URI oclURI = URI.createFileURI(oclPath);
		 
		Resource pivotResource = ocl.parse(oclURI);
		
		for(TreeIterator<EObject> tit = pivotResource.getAllContents(); tit.hasNext();)
		{
			EObject next = tit.next();	 
			if(next instanceof Constraint)
		    {	
		   	 	Constraint constraint = (Constraint)next;		    	 		    	 
		        constraintlist.add(constraint);		    	 
		    }
		}		 
	}	
	
	/**
	 * Process OCL content. It includes the keyword "import" and the model path to be opened.
	 * 
	 * @param oclContent: String containing all the OCL constraints in the form of Complete OCL documents
	 * @return
	 */
	private String processOCLContent(String oclContent)
	{		
		if (!oclContent.contains("import") && !oclContent.contains("package "))
		{
			oclContent = "import '"+ecoreFile+"'"+"\n\n"+"package _'"+ecoreRoot.getName()+"'"+"\n\n"+oclContent+"\n\nendpackage";			
		}		
		if (!oclContent.contains("import") && oclContent.contains("package ")) 
		{
			oclContent = "import '"+ecoreFile+"'"+"\n\n"+oclContent;
		}		
		return oclContent;
	}

	public MetaModelManager getMetamodelManager() 
	{
		return metamodelManager;
	}

	public HashMap <RefOntoUML.Element, EModelElement> getOntoUML2EcoreMap ()
	{
		return emap;
	}
	
	public ArrayList<Constraint> getConstraints()
	{
		return constraintlist;
	}
	
	public RefOntoUML.Package getOntoUMLRoot()
	{
		return ontoRoot;
	}
	
	public EPackage getEcoreRoot ()
	{
		return ecoreRoot;
	}
	
	public OCL getOCL()
	{
		return ocl;
	}
	
	public OntoUMLParser getOntoUMLParser ()
	{
		return refparser;
	}	
}
