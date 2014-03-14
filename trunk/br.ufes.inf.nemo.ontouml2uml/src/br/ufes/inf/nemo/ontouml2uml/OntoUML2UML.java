package br.ufes.inf.nemo.ontouml2uml;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * This class transforms OntoUML models into UML. It simply ignores the stereotypes of classes and relations.
 * The user can optionally ignore the package hierarchy or the derivation relationship in the transformation.
 * 
 * Furthermore, the user can additionally transform OntoUML into a Temporal UML model that includes UFO's
 * branching time structure. In this temporal UML model, each association of the OntoUML model is reified into a class.
 * This reified class in turn and every other top level class of the model is additionally linked to the worlds in which they exist.
 * Moreover, several world operations and existence operations were added to classes. This conversion is useful for
 * the Temporal OCL extension developed which uses this temporal UML model (in background) for manipulation of OCL constructs.
 *  
 * @author John Guerson
 *
 */
public class OntoUML2UML {
		 	  		
	public static String log = new String();
	private static OntoUML2UMLOption options = new OntoUML2UMLOption();	
	private static UMLTransformator utransformer;		
	private static org.eclipse.uml2.uml.Package umlRoot;
	private static String umlPath = new String();
	
	private static UMLTemporalGenerator tgenerator; 
	
	public static org.eclipse.uml2.uml.Package getRoot() { return umlRoot; }
	public static String getUmlPath() { return umlPath; }
	
	public static Resource convertToUML (RefOntoUML.Package refmodel, String umlPath)
	{
		return convertToUML(refmodel, umlPath, options);
	}
	
	public static Resource convertToUML (OntoUMLParser refparser, String umlPath)
	{
		return convertToUML(refparser, umlPath, options);
	}
	
	public static Resource convertToTemporalUML (OntoUMLParser refparser, String umlPath)
	{
		return convertToTemporalUML(refparser, umlPath, options);
	}
	
	public static Resource convertToTemporalUML (RefOntoUML.Package refmodel, String umlPath)
	{
		return convertToTemporalUML(refmodel, umlPath, options);
	}
	
	public static Resource includeTemporalStructure(org.eclipse.uml2.uml.Package umlRoot, String umlPath)
	{	
		tgenerator = new UMLTemporalGenerator(
			umlRoot, 
			utransformer.getConverter().ufactory, 
			utransformer.getConverter().umap);
		tgenerator.run();
		
		log += tgenerator.getTemporalLog();
		
		return OntoUML2UMLUtil.saveUML(umlPath,umlRoot);		
	}
	
	public static Resource convertToUML (RefOntoUML.Package refmodel, String umlPath, OntoUML2UMLOption opt)
	{
		log="";
		options=opt;
		Resource umlResource = null;
		
		OntoUMLParser refparser = new OntoUMLParser(refmodel);
		
		utransformer = new UMLTransformator(refparser,opt);			  
		org.eclipse.uml2.uml.Package umlmodel = utransformer.run();	
   		   
		OntoUML2UML.umlPath=umlPath;
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
   		   
		OntoUML2UML.umlPath=umlPath;
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlmodel);		   
		return umlResource;
	}		
	
	public static Resource convertToTemporalUML (RefOntoUML.Package refmodel, String umlPath, OntoUML2UMLOption opt)
	{
		Resource umlResource = convertToUML(refmodel, umlPath, opt);
		umlRoot = (org.eclipse.uml2.uml.Package)umlResource.getContents().get(0);
		
		tgenerator = new UMLTemporalGenerator(
			umlRoot, 
			utransformer.getConverter().ufactory, 
			utransformer.getConverter().umap);
		tgenerator.run();
		
		log += tgenerator.getTemporalLog();
		
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlRoot);
		
		return umlResource;
	}
	
	public static Resource convertToTemporalUML(OntoUMLParser refparser, String umlPath, OntoUML2UMLOption opt)
	{
		Resource umlResource = convertToUML(refparser, umlPath, opt);
		umlRoot = (org.eclipse.uml2.uml.Package)umlResource.getContents().get(0);
		
		tgenerator = new UMLTemporalGenerator(
			umlRoot, 
			utransformer.getConverter().ufactory, 
			utransformer.getConverter().umap);
		tgenerator.run();
		
		log += tgenerator.getTemporalLog();
		
		umlResource = OntoUML2UMLUtil.saveUML(umlPath,umlRoot);
		
		return umlResource;   		
	}
	
	public static HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> getStandardMap ()
	{
		return utransformer.getConverter().getMap();
	}
	
	public static HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>> getTemporalMap()
	{
		if (tgenerator !=null) return tgenerator.getMap();
		else return null;
	}
	
	public static String getLog ()
	{
		return log;
	}	
}
