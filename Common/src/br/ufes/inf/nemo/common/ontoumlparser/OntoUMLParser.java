package br.ufes.inf.nemo.common.ontoumlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.MomentClass;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

/** 
 * This class represents a parser for analyzing and keeping useful informations about ontoUML model. 
 * It associates an alias for every Element in the model, as well as if
 * this Element is selected or not (useful for transformation purposes).
 * 
 * @author John Guerson
 * @author Thiago Sales
 *
 */
public class OntoUMLParser {

	private Package model;	
	private String refmodelname;	
	private HashMap<EObject,ParsingElement> elementsHash;
	
	public static int NO_HIERARCHY = 0, SORTAL_ANCESTORS = 1, ALL_ANCESTORS = 2, ALL_DESCENDANTS = 3, COMPLETE_HIERARCHY = 4;
		
	/**
	 * This constructor creates a parser from a root ontoUML Package.
	 * 
	 * @param refmodel
	 */
	public OntoUMLParser(RefOntoUML.Package refmodel)
	{
		this.model = refmodel;	
		
		elementsHash = new HashMap<EObject,ParsingElement>();	
		
		NameHandler h1 = new NameHandler();
		this.refmodelname = h1.treatName(model);	
		
		NameHandler h2 = new NameHandler();
		initializeElementList(model, h2);
	}
		
	/**
	 * This constructor creates a parser from a absolute ontoUML file in file system.
	 * 
	 * @param refontoumlPath
	 * @throws IOException
	 */
	public OntoUMLParser(String refontoumlPath) throws IOException
	{
		Resource resource = ResourceUtil.loadReferenceOntoUML(refontoumlPath);
		Package refmodel = (Package)resource.getContents().get(0);	
		
		this.model = refmodel;		
		
		elementsHash = new HashMap<EObject,ParsingElement>();	
		
		NameHandler h1 = new NameHandler();
		this.refmodelname = h1.treatName(model);
		
		NameHandler h2 = new NameHandler();
		initializeElementList(model, h2);
	}	

	/**
	 * This private method initialize the HashMap used for keeping the mappings between
	 * ontoUML model elements.
	 * 
	 * @param rootpack
	 * @param h2
	 */
	private void initializeElementList (PackageableElement rootpack, NameHandler h2) 
	{
		ParsingElement e = new ParsingElement(rootpack, true, h2.treatName(rootpack));
		this.elementsHash.put(rootpack,e);
		
		for(PackageableElement p : ((Package) rootpack).getPackagedElement())
		{
			add2ElementHash(p, h2);
			
			if(p instanceof Package) initializeElementList(p, h2);
		}
	}		

	/**
	 * This private method add a Element to the HashMap. It associate an ontoUML Element
	 * with an unique alias and by default with boolean value selected=true.
	 *
	 * @param pe
	 * @param h2
	 */
	private void add2ElementHash(PackageableElement pe, NameHandler h2)
	{
		ParsingElement e;
		
		//Class
		if(pe instanceof Class)
		{
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementsHash.put(pe,e);
			
			//Generalization
			for (Generalization g : ((Class)pe).getGeneralization()) 
			{
				e = new ParsingElement(g, true, "");
				this.elementsHash.put(g,e);
			}
		}
		//Association
		else if(pe instanceof Association)
		{
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementsHash.put(pe,e);
			
			//Property
			Property property0 = ((Association)pe).getMemberEnd().get(0);				
			Property property1 = ((Association)pe).getMemberEnd().get(1);

			e = new ParsingElement(property0, true, h2.treatName(property0));
			this.elementsHash.put(property0,e);
			
			e = new ParsingElement(property1, true, h2.treatName(property1));
			this.elementsHash.put(property1,e);
		
		} else {
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementsHash.put(pe,e);			
		}
		
	}
	
	/**
	 * This method gets the alias of a given ontoUML Element.
	 * 
	 * @param elem
	 * @return
	 */
	public String getAlias(EObject elem) 
	{
		return elementsHash.get(elem).getAlias();
	}
	
