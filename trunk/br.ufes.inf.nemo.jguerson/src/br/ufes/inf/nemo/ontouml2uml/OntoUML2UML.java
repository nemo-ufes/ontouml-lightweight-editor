package br.ufes.inf.nemo.ontouml2uml;

import java.util.HashMap;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.OntoUMLParser;


public class OntoUML2UML {
		 	  
	private static UMLTransformator utransformer;	
	public static String log = new String();
	
	public static Resource convertToUML (RefOntoUML.Package refmodel, String umlPath)
	{
		return convertToUML(refmodel, umlPath, false,false);
	}
	
	public static Resource convertToUML (OntoUMLParser refparser, String umlPath)
	{
		return convertToUML(refparser, umlPath, false,false);
	}
	
	public static Resource convertToUML (RefOntoUML.Package refmodel, String umlPath, boolean ignorePackageHierarchy, boolean ignoreDerivation)
	{
		log="";
		Resource umlResource = null;
		
		OntoUMLParser refparser = new OntoUMLParser(refmodel);
		
		utransformer = new UMLTransformator(refparser,ignorePackageHierarchy, ignoreDerivation);			  
		org.eclipse.uml2.uml.Package umlmodel = utransformer.run();	
   		   
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlmodel);		   
		return umlResource;
	}
	
	
	public static Resource convertToUML (OntoUMLParser refparser, String umlPath, boolean ignorePackageHierarchy, boolean ignoreDerivation)
	{
		log="";
		Resource umlResource = null;
		
		utransformer = new UMLTransformator(refparser,ignorePackageHierarchy,ignoreDerivation);			  
		org.eclipse.uml2.uml.Package umlmodel = utransformer.run();	
   		   
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlmodel);		   
		return umlResource;
	}		
	
	public static HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> getMap ()
	{
		return utransformer.getConverter().getMap();
	}
	
	public static String getLog ()
	{
		return log;
	}	
}
