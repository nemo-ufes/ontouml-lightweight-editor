package br.ufes.inf.nemo.ontouml2uml;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;


public class OntoUML2UML {
		 	  
	public static Transformator transformer;	
	public static String logDetails = new String();
	
	
	public static void main(String[] args)
	{
		try {
			String path = "model/teste.refontouml";
			convertToUML(new OntoUMLParser(path),path.replace(".refontouml", ".uml" ),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Resource convertToUML (RefOntoUML.Package refmodel, String umlPath, boolean ignorePackageHierarchy)
	{
		logDetails="";
		Resource umlResource = null;
		
		OntoUMLParser refparser = new OntoUMLParser(refmodel);
		
		transformer = new Transformator(refparser,ignorePackageHierarchy);			  
		org.eclipse.uml2.uml.Package umlmodel = transformer.run();	
   		   
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlmodel);		   
		return umlResource;
	}
	
	
	public static Resource convertToUML (OntoUMLParser refparser, String umlPath, boolean ignorePackageHierarchy)
	{
		logDetails="";
		Resource umlResource = null;
		
		transformer = new Transformator(refparser,ignorePackageHierarchy);			  
		org.eclipse.uml2.uml.Package umlmodel = transformer.run();	
   		   
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlmodel);		   
		return umlResource;
	}		

}
