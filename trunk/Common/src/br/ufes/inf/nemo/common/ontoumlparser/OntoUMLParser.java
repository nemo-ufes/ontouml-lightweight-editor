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
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
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
	 * This method initialize the HashMap used for keeping the mappings between
	 * ontoUML model elements.
	 * 
	 * @param rootpack
	 * @param h2
	 */
	private void initializeElementList (PackageableElement rootpack, NameHandler h2) 
	{
		for(PackageableElement p : ((Package) rootpack).getPackagedElement())
		{
			add2ElementHash(p, h2);
			
			if(p instanceof Package) initializeElementList(p, h2);
		}
	}		

	/**
	 * This method add a Element to the HashMap. It associate an ontoUML Element
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
	 * This method set the selection of every ontoUML Element according to the list of selected elements.
	 * 
	 * @param list
	 */
	public void setSelection(ArrayList<EObject> list)
	{
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(list.contains(pe.getElement())) pe.setSelected(true);
			else pe.setSelected(false);
		}
	}
	
	/**
	 * This method get all the ontoUML Properties of the model.
	 * 
	 * @return
	 */
	public Set<Property> getProperties()
	{
		Set<Property> list = new HashSet<Property>(); 
		
		for (EObject o : elementsHash.keySet()) 
		{
			if(o instanceof Property) list.add((Property) o);
		}
		
		return list;
	}
	
	/**
	 * This methos get all the Selected Properties of the model.
	 * 
	 * @return
	 */
	public Set<Property> getSelectedProperties()
	{
		Set<Property> list = new HashSet<Property>(); 
		
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(pe.getElement() instanceof Property && pe.selected) list.add((Property) pe);
		}
		
		return list;
	}
	
	/**
	 * This method get all the ontoUML PackageableElements of the model.
	 * 
	 * @return
	 */
	public Set<PackageableElement> getPackageableElements()
	{
		Set<PackageableElement> list = new HashSet<PackageableElement>(); 
		
		for (EObject o : elementsHash.keySet()) 
		{
			if(o instanceof PackageableElement) list.add((PackageableElement) o);
		}
		
		return list;
	}
	
	/**
	 * This method get all the Selected ontoUML PackageableElement of the model.
	 * 
	 * @return
	 */
	public Set<PackageableElement> getSelectedPackageableElements()
	{
		Set<PackageableElement> list = new HashSet<PackageableElement>(); 
		
		for (ParsingElement o : elementsHash.values()) 
		{
			if(o.getElement() instanceof PackageableElement && o.getSelected())
			{
				list.add((PackageableElement) o);
			}
		}
		
		return list;
	} 
	
	/**
	 * This method gets all the ontoUML generalizations of the model.
	 * 
	 * @return
	 */
	public Set<Generalization> getGeneralizations()
	{
		Set<Generalization> list = new HashSet<Generalization>(); 
		
		for (EObject pe : elementsHash.keySet()) 
		{
			if(pe instanceof Generalization) list.add((Generalization) pe);
		}
		
		return list;
	}
	
	/**
	 * This method gets all the Selected generaliations of the model.
	 * 
	 * @return
	 */
	public Set<Generalization> getSelectedGeneralizations()
	{
		Set<Generalization> list = new HashSet<Generalization>(); 
		
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(pe.getElement() instanceof Generalization && pe.getSelected()) list.add((Generalization) pe);
		}
		
		return list;
	}
	
	/**
	 * This method get the alias of a given ontoUML Element.
	 * 
	 * @param elem
	 * @return
	 */
	public String getAlias(EObject elem) 
	{
		return elementsHash.get(elem).getAlias();
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
	 * 
	 * @return
	 */
	public Set<EObject> getElements()
	{
		return elementsHash.keySet();
	}
	
	/**
	 * Get all the ontoUML selected elements of the mode.
	 * @return
	 */
	public Set<EObject> getSelectedElements()
	{
		Set<EObject> list = new HashSet<EObject>(); 
		
		for (ParsingElement pe : elementsHash.values()) 
		{
			if(pe.selected) list.add(pe.getElement());
		}
		
		return list;
	}
		
	/**
	 * Recreates the model keeping only the selected classes.
	 * 
	 * @param root
	 * @param log
	 * @param includeHierarchy
	 * @param copier
	 * @param selected
	 * @return
	 */
	public Package recreatePackageFromSelectedClasses (String log, boolean includeHierarchy, Copier copier)
	{		
		ArrayList<EObject> selected = new ArrayList<EObject>();
		
		selected.addAll(this.getSelectedElements());
		
		ArrayList<EObject> class_add_list = new ArrayList<>();
		ArrayList<EObject> selected_copy = new ArrayList<EObject>();
		
		Package pack_copy;
		
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
		
		//if the option to include hierarchy for everytype class is selected.
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
		
		pack_copy = (Package) copier.copy(model);
		copier.copyReferences();
		
		for (EObject element : selected) 
		{
			selected_copy.add(copier.get(element));
		}
		
		deleteElement(pack_copy, selected_copy);
		
		return pack_copy;
	}
	
	/**
	 * Delete selected elements from a root Package model.
	 * 
	 * @param pack
	 * @param selected
	 */
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
