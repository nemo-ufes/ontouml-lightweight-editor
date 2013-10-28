package br.ufes.inf.nemo.ontouml2ecore;

import java.util.HashMap;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * @author John Guerson
 */

public class OntoUML2Ecore {
	  
	private static EcoreTransformator etransformer;	
	private static OntoUMLParser refparser;
	public static String log = new String();
	
	public static Resource convertToEcore (RefOntoUML.Package refmodel, String ecorePath)
	{
		return convertToEcore(refmodel, ecorePath, false, true);
	}
	
	public static Resource convertToEcore (OntoUMLParser refparser, String ecorePath)
	{
		return convertToEcore(refparser, ecorePath, false, true);
	}
	
	public static Resource convertToEcore (RefOntoUML.Package refmodel, String ecorePath, boolean ignorePackageHierarchy, boolean ignoreDerivation)
	{
		log="";
		Resource ecoreResource = null;
		
		refparser = new OntoUMLParser(refmodel);
		
		etransformer = new EcoreTransformator(refparser,ignorePackageHierarchy, ignoreDerivation);			  
		EPackage ecoremodel = etransformer.run();	
			   
		ecoreResource = OntoUML2EcoreUtil.saveEcore(ecorePath,ecoremodel);		   
		return ecoreResource;
	}
	
	
	public static Resource convertToEcore (OntoUMLParser ontoparser, String ecorePath, boolean ignorePackageHierarchy, boolean ignoreDerivation)
	{
		log="";
		Resource ecoreResource = null;
		refparser = ontoparser;
		
		etransformer = new EcoreTransformator(refparser,ignorePackageHierarchy,ignoreDerivation);			  
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
