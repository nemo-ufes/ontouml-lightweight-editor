package br.ufes.inf.nemo.common.ontoumlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 *  This class is used to read the model instance and to associate the Elements of the model 
 *  with their modified names into Hash Maps.
 */

public class OntoUMLParser {

	/** Name of the RefOntoUML Model root. */
	private String refmodelname;
	
	private HashMap<EObject,ParsingElement> elementHash;
	
	private Package model;
	
	/**
	 * Constructor.
	 *  
	 * @param refmodel: The root of .refontouml model (RefOntoUML.Model).
	 */	 
	public OntoUMLParser(RefOntoUML.Package refmodel)
	{
		this.model = refmodel;	
		
		elementHash = new HashMap<EObject,ParsingElement>();
		
		NameHandler h1 = new NameHandler();
		this.refmodelname = h1.treatName(model);
		
		NameHandler h2 = new NameHandler();
		initializeElementList(model, h2);
	}
	
	
	/**
	 * Constructor.
	 * 
	 * @param refontoumlPath: Absolute Path of the OntoUML Model.
	 * @throws IOException
	 */
	public OntoUMLParser(String refontoumlPath) throws IOException
	{
		Resource resource = ResourceUtil.loadReferenceOntoUML(refontoumlPath);
		Package refmodel = (Package)resource.getContents().get(0);
		
		this.model = refmodel;
		
		elementHash = new HashMap<EObject,ParsingElement>();
		
		NameHandler h1 = new NameHandler();
		this.refmodelname = h1.treatName(model);
		
		NameHandler h2 = new NameHandler();
		initializeElementList(model, h2);
	}	

	/** 
	 * Initialize the Hash Maps.
	 * 
	 * @param rootpack: Usually the root of .refontouml model (RefOntoUML.Model).
	 */
	private void initializeElementList (PackageableElement rootpack, NameHandler h2) 
	{
		for(PackageableElement p : ((Package) rootpack).getPackagedElement())
		{
			add2ElementHash(p, h2);
			if(p instanceof Package) 
				initializeElementList(p, h2);
		}
	}		
	
	/**
	 * Add packageable Element to the respective HashMap.
	 * 
	 * @param pe: A Packageable Element of the ontouml model.
	 */
	private void add2ElementHash(PackageableElement pe, NameHandler h2)
	{
		ParsingElement e;
		
		if(pe instanceof Class){
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementHash.put(pe,e);
			
			for (Generalization g : ((Class)pe).getGeneralization()) {
				e = new ParsingElement(g, true, "");
				this.elementHash.put(g,e);
			}
		}
		
		else if(pe instanceof Association){
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementHash.put(pe,e);
			
			Property property0 = ((Association)pe).getMemberEnd().get(0);				
			Property property1 = ((Association)pe).getMemberEnd().get(1);

			e = new ParsingElement(property0, true, h2.treatName(property0));
			this.elementHash.put(property0,e);
			
			e = new ParsingElement(property1, true, h2.treatName(property1));
			this.elementHash.put(property1,e);
		}
		
		else{
			e = new ParsingElement(pe, true, h2.treatName(pe));
			this.elementHash.put(pe,e);
		}
		
	}
	
	public void setSelection(ArrayList<EObject> list){
		for (ParsingElement pe : elementHash.values()) {
			if(list.contains(pe.getElement())){
				pe.setSelected(true);
			}
			else
				pe.setSelected(false);
		}
	}
	
	public Set<Property> getProperties()
	{
		Set<Property> list = new HashSet<Property>(); 
		
		for (EObject o : elementHash.keySet()) {
			if(o instanceof Property)
				list.add((Property) o);
		}
		
		return list;
	}
	
	public Set<Property> getSelectedProperties()
	{
		Set<Property> list = new HashSet<Property>(); 
		
		for (ParsingElement pe : elementHash.values()) {
			if(pe.getElement() instanceof Property && pe.selected)
				list.add((Property) pe);
		}
		
		return list;
	}
	
	public Set<PackageableElement> getPackageableElements()
	{
		Set<PackageableElement> list = new HashSet<PackageableElement>(); 
		
		for (EObject o : elementHash.keySet()) {
			if(o instanceof PackageableElement)
				list.add((PackageableElement) o);
		}
		
		return list;
	}
	
	public Set<PackageableElement> getSelectedPackageableElements()
	{
		Set<PackageableElement> list = new HashSet<PackageableElement>(); 
		
		for (ParsingElement o : elementHash.values()) {
			if(o.getElement() instanceof PackageableElement && o.getSelected()){
				list.add((PackageableElement) o);
			}
		}
		
		return list;
	} 
	
	public Set<Generalization> getGeneralizations()
	{
		Set<Generalization> list = new HashSet<Generalization>(); 
		
		for (EObject pe : elementHash.keySet()) {
			if(pe instanceof Generalization)
				list.add((Generalization) pe);
		}
		
		return list;
	}
	
	public Set<Generalization> getSelectedGeneralizations()
	{
		Set<Generalization> list = new HashSet<Generalization>(); 
		
		for (ParsingElement pe : elementHash.values()) {
			if(pe.getElement() instanceof Generalization && pe.getSelected())
				list.add((Generalization) pe);
		}
		
		return list;
	}
	
	public String getAlias(EObject elem)
	{
		return elementHash.get(elem).getAlias();
	}
	
