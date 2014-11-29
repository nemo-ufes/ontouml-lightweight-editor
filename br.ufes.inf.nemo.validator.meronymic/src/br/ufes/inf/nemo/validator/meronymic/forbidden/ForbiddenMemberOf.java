package br.ufes.inf.nemo.validator.meronymic.forbidden;

import javax.swing.JDialog;

import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.memberOf;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.forbidden.ui.ForbiddenMemberOfDialog;
import br.ufes.inf.nemo.validator.meronymic.ui.FixDialog;

public class ForbiddenMemberOf extends ForbiddenMeronymic<memberOf> {
	
	enum Action {REMOVE, REVERSE, CHANGE_ALL_TO_SUBCOLLECTIONOF, 
				MAKE_MEMBEROF_VALID, CHANGE_ALL_TO_COMPONENTOF,
				CHANGE_ALL_TO_SUBQUANTITYOF}
	
	public ForbiddenMemberOf(memberOf m, OntoUMLParser parser) {
		super(m, parser);
	}

	@Override
	public String getDescription() {
		String result = "MemberOf is intransitive. Existing path: ";
		
		int i = 0;
		for (Property p : path) {
			if(i!=0)
				result += " -> ";
			result += OntoUMLNameHelper.getNameAndType(p);
			i++;
		}
		
		return result;
	}
	
	@Override
	public Fix fix() {
		
		if(action == Action.REMOVE)
			remove();
		else if(action == Action.REVERSE)
			reverse();
		else if(action == Action.CHANGE_ALL_TO_COMPONENTOF){
			makeFunctionalDerivationPath();
			changeFobbidenTo(RelationStereotype.COMPONENTOF);
		}
		else if(action == Action.CHANGE_ALL_TO_SUBCOLLECTIONOF){
			makeSubCollectionDerivationPath();
			changeFobbidenTo(RelationStereotype.SUBCOLLECTIONOF);
		}
		else if(action == Action.CHANGE_ALL_TO_SUBQUANTITYOF){
			makeSubQuantityDerivationPath();
			changeFobbidenTo(RelationStereotype.SUBQUANTITYOF);
		}
		else if(action == Action.MAKE_MEMBEROF_VALID)
			makeMembershipDerivationPath();
		
		return fix;
			
	}

	public void setRemove(Meronymic meronymic){
		this.relationToRemove = meronymic;
		this.action = Action.REMOVE;
	}
	
	public void setReverse(Meronymic meronymic) {
		this.relationToReverse = meronymic;
		this.action = Action.REVERSE;
	}
	
	public void setChangeAllToSubCollectionOf(){
		this.action = Action.CHANGE_ALL_TO_SUBCOLLECTIONOF;
	}
	
	public void setChangeAllToComponentOf(){
		this.action = Action.CHANGE_ALL_TO_COMPONENTOF;
	}
	
	public void setChangeAllToSubQuantityOf(){
		this.action = Action.CHANGE_ALL_TO_SUBQUANTITYOF;
	}
	
	public void setMakeMemberOfValid(){
		this.action = Action.MAKE_MEMBEROF_VALID;
	}
	
	public void changeFobbidenTo(RelationStereotype stereotype){
		fix.addAll(fixer.changeRelationStereotypeTo(meronymic, stereotype));
	}
	
	@Override
	public FixDialog<?> createDialog(JDialog parent) {
		ForbiddenMemberOfDialog dialog = new ForbiddenMemberOfDialog(parent, this);
		dialog.setSize(600, 700);
		return dialog;
	}

	public boolean isRemove() {
		return action==Action.REMOVE;
	}
	
	public boolean isReverse() {
		return action==Action.REVERSE;
	}
	
	public boolean isChangeAllToSubCollectionOf() {
		return action==Action.CHANGE_ALL_TO_SUBCOLLECTIONOF;
	}
	
	public boolean isChangeAllToComponentOf() {
		return action==Action.CHANGE_ALL_TO_COMPONENTOF;
	}
	
	public boolean isChangeAllToSubQuantityOf() {
		return action==Action.CHANGE_ALL_TO_SUBQUANTITYOF;
	}
	
	public boolean isMakeMemberOfValid() {
		return action==Action.MAKE_MEMBEROF_VALID;
	}
}
