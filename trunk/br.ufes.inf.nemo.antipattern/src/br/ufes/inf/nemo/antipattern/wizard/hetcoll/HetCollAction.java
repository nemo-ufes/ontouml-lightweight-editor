package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import java.util.ArrayList;

import RefOntoUML.Association;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class HetCollAction extends AntiPatternAction<HetCollOccurrence>{

	public ArrayList<Association> partOfList = new ArrayList<Association>();
	
	public HetCollAction(HetCollOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { CHANGE_ALL_TO_COMPONENTOF, CHANGE_ALL_TO_COLLECTION_AND_SUBCOLLECTIONOF, CHANGE_ALL_TO_ONE_SUPER_MEMBER }
	
	public void setChangeAllToComponentOf(ArrayList<Association> partOfList)
	{
		code = Action.CHANGE_ALL_TO_COMPONENTOF;
		this.partOfList = partOfList;
	}
	
	public void setChangeAllToCollectionAndSubCollectionOf (ArrayList<Association> partOfList) 
	{
		code = Action.CHANGE_ALL_TO_COLLECTION_AND_SUBCOLLECTIONOF;
		this.partOfList = partOfList;
	}

	public void setChangeAllToOneSuperMember(ArrayList<Association> partOfList) {
		code = Action.CHANGE_ALL_TO_ONE_SUPER_MEMBER;
		this.partOfList = partOfList;		
	}
	
	@Override
	public void run() 
	{
		if(code==Action.CHANGE_ALL_TO_COMPONENTOF) ap.changeAllToComponentOf(partOfList);
		else if(code==Action.CHANGE_ALL_TO_COLLECTION_AND_SUBCOLLECTIONOF) ap.changeAllToCollectionAndSubCollectionOf(partOfList);		
		else if(code==Action.CHANGE_ALL_TO_ONE_SUPER_MEMBER) ap.changeAllToOneSuperMember(partOfList);
	}
	
	@Override
	public String toString(){
		String result = new String();
		
		if (code == Action.CHANGE_ALL_TO_COMPONENTOF){
			result += "Change all partOfs relations to <<componentOf>>";
		}
		if(code==Action.CHANGE_ALL_TO_COLLECTION_AND_SUBCOLLECTIONOF) {
			result += "Change all partOfs relations to <<subCollectionOf>>"+"\n";
			result += "Change all parts to <<Collection>>";
		}
		if(code==Action.CHANGE_ALL_TO_ONE_SUPER_MEMBER) {
			result += "Create a type, named MemberPart, and stereotype it accordingly"+"\n";
			result += "Create a «memberOf», named newMemberOf, between the Whole and the new MemberPart"+"\n";
			result += "Create generalizations from all the parts to the new MemberPart";
		}
		
		return result;
	}

}
