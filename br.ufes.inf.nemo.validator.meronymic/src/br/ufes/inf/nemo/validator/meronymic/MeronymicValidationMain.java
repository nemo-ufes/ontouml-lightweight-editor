package br.ufes.inf.nemo.validator.meronymic;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.parser.OntoUMLParser;

public class MeronymicValidationMain {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		
		ResourceSet resourceSet = new ResourceSetImpl();
		RefOntoUMLPackage refMetaPack = RefOntoUMLPackage.eINSTANCE;
		// Register the default resource factory -- only needed for stand-alone!
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// Get the URI of the model file.
	
		URI fileURI = URI.createFileURI(new File("GeneralizationCycle.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("InvalidGeneralization.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("IdentityProblem.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("AggregationProblem.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("MeronymicEndsProblem.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("MeronymicCycle.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("meronymicValid.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("allMeronymic.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("intransitiveMemberOf.refontouml").getAbsolutePath());
//		URI fileURI = URI.createFileURI(new File("forbiddenComponentOf.refontouml").getAbsolutePath());
		
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		RefOntoUML.Package m;
		m = (RefOntoUML.Package)resource.getContents().get(0);

		System.out.println("File loaded: "+fileURI.toString());
		
		OntoUMLParser parser = new OntoUMLParser(m);
		System.out.println("Parser loaded: "+ m.getName());
		
//		ValidationDialog dialog = new ValidationDialog(parser);
//		dialog.setBounds(100, 100, 860, 684);
//		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		dialog.setVisible(true);
		
//		ForbiddenFrame frame = new ForbiddenFrame(parser);
//		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frame.setVisible(true);
		
//		PreConditionDialog dialog = new PreConditionDialog(parser);
//		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		dialog.setVisible(true);
		
//		ValidationFrame.open(parser);`2
	}
	
}