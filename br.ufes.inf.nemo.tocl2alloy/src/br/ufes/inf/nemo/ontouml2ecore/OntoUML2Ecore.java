package br.ufes.inf.nemo.ontouml2ecore;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class OntoUML2Ecore {
	  
	private static EcoreTransformator etransformer;	
	public static String log = new String();
	
	public static Resource convertToEcore (RefOntoUML.Package refmodel, String ecorePath)
	{
		return convertToEcore(refmodel, ecorePath, false, false);
	}
	
	public static Resource convertToEcore (OntoUMLParser refparser, String ecorePath)
	{
		return convertToEcore(refparser, ecorePath, false, false);
	}
	
	public static Resource convertToEcore (RefOntoUML.Package refmodel, String ecorePath, boolean ignorePackageHierarchy, boolean ignoreDerivation)
	{
		log="";
		Resource ecoreResource = null;
		
		OntoUMLParser refparser = new OntoUMLParser(refmodel);
		
		etransformer = new EcoreTransformator(refparser,ignorePackageHierarchy, ignoreDerivation);			  
		EPackage ecoremodel = etransformer.run();	
			   
		ecoreResource = OntoUML2EcoreUtil.saveEcore(ecorePath,ecoremodel);		   
		return ecoreResource;
	}
	
	
	public static Resource convertToEcore (OntoUMLParser refparser, String ecorePath, boolean ignorePackageHierarchy, boolean ignoreDerivation)
	{
		log="";
		Resource ecoreResource = null;
		
		etransformer = new EcoreTransformator(refparser,ignorePackageHierarchy,ignoreDerivation);			  
		EPackage ecoremodel = etransformer.run();	
			   
		ecoreResource = OntoUML2EcoreUtil.saveEcore(ecorePath,ecoremodel);		   
		return ecoreResource;
	}		
	
	public static HashMap <RefOntoUML.Element, EModelElement> getMap ()
	{
		return etransformer.getConverter().getMap();
	}
	
	public static String getLog ()
	{
		return log;
	}
	
	public static void main(String[] args)
	{
		try {
			String path = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\Imóvel.refontouml";
			
			convertToEcore(new OntoUMLParser(path),path.replace(".refontouml", ".ecore" ),true,true);
			
			System.out.println(getLog());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