	/**
	 * This method gets the String representation for this element.
	 * 
	 * @param elem
	 * @return
	 */
	public String getStringRepresentation(EObject elem)
	{
		return elementsHash.get(elem).toString();
	}
	
	/**
	 * Get String Representation of All Elements.
	 * 
	 * @return
	 */
	public String getStringRepresentations()
	{
		String result = new String();
	
		for (EObject obj: getElements()) { result+= getStringRepresentation(obj)+"\n"; }
		
		return result;		
	}
	
	/**
	 * Get Alias List for a List of OntoUML Elements.
	 * 
	 * @param list
	 * @return
	 */
	public ArrayList<String> getAlias (ArrayList<EObject> list)
	{
		ArrayList<String> result = new ArrayList<String>();
		for(EObject obj: getElements())
		{
			if (list.contains(obj)) result.add(getAlias(obj));
		}
		return result;
	}
	
	/**
	 * Get String Representation of All Elements.
	 * 
	 * @return
	 */
	public String getStringRepresentations(ArrayList<EObject> list)
	{
		String result = new String();
	
		for (EObject obj: getElements()) { if (list.contains(obj)) result+= getStringRepresentation(obj)+"\n"; }
		
		return result;		
	}
	
	
	/**
	 * This method verifies if a given onotuML elem is selected or not.
	 * 
	 * @param elem
	 * @return
	 */
	public Boolean isSelected (EObject elem) 
	{		
		return elementsHash.get(elem).getSelected();
	}
	
	/**
	 * Get onotUML model name.
	 *  
	 * @return
	 */
	public String getModelName()
	{
		return refmodelname;
	}	
		
	/**
	 * Get all the ontoUML elements of the model.
	 * @return
	 */
	public Set<EObject> getElements()
	{
		Set<EObject> list = new HashSet<EObject>(); 
		
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(pe.getSelected()) list.add(pe.getElement());
		}
		
