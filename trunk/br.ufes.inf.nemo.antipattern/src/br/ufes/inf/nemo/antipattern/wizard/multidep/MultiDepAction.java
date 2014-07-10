package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class MultiDepAction extends AntiPatternAction<MultiDepOccurrence>{

	public ArrayList<ArrayList<Property>> matrix = new ArrayList<ArrayList<Property>>();
	public ArrayList<Property> properties;
	private HashMap<Property, Integer> order;
	
	public MultiDepAction(MultiDepOccurrence ap) {
		super(ap);
	}

	@Override
	public void run() {
		if(code==Action.CREATE_DEPENDENCIES) ap.createFormalAssociations(matrix);
		else if(code==Action.ADD_SUBTYPES_IN_NO_ORDER) ap.addSubtypesPerProperty(properties);
		else if(code==Action.ADD_SUBTYPES_IN_CUSTOM_ORDER) ap.addOrderedSubtypes(order);
	}
	
	public enum Action {CREATE_DEPENDENCIES, ADD_SUBTYPES_IN_NO_ORDER, ADD_SUBTYPES_IN_CUSTOM_ORDER, CREATE_GENERALIZATION_SET}
	
	public void setCreateDependencies(ArrayList<ArrayList<Property>> binFormalCombo){
		code = Action.CREATE_DEPENDENCIES;
		this.matrix = binFormalCombo;
	}
	
	public void setAddSubtypesInNoOrder(ArrayList<Property> properties){
		code = Action.ADD_SUBTYPES_IN_NO_ORDER;
		this.properties=properties;
	}
	
	public void setAddSubtypesInCustomOrder(HashMap<Property, Integer> order){
		code = Action.ADD_SUBTYPES_IN_CUSTOM_ORDER;
		this.order=order;
	}
		
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CREATE_DEPENDENCIES) {
			for(ArrayList<Property> list: matrix){
				if (list.size()==2){
					result+= "Create a formal association between "+list.get(0).getType().getName()+" and "+list.get(1).getType().getName()+"\n";
				}
			}					
		} else if(code==Action.ADD_SUBTYPES_IN_NO_ORDER){
			for(Property p: properties){
				result+= "Add Subtype to "+ap.getType().getName()+" and connect the Subtype to "+p.getType().getName()+" through the mediation"+"\n";
			}
			if (properties.size()>1) result+= "Create a generalization set between all the Subtypes created to the type "+ap.getType().getName()+"\n";
					
		}else if(code==Action.ADD_SUBTYPES_IN_CUSTOM_ORDER){
			result += "Add subtypes in custom order\n";
		} 
		
		return result;
	}

}
