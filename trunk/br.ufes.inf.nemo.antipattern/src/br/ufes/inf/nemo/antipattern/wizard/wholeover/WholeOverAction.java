package br.ufes.inf.nemo.antipattern.wizard.wholeover;

import java.util.ArrayList;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class WholeOverAction extends AntiPatternAction<WholeOverOccurrence>{

	public WholeOverAction(WholeOverOccurrence ap) {
		super(ap);
	}
	
	ArrayList<ArrayList<Property>> partEndLists;
	
	public enum Action {DISJOINT, EXCLUSIVE}

	@Override
	public void run() {
		
		if(code==Action.DISJOINT){}
			//ap.changeToRoleOrRoleMixin(rigidType);
		else if(code==Action.EXCLUSIVE){}

	}
	
	public void setExclusive(ArrayList<ArrayList<Property>> partEndLists){
		code = Action.EXCLUSIVE;
		this.partEndLists = partEndLists;
	}
	
	public void setDisjotin(ArrayList<ArrayList<Property>> partEndLists){
		code = Action.DISJOINT;
		this.partEndLists = partEndLists;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.EXCLUSIVE)
			result = "Create OCL constraint: Exclusive parts";
		else if(code==Action.DISJOINT)
			result = "Create OCL constraint: Disjoint parts";
		
		return result; 
	}

}
