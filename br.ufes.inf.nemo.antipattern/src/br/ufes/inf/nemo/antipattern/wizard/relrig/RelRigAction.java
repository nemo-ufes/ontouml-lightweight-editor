package br.ufes.inf.nemo.antipattern.wizard.relrig;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class RelRigAction extends AntiPatternAction<RelRigOccurrence>{

	public RelRigAction(RelRigOccurrence ap) {
		super(ap);
	}
	
	private Classifier rigidType;
	private Mediation mediation;
	
	public enum Action {CHANGE_TO_ROLE_OR_ROLEMIXIN, ADD_ROLE_SUBTYPE, BOTH_READ_ONLY, CHANGE_TO_MODE}

	@Override
	public void run() {
		
		if(code==Action.CHANGE_TO_ROLE_OR_ROLEMIXIN)
			ap.changeToRoleOrRoleMixin(rigidType);
		else if(code==Action.CHANGE_TO_MODE)
			ap.changeToMode(rigidType,mediation);
		else if(code==Action.ADD_ROLE_SUBTYPE)
			ap.createRoleSubType(rigidType,mediation);
		else if(code==Action.BOTH_READ_ONLY)	
			ap.setBothReadOnly(mediation);
	}
	
	public void setChangeStereotypeToRole(Classifier rigidType){
		code = Action.CHANGE_TO_ROLE_OR_ROLEMIXIN;
		this.rigidType = rigidType;
	}
	
	public void setChangeStereotypeToMode(Classifier rigidType, Mediation mediation){
		code = Action.CHANGE_TO_MODE;
		this.rigidType = rigidType;
		this.mediation = mediation;
	}
	
	public void setAddRoleSubtype(Classifier rigidType, Mediation mediation){
		code = Action.ADD_ROLE_SUBTYPE;
		this.rigidType = rigidType;
		this.mediation = mediation;
	}
	
	public void setBothReadOnly(Mediation mediation){
		code = Action.BOTH_READ_ONLY;
		this.mediation = mediation;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.CHANGE_TO_ROLE_OR_ROLEMIXIN)
			result = "Change Class' Stereotype: "+ap.getParser().getStringRepresentation(rigidType)+" to «role» or «roleMixin»";
		else if(code==Action.CHANGE_TO_MODE)
			result = "Change Class' Stereotype: "+ap.getParser().getStringRepresentation(rigidType)+" to «mode»"+
					 "\nChange Association's Stereotype: "+ap.getParser().getStringRepresentation(mediation)+" to «characterization»";
		else if(code==Action.ADD_ROLE_SUBTYPE)
			result ="Create Class: Subtype of "+ap.getParser().getStringRepresentation(rigidType)+
			   		"\nChange Association's Target End's Type: "+ap.getParser().getStringRepresentation(mediation);
		else if(code==Action.BOTH_READ_ONLY)	
			result = "Modify meta-property isReadOnly to true for "+ap.getParser().getStringRepresentation(mediation)+" at the \""+mediation.getMemberEnd().get(0).getType().getName()+"\"'s side"; 
		return result; 
	}

}
