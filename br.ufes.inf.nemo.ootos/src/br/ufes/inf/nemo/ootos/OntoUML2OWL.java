package br.ufes.inf.nemo.ootos;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.ParserException;

import RefOntoUML.Model;
import RefOntoUML.Package;
import br.ufes.inf.nemo.common.resource.RefOntoUMLResourceFactoryImpl;
import br.ufes.inf.nemo.ootos.ontouml2owl_swrl.Transformer;

public class OntoUML2OWL {
	public String errors = "";
	/***
	 * Transforms a RefOntoUML model into a OWL Ontology.
	 * 
	 * @param model
	 * @param ontologyIRI
	 * @return OWL ontology
	 * @throws Exception 
	 * @throws ParserException 
	 * @throws OWLOntologyCreationException
	 */
	public String Transformation(Package ecoreModel, String nameSpace, String oclRules) throws ParserException, Exception {
		Transformer transformer = new Transformer(ecoreModel, nameSpace, oclRules);
		String transform = transformer.transform();
		this.errors = transformer.getErrors();
		return transform;
	}

	/***
	 * Transforms a RefOntoUML model into a OWL Ontology.
	 * 
	 * @param modelFile
	 * @param ontologyIRI
	 * @return OWL ontology
	 * @throws Exception 
	 * @throws ParserException 
	 * @throws OWLOntologyCreationException
	 */
	public String Transformation(File modelFile, String nameSpace, String oclRules) throws ParserException, Exception {
		RefOntoUML.Model ecoreModel = intialize(modelFile);
		Transformer transformer = new Transformer(ecoreModel, nameSpace, oclRules);
		String transform = transformer.transform();
		this.errors = transformer.getErrors();
		return transform;
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
	//		String oclRules = "";
	//		String ret = null;
	//		try {
	//			OntoUML2OWL ontoUML2OWL = new OntoUML2OWL();
	//			ret = ontoUML2OWL.Transformation(intialize(new File("TesteWithRoles.refontouml")), "http://abcd/ontology/", oclRules);
	//			String errors = ontoUML2OWL.errors;
	//			System.out.println(errors);
	//			
	//		} catch (ParserException e1) {
	//			// TODO Auto-generated catch block
	//			e1.printStackTrace();
	//		} catch (Exception e1) {
	//			// TODO Auto-generated catch block
	//			e1.printStackTrace();
	//		}
	//		File arquivo;   
	//
	//		arquivo = new File("arquivo.owl");  // Chamou e nomeou o arquivo txt.  
	//		try{
	//			FileOutputStream fos = new FileOutputStream(arquivo);  // Perceba que estamos instanciando uma classe aqui. A FileOutputStream. Pesquise sobre ela!  
	//			fos.write(ret.getBytes());    
	//			fos.close();  // Fecha o arquivo. Nunca esquecer disso.  
	//		}catch(Exception e){
	//			e.printStackTrace();
	//		}
	//		System.out.println("foi");
	//	}
}
