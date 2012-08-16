package refontouml2alloy;

/**
 * This is used as a Reader for this transformation
 * 
 * @author Lucas Thom
 */

import java.io.File;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import refontouml2alloy.util.StringCheck;

import RefOntoUML.*;
import RefOntoUML.Class;
import RefOntoUML.Package;

public class Reader {
	
	public static Package p;
	public static String modelname;
	public static Transformer transformer;	
	public static ArrayList<PackageableElement> modelElements = new ArrayList<PackageableElement>();	
	public static String inputPath = "";
		
	/** Initializes the process of transformation.*/
	public static void init(Model m)
	{	
		transformer = new Transformer();
		
		// Read the objects in the model 
		if(m == null)
		{
			// Configure ResourceSet
			ResourceSet rs = initResource();
			
			// Open the model 
			URI uri = openFile();
			
			p = readModel(rs, uri);
		}
		// If the model is already instantiated
		else
			p = readModel(m);
		
		// PreProccess model to know which standard signatures that will be used (Signatures: Property; Object; DataType) 
		preProccess(p);
		
		// Initializes the transformation making a skell document(with standard structure from als file) for Alloy output 
		transformer.init(m);
	}

	public static void transformModel(Package p) 
	{
		checkNames(p);
		
		readClassifiers(p);
		readGeneralizations(p);
		readGeneralizationSets(p);
		readAssociations(p);
		
		if(transformer.ObjectsList.size() > 0)
			transformer.createExists("Object");
		
		if(transformer.PropertiesList.size() > 0)
			transformer.createExists("Property");
		
		if(transformer.datatypesList.size() > 0)
			transformer.createExists("Datatype");
		
		transformer.createKindDatatypePropertyDisjoint();
		transformer.finalAdditions();
	}
	
	private static void checkNames(Package p) 
	{
		StringCheck ch = new StringCheck();
		
		for(PackageableElement pe : p.getPackagedElement())
		{
			if((pe instanceof Class || pe instanceof Association || pe instanceof DataType) && !(pe instanceof PrimitiveType))
			{
				if(pe instanceof Association)
				{
					if(((Association)pe).getOwnedEnd().get(0).getName() != null && !(((Association)pe).getOwnedEnd().get(0).getName().equals("")))
					{
						((Association)pe).getOwnedEnd().get(0).setName(ch.removeSpecialNames(((Association)pe).getOwnedEnd().get(0).getName()));
					}
					if(((Association)pe).getOwnedEnd().get(1).getName() != null && !(((Association)pe).getOwnedEnd().get(1).getName().equals("")))
					{
						((Association)pe).getOwnedEnd().get(1).setName(ch.removeSpecialNames(((Association)pe).getOwnedEnd().get(1).getName()));
					}
				}
				if( pe.getName() != null )
					if(pe.getName().compareTo("") != 0)
						pe.setName(ch.removeSpecialNames(pe.getName()));
				else if (pe.getName().compareTo("") == 0)
					pe.setName(ch.removeSpecialNames(pe.getClass().toString()));
			}
		}
	}
	
	private static void preProccess(Package p) 
	{		
		modelname = p.getName();
		
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof ObjectClass) {
				transformer.defaultSignatures.add("Object");
				break;
			}
		}
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof MomentClass) {
				transformer.defaultSignatures.add("Property");
				break;
			}
		}
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof DataType && !(pe instanceof PrimitiveType) ) {
				transformer.defaultSignatures.add("DataType");
				break;
			}
		}
	}
	
	private static void readClassifiers(Package p) 
	{
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof Classifier) 
			{
				transformer.transformClassifier((Classifier) pe, p);
				
				if(((Classifier)pe).isIsAbstract())
					transformer.createAbstractClause((Classifier) pe,p);
				
				//For each rigid class (kind, subkind, collective, quantity, category, relator, mode, datatype), there is one fact that states its rigidity
				if(((pe instanceof Kind) || (pe instanceof SubKind) || (pe instanceof Collective) || (pe instanceof Quantity) || (pe instanceof Category) || (pe instanceof Relator) || (pe instanceof Mode) || (pe instanceof DataType)) && !(pe instanceof PrimitiveType))
					transformer.rigidElements.add((Classifier)pe);
			}
		}
	}
	
	private static void readAssociations(Package p) 
	{		
		for (PackageableElement pe : p.getPackagedElement())
		{
			if (pe instanceof Association && !(pe instanceof Derivation))
				transformer.transformAssociations((Association) pe);
		}
		for (PackageableElement pe : p.getPackagedElement())
		{
			if (pe instanceof Derivation)
				transformer.transformDerivations((Derivation) pe, p);
		}
	}
	
	private static void readGeneralizations(Package p) 
	{		
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof Class)
				for(Generalization gen : ((Class)pe).getGeneralization())
					transformer.transformGeneralizations(gen);
		}
	}
	
	private static void readGeneralizationSets(Package p) 
	{		
		for (PackageableElement pe : p.getPackagedElement())
		{			
			if (pe instanceof GeneralizationSet)
				transformer.transformGeneralizationSets((GeneralizationSet) pe);
		}
	}

	/**
	*This is a recursive method to collect elements there are no package, in
	*other words: This method removes the packages but keeps all elements
	*at the same level in hierarchy
	**/
	private static void addElements(PackageableElement pack) 
	{
		for(PackageableElement p : ((Package) pack).getPackagedElement())
		{
				if(p instanceof Package)
					addElements(p);
				else
					modelElements.add(p);
		}
	}
	
	/** 
	 *  Read model elements and prepare a new instance from model that will be used in transformation
	 *  That function constructs a model without package hierarchy. 
	 *  Already instantiated model (Model m)
	 */
	private static Package readModel(Model m) 
	{		
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;		
		Package p = m;
		Model m2 = factory.createModel();
		StringCheck ch = new StringCheck();
		
		if(p.getName() == null)
			m2.setName("alloymodel");
		else
			m2.setName(ch.removeSpecialNames(p.getName()));
		
		//Remove nested packages
		addElements(p);
		
		for(PackageableElement pack : modelElements)
			m2.getPackagedElement().add(pack);
		
		return (Package)m2;
	}
	
	/** 
	 *  Read model elements and prepare a new instance from model that will be used in transformation
	 *  That function constructs a model without package hierarchy. 
	 *  Non instantiated model (Will be created from the uri path)
	 */
	private static Package readModel(ResourceSet rs, URI uri) 
	{		
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;		
		Resource resource = rs.getResource(uri, true);
		EObject p1 = resource.getContents().get(0);
		StringCheck ch = new StringCheck();
		Package p = (Package)p1;
		Model m = factory.createModel();
		
		if(p.getName() == null)
			m.setName("alloymodel");
		else
			m.setName(ch.removeSpecialNames(p.getName()));
		
		addElements(p);
		
		for(PackageableElement pack : modelElements)
			m.getPackagedElement().add(pack);
		
		return (Package)m;
	}

	private static URI openFile() 
	{
		File sourceFile = new File(inputPath);
		if (!sourceFile.isFile())
		{
			System.out.println("Error accessing: " + sourceFile.getAbsolutePath());
			return null;
		}		
		URI uri = URI.createFileURI(sourceFile.getAbsolutePath());
		return uri;
	}

	private static ResourceSet initResource() 
	{		
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		rs.getPackageRegistry().put(RefOntoUMLPackage.eNS_URI,RefOntoUMLPackage.eINSTANCE);		
		return rs;
	}

}