	public Boolean isSelected (EObject elem) {
		return elementHash.get(elem).getSelected();
	}
	
	public String getModelName()
	{
		return refmodelname;
	}	
	
	public Set<EObject> getElements(){
		return elementHash.keySet();
	}
	
	public Set<EObject> getSelectedElements(){
		Set<EObject> list = new HashSet<EObject>(); 
		
		for (ParsingElement pe : elementHash.values()) {
			if(pe.selected)
				list.add(pe.getElement());
		}
		
		return list;
	}
	
	public Package recreatePackage (String log, boolean includeHierarchy, Copier copier){
		ArrayList<EObject> selected = new ArrayList<EObject>();
		selected.addAll(this.getSelectedElements());
		return recreateSelected(model, log, includeHierarchy, copier, selected);
	}
	
	//recreates the model keeping only the selected classes
	private Package recreateSelected (Package root, String log, boolean includeHierarchy, Copier copier, ArrayList<EObject> selected){
		
		ArrayList<EObject> class_add_list = new ArrayList<>(), gen_remove_list = new ArrayList<>();
		ArrayList<EObject> selected_copy = new ArrayList<EObject>();
		Package pack_copy;
		
		for (EObject o : selected){
			//guarantees that there will be no null pointer in the generalization, by including the general and the specific to the list of selected elementsz
			if(o instanceof Generalization){
				Generalization g = (Generalization) o;
				Classifier general, specific;
				
				general = g.getGeneral();
				if (!selected.contains(general) && !class_add_list.contains(general) ) {
					class_add_list.add(general);
					log += general.getName()+" added\n.";
				}
				
				specific = g.getSpecific();
				if (!selected.contains(specific) && !class_add_list.contains(specific)) {
					class_add_list.add(specific);
					log += specific.getName()+" added.\n";
				}
			}
			
			//guarantee that the types related in a association are included in the new model
			if(o instanceof Association) {
				Association a = (Association)o;
				Type source, target;
				
				source = a.getMemberEnd().get(0).getType();
				if(!selected.contains(source) && !class_add_list.contains(source)){
					class_add_list.add(source);
					log += source.getName()+" added.\n";
				}
				
				target = a.getMemberEnd().get(1).getType();
				if(!selected.contains(target) && !class_add_list.contains(target)){
					class_add_list.add(target);
					log += target.getName()+" added.\n";
				}
								
			}
		}
		
		selected.addAll(class_add_list);
		class_add_list = new ArrayList<>();
		
		//if the option to include hierarchy for everytype class is selected.
		if (includeHierarchy){
			for (EObject o : selected) {
				if (o instanceof Class){
					for (Classifier c : ((Classifier)o).allParents()) {
						if(!selected.contains(c) && !class_add_list.contains(c)){
							class_add_list.add(c);
							log += c.getName()+" added.\n";
						}
					}
				}
			}
			selected.addAll(class_add_list);
		}
		
		pack_copy = (Package) copier.copy(root);
		copier.copyReferences();
		
		for (EObject element : selected) {
			selected_copy.add(copier.get(element));
		}
		
		deleteElement(pack_copy, selected_copy);
		
		return pack_copy;
		
		/*for (EObject o : selected) {
			if (o instanceof Package)
				pack_copy.getPackagedElement().add(recreateSelected((Package) o, log, includeHierarchy, copier, selected));
			
			if (o instanceof Class){
				pack_copy.getPackagedElement().add((PackageableElement) copier.copy(o));
				
				for (Generalization g : ((Class)o).getGeneralization() ) {
					if(!includeHierarchy && !selected.contains(g))
						gen_remove_list.add(g);
				}
			}
			
			else if (o instanceof Association || o instanceof GeneralizationSet)
				pack_copy.getPackagedElement().add((PackageableElement) copier.copy(o));
		}
		
		for (int i = 0; i < gen_remove_list.size(); i++) {
			EcoreUtil.remove(gen_remove_list.get(i));
		}*/
		
		
		
	}
	
	private void deleteElement (Package pack, ArrayList<EObject> selected){
		ArrayList<EObject> delete_list = new ArrayList<EObject>();
		
		for (PackageableElement eo : pack.getPackagedElement()) {
			if(!selected.contains(eo))
				delete_list.add(eo);
			else {
				if (eo instanceof Package)
					deleteElement((Package) eo, selected);
				else {
					for (EObject c : eo.eContents()) {
						if(!selected.contains(c))
							delete_list.add(c);
					}
				}
				/*if (eo instanceof Class){
					for (Generalization g : ((Class)eo).getGeneralization()) {
						if (!selected.contains(g))
							EcoreUtil.remove(g);
					}
					
					for (Property p : ((Class)eo).getAttribute()){
						if(!selected.contains(p))
							EcoreUtil.remove(p);
					}
				}
				if (eo instanceof Association){
					if (!selected.contains(((Association)eo).getMemberEnd().get(0)))
						EcoreUtil.remove(((Association)eo).getMemberEnd().get(0));
					if (!selected.contains(((Association)eo).getMemberEnd().get(1)))
						EcoreUtil.remove(((Association)eo).getMemberEnd().get(1));
				}*/
					
			}		
		}
		
		for(int i = 0; i<delete_list.size(); i++)
			EcoreUtil.remove(delete_list.get(i));
	}
	
	
}
