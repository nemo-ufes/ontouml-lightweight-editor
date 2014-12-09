package RefOntoUML.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.AggregationKind;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Comment;
import RefOntoUML.Constraintx;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Element;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.Model;
import RefOntoUML.MomentClass;
import RefOntoUML.NamedElement;
import RefOntoUML.NominalQuality;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relationship;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.Structuration;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.util.RefOntoUMLResourceUtil;

/** 
 * This class represents a parser for analyzing and keeping useful informations about the ontoUML model. 
 * It associates an alias for every Element, as well as if this Element is selected or not (useful for transformation purposes). 
 * It can also perform the syntactical verification
 * 
 * @author John Guerson
 * @author Tiago Sales
 * @author Vinicius Sobral
 *
 */
public class OntoUMLParser {

	/** Root package of OntoUML model. */
	private Package model;		
	/** Unique name of root package. */
	private String refmodelname;	
	/** Syntactic verificator */
	private SyntacticVerificator verificator;
	
	/** 
	 * HashMap containing every element of the model associating with the custom parsing element. 
	 * The parsing Element contains all the useful information about the OntoUML element. 
	 */
	private HashMap<EObject,ParsingElement> elementsHash;

	/** HashMap containing every direct child of a particular OntoUML element.*/
	public HashMap<Classifier, HashSet<Classifier>> childHash;
	/** HashMap containing all children of a particular OntoUML element.*/
	public HashMap<Classifier, HashSet<Classifier>> allChildrenHash;
	
	/** Responsible for treating the name of each OntoUML element. */
	private OntoUMLNameHandler nameHandler = new OntoUMLNameHandler();
	
	/**
	 * Options for complete the selection of the elements in the model.
	 * Very useful for transformation purposes. 
	 */
	public static int NO_HIERARCHY = 0, SORTAL_ANCESTORS = 1, 
	ALL_ANCESTORS = 2, ALL_DESCENDANTS = 3, COMPLETE_HIERARCHY = 4;		
	
	/** Constructor */
	public OntoUMLParser(RefOntoUML.Package refmodel)
	{
		this.model = refmodel;	
		this.verificator = new SyntacticVerificator();		
		elementsHash = new HashMap<EObject,ParsingElement>();		
		OntoUMLNameHandler h1 = new OntoUMLNameHandler();
		this.refmodelname = h1.treatName(model);				
		initMap(model, nameHandler);
	}
		
	/** Constructor */
	public OntoUMLParser(String refontoumlPath) throws IOException
	{		
		Resource resource = RefOntoUMLResourceUtil.loadModel(refontoumlPath);
		Package refmodel = (Package)resource.getContents().get(0);		
		this.model = refmodel;		
		this.verificator = new SyntacticVerificator();		
		elementsHash = new HashMap<EObject,ParsingElement>();		
		OntoUMLNameHandler h1 = new OntoUMLNameHandler();
		this.refmodelname = h1.treatName(model);				
		initMap(model, nameHandler);
	}	

	/** This private method initializes the Map */
	private void initMap (PackageableElement rootpack, OntoUMLNameHandler h2) 
	{
		ParsingElement e = new ParsingElement(rootpack, true, h2.treatName(rootpack));
		this.elementsHash.put(rootpack,e);		
		for(PackageableElement p : ((Package) rootpack).getPackagedElement())
		{
			addToMap(p, h2);			
			if(p instanceof Package) initMap(p, h2);
		}
	}
	
	/**
	 * This private method add an element to the Map. It associates an ontoUML element
	 * with an unique alias and, by default, with a boolean value selected=true.
	 */
	private void addToMap(PackageableElement pe, OntoUMLNameHandler h2)
	{
		ParsingElement e;		
		//Comments
		for (Comment c: pe.getOwnedComment()) 
		{
			e = new ParsingElement(c, true, "");
			this.elementsHash.put(c,e);
		}		
		//Constraintx
		if(pe instanceof Constraintx)
		{
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementsHash.put(pe,e);
		}		
		//Class and DataType
		if(pe instanceof Class || ((pe instanceof DataType)&&!(pe instanceof PrimitiveType)&&!(pe instanceof Enumeration)) )
		{
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementsHash.put(pe,e);			
			//Generalization
			for (Generalization g : ((Classifier)pe).getGeneralization()) 
			{
				e = new ParsingElement(g, true, "");
				this.elementsHash.put(g,e);
			}			
			if (pe instanceof Class){			
				//Attributes
				for(Property p: ((Class)pe).getOwnedAttribute())
				{				
					e = new ParsingElement(p, true, h2.treatName(p));
					this.elementsHash.put(p,e);				
				}
			}
			if (pe instanceof DataType){			
				//Attributes
				for(Property p: ((DataType)pe).getOwnedAttribute())
				{				
					e = new ParsingElement(p, true, h2.treatName(p));
					this.elementsHash.put(p,e);				
				}
			}
		}		
		//Association
		else if(pe instanceof Association)
		{
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementsHash.put(pe,e);			
			//Properties			
			for(RefOntoUML.Property property: ((Association)pe).getMemberEnd())
			{											
				e = new ParsingElement(property, true, h2.treatName(property));
				this.elementsHash.put(property,e);
			}									
			//Generalization
			for (Generalization g : ((Classifier)pe).getGeneralization()) 
			{
				e = new ParsingElement(g, true, "");
				this.elementsHash.put(g,e);
			}							
		}
		
		//Enumeration
		else if (pe instanceof Enumeration)
		{
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementsHash.put(pe,e);			
			//Enumeration Literals
			for(EnumerationLiteral p: ((Enumeration)pe).getOwnedLiteral())
			{			   	
				e = new ParsingElement(p, true, h2.treatName(p));
				this.elementsHash.put(p,e);
			}		
			//Enumeration can also have attributes
			for(Property p: ((Enumeration)pe).getOwnedAttribute())
			{
				e = new ParsingElement(p, true, h2.treatName(p));
				this.elementsHash.put(p,e);
			}			
		}else {
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementsHash.put(pe,e);			
		}		
	}
	
