package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resource.XMI2UMLResource;



public class MapperEA implements Mapper {
	
	Model model;
	
	public MapperEA(String inputPath) {
		ResourceSet rs = new ResourceSetImpl();
//		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
//		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
//		rs.getPackageRegistry().put(UMLPackage.eNS_URI,UMLPackage.eINSTANCE);
		rs.getPackageRegistry().put("http:||schema.omg.org/spec/XMI/2.1", UMLPackage.eINSTANCE);
		rs.getPackageRegistry().put("http:||schema.omg.org/spec/UML/2.1", UMLPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(XMI2UMLResource.FILE_EXTENSION, new XMIResourceFactoryImpl());
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		rs.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		
		
		URI uri = URI.createFileURI(inputPath);
		//rs.createResource(uri);
		Resource resource = rs.getResource(uri, true);
		//Package root = UML2Util.load(rs, uri, UMLPackage.Literals.PACKAGE);
		//System.out.println(root);
		
//		System.out.println(UMLPackage.eNS_URI);
//		
//		File sourceFile = new File(inputPath);
//		if (!sourceFile.isFile())
//		{
//			System.out.println("Error accessing: " + sourceFile.getAbsolutePath());
//			return;
//		}		
//		URI uri = URI.createFileURI(sourceFile.getAbsolutePath());
//		
//		System.out.println("criou uri");
//		
//		rs.createResource(uri);
//		
//		Resource resource = rs.getResource(uri, true);
//		
//		System.out.println("criou resource");
//		
//		model = (Model) resource.getContents().get(0);
//		
//		System.out.println("tudo certo");
		
//		try {
//		      System.out.println("Loading resources.\n"); //$NON-NLS-1$
//		      // Loads the model to modify
//		      final EObject model1 = ModelUtils.load(sourceFile, rs);
//		 
//		      System.out.println("Loaded.");
//		   } catch (Exception e) { // shouldn't be thrown
//		      e.printStackTrace();
//		   }
	}

	@Override
	public String getIDName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getModelElement() {
		return model;
	}

	@Override
	public List<Object> getElements(Object element, ElementType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStereotype(Object elem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getProperties(Object elem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID(Object elem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(Object elem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementType getType(Object elem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getElementById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
