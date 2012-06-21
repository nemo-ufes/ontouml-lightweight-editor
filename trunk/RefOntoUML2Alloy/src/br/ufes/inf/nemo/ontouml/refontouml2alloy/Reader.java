package br.ufes.inf.nemo.ontouml.refontouml2alloy;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import br.ufes.inf.nemo.ontouml.refontouml2alloy.namesaux.StringCheck;

import RefOntoUML.*;
import RefOntoUML.Class;
import RefOntoUML.Package;

public class Reader {
	public static Package p;
	public static String modelname;
	
	public static ArrayList<PackageableElement> modelElements = new ArrayList<PackageableElement>();
	
	public static String inputPath = "teste.xml";
	
	public static void init(Model m){		
		// Read the objects in the model
		if(m == null)
		{
			// Configure ResourceSet
			ResourceSet rs = initResource();
			
			// Open the model
			URI uri = openFile();
			
			p = readModel(rs, uri);
		}
		else
			p = readModel(m);
		
		preProccess(p);
	}

	public static void transformModel(Package p) {
		checkNames(p);
		readClassifiers(p);
		readGeneralizations(p);
		readGeneralizationSets(p);
		readAssociations(p);
		if(Transformer.substantialsList.size() > 0)
			Transformer.createExists("Substantial");
		if(Transformer.momentsList.size() > 0)
			Transformer.createExists("Moment");
		if(Transformer.datatypesList.size() > 0)
			Transformer.createExists("Datatype");
		Transformer.createKindDatatypeMomentDisjoint();
		Transformer.finalAdditions();
	}
	
	private static void checkNames(Package p) {
		StringCheck ch = new StringCheck();
		for(PackageableElement pe : p.getPackagedElement())
		{
			if(pe instanceof Class || pe instanceof Association)
			{
				if( pe.getName() != null )
					if(pe.getName().compareTo("") != 0)
						pe.setName(ch.removeSpecialNames(pe.getName()));
				else if (pe.getName().compareTo("") == 0)
					pe.setName(ch.removeSpecialNames(pe.getClass().toString()));
			}
		}
	}
	
	private static void preProccess(Package p) {
		modelname = p.getName();
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof ObjectClass) {
				Transformer.defaultSignatures.add("Substantial");
				break;
			}
		}
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof MomentClass) {
				Transformer.defaultSignatures.add("Moment");
				break;
			}
		}
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof DataType) {
				Transformer.defaultSignatures.add("DataType");
				break;
			}
		}
	}
	
	private static void readClassifiers(Package p) {
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof Classifier) {
				Transformer.transformClassifier((Classifier) pe, p);
				if(((Classifier)pe).isIsAbstract())
					Transformer.createAbstractClause((Classifier) pe,p);
				//For each rigid class (kind, subkind, collective, quantity, category, relator, mode, datatype), there is one fact that states its rigidity
				if((pe instanceof Kind) || (pe instanceof SubKind) || (pe instanceof Collective) || (pe instanceof Quantity) || (pe instanceof Category) || (pe instanceof Relator) || (pe instanceof Mode) || (pe instanceof DataType))
					Transformer.rigidElements.add((Classifier)pe);
			}
		}
	}
	
	private static void readAssociations(Package p) {
		for (PackageableElement pe : p.getPackagedElement())
		{
			if (pe instanceof Association && !(pe instanceof Derivation))
				Transformer.transformAssociations((Association) pe);
		}
		for (PackageableElement pe : p.getPackagedElement())
		{
			if (pe instanceof Derivation)
				Transformer.transformDerivations((Derivation) pe, p);
		}
	}
	
	private static void readGeneralizations(Package p) {
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof Class)
				for(Generalization gen : ((Class)pe).getGeneralization())
					Transformer.transformGeneralizations(gen);
		}
	}
	
	private static void readGeneralizationSets(Package p) {
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof GeneralizationSet)
				Transformer.transformGeneralizationSets((GeneralizationSet) pe);
		}
	}

	private static void addElements(PackageableElement pack) {
		for(PackageableElement p : ((Package) pack).getPackagedElement())
		{
				if(p instanceof Package)
					addElements(p);
				else
					modelElements.add(p);
		}
	}
	
	private static Package readModel(Model m) {
		
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		
		Package p = m;
		Model m2 = factory.createModel();
		StringCheck ch = new StringCheck();
		if(p.getName() == null)
			m2.setName("modelo");
		else
			m2.setName(ch.removeSpecialNames(p.getName()));
		
		addElements(p);
		
		for(PackageableElement pack : modelElements)
			m2.getPackagedElement().add(pack);
		
		return (Package)m2;
	}
	
	private static Package readModel(ResourceSet rs, URI uri) {
		
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		
		Resource resource = rs.getResource(uri, true);
		EObject p1 = resource.getContents().get(0);
		StringCheck ch = new StringCheck();
		Package p = (Package)p1;
		Model m = factory.createModel();
		if(p.getName() == null)
			m.setName("modelo");
		else
			m.setName(ch.removeSpecialNames(p.getName()));
		
		addElements(p);
		
		for(PackageableElement pack : modelElements)
			m.getPackagedElement().add(pack);
		
		return (Package)m;
	}

	private static URI openFile() {
		File sourceFile = new File(inputPath);
		if (!sourceFile.isFile())
		{
			System.out.println("Error accessing: " + sourceFile.getAbsolutePath());
			return null;
		}		
		URI uri = URI.createFileURI(sourceFile.getAbsolutePath());
		return uri;
	}

	private static ResourceSet initResource() {
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		rs.getPackageRegistry().put(RefOntoUMLPackage.eNS_URI,RefOntoUMLPackage.eINSTANCE);
		return rs;
	}

}
