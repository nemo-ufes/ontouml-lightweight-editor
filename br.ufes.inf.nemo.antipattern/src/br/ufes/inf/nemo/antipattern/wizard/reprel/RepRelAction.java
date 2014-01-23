package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class RepRelAction extends AntiPatternAction<RepRelOccurrence>{

	public RepRelAction(RepRelOccurrence ap) {
		super(ap);
	}
	
	int n;
	ArrayList<Mediation> mediationList = new ArrayList<Mediation>();
	
	public enum Action {CHANGE_UPPER_MULT, INCLUDE_QUALITIES, CREATE_INVARIANT_WITH_QUALITIES, CREATE_INVARIANT}

	@Override
	public void run() {
		
		if(code==Action.CHANGE_UPPER_MULT) ; //ap.changeToRoleOrRoleMixin(rigidType);
		else if(code==Action.INCLUDE_QUALITIES) ; //ap.createRoleSubType(rigidType,mediation);
		else if(code==Action.CREATE_INVARIANT_WITH_QUALITIES) ; //ap.changeToMode(rigidType,mediation);
		else if(code==Action.CREATE_INVARIANT)	; //ap.setBothReadOnly(mediation);
	}
	
	public void setChangeUpperMult(Integer n){
		code = Action.CHANGE_UPPER_MULT;
		this.n = n;
	}
	
	public void setIncludeQualities(Classifier rigidType){
		code = Action.INCLUDE_QUALITIES;
	}
	
	public void setCreateInvariantWithQualities(ArrayList<Mediation> mediationList){
		code = Action.CREATE_INVARIANT_WITH_QUALITIES;
		this.mediationList = mediationList;
	}
	
	public void setCreateInvariant(ArrayList<Mediation> mediationList){
		code = Action.CREATE_INVARIANT;
		this.mediationList = mediationList;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CHANGE_UPPER_MULT);
			//result = "Change Class' Stereotype: "+ap.getParser().getStringRepresentation(rigidType)+" to «role» or «roleMixin»";
		else if(code==Action.INCLUDE_QUALITIES);
			//result = "Change Class' Stereotype: "+ap.getParser().getStringRepresentation(rigidType)+" to «mode»"+
			//		 "\nChange Association's Stereotype: "+ap.getParser().getStringRepresentation(mediation)+" to «characterization»";
		else if(code==Action.CREATE_INVARIANT_WITH_QUALITIES);
			//result ="Create Class: Subtype of "+ap.getParser().getStringRepresentation(rigidType)+
			//   		"\nChange Association's Target End's Type: "+ap.getParser().getStringRepresentation(mediation);
		else if(code==Action.CREATE_INVARIANT);
			//result = "Modify meta-property: isReadOnly=true for "+ap.getParser().getStringRepresentation(mediation);
		return result; 
	}

}
