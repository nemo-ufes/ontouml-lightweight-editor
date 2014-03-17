package br.ufes.inf.nemo.antipattern.wizard.depphase;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class DepPhaseAction extends AntiPatternAction<DepPhaseOccurrence>{

	public enum Action {CHANGE_TO_ROLE, ADD_SUBTYPE_MOVE_MEDIATION, ADD_SUPERTYPE_MOVE_MEDIATION};
	
	public Property propertyToMove;
	
	public DepPhaseAction(DepPhaseOccurrence ap) {
		super(ap);
	}

	@Override
	public void run() {
		if(code==Action.CHANGE_TO_ROLE) ap.changeToRole();
		else if(code==Action.ADD_SUBTYPE_MOVE_MEDIATION) ap.separateRelationalDependencyOnSubtype(propertyToMove);
		else if(code==Action.ADD_SUPERTYPE_MOVE_MEDIATION) ap.separateRelationalDependencyOnSupertype(propertyToMove);
	}
	
	
	public void setChangeToRole(){
		code = Action.CHANGE_TO_ROLE;
		propertyToMove = null;
	}
	
	public void setAddSubtypeAndMoveMediation(Property p){
		code = Action.ADD_SUBTYPE_MOVE_MEDIATION;
		propertyToMove = p;
	}
	
	public void setAddSupertypeAndMoveMediation(Property p){
		code = Action.ADD_SUPERTYPE_MOVE_MEDIATION;
		propertyToMove = p;
	}
		
	@Override
	public String toString(){
		String result = new String();
		if(code==Action.CHANGE_TO_ROLE) 
			result += "Change Class: Change stereotype of <"+ap.getPhase().getName()+"> to «Role»";
		else if(code==Action.ADD_SUBTYPE_MOVE_MEDIATION) 
			result += 	"Add Class: «Role» subtype of <"+ap.getPhase().getName()+">" +
						"\nChange Relation: new mediated end of Mediation <"+propertyToMove.getAssociation().getName()+">";
		else if(code==Action.ADD_SUPERTYPE_MOVE_MEDIATION) 
			result += 	"Add Class: «Role» supertype of <"+ap.getPhase().getName()+">" +
						"\nChange Relation: new mediated end of Mediation <"+propertyToMove.getAssociation().getName()+">";
		else
			result = "Invalid action!";
		
		
		return result;
	}
}
