package br.ufes.inf.nemo.ontouml2temporalowl;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import RefOntoUML.Package;
import br.ufes.inf.nemo.ontouml2temporalowl.auxiliary.MappingType;
import br.ufes.inf.nemo.ontouml2temporalowl.auxiliary.OWLStructure;
import br.ufes.inf.nemo.ontouml2temporalowl.tree.TreeProcessor;
import br.ufes.inf.nemo.ontouml2temporalowl.verbose.FileManager;

public class OntoUML2OWL
{

	static FileManager myfile;
	private static OWLStructure owl;
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		OntoUML2OWL.Transformation(args[0], args[0].replace(".refontouml", ".owl"));
	}
	
	public static void Transformation (String fileAbsolutePath, String modelName)
	{
		// Configure ResourceSet
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI,RefOntoUML.RefOntoUMLPackage.eINSTANCE);
				
		// Open the model
		File sourceFile = new File(fileAbsolutePath);
		if (!sourceFile.isFile())
		{
			System.out.println("Error accessing: " + sourceFile.getAbsolutePath());
			return;
		}		
		URI uri = URI.createFileURI(sourceFile.getAbsolutePath());
						
		try
		{
			// Read the objects in the model
			Resource resource = resourceSet.getResource(uri, true);
			EObject p1 = resource.getContents().get(0);
			Package p = (Package) p1;

			// Processing the OntoUML model as a structure containing 
			// a specialization tree for the Classes 
			// and a list of Binary Associations
			TreeProcessor tp = new TreeProcessor(p);

			// mapping the OntoUML-based structure into an OWL-based structure
			// according to a certain mapping type
			map2OWLStructure(tp, MappingType.WORM_VIEW_A0);
			
			// Writing transformed model into owl file 
			myfile = new FileManager(fileAbsolutePath.replace(".refontouml", ".owl"));
			myfile.write(owl.verbose(modelName));
			myfile.done();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void map2OWLStructure (TreeProcessor tp, MappingType mt)
	{
		owl = new OWLStructure(mt);
		owl.map(tp);
	}
}
