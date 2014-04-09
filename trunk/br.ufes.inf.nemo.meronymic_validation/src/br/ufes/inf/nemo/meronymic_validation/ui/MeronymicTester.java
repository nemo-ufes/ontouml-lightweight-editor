package br.ufes.inf.nemo.meronymic_validation.ui;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MeronymicTester {
	
	public static void main(String[] args) throws Exception {
		
		ResourceSet resourceSet = new ResourceSetImpl();
		@SuppressWarnings("unused")
		RefOntoUMLPackage refMetaPack = RefOntoUMLPackage.eINSTANCE;
		// Register the default resource factory -- only needed for stand-alone!
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// Get the URI of the model file.
	
//		URI fileURI = URI.createFileURI(new File("GeneralizationCycle.refontouml").getAbsolutePath());
		URI fileURI = URI.createFileURI(new File("IdentityProblem.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("InvalidGeneralization.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("GeneralizationCycle.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("GeneralizationCycle.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("GeneralizationCycle.refontouml").getAbsolutePath());
	
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		RefOntoUML.Package m;
		m = (RefOntoUML.Package)resource.getContents().get(0);

		System.out.println("File loaded: Teste.refontouml");
		
		OntoUMLParser parser = new OntoUMLParser(m);
		System.out.println("Parser loaded: "+ m.getName());
		
	
		PreConditionDialog.open(parser);
	}
	
}