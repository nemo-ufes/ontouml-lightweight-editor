package br.ufes.inf.nemo.meronymic_validation.forbidden;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.MixinClass;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.memberOf;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ForbiddenMemberOf extends ForbiddenMeronymic<memberOf> {
	
	enum Action {REMOVE_MEMBEROF, CHANGE_ALL_TO_SUBCOLLECTIONOF, 
				MAKE_MEMBEROF_VALID, CHANGE_ALL_TO_COMPONENTOF,
				CHANGE_ALL_TO_SUBQUANTITYOF}


	private memberOf memberOfToRemove;
	
	public ForbiddenMemberOf(memberOf m) {
		super(m);
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
	public Fix runFix() {
		
		if(action == Action.REMOVE_MEMBEROF)
			return removeMemberOf();
		if(action == Action.CHANGE_ALL_TO_COMPONENTOF)
			return changeAllToComponentOf();
		if(action == Action.CHANGE_ALL_TO_SUBCOLLECTIONOF)
			return changeAllToSubCollectionOf();
		if(action == Action.CHANGE_ALL_TO_SUBQUANTITYOF)
			return changeAllToSubQuantityOf();
		if(action == Action.MAKE_MEMBEROF_VALID)
			return makeMemberOfValid();
		
		return null;
			
	}
	
	public void setRemoveMemberOf(memberOf memberOfToRemove){
		this.memberOfToRemove = memberOfToRemove;
		this.action = Action.REMOVE_MEMBEROF;
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

	private Fix removeMemberOf(){
		OutcomeFixer fixer = new OutcomeFixer(getRootPackage());
		return fixer.deleteElement(memberOfToRemove);
	}
	
	private Fix changeAllTo(RelationStereotype stereotype){
		OutcomeFixer fixer = new OutcomeFixer(getRootPackage());
		Fix fix = new Fix();
		
		for (Property p : path) {
			fix.addAll(fixer.changeRelationStereotypeTo(p.getAssociation(), stereotype));
		}
		
		fix.addAll(fixer.changeRelationStereotypeTo(meronymic, stereotype));
		
		return fix;
	}
	
	private Fix changeAllToSubCollectionOf(){
		Fix fix = new Fix();
		ArrayList<Classifier> visited = new ArrayList<Classifier>();
		fix.addAll(changeNature((Classifier) OntoUMLParser.getPartEnd(meronymic).getType(), visited, ClassStereotype.COLLECTIVE));
		fix.addAll(changeAllTo(RelationStereotype.SUBCOLLECTIONOF));
		
		return fix;
	}
	
	private Fix changeAllToComponentOf(){
		ArrayList<Classifier> visited;
		Fix fix = new Fix();
	
		for (Property p : path) {
			visited = new ArrayList<Classifier>();
			fix.addAll(changeNature((Classifier)p.getType(), visited, ClassStereotype.KIND));
		}
		
		visited = new ArrayList<Classifier>();
		fix.addAll(changeNature((Classifier) OntoUMLParser.getWholeEnd(meronymic).getType(), visited, ClassStereotype.KIND));
		
		fix.addAll(changeAllTo(RelationStereotype.COMPONENTOF));
		
		return fix;
	}
	
	private Fix changeAllToSubQuantityOf(){
		ArrayList<Classifier> visited;
		Fix fix = new Fix();
	
		for (Property p : path) {
			visited = new ArrayList<Classifier>();
			fix.addAll(changeNature((Classifier)p.getType(), visited, ClassStereotype.QUANTITY));
		}
		
		visited = new ArrayList<Classifier>();
		fix.addAll(changeNature((Classifier) OntoUMLParser.getWholeEnd(meronymic).getType(), visited, ClassStereotype.QUANTITY));
		
		fix.addAll(changeAllTo(RelationStereotype.SUBQUANTITYOF));
		
		return fix;
	}
	
	private Fix makeMemberOfValid(){
		OutcomeFixer fixer = new OutcomeFixer(getRootPackage());
		Fix fix = new Fix();
		
		for (int i = 0; i < path.size()-1; i++) {
			Property p = path.get(i);
			fix.addAll(fixer.changeRelationStereotypeTo(p.getAssociation(), RelationStereotype.SUBCOLLECTIONOF));
		}
		
		return fix;
	}
	
	private Fix changeNature(Classifier c, ArrayList<Classifier> visited, ClassStereotype newNature){
		
		if(newNature!=ClassStereotype.KIND && newNature!=ClassStereotype.COLLECTIVE && newNature!=ClassStereotype.QUANTITY)
			return null;
		
		Fix fix = new Fix();
		visited.add(c);
		
		OutcomeFixer fixer = new OutcomeFixer(getRootPackage());
		
		if(c instanceof Kind || c instanceof Quantity || c instanceof Collective){
			if(fixer.getClassStereotype(c)==newNature)
				return fix;
			else
				return fixer.changeClassStereotypeTo(c, newNature);
		}
			
		
		if(c instanceof SubKind || c instanceof Role || c instanceof Phase){
			for (Classifier parent : c.parents()) {
				if(!visited.contains(parent))
					fix.addAll(changeNature(parent, visited, newNature));
			}
		}
		
		if(c instanceof MixinClass){
			for (Classifier child : c.children()) {
				if(!visited.contains(child))
					fix.addAll(changeNature(child, visited, newNature));
			}
		}
		
		return fix;
	}
	
	
	
}
