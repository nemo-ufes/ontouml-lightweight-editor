package br.ufes.inf.nemo.ontouml2uml;

import java.util.HashMap;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * This class transforms OntoUML models into UML models. It simply ignores the stereotypes of classes and relations.
 * The user can optionally ignore the package hierarchy or the derivation relationship in the transformation.
 * 
 * @author John Guerson
 *
 */
public class OntoUML2UML {
		 	  
		
	public static String log = new String();
	private static UMLTransformator utransformer;
	private static OntoUML2UMLOption options = new OntoUML2UMLOption();
	
	public static Resource convertToUML (RefOntoUML.Package refmodel, String umlPath)
	{
		return convertToUML(refmodel, umlPath, options);
	}
	
	public static Resource convertToUML (OntoUMLParser refparser, String umlPath)
	{
		return convertToUML(refparser, umlPath, options);
	}
	
	public static Resource convertToUML (RefOntoUML.Package refmodel, String umlPath, OntoUML2UMLOption opt)
	{
		log="";
		options=opt;
		Resource umlResource = null;
		
		OntoUMLParser refparser = new OntoUMLParser(refmodel);
		
		utransformer = new UMLTransformator(refparser,opt);			  
		org.eclipse.uml2.uml.Package umlmodel = utransformer.run();	
   		   
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlmodel);		   
		return umlResource;
	}
	
	
	public static Resource convertToUML (OntoUMLParser refparser, String umlPath, OntoUML2UMLOption opt)
	{
		log="";
		options=opt;
		Resource umlResource = null;
		
		utransformer = new UMLTransformator(refparser,opt);			  
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
