package br.ufes.inf.nemo.oled.ontouml2owl_swrl;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import RefOntoUML.Model;
import br.ufes.inf.nemo.common.resource.RefOntoUMLResourceFactoryImpl;

public class OntoUML2OWL {
	/***
	 * Transforms a RefOntoUML model into a OWL Ontology.
	 * 
	 * @param model
	 * @param ontologyIRI
	 * @return OWL ontology
	 * @throws OWLOntologyCreationException
	 */
	public static String Transformation(Model ecoreModel, String nameSpace) {
		try {			
			Transformer transformer = new Transformer(nameSpace);
			String transform = transformer.transform(ecoreModel);
			return transform;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "ERROR: " + ex.getMessage();
		}
	}

	/***
	 * Transforms a RefOntoUML model into a OWL Ontology.
	 * 
	 * @param modelFile
	 * @param ontologyIRI
	 * @return OWL ontology
	 * @throws OWLOntologyCreationException
	 */
	public static String Transformation(File modelFile, String nameSpace) {
		try {			
			Transformer transformer = new Transformer(nameSpace);
			RefOntoUML.Model ecoreModel = intialize(modelFile);
			String transform = transformer.transform(ecoreModel);
			return transform;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "ERROR: " + ex.getMessage();
		}
	}
	
	
	public static RefOntoUML.Model intialize(File address) {
		try{
			//Criar um ResourceSet para "gerenciar" o resource do modelo
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new RefOntoUMLResourceFactoryImpl());
			resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,RefOntoUML.RefOntoUMLPackage.eINSTANCE);
			
			Resource resource = resourceSet.getResource(URI.createFileURI(address.getAbsolutePath()),true);
			return (Model) resource.getContents().get(0);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;

	}

//	public static void main(String[] args) {
//		String ret = OntoUML2OWL.Transformation(intialize(new File("modelo.refontouml")), "http://abcd/ontology/");
//		//System.out.println(ret);
//	}

}
