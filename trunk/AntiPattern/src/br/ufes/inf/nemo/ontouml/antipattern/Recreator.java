package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Class;
import RefOntoUML.Type;

public class Recreator {

	//recreates the model keeping only the selected classes
	public static Package recreatePackage (ArrayList<EObject> selected, String log, boolean includeHierarchy, Copier copier){
		ArrayList<EObject> class_add_list = new ArrayList<>(), gen_remove_list = new ArrayList<>();
		
		
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		Package pack_copy = factory.createPackage();
				
		
		if (selected == null || selected.size()==0) 
			return null;
		
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
				
		for (EObject o : selected) {
			if (o instanceof Package)
				pack_copy.getPackagedElement().add(recreatePackage(selected, log, includeHierarchy, copier));
			
			if (o instanceof Class){
				pack_copy.getPackagedElement().add((PackageableElement) copier.copy(o));
				
				for (Generalization g : ((Class)o).getGeneralization() ) {
					if(!includeHierarchy && !selected.contains(g))
						gen_remove_list.add(g);
					
				}
			}
			
			else
				pack_copy.getPackagedElement().add((PackageableElement) o);
		}
		
		for (int i = 0; i < gen_remove_list.size(); i++) {
			EcoreUtil.remove(gen_remove_list.get(i));
		}
		
		copier.copyReferences();
		System.out.println(log);
		return pack_copy;
	}

}
