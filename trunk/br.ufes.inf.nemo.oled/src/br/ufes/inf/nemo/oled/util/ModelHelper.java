package br.ufes.inf.nemo.oled.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Element;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relationship;
import RefOntoUML.VisibilityKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.NamedElementImpl;
import RefOntoUML.impl.RefOntoUMLPackageImpl;
import RefOntoUML.provider.RefOntoUMLItemProviderAdapterFactory;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.model.UmlProject;

public class ModelHelper {

	private static Diagnostician validator;
	private static ResourceSet resourceSet;
	private static RefOntoUMLFactory factory;
	private static ComposedAdapterFactory adapterFactory; // TODO Cleanup
	private static AdapterFactoryEditingDomain editingDomain; // TODO Cleanup
	private static boolean initialized = false;
	
	private static HashMap<Element,DiagramElement> mappings;

	private ModelHelper() {
	}

	public static Resource createResource() {
		if (!initialized) {
			initializeHelper();
		}
		Resource resource = resourceSet.createResource(URI.createFileURI(""));
		
		return resource;
	}

	public static void initializeHelper() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,new OLEDResourceFactory());
		
		resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI, RefOntoUML.RefOntoUMLPackage.eINSTANCE);		
				
		RefOntoUMLPackageImpl.init();

		validator = Diagnostician.INSTANCE;
		
		factory = RefOntoUMLFactory.eINSTANCE;
		adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new RefOntoUMLItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		// As the first validation takes long due to initialization process,
		// we start it here so the user doesn't get the initialization hit
		//validator.validate(factory.createClass());
		
		//validator2 = RefOntoUMLValidator.INSTANCE;
		
		mappings = new HashMap<>();
		
		initialized = true;
	}
	
	//Adds mapping from RefOntoUMLElement to DiagramElement (metamodel->concretemodel)
	//Returns true if the element was successfully added;
	public static boolean addMapping (Element element, DiagramElement diagramElement){
		DiagramElement result;
		if (!initialized) initializeHelper();		
		if (element==null || diagramElement==null) return false;		
		result = mappings.put(element, diagramElement);		
		if (result!=null) return true;
		else return false;
	}
	
	public static boolean removeMapping(Element element)
	{
		DiagramElement result;		
		if (!initialized) initializeHelper();		
		if (element==null) return false;				
		result = mappings.remove(element);		
		if (result!=null) return true;
		else return false;
	}
	
	public static boolean removeMapping(DiagramElement element)
	{
		DiagramElement result;		
		if (!initialized) initializeHelper();		
		if (element==null) return false;
		Element elem = getElement(element);
		if (elem!=null) {
			result = mappings.remove(elem);
			if (result!=null) return true;
		}		
		return false;
	}
	
	public static DiagramElement getDiagramElement (Element element){
		
		if (!initialized)
			initializeHelper();
		
		return mappings.get(element);
	}

    public static RefOntoUML.Element getElement(DiagramElement value) 
    {    	
        for (Entry<RefOntoUML.Element,DiagramElement> entry : mappings.entrySet()) 
        {
            if (value.equals(entry.getValue())) 
            {
                return entry.getKey();
            }
        }
        return null;
    }
	    
	   
	public static Collection<DiagramElement> getDiagramElements(Collection<Element> elements)
	{
		ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();		
		for(Element elem: elements){
			DiagramElement dElem = mappings.get(elem);
			if(dElem!=null) list.add(dElem);
		}
		return list;
	}
	
 
	public static Collection<Element> getElements(Collection<DiagramElement> diagramElements)
	{
		ArrayList<Element> list = new ArrayList<Element>();		
		for(DiagramElement e: diagramElements){
			Element elem = getElement(e);
			if(elem!=null) list.add(elem);
		}
		return list;
	}
	
	public static Collection<DiagramElement> getAllDiagramElements()
	{
		return mappings.values();
	}
	
	public static RefOntoUMLFactory getFactory() {
		if (!initialized) {
			initializeHelper();
		}
		return factory;
	}

	public static AdapterFactoryEditingDomain getAdapterEditingDomain() {
		if (!initialized) {
			initializeHelper();
		}
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
				new BasicCommandStack(), resourceSet);
		return editingDomain;
	}
	
	public static String getUUIDFromElement(Element element)
	{
		return element.eResource().getURIFragment(element);
	}
	
	public static Element getElementByUUID(RefOntoUML.Package model, String uuid)
	{
		return (Element) model.eResource().getEObject(uuid);
	}
	
	//Pseudo clone
	public static Classifier clone(Classifier classifier) {
		Classifier cloned = (Classifier) factory.create(classifier.eClass());
		// For now, copy only the essential attributes
		cloned.setName(classifier.getName());
		cloned.setIsAbstract(classifier.isIsAbstract());
		cloned.setVisibility(classifier.getVisibility());
		return cloned;
	}

	//Pseudo clone
	public static Relationship clone(Relationship relationship) {
		Relationship cloned = (Relationship) factory.create(relationship.eClass());
		
		if(cloned instanceof AssociationImpl)
		{
			Association association = (Association) relationship;
			Association associationCloned = (Association) cloned;
			
			associationCloned.setName(association.getName());
			associationCloned.setIsAbstract(association.isIsAbstract());
			associationCloned.setVisibility(association.getVisibility());
			associationCloned.setIsDerived(association.isIsDerived());
			
			if(cloned instanceof MeronymicImpl)
			{
				Meronymic meronymic = (Meronymic) relationship; 
				Meronymic meronymicCloned = (Meronymic) cloned;
				
				meronymicCloned.setIsShareable(meronymic.isIsShareable());
				meronymicCloned.setIsEssential(meronymic.isIsEssential());
				meronymicCloned.setIsInseparable(meronymic.isIsInseparable());
				meronymicCloned.setIsImmutableWhole(meronymic.isIsImmutableWhole());
				meronymicCloned.setIsImmutablePart(meronymic.isIsImmutablePart());
			}
		}
		
		return cloned;
	}

	  /**
	   * Create only the RefOntoUML Type element and do not create the referred UmlNode on the Diagram.
	   */
	  public static RefOntoUML.Type createType(ElementType elementType, String name)
	  {
		  RefOntoUML.Type type = null;
		  if (elementType.equals(ElementType.KIND)) type = factory.createKind();
		  if (elementType.equals(ElementType.COLLECTIVE)) type = factory.createCollective();
		  if (elementType.equals(ElementType.QUANTITY)) type = factory.createQuantity();
		  if (elementType.equals(ElementType.SUBKIND)) type = factory.createSubKind();
		  if (elementType.equals(ElementType.PHASE)) type = factory.createPhase();
		  if (elementType.equals(ElementType.ROLE)) type = factory.createRole();
		  if (elementType.equals(ElementType.CATEGORY)) { type = factory.createCategory(); ((Classifier)type).setIsAbstract(true); }	  
		  if (elementType.equals(ElementType.ROLEMIXIN)) { type = factory.createRoleMixin(); ((Classifier)type).setIsAbstract(true); }
		  if (elementType.equals(ElementType.MIXIN)) { type = factory.createMixin(); ((Classifier)type).setIsAbstract(true); }
		  if (elementType.equals(ElementType.MODE)) { type = factory.createMode();}
		  if (elementType.equals(ElementType.RELATOR)) { type = factory.createRelator();  }
		  if (elementType.equals(ElementType.DATATYPE)) { type = factory.createDataType();  }	  
		  type.setName(name);
		  type.setVisibility(VisibilityKind.PUBLIC);	  	
		  return type;			  
	  }
	  
	  /**
	   * Create only the RefOntoUML Relationship element and do not create the referred UmlConnection on the Diagram.
	   */
	  public static RefOntoUML.Relationship createRelationship(RelationType relationType, String name)
	  {
		  RefOntoUML.Relationship rel = null;
		  if (relationType.equals(RelationType.GENERALIZATION)) rel = factory.createGeneralization();
		  if (relationType.equals(RelationType.CHARACTERIZATION)) rel = factory.createCharacterization();
		  if (relationType.equals(RelationType.FORMAL)) rel = factory.createFormalAssociation();
		  if (relationType.equals(RelationType.MATERIAL)) { rel = factory.createMaterialAssociation(); ((MaterialAssociation)rel).setIsDerived(true); }
		  if (relationType.equals(RelationType.MEDIATION)) rel = factory.createMediation();
		  if (relationType.equals(RelationType.MEMBEROF)) { rel = factory.creatememberOf(); ((memberOf)rel).setIsShareable(true); }
		  if (relationType.equals(RelationType.SUBQUANTITYOF)) { rel = factory.createsubQuantityOf(); ((subQuantityOf)rel).setIsShareable(false); } 
		  if (relationType.equals(RelationType.SUBCOLLECTIONOF)) { rel = factory.createsubCollectionOf(); ((subCollectionOf)rel).setIsShareable(true); } 
		  if (relationType.equals(RelationType.COMPONENTOF)) { rel = factory.createcomponentOf(); ((componentOf)rel).setIsShareable(true); }
		  if (relationType.equals(RelationType.DERIVATION)) rel = factory.createDerivation();
		  if (relationType.equals(RelationType.ASSOCIATION)) rel = factory.createAssociation();	  
		  if (rel instanceof Classifier){			  
			  ((Classifier)rel).setName(name);		  
			  ((Classifier)rel).setVisibility(VisibilityKind.PUBLIC);
		  }
		  return rel;			  
	  }
	public static boolean validate(RefOntoUML.Package model, DiagnosticChain diagnostics,
			Map<Object, Object> context) {

		if (!initialized) {
			initializeHelper();
		}
				
		return validator.validate(model, diagnostics, context);
	}
	
	/**
	 * Handles the objects name when showing the error message
	 */
	public static String handleName(Object element)
	{
		String name = "[unnamed]";
		if(element instanceof NamedElementImpl)
		{
			NamedElementImpl namedElement = (NamedElementImpl) element;
			if (namedElement.getName() != null)
				name = namedElement.getName();
		}
		
		return MessageFormat.format("{0} ({1})", name, getClassAsStereotype((EObject) element));
	}
	
	public static String getClassAsStereotype(EObject eObject) {
		String ret = eObject.eClass().getName().toLowerCase()
				.replace("association", "");
		return "<<" + ret + ">>";
	}
 
	/**
	 * Helper method which creates a property with default multiplicity 1..1 as a owned end to associations
	 * of a given classifier
	 */
	public static Property getDefaultOwnedEnd(Classifier classifier, int lower, int upper) {
		if (!initialized) {
			initializeHelper();
		}
		
		Property property = factory.createProperty();
		property.setType(classifier);

		LiteralInteger lowerBound = factory.createLiteralInteger();
		lowerBound.setValue(lower);
		LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
		upperBound.setValue(upper);

		property.setLowerValue(lowerBound);
		property.setUpperValue(upperBound);

		return property;
	}

	
	public static String getMultiplicityString(Property property) {
		int lowerBound = property.getLower(), upperBound = property.getUpper();
		if (upperBound == -1)
			return lowerBound == 0 ? "*" : lowerBound + "..*";
		if (lowerBound == upperBound)
			return String.valueOf(lowerBound);
		return lowerBound + ".." + upperBound;
	}

	public static void setMultiplicityFromString(Property property, String str)
			throws ParseException {
		if (!initialized) {
			initializeHelper();
		}
		
		LiteralInteger lowerBound = factory.createLiteralInteger();
		LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
		
		Pattern pattern = Pattern.compile("\\d+|\\*|\\d+\\.\\.(\\d+|\\*)"); // 1..2, 1, N, *, 4..1, 1..1, 1..*, 0..*

		if (pattern.matcher(str).matches()) { 
			int lowerValue = 0, upperValue = 0;
			if (!str.contains("..")) {
				if(!str.contains("*")) // Multiplicities: 1, N
				{
					lowerValue = Integer.valueOf(str);
					upperValue = lowerValue;
				}
				else // Multiplicities: *
				{
					lowerValue = 0;
					upperValue = -1;
				}
			}
			else //1..2, 4..1, 1..1, 1..*, 0..*
			{
				String[] comps = str.split("\\.\\.");
				if(!str.contains("*")) // Multiplicities: 1..3, 0..2, 3..1
				{
					if(comps[0] == comps[1])
					{
						lowerValue = Integer.valueOf(str);
						upperValue = lowerValue;
					}
					else
					{
						lowerValue = Integer.valueOf(comps[0]);
						upperValue = Integer.valueOf(comps[1]);
						
						if(lowerValue > upperValue)
							throw new ParseException("could not parse '" + str + "'", 0);
					}
				}
				else // Multiplicities: 1..*, 0..*
				{
					lowerValue = Integer.valueOf(comps[0]);
					upperValue = -1;
				}
			}
			lowerBound.setValue(lowerValue);
			upperBound.setValue(upperValue);
		}
		else
		{
			throw new ParseException("could not parse '" + str + "'", 0);
		}

		if (property != null) {
			property.setLowerValue(lowerBound);
			property.setUpperValue(upperBound);
		}
	}

	public static void saveXMI(Resource resource) {
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
				Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);

		try {
			resource.save(saveOptions);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public static List<DataType> getModelDataTypes(UmlProject project) {
		List<DataType> dataTypes = new LinkedList<DataType>();
		Iterator<?> it = project.getModel().getPackagedElement().iterator();
		while (it.hasNext())
		{
			Object element = it.next();
			if(element instanceof DataTypeImpl)
				dataTypes.add((DataType) element);
		}
		return dataTypes;
	}
	
	public static ChangeRecorder getChangeRecorder()
	{
		if (!initialized) {
			initializeHelper();
		}
		return new ChangeRecorder(resourceSet);
	}
}