		return list;
	}
	
	/**
	 * Select this elements in the model. If 'unselectOthers' is true, the other elements are unselected (i.e. selected=false). 
	 * Otherwise, if 'unselectOther' is false, then nothing is made with the others elements, they maybe selected or not. i.e. selected = true or false.
	 * 
	 * @param list
	 */
	public void selectThisElements(ArrayList<EObject> selected, boolean unselectOthers)
	{
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(selected.contains(pe.getElement())) pe.setSelected(true);
			else if (unselectOthers) pe.setSelected(false);
		}
	}
	
	/**
	 * This method selects all Elements of the model.
	 */
	public void SelectAllElements()
	{
		for (ParsingElement pe : elementsHash.values()) 
		{
			pe.setSelected(true);
		}
	}
	
	/**
	 * This method returns all elements from the input list that are selected in the parser.
	 * 
	 * @param elements: list of elements of any type.
	 */
	public <T> Set<T> retainSelected(List<T> elements){
		Set<T> result = new HashSet<T>();
		
		for (T elem : elements) {
			if(elem instanceof EObject && isSelected((EObject) elem))
				result.add(elem);
		}
		
		return result;
	}
			
	/**
	 *This method gets all the ontoUML generalization that the Classifier c is the general classifier.
	 * 
	 * @return
	 */
	public Set<Generalization> getGeneralizations(Classifier c)
	{
		Set<Generalization> list = new HashSet<Generalization>();		
		for (EObject obj : getElements()) 
		{
			if(obj instanceof Generalization && ((Generalization)obj).getGeneral().equals(c)) 
				
			list.add((Generalization)obj);			
		}		
		return list;
	}
			
	/**
	 * This method gets rigid Classes of the model.
	 * 
	 * @return
	 */
	public Set<Classifier> getRigidClasses()
	{
		Set<Classifier> list = new HashSet<Classifier>();		
		for (EObject obj : getElements())
		{			
			if (
				(obj instanceof RigidSortalClass) || (obj instanceof Category) || 
				(obj instanceof MomentClass) || ((obj instanceof DataType)&&!(obj instanceof PrimitiveType))
			)				
			list.add((Classifier) obj);			
		}		
		return list;
	}
			
	/**
	 * This method gets anti rigid Classes of the model.
	 * 
	 * @return
	 */
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
	
	
	
	
	/**
	 * Get all descendants (direct or indirect) of Classifier c.
	 * 
	 * @param c
	 */
	public Set<Classifier> getAllChildren(Classifier c){
		Set<Classifier> result = new HashSet<Classifier>();
		
		for (Classifier classifier : c.allChildren()) {
			if(isSelected(classifier))
				result.add(classifier);
		}
		
		return result;
	}
	
	/**
	 * Get all direct descendants of Classifier c.
	 * 
	 * @param c
	 */
	public Set<Classifier> getChildren(Classifier c){
		Set<Classifier> result = new HashSet<Classifier>();
		
		for (Classifier classifier : c.children()) {
			if(isSelected(classifier))
				result.add(classifier);
		}
		
		return result;
	} 
	
	/**
	 * Get all ancestors (direct or indirect) of Classifier c.
	 * 
	 * @param c
	 */
	public Set<Classifier> getAllParents(Classifier c){
		Set<Classifier> result = new HashSet<Classifier>();
		
		for (Classifier classifier : c.allParents()) {
			if(isSelected(classifier))
				result.add(classifier);
		}
		
		return result;
	}
	
	/**
	 * Get all direct ancestors of Classifier c.
	 * 
	 * @param c
	 */
	public Set<Classifier> getParents(Classifier c){
		Set<Classifier> result = new HashSet<Classifier>();
		
		for (Classifier classifier : c.parents()) {
			if(isSelected(classifier))
				result.add(classifier);
		}
		
		return result;
	} 
	
	/**
	 * Get all non-abstract descendants of Classifier c.
	 * 
	 * @param c
	 */
		
	public Set<Classifier> getAllConcreteChildren(Classifier c){
		Set<Classifier> result = new HashSet<Classifier>();
		
		for (Classifier classifier : getAllChildren(c)) {
			if(isSelected(classifier))
				result.add(classifier);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Get all selected instances of a given class in the OntoUMLParser 
	 * 
	 * @param type: class from the OntoUML metamodel. use ´class_name´.class
	 * @return all instances of the type in the parameter 
	 */
	public <T> Set<T> getAllInstances(java.lang.Class<T> type){
		Set<T> result = new HashSet<T>();
		
		for (EObject o : getElements()) {
			if(isSelected(o) && type.isInstance(o) )
				result.add((T) o);
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> Set<T> getTopLevelInstances(java.lang.Class<T> type){
		Set<T> result = new HashSet<T>();
		
		for (EObject o : getElements()) {
			if(isSelected(o) && type.isInstance(o)){
				if(o instanceof Classifier && ((Classifier)o).getGeneralization().size()==0){
					if(o instanceof DataType && ! (o instanceof PrimitiveType)) result.add((T) o);
					else if (!(o instanceof DataType)) result.add((T) o);
					
				}
					
			}
		}
		return result;
	}
	
	
	
	/**
	 * Get all Meronymic relations that have as a Whole the Classifier 'c' or one of its Super Types.
	 * 
	 * @param c
	 * @param result
	 */
	public void getAllMeronymics(Classifier c, ArrayList<Meronymic> result)
	{
		for(EObject obj : getElements())
		{
			if(obj instanceof Meronymic)
			{
				for( Property p : ((Meronymic)obj).getMemberEnd())
				{
					if (!p.getAggregation().equals(AggregationKind.NONE))
					{					
						if (isSelected(p.getType()) && p.getType().equals(c))
						{
							result.add((Meronymic)obj);
						}						
					}
				}
			}
		}
		for(Generalization gen : c.getGeneralization())
		{
			if (isSelected(gen)) getAllMeronymics(gen.getGeneral(),result);			
		}
	}
			
	/**
	 * Get all Mediations that have as a source the Relator 'r' or one of its Super Types.
	 * 
	 * @param r
	 * @param result
	 */
	public void getAllMediations(Relator r,ArrayList<Mediation> result)
	{
		for(EObject obj : getElements())
		{
			if(obj instanceof Mediation)
			{				
				for( Property p : ((Mediation)obj).getMemberEnd())
				{
					if (p.getType() instanceof Relator)
					{
						if (isSelected(p.getType()) && p.getType().equals(r))
						{
							result.add((Mediation)obj);							
						}
					}
				}
			}			
		}
		for(Generalization gen : r.getGeneralization())
		{						
			if(isSelected(gen))	if (gen.getGeneral() instanceof Relator) getAllMediations((Relator)gen.getGeneral(),result);			
		}
	}
	
	/**
	 * Verify if a Classifier 'c' is a General Classifier in a GeneralizationSet that is Disjoint and Complete
	 * Which means that this Classifier is an Abstract Classifier.
	 * 
	 * @param c
	 * @return
	 */
	public boolean isAbstractFromGeneralizationSet(Classifier c) 
	{
		for(GeneralizationSet gs : getAllInstances(GeneralizationSet.class))
		{			
			if(gs.isIsCovering())
			{
				for(Generalization gen : gs.getGeneralization())
				{
					if (isSelected(gen)) 
					{
						if (isSelected(gen.getGeneral()) && gen.getGeneral().equals(c)) return true;
					}
				}
			}
		}		
		return false;
	}
		 
	/**
	 * Auto Select Elements : Mandatory Dependencies.
	 * 
	 * In Generalizations, the general and specific classifiers must be selected, as well as the generalization set. 
	 * In Associations, the source and target types must be selected.
	 * In PackageableElements, the container of this elements, for instance, the package container, must be selected. 
	 * 
	 * @return
	 */
	private ArrayList<EObject> autoSelectMandatoryDependencies()
	{
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();
		
		for (EObject obj : getElements()) 
		{
			if(obj instanceof Generalization)
			{
				Generalization g = (Generalization) obj;
				// general
				if (!isSelected(g.getGeneral()) && !objectsToAdd.contains(g.getGeneral()) ) 
				{
					objectsToAdd.add(g.getGeneral());										
				}
				//specific
				if (!isSelected(g.getSpecific()) && !objectsToAdd.contains(g.getSpecific()) ) 
				{
					objectsToAdd.add(g.getSpecific());					
				}
			}
			if(obj instanceof Association) 
			{
				Association a = (Association)obj;				
				Type source = a.getMemberEnd().get(0).getType();
				Type target = a.getMemberEnd().get(1).getType();
				//source
				if(!isSelected(source) && !objectsToAdd.contains(source))
				{
					objectsToAdd.add(source);					
				}
				//target
				if(!isSelected(target) && !objectsToAdd.contains(target))
				{
					objectsToAdd.add(target);					
				}								
			}		
			
			if(obj instanceof PackageableElement) 
			{
				// packages
				if((obj.eContainer()!=null) && !isSelected(obj.eContainer()) && !objectsToAdd.contains(obj.eContainer())) 
				{
					objectsToAdd.add(obj.eContainer());
				}
			}
		}
		
		// add this elements to selection...
		selectThisElements(objectsToAdd,false);
		
		return objectsToAdd;
	}
	
	private ArrayList<EObject> autoSelectGeneralizationSetFromGeneralization(){
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();
		
		for (Generalization g : getAllInstances(Generalization.class))
		{
			for (GeneralizationSet gs : g.getGeneralizationSet()) 
	        {
	            if(!isSelected(gs) && !objectsToAdd.contains(gs))
	            {
	                objectsToAdd.add(gs);                                        
	            }
	        }
		}
		
		// add this elements to selection...
		selectThisElements(objectsToAdd,false);
		
		return objectsToAdd;
	}
	
	/**
	 * Auto Select Elements: Generalization Set.
	 * 
	 * In Generalization Set, the Generalizations and the general and specific classifiers, must be selected.
	 * 
	 * @return
	 */
	private ArrayList<EObject> autoSelectGeneralizationSet() 
	{		
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();
		
		for(EObject obj : getElements()){
			if(obj instanceof GeneralizationSet) 
			{
				GeneralizationSet gs = (GeneralizationSet)obj;				
				//generalizations
				for(Generalization g: gs.getGeneralization())
				{
					if(!isSelected(g))
					{
						objectsToAdd.add(g);						
					}
					//specific
					if (!isSelected(g.getSpecific()) && !objectsToAdd.contains(g.getSpecific())) 
					{
						objectsToAdd.add(g.getSpecific());						
					}
					//general
					if (!isSelected(g.getGeneral()) && !objectsToAdd.contains(g.getGeneral())) 
					{
						objectsToAdd.add(g.getGeneral());						
					}
				}								
			}
		}
		
		// add this elements to selection...
		selectThisElements(objectsToAdd,false);
		
		return objectsToAdd;
	}
	
	/**
	 * Auto Select Elements : All the ancestors of a Class. i.e. Parents and Generalizations.
	 * 
	 * Every Class must have all the parents and their generalizations selected,
	 * or until some Substance Sortal be found.
	 * 
	 * @param option
	 * @return
	 */
	private ArrayList<EObject> autoSelectHierarchy(int option) 
	{
		ArrayList<EObject> objectsToAdd = new ArrayList<EObject>();
		
		if (option==NO_HIERARCHY) return objectsToAdd;

		for (EObject o : getElements()) 
		{
			if (o instanceof Class) {
				if (option==SORTAL_ANCESTORS || option==ALL_ANCESTORS || option==COMPLETE_HIERARCHY)
					getAncestorsHierarchy((Classifier) o, objectsToAdd, option);
				if (option==ALL_DESCENDANTS || option==COMPLETE_HIERARCHY)
					getDescendantsHierarchy((Classifier) o, objectsToAdd);
			}
		}
		
		// add this elements to selection...
		selectThisElements(objectsToAdd,false);
	
		return objectsToAdd;
	}

	/**
	 * Private method to get all the ancestors of a Class. i.e. Parents and Generalizations.
	 * 
	 * @param o
	 * @param objectsToAdd
	 * @param option
	 */
	private void getDescendantsHierarchy (Classifier o, ArrayList<EObject> objectsToAdd){
		
		for (Classifier c : o.children()) 
		{
			if(!isSelected(c) && !objectsToAdd.contains(c))
			{
				objectsToAdd.add(c);
				for (Generalization g : c.getGeneralization()) {
					if(g.getGeneral().equals(o))
						objectsToAdd.add(g);
				}
				getDescendantsHierarchy(c, objectsToAdd);
			}
		}
	}
	
	/**
	 * Private method to get all the ancestors of a Class. i.e. Parents and Generalizations.
	 * 
	 * @param o
	 * @param objectsToAdd
	 * @param option
	 */
	private void getAncestorsHierarchy (Classifier o, ArrayList<EObject> objectsToAdd, int option){
		
		if(o instanceof SubstanceSortal && option==SORTAL_ANCESTORS) return;
		
		for (Generalization g : ((Class) o).getGeneralization()) 
		{
			if(!isSelected(g) && !objectsToAdd.contains(g))
			{
				objectsToAdd.add(g);
				objectsToAdd.add(g.getGeneral());
				
				getAncestorsHierarchy(g.getGeneral(), objectsToAdd,option);
			}
		}
	}	
	
	/**
	 * Auto Select Elements :  explicit dependencies through parameters.
	 * 
	 * @param hierarchyOption
	 * @param includeGeneralizationSet
	 * @return
	 */
	public ArrayList<EObject> autoSelectDependencies(int hierarchyOption, boolean includeGeneralizationSet)
	{		
		ArrayList<EObject> objectsAdded = new ArrayList<EObject>();
		
		objectsAdded.addAll(autoSelectMandatoryDependencies());
						
		objectsAdded.addAll(autoSelectHierarchy(hierarchyOption));
		
		objectsAdded.addAll(autoSelectGeneralizationSetFromGeneralization());
		
		if (includeGeneralizationSet) objectsAdded.addAll(autoSelectGeneralizationSet());
		
		return objectsAdded;
	}

	
	
	
	
	/*=============================================================================*/
	
	
	
	
	public ArrayList<EObject> getSelectedResolvingLostReferences (String log, boolean includeHierarchy)
	{
		ArrayList<EObject> class_add_list = new ArrayList<>();		
		ArrayList<EObject> selected = new ArrayList<EObject>();
		
		selected.addAll(getElements());		
		
		for (EObject o : selected)
		{
			//guarantees that there will be no null pointer in the generalization, 
			//by including the general and the specific to the list of selected elementsz
			
			if(o instanceof Generalization)
			{
				Generalization g = (Generalization) o;
				Classifier general, specific;
				
				general = g.getGeneral();
				if (!selected.contains(general) && !class_add_list.contains(general) ) 
				{
					class_add_list.add(general);
					log += general.getName()+" added\n.";
				}
				
				specific = g.getSpecific();
				if (!selected.contains(specific) && !class_add_list.contains(specific)) 
				{
					class_add_list.add(specific);
					log += specific.getName()+" added.\n";
				}
			}
			
			//guarantee that the types related in a association are included in the new model
			
			if(o instanceof Association) 
			{
				Association a = (Association)o;
				Type source, target;
				
				source = a.getMemberEnd().get(0).getType();
				if(!selected.contains(source) && !class_add_list.contains(source))
				{
					class_add_list.add(source);
					log += source.getName()+" added.\n";
				}
				
				target = a.getMemberEnd().get(1).getType();
				if(!selected.contains(target) && !class_add_list.contains(target))
				{
					class_add_list.add(target);
					log += target.getName()+" added.\n";
				}								
			}
		}
		
		selected.addAll(class_add_list);
		class_add_list = new ArrayList<>();
		
		//if the option to include hierarchy for every type class is selected.
		if (includeHierarchy)
		{
			for (EObject o : selected) 
			{
				if (o instanceof Class)
				{
					for (Classifier c : ((Classifier)o).allParents()) 
					{
						if(!selected.contains(c) && !class_add_list.contains(c))
						{
							class_add_list.add(c);
							log += c.getName()+" added.\n";
						}
					}
				}
			}
			selected.addAll(class_add_list);
		}
		return selected;
	}	
	
	public Package recreatePackageFromSelectedClasses (String log, boolean includeHierarchy, Copier copier)
	{		
		Package pack_copy;
		ArrayList<EObject> selected_copy = new ArrayList<EObject>();
		
		ArrayList<EObject> selected = getSelectedResolvingLostReferences(log,includeHierarchy);
		
		pack_copy = (Package) copier.copy(model);
		copier.copyReferences();
		
		for (EObject element : selected) 
		{
			selected_copy.add(copier.get(element));
		}
		
		deleteElement(pack_copy, selected_copy);
		
		return pack_copy;
	}
	
	private void deleteElement (Package pack, ArrayList<EObject> selected)
	{
		ArrayList<EObject> delete_list = new ArrayList<EObject>();
		
		for (PackageableElement eo : pack.getPackagedElement()) 
		{
			if(!selected.contains(eo)) delete_list.add(eo);
			
			else {
				
				if (eo instanceof Package) deleteElement((Package) eo, selected);
				
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
	
	
}