	/**
	 * Add a new Element to this parser. 
	 * But note that this new element must already have been added in the Model (i.e., in the root Package or file in which this parser was created). 
	 * Also, this method do not verify dependences in the addition. If the argument is a class, than it will add
	 * all its attributes, generalization and comments, and so on for associations and other elements.
	 */
	public void addElement(EObject obj)
	{
		// only add if it is not there already
		if (this.elementsHash.get(obj)==null)
		{			
			if (obj instanceof RefOntoUML.Comment){
				ParsingElement e = new ParsingElement(obj,true,"");
				this.elementsHash.put(obj,e);			
			}else if (obj instanceof RefOntoUML.Generalization)
			{				
				ParsingElement e = new ParsingElement(obj,true,"");
				this.elementsHash.put(obj,e);				
			}else if (obj instanceof RefOntoUML.Property)
			{
				ParsingElement e = new ParsingElement(obj,true,nameHandler.treatName((NamedElement)obj));
				this.elementsHash.put(obj,e);				
			}else if (obj instanceof PackageableElement)
			{
				addToMap((PackageableElement)obj, nameHandler);
			}
		}else{
			updateElement(obj);
		}
	}

	
	/**
	 * Remove an Element from this parser. 
	 * But note that this element must already have been removed from the Model (i.e., in the root Package or file in which this parser was created).
	 * Also, this method do not verify dependences in deletion. If the argument is a class, than it will delete
	 * all its attributes, generalization and comments, and so on for associations,  etc.
	 */
	public void removeElement(EObject obj)
	{	
		// Comments
		if (obj instanceof PackageableElement) 
		{ 
			for (Comment c: ((PackageableElement)obj).getOwnedComment()) removeFromMap(c); 
		}			
		// Constraintx
		if (obj instanceof Constraintx) 
		{ 
			removeFromMap((Constraintx)obj); 
		}		
		// Class and DataType
		if (obj instanceof RefOntoUML.Class || ((obj instanceof DataType)&&!(obj instanceof PrimitiveType)&&!(obj instanceof Enumeration)))
		{			
			//Generalization
			for (Generalization g : ((Classifier)obj).getGeneralization()) removeFromMap(g);
			//Attributes
			if (obj instanceof Class){ for(Property p: ((Class)obj).getOwnedAttribute()) removeFromMap(p); }
			if (obj instanceof DataType){ for(Property p: ((DataType)obj).getOwnedAttribute()) removeFromMap(p); }
			removeFromMap(obj);
		}		
		//Association
		else if(obj instanceof Association)
		{
			removeFromMap(obj);					
			//Properties			
			for(Property p: ((Association)obj).getMemberEnd()) { removeFromMap(p); }
			//Generalization
			for (Generalization g : ((Classifier)obj).getGeneralization()) removeFromMap(g);
		}		
		//Enumeration
		else if (obj instanceof Enumeration)
		{						
			//Enumeration Literals
			for(EnumerationLiteral p: ((Enumeration)obj).getOwnedLiteral()) removeFromMap(p);			
			//Enumeration can also have attributes
			for(Property p: ((Enumeration)obj).getOwnedAttribute()) removeFromMap(p);
			removeFromMap(obj);
		}
		//Generalization
		else if (obj instanceof Generalization)
		{	
			//Generalization Set
			for(GeneralizationSet genSet: ((Generalization)obj).getGeneralizationSet()){				
				if(genSet.getGeneralization().size()==1 && genSet.getGeneralization().get(0).equals(obj)) removeFromMap(genSet);
				if(genSet.getGeneralization().size()==0) removeFromMap(genSet);
			}			
			
			removeFromMap(obj);
		}
		//Generalization Set
		else if(obj instanceof GeneralizationSet)
		{
			removeFromMap(obj);
		}
		else{
			removeFromMap(obj);
		}
	}
	
	/** Private method that performs the deletion of an element from the Map */
	private void removeFromMap(EObject obj)
	{
		ParsingElement e = elementsHash.get(obj);
		if (e!=null) 
		{
			nameHandler.remove(e.getAlias());
			this.elementsHash.remove(obj);			
		}	
	}
	
	/** Private method that performs the update of an element in the Map */
	public void updateElement(EObject obj)
	{
		ParsingElement e = elementsHash.get(obj);
		if (e!=null && (e.getElement() instanceof NamedElement)) 
		{
			nameHandler.remove(e.getAlias());		
			String alias = nameHandler.treatName((NamedElement)obj);
			e.setAlias(alias);
		}
	}
	
	/** Get root package */
	public RefOntoUML.Package getModel()
	{
		return model;
	}
	
	/** Get the name of the root package. */
	public String getModelName()
	{
		return refmodelname;
	}
	
	/** Run syntactical verification in the entire model */
	public void parse()
	{
		verificator.run(this.model);
	}
			
	/** Get message with the time spent in the syntactical verification */
	public String getTimingMessage()
	{
		return verificator.getTimingMessage();
	}
	
	/** Get result of the syntactical verification in the form of a string */
	public String getResult()
	{
		return verificator.getResult();
	}
		
	/** Return the result of the verification in the form of a Map */
	public Map<Element, ArrayList<String>> getMap()
	{
		return verificator.getMap();
	}
	
	/** Get element from its alias name. */
	public EObject getElement(String alias)
	{	 
		for (Entry<EObject,ParsingElement> entry : elementsHash.entrySet()) 
        {
            String name = ((ParsingElement)entry.getValue()).getAlias(); 
            if (alias.trim().toLowerCase().equals(name.trim().toLowerCase())) return entry.getKey();            
        }
        return null;	    
	}
	
