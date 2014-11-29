package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.checkers.ui.MeronymicCycleDialog;

public class MeronymicCycleError extends MeronymicError<ArrayList<Property>> {

	ArrayList<Meronymic> meronymicCycle;
	ArrayList<Type> typeCycle;
	ArrayList<Action> actions;
	
	public MeronymicCycleError(OntoUMLParser parser, ArrayList<Property> propertyCycle) {
		super(parser,new ArrayList<Property>(propertyCycle));
		meronymicCycle = new ArrayList<Meronymic>();
		typeCycle = new ArrayList<Type>();
		
		typeCycle.add(propertyCycle.get(0).getOpposite().getType());
		
		for (Property p : propertyCycle) {
			meronymicCycle.add((Meronymic) p.getAssociation());
			typeCycle.add(p.getType());
		}
		
		actions = new ArrayList<Action>();
		for (Meronymic m : meronymicCycle) {
			actions.add(new Action(m));
		}
		
	}
	
	public ArrayList<Property> getPropertyCycle(){
		return element;
	}
	
	public ArrayList<Meronymic> getMeronymicCycle() {
		return meronymicCycle;
	}

	public ArrayList<Type> getTypeCycle() {
		return typeCycle;
	}

	@Override
	public String getDescription(){
		
		String result = getPropertyCycle().get(0).getOpposite().getType().getName();
		
		for (int i = 0; i < getPropertyCycle().size(); i++) {
			Classifier part = (Classifier) getPropertyCycle().get(i).getType();
			Classifier nextWhole;
			
			if(i<getPropertyCycle().size()-1){
				result += ", ";
				nextWhole = (Classifier) getPropertyCycle().get(i+1).getOpposite().getType();
			}
			else{
				result += " and ";
				nextWhole = (Classifier) getPropertyCycle().get(0).getOpposite().getType();
			}
			
			result += part.getName();
			
			if(nextWhole.allParents().contains(part))
				result += " (Sub: "+nextWhole.getName()+")";
			if(nextWhole.allChildren().contains(part))
				result += " (Super: "+nextWhole.getName()+")";
		}

		return result;
	}
	
	@Override
	public String getType() {
		return "Part-Whole Cycle";
	}

	@Override
	public JDialog createDialog(JDialog dialog) {
		return new MeronymicCycleDialog(dialog, this);
	}

	@Override
	public Fix fix() {
		for (Action action : getValidActions()) {
			if(action.isDelete())
				fix.addAll(fixer.deleteElement(action.m));
			else if(action.isReverse())
				fix.addAll(fixer.invertEnds(action.m));
			else if(action.isChange())
				fix.addAll(fixer.changeRelationStereotypeTo(action.m, action.stereotype));
		}
		return fix;
	}
	
	@Override
	public boolean hasAction(){
		return getValidActions().size()>0;
	}
	
	public Action getAction(Meronymic m){
		for (Action action : actions) {
			if(action.m.equals(m))
				return action;
		}
		
		return null;
	}
	
	public ArrayList<Action> getValidActions(){
		ArrayList<Action> validActions = new ArrayList<Action>();
		
		for (Action a : actions) {
			if(a.isValid())
				validActions.add(a);
		}
		
		return validActions;
	}
	
	public boolean isDelete(Meronymic m){
		return getAction(m).isDelete();
	}
	
	public boolean isReverse(Meronymic m){
		return getAction(m).isReverse();
	}
	
	public boolean isChange(Meronymic m){
		return getAction(m).isChange();
	}
	
	public RelationStereotype getSelectedStereotype(Meronymic m){
		return getAction(m).stereotype;
	}
	
	public boolean hasValidAction(){
		for (Action a : actions) {
			if(a.isValid())
				return true;
		}
		
		return false;
	}
	
	public void saveValuesFor(Meronymic m, boolean isDelete, boolean isReverse, boolean isChange, RelationStereotype stereotype){
		Action a = getAction(m);
		
		if(isDelete && !isReverse && !isChange){
			a.setDelete();
		}
		else if(!isDelete && isReverse && !isChange){
			a.setReverse();
		}
		else if(!isDelete && !isReverse && isChange){
			a.setChange(stereotype);
		}
		else{
			a.setNone();
		}
		
	}

	public void resetAllActions() {
		for (Action a : actions) {
			a.setNone();
		}
		
	}

	public boolean hasValidAction(Meronymic m) {
		return getAction(m).isValid();
	}
}

class Action{
	enum Code {REVERSE, DELETE, CHANGE};
	Meronymic m;
	Code code;
	RelationStereotype stereotype;
	
	public Action(Meronymic m){
		this.m = m;
		this.code = null;
		stereotype = null;
	}
	
	public void setDelete(){
		code = Code.DELETE;
	}
	
	public void setReverse(){
		code = Code.REVERSE;
	}
	
	public void setChange(RelationStereotype stereotype){
		code = Code.CHANGE;
		this.stereotype = stereotype;
	}
	
	public void setNone(){
		code = null;
	}
	
	public boolean isValid(){
		return code!=null;
	}
	
	public boolean isDelete(){
		return code==Code.DELETE;
	}
	
	public boolean isReverse(){
		return code==Code.REVERSE;
	}
	
	public boolean isChange(){
		return code==Code.CHANGE;
	}
}
