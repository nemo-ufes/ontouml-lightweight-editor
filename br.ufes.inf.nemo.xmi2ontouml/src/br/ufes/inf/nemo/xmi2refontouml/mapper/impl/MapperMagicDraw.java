package br.ufes.inf.nemo.xmi2refontouml.mapper.impl;

import java.util.List;
import java.util.Map;

import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2refontouml.util.ElementType;

public class MapperMagicDraw implements Mapper {

	@Override
	public Object getModelElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementType getType(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStereotype(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getElementById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getElements(Object element, ElementType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getProperties(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRelatorfromMaterial(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getDiagramList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDiagramElements(Object diagram) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPath(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	
//	public MapperEA(String inputPath) {		
//	//preProcess(inputPath);
//	
//	ResourceSet rs = new ResourceSetImpl();
//	rs.getPackageRegistry().put("http://schema.omg.org/spec/XMI/2.1", UMLPackage.eINSTANCE);
//	rs.getPackageRegistry().put("http://schema.omg.org/spec/UML/2.1", UMLPackage.eINSTANCE);
//	rs.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
//	rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(XMI2UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
//	rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", UMLResource.Factory.INSTANCE);
//	rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
//	//rs.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
//	
//	URI uri = URI.createFileURI(inputPath);
//	Model model = UML2Util.load(rs, uri, UMLPackage.Literals.MODEL);
//	//Profile prof = UML2Util.load(rs, uri, UMLPackage.Literals.PROFILE);
//	
//	System.out.println(model);
//
//	//model = (Model) rs.getResource(uri, true).getContents().get(0);
//	System.out.println(model);
//	//prof = (Profile) rs.getResource(uri, true).getContents().get(0);
//	
////	Resource resource = rs.getResource(uri, true);
////	for (EObject eobj : resource.getContents()) {
////		System.out.println(eobj);
////		for (EObject e : eobj.eContents()) {
////			System.out.println("    " + e);
////		}
////	}
//	
//	Resource resource = rs.createResource(URI.createURI("teste.uml"));
//	resource.getContents().add(model);
//	try {
//		resource.save(Collections.EMPTY_MAP);
//	}catch (Exception e) {
//		Mediator.errorLog += e.getMessage() + "\n";
//	}
//	
//	//aplicastereotipo(model);
//	
//	//saveDoc(docBackup, inputPath);
//	
//}
//
//private void preProcess(String inputPath) {
//	try {
//		// Creates an instance of the parser that will read the XMI
//        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//        docBuilderFactory.setNamespaceAware(true);
//        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//        
//        Document doc = docBuilder.parse(new File(inputPath));
//        docBackup = doc;
//        
////        NamedNodeMap atts = doc.getDocumentElement().getAttributes();
////        for (int i = 0; i < atts.getLength() ; i++) {
////        	System.out.println(atts.item(i).getNodeName());
////        	System.out.println(atts.item(i).getNodeValue());
////        }
//        
//        if (doc.getDocumentElement().hasAttribute("xmlns:thecustomprofile")) {
//        	String CUSTOM_NS = doc.getDocumentElement().getAttribute("xmlns:thecustomprofile");
//        	NodeList stereotypes = doc.getElementsByTagNameNS(CUSTOM_NS, "*");
//        	
//        	for (int i = 0; i < stereotypes.getLength(); i++) {
//        		Element newChild = doc.createElement("packagedElement");
//        		newChild.setAttribute("redefinedClassifier", stereotypes.item(i).
//        				getAttributes().item(0).getNodeValue());
//        		newChild.setAttribute("name", stereotypes.item(i).
//        				getNodeName().replace("thecustomprofile:", ""));
//        		newChild.setAttribute("xmi:type", "uml:Stereotype");
//        		
//        		stereotypes.item(i).getParentNode().appendChild(newChild);
//        	}
//        	
//        	saveDoc(doc, inputPath);
//        }
//        
//	} catch (SAXParseException err) {
//        Mediator.errorLog += "** Parsing error" + ", line "
//                + err.getLineNumber() + ", uri " + err.getSystemId();
//        Mediator.errorLog += " " + err.getMessage();
//
//	} catch (SAXException e) {
//		Exception x = e.getException();
//		Mediator.errorLog += ((x == null) ? e : x).getMessage();
//		
//	} catch (IOException e) {
//		Mediator.errorLog += "File " + inputPath + 
//		" does not exist or could not be oppened.";
//		
//	} catch (ParserConfigurationException e) {
//		Mediator.errorLog += e.getMessage();
//	}
//}
//
//private void saveDoc(Document doc, String inputPath) {
//	try {
//		Source source = new DOMSource(doc);
//		Result result = new StreamResult(new File(inputPath));
//		
//		// Write the DOM document to the file
//		Transformer xformer = TransformerFactory.newInstance().newTransformer();
//		xformer.transform(source, result);
//		
//	} catch (TransformerConfigurationException e) {
//		e.printStackTrace();
//	} catch (TransformerFactoryConfigurationError e) {
//		e.printStackTrace();
//	} catch (TransformerException e) {
//		e.printStackTrace();
//	}
//}
//
//public void aplicastereotipo(org.eclipse.uml2.uml.Package pack) {
//	EList<PackageableElement> list = pack.getPackagedElements();
//	System.out.println(list.size());
//	for (PackageableElement p : list) {
//		if (p instanceof org.eclipse.uml2.uml.Stereotype) {
//			System.out.println(p.getName());
//			System.out.println(((Stereotype)p).getRedefinedClassifiers());
//		}
//	}
//}
//
//@Override
//public Object getModelElement() {
//	return model;
//}
//
//@Override
//public List<Object> getElements(Object element, ElementType type) {
//	List<Object> elemList = new ArrayList<Object>();
//	List<?> newList;
//	
//	switch (type) {
//		case PACKAGE:
//			newList = ((org.eclipse.uml2.uml.Package)element).getNestedPackages();
//			elemList = (List<Object>) newList;
//			break;
//		case CLASS:
//			//EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.MODEL);
//			for (PackageableElement pel : ((org.eclipse.uml2.uml.Package)element).getPackagedElements()) {
//				if (pel instanceof org.eclipse.uml2.uml.Class) {
//					elemList.add(pel);
//				}
//			}
//			break;
//		case PROPERTY:
//			if (element instanceof org.eclipse.uml2.uml.Class) {
//				newList = ((org.eclipse.uml2.uml.Class)element).getOwnedAttributes();
//				elemList = (List<Object>) newList;
//			} else if (element instanceof org.eclipse.uml2.uml.Association) {
//				newList = ((org.eclipse.uml2.uml.Association)element).getOwnedEnds();
//				elemList = (List<Object>) newList;
//			}
//			break;
//		case ASSOCIATION:
//			for (PackageableElement pel : ((org.eclipse.uml2.uml.Package)element).getPackagedElements()) {
//				if (pel instanceof org.eclipse.uml2.uml.Association) {
//					elemList.add(pel);
//				}
//			}
//			break;
//		case GENERALIZATION:
//			newList = ((org.eclipse.uml2.uml.Classifier)element).getGeneralizations();
//			elemList = (List<Object>) newList;
//			break;
//		case GENERALIZATIONSET:
//			for (PackageableElement pel : ((org.eclipse.uml2.uml.Package)element).getPackagedElements()) {
//				if (pel instanceof org.eclipse.uml2.uml.GeneralizationSet) {
//					elemList.add(pel);
//				}
//			}
//			break;
//		case DEPENDENCY:
//			for (PackageableElement pel : ((org.eclipse.uml2.uml.Package)element).getPackagedElements()) {
//				if (pel instanceof org.eclipse.uml2.uml.Dependency) {
//					elemList.add(pel);
//				}
//			}
//			break;
//		case COMMENT:
//			newList = ((org.eclipse.uml2.uml.Element)element).getOwnedComments();
//			elemList = (List<Object>) newList;
//			break;
//	}
//	
//	return elemList;
//}
//
//@Override
//public String getStereotype(Object elem) {
//	EList<PackageableElement> list = model.getPackagedElements();
//	
//	for (PackageableElement p : list) {
//		if (p instanceof org.eclipse.uml2.uml.Stereotype) {
//			
//			EList<Classifier> list2 = ((Classifier) p).getRedefinedClassifiers();
//			
//			for (Classifier c : list2) {
//				if (c.equals(elem)) {
//					System.out.println(p.getName());
//					return p.getName();
//				}
//			}
//		}
//	}
//	return null;
//}
	
}
