package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import java.util.ArrayList;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class HetCollAction extends AntiPatternAction<HetCollOccurrence>{

	public enum Action { CHANGE_TO_COMPONENTOF, CHANGE_TO_SUBCOLLECTIONOF_AND_MEMBEROF, MERGE_MEMBEROF }
	
	private ArrayList<Property> changeToMemberOfList;
	private ArrayList<Property> changeToSubCollectionOfList;
	
	public HetCollAction(HetCollOccurrence ap) {
		super(ap);
	}
	
	public void setChangeAllToComponentOf() {
		code = Action.CHANGE_TO_COMPONENTOF;
	}
	
	public void setMergeMemberOf() {
		code = Action.MERGE_MEMBEROF;
	}
	
	public void setChangeToSubCollectionOfAndMemberOf (ArrayList<Property> changeToSubCollectionOfList, ArrayList<Property> changeToMemberOfList) {
		code = Action.CHANGE_TO_SUBCOLLECTIONOF_AND_MEMBEROF;
		this.changeToSubCollectionOfList = changeToSubCollectionOfList;
		this.changeToMemberOfList = changeToMemberOfList;	
	}
	
	@Override
	public void run() 	{
		if(code==Action.CHANGE_TO_COMPONENTOF) 
			ap.changeToComponentOf();
		else if(code==Action.CHANGE_TO_SUBCOLLECTIONOF_AND_MEMBEROF) 
			ap.changeToSubCollectionOf(changeToSubCollectionOfList,changeToMemberOfList);
		else if(code==Action.MERGE_MEMBEROF) 
			ap.mergeToMemberOf();
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if (code == Action.CHANGE_TO_COMPONENTOF)
		{
			result += "Change nature: "+OntoUMLNameHelper.getTypeAndName(ap.getWhole(), true, true)+" (new nature: functional complex)\n";
			for (Property p : ap.getMemberProperties()) {
				result += "Change association: "+OntoUMLNameHelper.getCompleteName(p.getAssociation())+" (new stereotype: componentOf)\n";
				result += "Change nature: "+OntoUMLNameHelper.getTypeAndName(p.getType(), true, true)+" (new nature: functional complex)\n";
			}
			for (Property p : ap.getCollectionProperties()) {
				result += "Change association: "+OntoUMLNameHelper.getCompleteName(p.getAssociation())+" (new stereotype: componentOf)\n";
				result += "Change nature: "+OntoUMLNameHelper.getTypeAndName(p.getType(), true, true)+" (new nature: functional complex)\n";
			}
		}
		
		else if(code==Action.CHANGE_TO_SUBCOLLECTIONOF_AND_MEMBEROF) 
		{
			result += "Change nature: "+OntoUMLNameHelper.getTypeAndName(ap.getWhole(), true, true)+" (new nature: collection)\n";

			for(Property p: changeToSubCollectionOfList) { 
				result += "Change association: "+OntoUMLNameHelper.getCompleteName(p.getAssociation())+" (new stereotype: subCollectionOf)\n";
				result += "Change nature: "+OntoUMLNameHelper.getTypeAndName(p.getType(), true, true)+" (new nature: collection)\n";
			}
			
			for(Property p: changeToMemberOfList) { 
				result += "Change association: "+OntoUMLNameHelper.getCompleteName(p.getAssociation())+" (new stereotype: memberOf)\n";
			}
		}
		
		else if(code==Action.MERGE_MEMBEROF) 
		{
			result += 	"Create Class: NewMemberParent (supertype of all member types)\n"+
						"Create Association: memberOf ("+OntoUMLNameHelper.getTypeAndName(ap.getWhole(), true, true)+" -> NewMemberParent)\n";

			for(Property p: ap.getMemberProperties()) { 
				result += "Remove Association: "+OntoUMLNameHelper.getCommonName(p.getAssociation())+"\n";
			}
		}
		
		return result;
	}


}
