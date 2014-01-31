package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class MultiDepAction extends AntiPatternAction<MultiDepOccurrence>{

	public ArrayList<ArrayList<Property>> matrix = new ArrayList<ArrayList<Property>>();
	public ArrayList<Property> properties;
	
	public MultiDepAction(MultiDepOccurrence ap) {
		super(ap);
	}

	@Override
	public void run() {
		if(code==Action.CREATE_FORMAL_ASSOCIATIONS) ap.createFormalAssociations(matrix);
		else if(code==Action.ADD_SUBTYPE_INVOLVING_MEDIATION) ap.addSubTypeInvolvingMediation(properties);
		else if(code==Action.ADD_SUBTYPE_WITH_INTERMEDIATE_TYPE) ap.addSubTypeWithIntermediate(properties);
	}
	
	public enum Action {CREATE_FORMAL_ASSOCIATIONS, ADD_SUBTYPE_INVOLVING_MEDIATION, ADD_SUBTYPE_WITH_INTERMEDIATE_TYPE, CREATE_GENERALIZATION_SET}
	
	public void setCreateFormalAssocs(ArrayList<ArrayList<Property>> binFormalCombo){
		code = Action.CREATE_FORMAL_ASSOCIATIONS;
		this.matrix = binFormalCombo;
	}
	
	public void setAddSubTypeInvolvingMediation(ArrayList<Property> properties){
		code = Action.ADD_SUBTYPE_INVOLVING_MEDIATION;
		this.properties=properties;
	}
	
	public void setAddSubTypeWithIntermediate(ArrayList<Property> properties){
		code = Action.ADD_SUBTYPE_WITH_INTERMEDIATE_TYPE;
		this.properties=properties;
	}
		
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CREATE_FORMAL_ASSOCIATIONS) {
			for(ArrayList<Property> list: matrix){
				if (list.size()==2){
					result+= "Create a formal association between "+list.get(0).getType().getName()+" and "+list.get(1).getType().getName()+"\n";
				}
			}					
		} else if(code==Action.ADD_SUBTYPE_INVOLVING_MEDIATION){
			for(Property p: properties){
				result+= "Add Subtype to "+ap.getType().getName()+" and connect the Subtype to "+p.getType().getName()+" through the mediation"+"\n";
			}
			if (properties.size()>1) result+= "Create a generalization set between all the Subtypes created to the type "+ap.getType().getName()+"\n";
					
		}else if(code==Action.ADD_SUBTYPE_WITH_INTERMEDIATE_TYPE){
			for(Property p: properties){
				result+= "Add an intermediate type and a Subtype to "+ap.getType().getName()+" and connect the Subtype to "+p.getType().getName()+" through the mediation"+"\n";
			}
			if (properties.size()>1) result+= "Create a generalization set between all the Subtypes created to the intermediate type "+ap.getType().getName()+"\n";			
		} 
		
		return result;
	}
}
