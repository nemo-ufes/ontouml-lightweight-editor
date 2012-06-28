package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resource.XMI2UMLResource;
import org.w3c.dom.Element;



public class MapperEA implements Mapper {
	
	Model model;
	
	public MapperEA(String inputPath) {
		teste1(inputPath);
	}
	
	public void teste1(String inputPath) {
		ResourceSet rs = new ResourceSetImpl();
		rs.getPackageRegistry().put("http://schema.omg.org/spec/XMI/2.1", UMLPackage.eINSTANCE);
		rs.getPackageRegistry().put("http://schema.omg.org/spec/UML/2.1", UMLPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(XMI2UMLResource.FILE_EXTENSION, new XMIResourceFactoryImpl());
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMIResourceFactoryImpl());
		rs.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		
		URI uri = URI.createFileURI(inputPath);
		model = UML2Util.load(rs, uri, UMLPackage.Literals.MODEL);
		System.out.println(model);
//		System.out.println(root);
//		for (PackageableElement pel : root.getPackagedElements()) {
//			System.out.println(pel);
//		}
//		
//		Resource resource = rs.createResource(URI.createURI("teste.xmi"));
//		resource.getContents().add(root);
//		
//		try {
//			resource.save(Collections.EMPTY_MAP);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		uri = URI.createURI("PessoaSimplesEA2.1.xml");		
//		org.eclipse.uml2.uml.Package root2 = UML2Util.load(rs, uri, UMLPackage.Literals.PACKAGE);
//		System.out.println(root2);
		
	}
	
	public void teste2() {
		ResourceSet rs = new ResourceSetImpl();
		// Register additional packages here. For UML2:
	   Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	   rs.getPackageRegistry().put(UMLPackage.eNS_URI,UMLPackage.eINSTANCE);
	   
	   try {
	      System.out.println("Loading resources.\n"); //$NON-NLS-1$
	      // Loads the model to modify
//	      final EObject model1 = ModelUtils.load(new 
//	    		  File("D:/eclipse/workspace/EMFTEST/model/model.uml"),rs);
	 
	      System.out.println("Loaded.");
	   } catch (Exception e) { // shouldn't be thrown
	      e.printStackTrace();
	   }
	}
	
	public void teste3() {
		ResourceSet rs = new ResourceSetImpl();
		rs.getPackageRegistry().put(UMLPackage.eNS_URI, 
		UMLPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap( ).put(UMLResource.FILE_EXTENSION, 
		UMLResource.Factory.INSTANCE);

//		Map<URI, URI> uriMap = rs.getURIConverter().getURIMap();
//		URI pathmap = URI.createURI("pathmap://XMLmodeling_LIBRARIES/XSDDataTypes.library.uml/");
//		URI types = URI.createFileURI(new File(typesFilename).getAbsolutePath());
//		uriMap.put(pathmap, types);

		URI uri = URI.createFileURI(new File("PessoaSimplesEA2.1.xmi").getAbsolutePath());
		Model model = null;
		try {
			Resource resource = rs.getResource(uri, true);
			EcoreUtil.resolveAll(resource);
	
			model = (Model) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.MODEL);
			System.out.println(model);
			
		} catch (WrappedException we) {
			System.err.println(we.getMessage());
		}
	}
	
	public void teste4() {
		ResourceSet rs = new ResourceSetImpl();
//		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
//		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
//		rs.getPackageRegistry().put(UMLPackage.eNS_URI,UMLPackage.eINSTANCE);
		//rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		
		rs.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
//		Map uriMap = rs.getURIConverter().getURIMap();
//		URI uri = URI.createURI("jar:file:/C:/eclipse/plugins/org.eclipse.uml2.uml.resources_<version>.jar!/"); // for example
//		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
//		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
//		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
		
		//rs.createResource(uri);
		//Resource resource = rs.getResource(uri, true);
		
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
	public Object getModelElement() {
		return model;
	}

	@Override
	public List<Object> getElements(Object element, ElementType type) {
		List<Object> elemList = new ArrayList<Object>();
		List<?> newList;
		
		switch (type) {
			case PACKAGE:
				newList = ((org.eclipse.uml2.uml.Package)element).getNestedPackages();
				elemList = (List<Object>) newList;
				break;
			case CLASS:
				for (PackageableElement pel : ((org.eclipse.uml2.uml.Package)element).getPackagedElements()) {
					if (pel instanceof org.eclipse.uml2.uml.Class) {
						elemList.add(pel);
					}
				}
				break;
			case PROPERTY:
				if (element instanceof org.eclipse.uml2.uml.Class) {
					newList = ((org.eclipse.uml2.uml.Class)element).getOwnedAttributes();
					elemList = (List<Object>) newList;
				} else if (element instanceof org.eclipse.uml2.uml.Association) {
					newList = ((org.eclipse.uml2.uml.Association)element).getOwnedEnds();
					elemList = (List<Object>) newList;
				}
				break;
			case ASSOCIATION:
				for (PackageableElement pel : ((org.eclipse.uml2.uml.Package)element).getPackagedElements()) {
					if (pel instanceof org.eclipse.uml2.uml.Association) {
						elemList.add(pel);
					}
				}
				break;
			case GENERALIZATION:
				newList = ((org.eclipse.uml2.uml.Classifier)element).getGeneralizations();
				elemList = (List<Object>) newList;
				break;
			case GENERALIZATIONSET:
				for (PackageableElement pel : ((org.eclipse.uml2.uml.Package)element).getPackagedElements()) {
					if (pel instanceof org.eclipse.uml2.uml.GeneralizationSet) {
						elemList.add(pel);
					}
				}
				break;
			case DEPENDENCY:
				for (PackageableElement pel : ((org.eclipse.uml2.uml.Package)element).getPackagedElements()) {
					if (pel instanceof org.eclipse.uml2.uml.Dependency) {
						elemList.add(pel);
					}
				}
				break;
			case COMMENT:
				newList = ((org.eclipse.uml2.uml.Element)element).getOwnedComments();
				elemList = (List<Object>) newList;
				break;
		}
		
		System.out.println(elemList);
		
		return elemList;
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