	/** Get the alias of a given element. */
	public String getAlias(EObject elem) 
	{
		ParsingElement pe = elementsHash.get(elem);
		if(pe!=null) return pe.getAlias();		
		else{ 
			try{
				throw new Exception("Element not found in the OntoUML parser: "+elem);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return "";
	}

	/** Get respective aliases from a list of elements. */
	public ArrayList<String> getAlias (ArrayList<EObject> list)
	{
		ArrayList<String> result = new ArrayList<String>();
		for(EObject obj: getElements())
		{
			if (list.contains(obj)) result.add(getAlias(obj));
		}
		return result;
	}
	
	/** Get a string representation of a given element. */
	public String getStringRepresentation(EObject elem)
	{
		ParsingElement parsingElem = elementsHash.get(elem);		
		if (parsingElem == null) return "Unknown Element";
		else return parsingElem.toString();
	}
	
	/** Get a string representation of all elements in the model. */
	public String getStringRepresentations()
	{
		String result = new String();	
		for (EObject obj: getElements()) { result+= getStringRepresentation(obj)+"\n"; }		
		return result;		
	}
	
	/** Get the respective string representations from a List of elements. */
	public String getStringRepresentations(ArrayList<EObject> list)
	{
		String result = new String();	
		for (EObject obj: getElements()) { if (list.contains(obj)) result+= getStringRepresentation(obj)+"\n"; }		
		return result;		
	}
	
	/** Get the stereotype of a given element. */
	public String getStereotype(EObject elem)
	{
		ParsingElement parsingElem = elementsHash.get(elem);		
		if (parsingElem == null) return "Unknown Element";
		else return parsingElem.getType();
	}
	
	/** String representation of the entire parser. */
	@Override
	public String toString()
	{
		String result = new String();		
		for(EObject obj: getElements())
		{
			result += elementsHash.get(obj)+"\n";
		}
		return result;
	}
	
	/** Get respective parsing elements of a list of elements */
	public ArrayList<ParsingElement> getParsingElements(ArrayList<Element> list)
	{
		ArrayList<ParsingElement> result = new ArrayList<ParsingElement>();
		for(EObject eobj: list)
		{
			if (elementsHash.get(eobj)!=null) result.add(elementsHash.get(eobj));			
		}
		return result;
	}
	
	/** Verifies if a given element is selected. */
	public Boolean isSelected (EObject elem) 
	{		
		if (elem!=null)
		{
			if (elementsHash.get(elem)!=null) return elementsHash.get(elem).getSelected();
			else{
				try {
					throw new Exception("Element not contained in OntoUML parser.");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		}else{
			return false;
		}
	}		
		
	/** Get all selected elements of the model. */
	public Set<EObject> getElements()
	{
		Set<EObject> list = new HashSet<EObject>();		
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(pe.getSelected()) { list.add(pe.getElement()); }
		}		
		return list;
	}
	
	/**
	 * Select this elements in the model. If 'unselectOthers' is true, the other elements will be unselected (i.e. selected=false). 
	 * Otherwise, if 'unselectOther' is false, nothing is made with the others elements, they maybe selected or not. i.e. selected = true or false.
	 */
	public void select(ArrayList<EObject> elements, boolean unselectOthers)
	{
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(elements.contains(pe.getElement())) pe.setSelected(true);
			else if (unselectOthers) pe.setSelected(false);
		}
	}
	
	/**
	 * Unselect this elements in the model. Nothing is made with the others elements, 
	 * They maybe selected or not. i.e. selected = true or false.
	 */
	public void unselect(ArrayList<EObject> elements)
	{
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(elements.contains(pe.getElement())) pe.setSelected(false);
		}		
	}
	
	/** Select all elements of the model. */
	public void selectAll()
	{
		for (ParsingElement pe : elementsHash.values()) 
		{
			pe.setSelected(true);
		}
	}
	
	/** Return all elements from the input list that are selected in the parser. */
	public <T> Set<T> retainSelected(List<T> elements)
	{
		Set<T> result = new HashSet<T>();		
		for (T elem : elements) 
		{
			if(elem instanceof EObject && isSelected((EObject) elem)) result.add(elem);
		}		
		return result;
	}
	
	/** Return all relationship classes. */
	public Set<Classifier> getAssociations()
	{
		Set<Classifier> list = new HashSet<Classifier>();		
		for (EObject obj : getElements())
		{
			if((obj instanceof Association))				
			list.add((Classifier)obj);			
		}		
		return list;
	}
	
	
			
	/**Return all rigid, selected classes of the model. */
	public Set<Classifier> getRigidClasses()
	{
		Set<Classifier> list = new HashSet<Classifier>();		
		for (EObject obj : getElements()){			
			if ((obj instanceof RigidSortalClass) || (obj instanceof Category) || 
			(obj instanceof MomentClass) || ((obj instanceof DataType)&&!(obj instanceof PrimitiveType)))				
			list.add((Classifier) obj);			
		}
		return list;
	}
			
	/** Return all anti-rigid, selected classes of the model. */
	public Set<Classifier> getAntiRigidClasses()
	{
		Set<Classifier> list = new HashSet<Classifier>();		
		for (EObject obj : getElements())
		{
			if((obj instanceof Role) || (obj instanceof RoleMixin) || (obj instanceof Phase))				
			list.add((Class)obj);			
		}		
		return list;
	}
	
	/** Return all selected attributes of the model. */
	public Set<Property> getAttributes()
	{
		Set<Property> list = new HashSet<Property>();		
		for (EObject obj : getElements())
		{			
			if( (obj instanceof Property) && ((Property)obj).getAssociation()==null )				
			list.add((Property)obj);			
		}		
		return list;
	}
		
	/** Return all selected descendants (direct or indirect) of an element. */
	public Set<Classifier> getAllChildren(Classifier c)
	{
		Set<Classifier> result = new HashSet<Classifier>();		
		for (Classifier classifier : c.allChildren()) 
		{
			if(isSelected(classifier)) result.add(classifier);
		}		
		return result;
	}
	
	/** Return all selected direct descendants of an element. */
	public Set<Classifier> getChildren(Classifier c)
	{
		Set<Classifier> result = new HashSet<Classifier>();		
		for (Classifier classifier : c.children()) 
		{
			if(isSelected(classifier)) result.add(classifier);
		}		
		return result;
	} 
	
	/** 
	 * Return all selected ancestors (direct or indirect) of an element. 
	 * This method might return null when it falls into a loop
	 */
	public Set<Classifier> getAllParents(Classifier c)
	{
		Set<Classifier> result = new HashSet<Classifier>();		
		for (Object classifier : c.allParents()) 
		{
			if(classifier instanceof Classifier && isSelected((EObject) classifier)) 
			result.add((Classifier) classifier);
		}		
		return result;
	}
	
	/** Return all direct, selected ancestors of an element. */
	public Set<Classifier> getParents(Classifier c)
	{
		Set<Classifier> result = new HashSet<Classifier>();		
		for (Classifier classifier : c.parents()) 
		{
			if(isSelected(classifier)) result.add(classifier);
		}		
		return result;
	} 
	
	/** Return all selected, non-abstract (i.e. concrete) descendants of an element. */		
	public Set<Classifier> getAllConcreteChildren(Classifier c)
	{
		Set<Classifier> result = new HashSet<Classifier>();		
		for (Classifier classifier : getAllChildren(c)) 
		{
			if(isSelected(classifier)) result.add(classifier);
		}
		return result;
	}
	
	/** Check if this element is valid according to the set of stereotypes allowed in this version of OntoUML. */
	public boolean isValidStereotype(EObject e)
	{
		if (e instanceof Kind || e instanceof Collective || e instanceof Quantity || e instanceof Category || e instanceof SubKind ||
			e instanceof Mixin || e instanceof RoleMixin || e instanceof Role || e instanceof Phase || e instanceof Relator ||
			e instanceof Mode || e instanceof DataType || e instanceof MaterialAssociation || e instanceof FormalAssociation || 
			e instanceof Mediation || e instanceof Characterization || e instanceof Derivation || e instanceof RefOntoUML.Package || e instanceof Model ||
			e instanceof componentOf || e instanceof memberOf || e instanceof subCollectionOf || e instanceof subQuantityOf || e instanceof Association ||
			e instanceof Enumeration || e instanceof EnumerationLiteral || e instanceof PrimitiveType || e instanceof NominalQuality || 
			e instanceof PerceivableQuality || e instanceof NonPerceivableQuality || e instanceof Structuration || e instanceof Generalization || e instanceof GeneralizationSet
		) return true;			
		return false;
	}
	
	/** Check if this name contains a OCL keyword. */
	public boolean isOCLkeyword (String name)
	{
		if (name == null ) return false;		
		if ( name =="and" || name =="body" ||name =="context" ||name =="def" ||name =="derive" ||name =="else" ||
		     name =="init" ||name.equals("inv") ||name =="invalid" ||name =="let" ||name =="not" ||name =="null" ||
		     name =="endif" ||name =="endpackage" ||name =="false" ||name =="if" ||name =="implies" ||name =="in" ||
		     name =="or" ||name =="package" ||name =="post" ||name =="static" ||name =="true" ||name =="then" ||		 
		     name =="xor" ||name =="Bag" ||name =="Boolean" ||name =="Collection" ||name =="Integer" ||name =="OclAny" ||
		     name =="OclInvalid" ||name =="OclMessage" ||name =="OclVoid" ||name =="OrderedSet" ||name =="Real" ||name =="Sequence" ||
		     name =="Set" ||name =="String" ||name =="Tuple" ||name =="UnlimitedNatural" 
		) return true;		
		return false;
	}		
	
	/** 
	 * Return all selected instances of a given meta-class contained in the parser. 
	 * For instance, to get all the elements of the type Kind universal of the parser, write: getAllInstances(Kind.class).
	 * And so on and so forth. 
	 */	
	@SuppressWarnings("unchecked")
	public <T> Set<T> getAllInstances(java.lang.Class<T> type)
	{
		Set<T> result = new HashSet<T>();		
		for (EObject o : getElements()) 
		{
			if(isSelected(o) && type.isInstance(o)) result.add((T) o);
		}
		return result;
	}
	
	/**
	 * Return all selected, top level instances of a given meta-class contained in the parser.
	 * For instance, to get all the top levels classes of the type Kind in the parser, write: getTopLevelInstances(Kind.class)
	 */
	@SuppressWarnings("unchecked")
	public <T> Set<T> getTopLevelInstances(java.lang.Class<T> type)
	{
		Set<T> result = new HashSet<T>();		
		for (EObject o : getElements()) 
		{
			if(isSelected(o) && type.isInstance(o))
			{
				if(o instanceof Classifier && ((Classifier)o).getGeneralization().size()==0)
				{
					if(o instanceof DataType && ! (o instanceof PrimitiveType)) result.add((T) o);
					else if (!(o instanceof DataType)) result.add((T) o);					
				}					
			}
		}
		return result;
	}	
	
	/** Return all selected parthood relations that have as a whole the specified element or one of its super types. */
	public void getAllMeronymics(Classifier c, ArrayList<Meronymic> result)
	{
		for(EObject obj : getElements())
		{
			if(obj instanceof Meronymic) {
				for( Property p : ((Meronymic)obj).getMemberEnd())
				{
					if (!p.getAggregation().equals(AggregationKind.NONE)) {					
						if (isSelected(p.getType()) && p.getType().equals(c)) result.add((Meronymic)obj);
					}
				}
			}
		}
		for(Generalization gen : c.getGeneralization())
		{
			if (isSelected(gen.getGeneral()) && !gen.getGeneral().equals(c)) getAllMeronymics(gen.getGeneral(),result);			
		}
	}
			
	/** Return all selected mediations that have as a source the specified relator or one of its super types. */
	public void getAllMediations(Classifier relator, ArrayList<Mediation> result)
	{
		result.addAll(getMediations(relator));		
		for(Generalization gen : relator.getGeneralization())
		{						
			if(isSelected(gen))	
			{
				if (gen.getGeneral() instanceof Relator) getAllMediations((Relator)gen.getGeneral(),result);
			}
		}
	}
	
	/** Return all selected, direct mediations that have as a source the specified relator. */
	public ArrayList<Mediation> getMediations(Classifier relator) 
	{
		ArrayList<Mediation> result = new ArrayList<Mediation>();		
		for (Mediation m : getAllInstances(Mediation.class)) 
		{
			if(getRelator(m)!=null && getRelator(m).equals(relator)) result.add(m);
		}		
		return result;
	}
	
	/** Return the relator of a mediation */
	public static Classifier getRelator(Mediation m) 
	{
		return (Classifier) getRelatorEnd(m).getType();
	}
	
	/** Return the mediated end i.e. property or association end-point in the role's side, of a mediation */
	public static Property getMediatedEnd (Mediation m)
	{		
		return getRelatorEnd(m).getOpposite();
	}
	
	/** Return the relator end i.e. property or association end-point in the relator side, connected to this mediation */
	public static Property getRelatorEnd (Mediation m)
	{		
		if (m.sourceEnd().getType() instanceof Relator) return m.sourceEnd();
		else if (m.targetEnd().getType() instanceof Relator) return m.targetEnd();
		else {
			if(m.sourceEnd().getType()!=null)
			{
				for (Classifier c : ((Classifier)m.sourceEnd().getType()).allParents()) 
				{
					if (c instanceof Relator) return m.sourceEnd();
				}
				for (Classifier c : ((Classifier)m.targetEnd().getType()).allParents()) 
				{
					if (c instanceof Relator) return m.targetEnd();
				}
			}
		}		
		return m.sourceEnd();		
	}
	
	/** Return the mediated type i.e. usually roles, of a mediation */
	public static Classifier getMediatedType(Mediation m) throws Exception
	{		
		return (Classifier) getMediatedEnd(m).getType();
	}
	
	/** Return the whole end i.e. property or association end-point in the whole side, of a meronymic */
	public static Property getWholeEnd(Meronymic m)
	{
		Property sourceEnd = m.getMemberEnd().get(0);
		Property targetEnd = m.getMemberEnd().get(1);		
		if (targetEnd.getAggregation()!=AggregationKind.NONE && sourceEnd.getAggregation()==AggregationKind.NONE) return targetEnd;
		else return sourceEnd;
	}
	
	/** Return the part end i.e. property or association end-point in the part side, of a meronymic */
	public static Property getPartEnd(Meronymic m)
	{
		Property sourceEnd = m.getMemberEnd().get(0);
		Property targetEnd = m.getMemberEnd().get(1);		
		if(getWholeEnd(m).equals(sourceEnd)) return targetEnd;
		else return sourceEnd;
	}
	
	/** Return the characterizing end i.e. property or association end-point in the mode's side */
	public static Property getCharacterizingEnd(Characterization c)
	{
		if (c.getMemberEnd().get(1).getType() instanceof Mode && !(c.getMemberEnd().get(0).getType() instanceof Mode)) return c.getMemberEnd().get(1);
		return c.getMemberEnd().get(0);
	}
	
	/** Return the characterized end i.e. property or association end-point in the mode's opposite direction */
	public static Property getCharacterizedEnd(Characterization c)
	{
		return getCharacterizingEnd(c).getOpposite();
	}
	
	/** Return the material association related to this derivation */
	public static MaterialAssociation getMaterial(Derivation d)
	{
		Type source = d.getMemberEnd().get(0).getType();
		Type target = d.getMemberEnd().get(1).getType();		
		if(source instanceof MaterialAssociation) return (MaterialAssociation) source;
		if(target instanceof MaterialAssociation) return (MaterialAssociation) target;		
		return null;
	}
	
	/** Return the relator related to this derivation */
	public static Relator getRelator(Derivation d)
	{
		Type source = d.getMemberEnd().get(0).getType();
		Type target = d.getMemberEnd().get(1).getType();		
		if(source instanceof Relator) return (Relator) source;
		if(target instanceof Relator) return (Relator) target;		
		return null;
	}
	
	/** Return the derivation relationship attached to this material association */
	public Derivation getDerivation (MaterialAssociation material)
	{
		for (Derivation d: getAllInstances(Derivation.class))
		{
			for(Property prop : d.getMemberEnd())
			{
				if(prop.getType().equals(material)) return d;
			}
		}
		return null;
	}

	/**
	 * Verify if the given element is a general classifier in a partition, which means that this 
	 * element is an abstract classifier.
	 */
	public boolean isAbstractFromGSet(Classifier c) 
	{
		for(GeneralizationSet gs : getAllInstances(GeneralizationSet.class)) {			
			if(gs.isIsCovering()) {
				for(Generalization gen : gs.getGeneralization()) {
					if (isSelected(gen)) {
						if (isSelected(gen.getGeneral()) && gen.getGeneral().equals(c)) return true;
					}
				}
			}
		}		
		return false;
	}
		 
	/**
	 * Auto select elements according to pre-defined mandatory dependencies. 
	 * In Generalizations, the general and specific classifiers must be selected. 
	 * In Associations, the source and target types must be selected.
	 */
	private ArrayList<EObject> autoSelectMandatoryDependencies()
	{
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();		
		for (EObject obj : getElements()) 
		{
			if(obj instanceof Generalization)
			{
				Generalization g = (Generalization) obj;
				if (!isSelected(g.getGeneral()) && !objectsToAdd.contains(g.getGeneral())) objectsToAdd.add(g.getGeneral());										
				if (!isSelected(g.getSpecific()) && !objectsToAdd.contains(g.getSpecific())) objectsToAdd.add(g.getSpecific());					
			}
			if(obj instanceof Association) 
			{
				Association a = (Association)obj;	
				if (a.getMemberEnd().size()>=1)
				{
					Type source = a.getMemberEnd().get(0).getType();
					if(!isSelected(source) && !objectsToAdd.contains(source)) objectsToAdd.add(source);					
				}				
				if (a.getMemberEnd().size()>=2)
				{				
					Type target = a.getMemberEnd().get(1).getType();
					if(!isSelected(target) && !objectsToAdd.contains(target)) objectsToAdd.add(target);					
				}
			}			
		}		
		select(objectsToAdd,false);		
		return objectsToAdd;
	}
	
	/**
	 * Auto select elements according to pre-defined package dependencies.	 
	 * In PackageableElements or Packages their container must be selected. 
	 */
	private ArrayList<EObject> autoSelectPackagesDependencies()
	{
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();		
		for (ParsingElement obj : elementsHash.values()) 
		{	
			if(obj.getElement() instanceof PackageableElement || obj.getElement() instanceof Package) 
			{
				if((obj.getElement().eContainer()!=null) && !isSelected(obj.getElement().eContainer()) && !objectsToAdd.contains(obj.getElement().eContainer())) 
				{
					objectsToAdd.add(obj.getElement().eContainer());
				}
			}
		}		
		select(objectsToAdd,false);		
		return objectsToAdd;
	}
	
	/**
	 * Auto select elements according to pre-defined generalization and generalization sets dependencies.
	 * In Generalizations, their Generalizations Set must be selected.
	 */
	private ArrayList<EObject> autoSelectGenDependenties()
	{
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();		
		for (Generalization g : getAllInstances(Generalization.class))
		{
			for (GeneralizationSet gs : g.getGeneralizationSet()) 
	        {
	            if(!isSelected(gs) && !objectsToAdd.contains(gs)) objectsToAdd.add(gs);
	        }
		}		
		select(objectsToAdd,false);		
		return objectsToAdd;
	}

	/**
	 * Auto select elements according to pre-defined generalization sets and generalizations dependencies.
	 * In Generalization Set, the Generalizations and the general and specific classifiers, must be selected.
	 */
	private ArrayList<EObject> autoSelectGenSetDependencies() 
	{		
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();		
		for(EObject obj : getElements()){
			if(obj instanceof GeneralizationSet) 
			{
				GeneralizationSet gs = (GeneralizationSet)obj;				
				//generalizations
				for(Generalization g: gs.getGeneralization())
				{
					if(!isSelected(g)) objectsToAdd.add(g);						
					if (!isSelected(g.getSpecific()) && !objectsToAdd.contains(g.getSpecific())) objectsToAdd.add(g.getSpecific());						
					if (!isSelected(g.getGeneral()) && !objectsToAdd.contains(g.getGeneral())) objectsToAdd.add(g.getGeneral());						
				}								
			}
		}		
		select(objectsToAdd,false);		
		return objectsToAdd;
	}
	
	/** Auto select associations connected to the given elements. */
	public ArrayList<EObject> autoSelectRelatedElements(ArrayList<Classifier> classifiers)
	{
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();		
		for (Association a : getAllInstances(Association.class))
		{
			if (!isSelected(a))
			{
				//try clause added because the model may be inconsistent and generate exceptions.
				try {
					Type source = a.getMemberEnd().get(0).getType();
					Type target = a.getMemberEnd().get(1).getType();					
					if (!isSelected(source) && !objectsToAdd.contains(source) && !classifiers.contains(source)) objectsToAdd.add(source);
					if (!isSelected(target) && !objectsToAdd.contains(target) && !classifiers.contains(target))	objectsToAdd.add(target);
				}
				//ignores association and unselect it.
				catch (Exception e) {				
					this.elementsHash.get(a).setSelected(false);
				}
			}
		}		
		select(objectsToAdd,false);		
		return objectsToAdd;
	}	
	
	/** Auto select an entire hierarchy according to a option of selection. */
	private ArrayList<EObject> autoSelectHierarchies(int hierarchySelectionOption) 
	{
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();		
		if (hierarchySelectionOption==NO_HIERARCHY) return objectsToAdd;
		for (EObject o : getElements()) 
		{
			if (o instanceof Class) 
			{
				if (hierarchySelectionOption==SORTAL_ANCESTORS || hierarchySelectionOption==ALL_ANCESTORS || hierarchySelectionOption==COMPLETE_HIERARCHY)					
					getUnselectedAncestorsHierarchy((Classifier) o, objectsToAdd, hierarchySelectionOption);				
				if (hierarchySelectionOption==ALL_DESCENDANTS || hierarchySelectionOption==COMPLETE_HIERARCHY)					
					getUnselectedDescendantsHierarchy((Classifier) o, objectsToAdd);
			}
		}		
		select(objectsToAdd,false);	
		return objectsToAdd;
	}

	/** Return all the unselected descendants hierarchy of a classifier i.e. its children and specializations which are not selected. */
	private void getUnselectedDescendantsHierarchy (Classifier o, ArrayList<EObject> hierarchy)
	{		
		for (Classifier c : o.children()) 
		{
			if(!isSelected(c) && !hierarchy.contains(c)) hierarchy.add(c);			
			for (Generalization g : c.getGeneralization()) 
			{
				if(g.getGeneral().equals(o) && !isSelected(g) && !hierarchy.contains(g)) hierarchy.add(g);
			}
			getUnselectedDescendantsHierarchy(c, hierarchy);			
		}
	}
	
	/** Return all the unselected ancestors hierarchy of a Classifier i.e. its parents and generalizations which are not selected. */
	private void getUnselectedAncestorsHierarchy (Classifier o, ArrayList<EObject> hierarchy, int option)
	{		
		if(o instanceof SubstanceSortal && option==SORTAL_ANCESTORS) return;		
		for (Generalization g : ((Class) o).getGeneralization()) 
		{
			if(!isSelected(g) && !hierarchy.contains(g)) hierarchy.add(g);
			if(!isSelected(g.getGeneral()) && !hierarchy.contains(g.getGeneral())) hierarchy.add(g.getGeneral());			
			getUnselectedAncestorsHierarchy(g.getGeneral(), hierarchy,option);
		}
	}	
	
	/** Auto select elements from explicit selection options */
	public ArrayList<EObject> autoSelectDependencies(int hierarchySelectionOption, boolean includeGenSet)
	{		
		ArrayList<EObject> objectsAdded = new ArrayList<EObject>();		
		objectsAdded.addAll(autoSelectMandatoryDependencies());		
		objectsAdded.addAll(autoSelectPackagesDependencies());						
		objectsAdded.addAll(autoSelectHierarchies(hierarchySelectionOption));		
		objectsAdded.addAll(autoSelectGenDependenties());		
		if (includeGenSet) objectsAdded.addAll(autoSelectGenSetDependencies());		
		return objectsAdded;
	}

	/** Create a new model from the selections. */
	public Package createModelFromSelections (Copier copier)
	{		
		Package pack_copy;
		ArrayList<EObject> selected_copy = new ArrayList<EObject>();				
		pack_copy = (Package) copier.copy(model);
		copier.copyReferences();		
		for (EObject element : getElements()) 
		{
			selected_copy.add(copier.get(element));
		}		
		deleteUnselectedElements(pack_copy, selected_copy);		
		return pack_copy;
	}
	
	/** Delete elements of the package that aren't selected. */
	private void deleteUnselectedElements (Package pack, ArrayList<EObject> selected)
	{
		ArrayList<EObject> delete_list = new ArrayList<EObject>();		
		for (PackageableElement eo : pack.getPackagedElement()) 
		{
			if(!selected.contains(eo)) delete_list.add(eo);			
			else{				
				if (eo instanceof Package) deleteUnselectedElements((Package) eo, selected);				
				else {
					for (EObject c : eo.eContents()) 
					{
						if(!selected.contains(c)) delete_list.add(c);
					}
				}					
			}		
		}		
		for(int i = 0; i<delete_list.size(); i++) EcoreUtil.remove(delete_list.get(i));
	}
		
	/**
	 * Return all classes that do not have an identity. 
	 * i.e. all the sortals that do not have a substance sortal as its ancestor.
	 */
	public ArrayList<RefOntoUML.Classifier> getClassesWithIdentityMissing ()
	{
		ArrayList<RefOntoUML.Classifier> list = new ArrayList<RefOntoUML.Classifier>();
		boolean hasIdentity;
		for (RefOntoUML.Classifier c: getAllInstances(RefOntoUML.Class.class))
		{
			hasIdentity=false;
			if (c instanceof RefOntoUML.Role || c instanceof RefOntoUML.Phase || c instanceof RefOntoUML.SubKind)
			{
				for (Classifier s: getAllParents(c)) if (s instanceof RefOntoUML.SubstanceSortal) hasIdentity=true;
				if (!hasIdentity) { list.add(c); }
			}			
		}
		return list;
	}
		
	/** Return the relators that have the sum of all the lower cardinalities of each attached mediation less than 2. */
	public ArrayList<Relator> getRelatorsWithInvalidAxiom ()
	{
		ArrayList<Relator> relators = new ArrayList<Relator>();
		for (Relator r: getAllInstances(Relator.class))
		{
			ArrayList<Mediation> mediations = new ArrayList<Mediation>();
			try { getAllMediations(r, mediations); } catch (Exception e) {e.printStackTrace();}			
			int sum=0;
			for(Mediation m: mediations)
			{
				if (m.getMemberEnd().get(1).getUpper()==-1) sum += 2;
				else sum += m.getMemberEnd().get(1).getUpper();
			}			
			if (sum <2) relators.add(r);
		}
		return relators;
	}
	
	/**  Return the wholes that have the sum of all the lower cardinalities of each attached meronymic less than 2. */
	public ArrayList<RigidSortalClass> getWholesWithInvalidWeakSupplementation ()
	{
		ArrayList<RigidSortalClass> classifiers = new ArrayList<RigidSortalClass>();
		for (RigidSortalClass r: getAllInstances(RigidSortalClass.class))
		{
			ArrayList<Meronymic> meronymics = new ArrayList<Meronymic>();
			try { getAllMeronymics(r, meronymics); } catch (Exception e) {e.printStackTrace();}			
			int sum=0;
			for(Meronymic m: meronymics)
			{
				if (m.getMemberEnd().get(1).getUpper()==-1) sum += 2;
				else sum += m.getMemberEnd().get(1).getUpper();
			}			
			if (sum <2 && meronymics.size()>=1) classifiers.add(r);
		}
		return classifiers;
	}
	
	/** Return all the selected generalization sets that this generalization is attached */
	public ArrayList<GeneralizationSet> getGeneralizationSet(Generalization g)
	{
		ArrayList<GeneralizationSet> genSets = new ArrayList<GeneralizationSet>();		
		for (GeneralizationSet gs : getAllInstances(GeneralizationSet.class)) 
		{
			if(gs.getGeneralization().contains(g)) genSets.add(gs);
		}		
		return genSets;
	}
	
	/** Return all the selected generalizations that this type is attached */
	public ArrayList<Generalization> getGeneralizations(RefOntoUML.Classifier type)
	{
		ArrayList<RefOntoUML.Generalization> genList = new ArrayList<RefOntoUML.Generalization>();
		for(RefOntoUML.Generalization gen: getAllInstances(RefOntoUML.Generalization.class))
		{			
			if(type.equals(gen.getGeneral())) genList.add(gen); 
			if(type.equals(gen.getSpecific())) genList.add(gen);
		}
		return genList;
	}
	
	/** Return all the generalizations that this type is attached as the general type*/
	public ArrayList<Generalization> getSpecializations(RefOntoUML.Classifier type)
	{
		ArrayList<RefOntoUML.Generalization> genList = new ArrayList<RefOntoUML.Generalization>();
		for(RefOntoUML.Generalization gen: getAllInstances(RefOntoUML.Generalization.class))
		{			
			if(type.equals(gen.getGeneral())) genList.add(gen);	
		}
		return genList;
	}
	
	/** Return all selected, direct relationships (i.e. generalizations and associations) of a given element */
	public ArrayList<Relationship> getDirectRelationships(EObject eObject)
	{
		ArrayList<Relationship> relations = new ArrayList<Relationship>();		
		for (EObject a : getAllInstances(Relationship.class)) 
		{
			if (a instanceof Generalization)
			{
				Generalization g = (Generalization)a;
				if(g.getGeneral()!=null && g.getSpecific()!=null)
				{
					if(g.getGeneral().equals(eObject) || g.getSpecific().equals(eObject)) relations.add(g);
				}				
			}else if (a instanceof Association)
			{
				Association assoc = (Association)a;				
				RefOntoUML.Type Src = assoc.getMemberEnd().get(0).getType();
				RefOntoUML.Type Tgt = assoc.getMemberEnd().get(1).getType();
				if (Src !=null && Src.equals(eObject) || Tgt!=null && Tgt.equals(eObject)) relations.add(assoc);					
			}
		}
		return relations;
	}
		
	/** Return all selected, direct associations of a given element */
	public ArrayList<Association> getDirectAssociations(EObject eObject)
	{
		ArrayList<Association> relations = new ArrayList<Association>();		
		for (EObject a : getAllInstances(Association.class)) 
		{			
			Association assoc = (Association)a;
			RefOntoUML.Type Src = assoc.getMemberEnd().get(0).getType();
			RefOntoUML.Type Tgt = assoc.getMemberEnd().get(1).getType();
			if (Src !=null && Src.equals(eObject) || Tgt!=null && Tgt.equals(eObject)) relations.add(assoc);			
		}
		return relations;
	}	
	
	/** Return all selected, direct generalizations of a given element i.e. in which it participates */
	public ArrayList<Generalization> getDirectGeneralizations(EObject eObject)
	{
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();		
		for (EObject a : getAllInstances(Generalization.class)) 
		{			
			Generalization gen = (Generalization)a;
			RefOntoUML.Type general = gen.getGeneral();
			RefOntoUML.Type specific = gen.getSpecific();
			if (general !=null && general.equals(eObject) || specific!=null && specific.equals(eObject)) generalizations.add(gen);				
		}
		return generalizations;
	}
	
	/** Return all selected, indirect associations of a given element i.e. associations in which the parents of a given elements participate */
	public ArrayList<Association> getIndirectAssociations(EObject eObject)
	{
		ArrayList<Association> relations = new ArrayList<Association>();		
		for (EObject a : getAllInstances(Association.class)) 
		{			
			Association assoc = (Association)a;
			RefOntoUML.Type Src = assoc.getMemberEnd().get(0).getType();
			RefOntoUML.Type Tgt = assoc.getMemberEnd().get(1).getType();
			if (((RefOntoUML.Classifier)eObject).allParents().contains(Src)) relations.add(assoc);
			if(((RefOntoUML.Classifier)eObject).allParents().contains(Tgt)) relations.add(assoc);					
		}
		return relations;
	}
	
	/** Return all selected, indirect generalizations of a given element i.e. generalizations in which the parents of a given elements participate */
	public ArrayList<Generalization> getIndirectGeneralizations(EObject eObject)
	{
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();		
		for (EObject a : getAllInstances(Generalization.class)) 
		{			
			Generalization gen = (Generalization)a;			
			RefOntoUML.Type specific = gen.getSpecific();			
			if(((RefOntoUML.Classifier)eObject).allParents().contains(specific)) generalizations.add(gen);					
		}
		return generalizations;
	}
	
	/** 
	 * Check if this element is a functional complex i.e.
	 * i) If it is a kind, or ii) if it is a subkind or anti-rigid sortal with exactly one identity provider of the type kind, or,
	 * iii) if it is a mixin class in which all their children are functional complexes.  
	 */
	public boolean isFunctionalComplex(Classifier c)
	{		
		if(c instanceof Kind) return true;		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass)
		{
			HashSet<Classifier> identityProviders = getIdentityProvider(c);
			if(identityProviders.size()==1 && identityProviders.toArray()[0] instanceof Kind) return true;
		}		
		if(c instanceof MixinClass)
		{
			if(getChildren(c).size()==0) return false;
			for (Classifier child : getChildren(c)) 
			{
				if(!isFunctionalComplex(child)) return false;
			}
			return true;
		}		
		return false;
	}
	
