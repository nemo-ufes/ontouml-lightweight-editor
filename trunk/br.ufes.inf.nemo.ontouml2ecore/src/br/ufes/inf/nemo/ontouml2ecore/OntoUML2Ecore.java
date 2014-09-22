package br.ufes.inf.nemo.ontouml2ecore;

import java.util.HashMap;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import RefOntoUML.parser.OntoUMLParser;

/**
 * This class transforms OntoUML models into Ecore models. It simply ignores the stereotypes of classes and relations.
 * The user can optionally ignore the package hierarchy or the derivation relationship in the transformation.
 * 
 * @author John Guerson
 */
public class OntoUML2Ecore {
	  
	public static String log = new String();
	private static EcoreTransformator etransformer;
	private static OntoUML2EcoreOption options;
	private static OntoUMLParser refparser;
	
	public static Resource convertToEcore (RefOntoUML.Package refmodel, String ecorePath)
	{
		return convertToEcore(refmodel, ecorePath, new OntoUML2EcoreOption(false, true));
	}
	
	public static Resource convertToEcore (OntoUMLParser refparser, String ecorePath)
	{
		return convertToEcore(refparser, ecorePath, new OntoUML2EcoreOption(false, true));
	}
	
	public static Resource convertToEcore (RefOntoUML.Package refmodel, String ecorePath, OntoUML2EcoreOption opt)
	{
		log="";
		options=opt;
		Resource ecoreResource = null;
		
		refparser = new OntoUMLParser(refmodel);
		
		etransformer = new EcoreTransformator(refparser,options);			  
		EPackage ecoremodel = etransformer.run();	
			   
		ecoreResource = OntoUML2EcoreUtil.saveEcore(ecorePath,ecoremodel);		   
		return ecoreResource;
	}
	
	
	public static Resource convertToEcore (OntoUMLParser ontoparser, String ecorePath, OntoUML2EcoreOption opt)
	{
		log="";
		options=opt;
		Resource ecoreResource = null;
		refparser = ontoparser;
		
		etransformer = new EcoreTransformator(refparser,options);			  
		EPackage ecoremodel = etransformer.run();	
			   
		ecoreResource = OntoUML2EcoreUtil.saveEcore(ecorePath,ecoremodel);		   
		return ecoreResource;
	}		
	
	public static HashMap <RefOntoUML.Element, EModelElement> getOntoUML2EcoreMap ()
	{
		return etransformer.getConverter().getMap();
	}
	
	public static String getLog ()
	{
		return log;
	}
	
	public static OntoUMLParser getOntoUMLParser()
	{
		return refparser;
	}

}
