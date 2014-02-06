package br.ufes.inf.nemo.oled.util;

import java.text.MessageFormat;
import java.text.Normalizer;
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
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relationship;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.CharacterizationImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.DerivationImpl;
import RefOntoUML.impl.DirectedBinaryAssociationImpl;
import RefOntoUML.impl.FormalAssociationImpl;
import RefOntoUML.impl.MaterialAssociationImpl;
import RefOntoUML.impl.MediationImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.NamedElementImpl;
import RefOntoUML.impl.RefOntoUMLPackageImpl;
import RefOntoUML.provider.RefOntoUMLItemProviderAdapterFactory;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;

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
	
	public static boolean addMapping (StructureDiagram diagram)
	{
		boolean succeeds=false;
		for(DiagramElement dElem: diagram.getChildren())
		{
			if (dElem instanceof ClassElement) {
				ModelHelper.addMapping(((ClassElement)dElem).getClassifier(), dElem);
				succeeds=true;
			}
			if (dElem instanceof AssociationElement) {
				ModelHelper.addMapping(((AssociationElement)dElem).getRelationship(), dElem);
				succeeds=true;
			}
			if (dElem instanceof GeneralizationElement) {
				ModelHelper.addMapping(((GeneralizationElement)dElem).getRelationship(), dElem);
				succeeds=true;
			}
		}
		return succeeds;
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

	public static HashMap<Element,DiagramElement> getMapping()
	{
		return mappings;
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
	
	public static String getAllDiagramElementsString()
	{
		String result = new String("Diagram Elements:");
		for(DiagramElement elem: mappings.values()){
			result += elem.toString()+"\n";
		}
		return result;
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
			
			RefOntoUML.Property p1Cloned = (RefOntoUML.Property)factory.create(association.getMemberEnd().get(0).eClass());
			RefOntoUML.Property p2Cloned = (RefOntoUML.Property)factory.create(association.getMemberEnd().get(1).eClass());
			RefOntoUML.Property p1 = association.getMemberEnd().get(0);
			RefOntoUML.Property p2 = association.getMemberEnd().get(1);
			
			p1Cloned.setAggregation(p1.getAggregation());
			p2Cloned.setAggregation(p2.getAggregation());
			
			p1Cloned.setType(p1.getType());
			p2Cloned.setType(p2.getType());
			
			LiteralInteger lower1Cloned = factory.createLiteralInteger();
			lower1Cloned.setValue(p1.getLower());
			LiteralUnlimitedNatural upper1Cloned = factory.createLiteralUnlimitedNatural();
			upper1Cloned.setValue(p1.getUpper());
			
			p1Cloned.setLowerValue(lower1Cloned);			
			p1Cloned.setUpperValue(upper1Cloned);
			
			LiteralInteger lower2Cloned = factory.createLiteralInteger();
			lower2Cloned.setValue(p2.getLower());
			LiteralUnlimitedNatural upper2Cloned = factory.createLiteralUnlimitedNatural();
			upper2Cloned.setValue(p2.getUpper());
			
			p2Cloned.setLowerValue(lower2Cloned);			
			p2Cloned.setUpperValue(upper2Cloned);
			
			String node1Name  = new String();		
			if(p1.getType()!=null)
			{ 
				node1Name = p1.getType().getName();	    		
				if(node1Name==null || node1Name.trim().isEmpty()) node1Name = "source";
				else node1Name = node1Name.trim().toLowerCase();
			}
			String node2Name  = new String();
			if(p2.getType()!=null)
			{ 
				node2Name = p2.getType().getName();	    		
				if(node2Name==null || node2Name.trim().isEmpty()) node2Name = "target";
				else node2Name = node2Name.trim().toLowerCase();
			}
			
			p1Cloned.setName(node1Name);
			p2Cloned.setName(node2Name);	
						
			associationCloned.getMemberEnd().add(p1Cloned);
			associationCloned.getMemberEnd().add(p2Cloned);
			associationCloned.getOwnedEnd().add(p1Cloned);
			associationCloned.getOwnedEnd().add(p2Cloned);
			
			if(association instanceof DirectedBinaryAssociationImpl || association instanceof FormalAssociationImpl || association instanceof MaterialAssociationImpl)
			{
				associationCloned.getNavigableOwnedEnd().add(p1Cloned);
				associationCloned.getNavigableOwnedEnd().add(p2Cloned);	    			
				//If the association is Mediation or Characterization, set target readonly to help in validation
				if(association instanceof MediationImpl || association instanceof CharacterizationImpl || association instanceof DerivationImpl) p2Cloned.setIsReadOnly(true);
			}
			else
			{
				if(p1Cloned.getType() instanceof DataTypeImpl) associationCloned.getNavigableOwnedEnd().add(p1Cloned);	    		
				if(p2Cloned.getType() instanceof DataTypeImpl) associationCloned.getNavigableOwnedEnd().add(p2Cloned);
			}	
		}
						
		return cloned;
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
		
	public static String getStereotype(RefOntoUML.Element element){
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);	    
	    type = type.replace("Association","");	    
	    return type;
	}
    
	/**
	 * Helper method which creates a property with default multiplicity 1..1 as a owned end to associations
	 * of a given classifier
	 */
	public static Property createDefaultOwnedEnd(Classifier classifier, int lower, int upper) {
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