	/** 
	 * Check if a particular element is a quantity i.e.
	 * i) if it is a quantity element, or, ii) if it is a subkind or anti-rigid sortal with exactly one identity provider of the type Quantity, or,
	 * iii) if it is a mixin class in which all their children are quantities.
	 */
	public boolean isQuantity(Classifier c)
	{
		if(c instanceof Quantity) return true;		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass)
		{
			HashSet<Classifier> identityProviders = getIdentityProvider(c);
			if(identityProviders.size()==1 && identityProviders.toArray()[0] instanceof Quantity) return true;
		}		
		if(c instanceof MixinClass)
		{
			if(getChildren(c).size()==0) return false;			
			for (Classifier child : getChildren(c)) 
			{
				if(!isQuantity(child)) return false;
			}
			return true;
		}		
		return false;
	}
	
	/** 
	 * Check if a particular element is a collective i.e.
	 * i) if it is a collective element, or, ii) if it is a subkind or anti-rigid sortal with exactly one identity provider of the type Collective, or,
	 * iii) if it is a mixin class in which all their children are collectives.
	 */
	public boolean isCollective(Classifier c)
	{
		if(c instanceof Collective) return true;		
		if(c instanceof SubKind || c instanceof AntiRigidSortalClass)
		{
			HashSet<Classifier> identityProviders = getIdentityProvider(c);
			if(identityProviders.size()==1 && identityProviders.toArray()[0] instanceof Collective) return true;
		}		
		if(c instanceof MixinClass)
		{
			if(getChildren(c).size()==0) return false;			
			for (Classifier child : getChildren(c)) 
			{
				if(!isCollective(child)) return false;
			}
			return true;
		}		
		return false;
	}
		
	/** Check if this mixin class as children at least one functional complex */
	public boolean hasFunctionalComplexChild(MixinClass mixin)
	{
		for (Classifier child : getAllChildren(mixin)) 
		{
			if(isFunctionalComplex(child)) return true;
		}
		
		return false;
	}
	
	/** Check if this mixin class has as children at least one collective*/
	public boolean hasCollectiveChild(MixinClass mixin)
	{
		for (Classifier child : getAllChildren(mixin)) 
		{
			if(isCollective(child)) return true;
		}
		
		return false;
	}
	
	/** Check if this mixin class has as children at least one quantity */
	public boolean hasQuantityChild(MixinClass mixin)
	{
		for (Classifier child : getAllChildren(mixin)) 
		{
			if(isQuantity(child)) return true;
		}		
		return false;
	}
	
	/** 
	 * Return the identity provider for a given class i.e.
	 * i) If it is a kind, quantity or collective, or, ii)
	 * If it is anti-rigid sortal, search for the identity provider in all parents, or , iii)
	 * If it is a mixin class search in children and parents for the identity providers.  
	 */
	public HashSet<Classifier> getIdentityProvider(Classifier c)
	{
		HashSet<Classifier> result = new HashSet<Classifier>();
		if (c instanceof SubstanceSortal) result.add(c);		
		if (c instanceof AntiRigidSortalClass || c instanceof SubKind)
		{
			for(Classifier p: getAllParents(c))
			{
				if(p instanceof SubstanceSortal) result.add(p);
			}
		}		
		if (c instanceof MixinClass)
		{
			for(Classifier child: getAllChildren(c))
			{
				if(child instanceof SubstanceSortal) result.add(child);
				if(child instanceof AntiRigidSortalClass || child instanceof SubKind)
				{
					for(Classifier childParent: child.allParents())
					{
						if (childParent instanceof SubstanceSortal) result.add(childParent);
					}
				}
			}
			for(Classifier parent: getAllParents(c))
			{
				for(Classifier parentChild: getAllChildren(parent))
				{
					if (parentChild instanceof SubstanceSortal) result.add(parentChild);
					if (parentChild instanceof AntiRigidSortalClass || parentChild instanceof SubKind) 
					{
						for(Classifier p: parentChild.allParents())
						{
							if (p instanceof SubstanceSortal) result.add(p);
						}
					}
				}
			}
		}			
		return result;		
	}
	
	/** Return all the associations between the types in the list */
	public ArrayList<Association> getAssociationsBetween(HashSet<Type> typeList)
	{
		ArrayList<Association> relations = new ArrayList<Association>();		
		for (Association assoc : getAllInstances(Association.class)) 
		{
			try{
				RefOntoUML.Type source = assoc.getMemberEnd().get(0).getType();
				RefOntoUML.Type target = assoc.getMemberEnd().get(1).getType();			
				if (source !=null && target!=null && typeList.contains(source) && typeList.contains(target)) relations.add(assoc);			
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return relations;
	}
	
	/** Return all the generalizations between the types in the list */
	public ArrayList<Generalization> getGeneralizationsBetween(HashSet<Type> typeList)
	{
		ArrayList<Generalization> generalizations = new ArrayList<Generalization>();		
		for (Generalization gen : getAllInstances(Generalization.class)) 
		{
			try{
				RefOntoUML.Type specific = gen.getSpecific();
				RefOntoUML.Type general = gen.getGeneral();			
				if (specific !=null && general!=null && typeList.contains(specific) && typeList.contains(general)) generalizations.add(gen);			
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return generalizations;
	}
	
	/** Return the common subtypes for all the types in the list */
	public ArrayList<Classifier> getCommonSubtypes(ArrayList<Classifier> types) 
	{
		ArrayList<Classifier> subtypes = new ArrayList<Classifier>();		
		if(types==null || types.size()==0) return subtypes;		
		subtypes.addAll(allChildrenHash.get(types.get(0)));		
		for (int i = 1; i < types.size(); i++) 
		{
			ArrayList<Classifier> currentSubtypes = new ArrayList<Classifier>();
			currentSubtypes.addAll(allChildrenHash.get(types.get(i)));
			subtypes.retainAll(currentSubtypes);
		}		
		return subtypes;
	}
	
	/** Return the common supertypes for all the types in the list */
	public ArrayList<Classifier> getCommonSupertypes(ArrayList<Classifier> types) 
	{
		ArrayList<Classifier> superTypes = new ArrayList<Classifier>();		
		if(types==null || types.size()==0) return superTypes;		
		superTypes.addAll(getAllParents(types.get(0)));		
		for (int i = 1; i < types.size(); i++) 
		{
			ArrayList<Classifier> currentSupertypes = new ArrayList<Classifier>(types.get(i).allParents());
			superTypes.retainAll(currentSupertypes);
		}		
		return superTypes;
	}
		
	/** Return the common subtypes for all the types of the properties in the list */
	public ArrayList<Classifier> getCommonSubtypesFromProperties(ArrayList<Property> properties) 
	{		
		ArrayList<Classifier> types = new ArrayList<Classifier>();
		for (Property property : properties) 
		{
			types.add((Classifier) property.getType());
		}	
		return getCommonSubtypes(types);
	}
	
	/** Return the common supertypes for all the types of the properties in the list */
	public ArrayList<Classifier> getCommonSupertypesFromProperties(ArrayList<Property> properties) 
	{		
		ArrayList<Classifier> types = new ArrayList<Classifier>();
		for (Property property : properties) 
		{
			types.add((Classifier) property.getType());
		}	
		return getCommonSupertypes(types);
	}
	
	/** Return the generalization sets in which this classifier participates */
	public ArrayList<GeneralizationSet> getGeneralizationSets(Classifier c)
	{
		Set<GeneralizationSet> genSets = new HashSet<GeneralizationSet>();		
		for (Generalization g : c.getGeneralization()) 
		{
			genSets.addAll(g.getGeneralizationSet());
		}		
		ArrayList<GeneralizationSet> result = new ArrayList<GeneralizationSet>();
		result.addAll(genSets);
		return result;
	}

	/** Return the generalization sets in which all the direct children of a classifier participate */
	public ArrayList<GeneralizationSet> getSubtypesGeneralizationSets(Classifier c)
	{
		if(c==null) return null;		
		Set<GeneralizationSet> genSets = new HashSet<GeneralizationSet>();		
		if(childHash==null) buildChildrenHashes();		
		for (Classifier child : childHash.get(c)) 
		{
			genSets.addAll(getGeneralizationSets(child));
		}		
		ArrayList<GeneralizationSet> result = new ArrayList<GeneralizationSet>();
		result.addAll(genSets);
		return result;
	}

	/** Build the mappings containing direct children and their classifiers, and, all children and their classifiers. */
	public void buildChildrenHashes() 
	{
		childHash = new HashMap<Classifier,HashSet<Classifier>>();
		allChildrenHash = new HashMap<Classifier,HashSet<Classifier>>();		
		for (Classifier c : getAllInstances(Classifier.class)) 
		{
			childHash.put(c, new HashSet<Classifier>());
			allChildrenHash.put(c, new HashSet<Classifier>());
		}		
		for (Classifier c : childHash.keySet()) 
		{
			for (Classifier parent : c.parents()) childHash.get(parent).add(c);			
			for (Classifier parent : c.allParents()) allChildrenHash.get(parent).add(c);			
		}		
	}

	/** 
	 * Return true if all types of the properties share a common supertype and there is no generalizationSet making them disjoint; returns false otherwise.
	 * if it returns true, the parameters commonSupertypes and genSets are set with the common supertypes and the relevant generalizations sets.
	 */	  
	public boolean allTypesOverlap( ArrayList<Classifier> types, ArrayList<Classifier> commonSupertypes, ArrayList<GeneralizationSet> genSets) throws Exception 
	{		
		if(commonSupertypes==null || commonSupertypes.size()>0) throw new Exception();		
		if(genSets==null || genSets.size()>0) throw new Exception();		
		//Get commmon supertypes; there must be at least one
		commonSupertypes.addAll(getCommonSupertypes(types));
		if(commonSupertypes.size()<1) return false;		
		return madeDisjointByGeneralizationSet(types, genSets,commonSupertypes);
	}

	/**
	 * Returns true if there is no generalizationSet making all property types disjoint; returns false otherwise.
	 * If it returns true, the parameter genSets is set with the relevant generalizations sets.
	 */
	public boolean allTypesOverlap(ArrayList<Classifier> types, ArrayList<GeneralizationSet> genSets) throws Exception 
	{		
		if(genSets==null || genSets.size()>0) throw new Exception();		
		//Get commmon supertypes; there must be at least one
		ArrayList<Classifier> commonSupertypes = getCommonSupertypes(types);
		if(commonSupertypes.size()==0) return true;		
		return madeDisjointByGeneralizationSet(types, genSets, commonSupertypes);
	}		
	
	/** TODO: Should have a description here... */
	public boolean madeDisjointByGeneralizationSet(	ArrayList<Classifier> types, ArrayList<GeneralizationSet> genSets, ArrayList<Classifier> commonSupertypes) 
	{
		//Collect generalizationSets
		HashSet<GeneralizationSet> generalizationSets = new HashSet<GeneralizationSet>();		
		if(childHash==null || allChildrenHash==null) buildChildrenHashes();		
		for (Classifier parent : commonSupertypes) 
		{
			generalizationSets.addAll(getSubtypesGeneralizationSets(parent));
		}		
		//Verifies if there is a generalization set which makes the subtypes disjoint
		ArrayList<Classifier> found;
		for (GeneralizationSet gs : generalizationSets) 
		{
			found = new ArrayList<Classifier>();					
			for (Generalization g1 : gs.getGeneralization())
			{				
				if(types.contains(g1.getSpecific()))
				{
					found.add(g1.getSpecific());
				}
				else {
					for (Classifier type : types) 
					{
						if(getAllParents(type).contains(g1.getSpecific())) { found.add(type); break; }
					}
					//	for (Classifier child : allChildrenHash.get(g1.getSpecific())) {
					//	if(types.contains(child)){
					//		found.add(child);
					//		break;
					//	}
					//	}
				}
			}			
			if(found.size()>1){
				if(gs.isIsDisjoint()) return false;
				else genSets.add(gs);
			}		
		}
		return true;
	}
}
