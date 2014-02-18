package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class OverlappingAction extends AntiPatternAction<OverlappingOccurrence>{

	OverlappingGroup variation;
	
	public OverlappingAction(OverlappingOccurrence occurrence, OverlappingGroup variation) {
		super(occurrence);
		this.variation = variation;
	}
	
	private ArrayList<Property> partEndList;
	
	public enum Action {DISJOINT, EXCLUSIVE}

	@Override
	public void run() {
		
		if(code==Action.DISJOINT){
			variation.makeEndsDisjoint(ap, partEndList);
		}
		else if(code==Action.EXCLUSIVE){
			variation.makeEndsExclusive(ap, partEndList);
		}

	}
	
	public void setExclusive(ArrayList<Property> partEndList){
		code = Action.EXCLUSIVE;
		this.partEndList = partEndList;
	}
	
	public void setDisjoint(ArrayList<Property> partEndList){
		code = Action.DISJOINT;
		this.partEndList = partEndList;
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if(code==Action.EXCLUSIVE)
			result = "Create OCL constraint: Exclusive parts (";
		else if(code==Action.DISJOINT)
			result = "Modify model: Disjoint parts (";
		
		for (int i = 0; i < partEndList.size(); i++) {
			if(i!=0)
				result += ", ";
			
			Property partEnd = partEndList.get(i);
			result+=partEnd.getType().getName();
			
			if(partEnd.getName()==null || partEnd.getName().trim().isEmpty())
				result += " (unnamed)";
			else
				result += " ("+partEnd.getName()+")";
			
		}
		result += ")";
		
		return result; 
	}
	
	public boolean isDisjoint(){
		if(getCode()==Action.DISJOINT)
			return true;
		return false;
	}
	
	public boolean isExclusive(){
		if(getCode()==Action.EXCLUSIVE)
			return true;
		else
			return false;
	}

	public ArrayList<Property> getPartEndsLists() {
		return partEndList;
	}

}
