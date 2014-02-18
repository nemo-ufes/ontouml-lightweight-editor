package br.ufes.inf.nemo.antipattern.wizard.binover;

import RefOntoUML.Association;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;
import br.ufes.inf.nemo.antipattern.wizard.AntiPatternAction;

public class BinOverAction extends AntiPatternAction<BinOverOccurrence>{

	private BinaryPropertyValue reflexivityValue, symmetryValue, transitivityValue, cyclicityValue;
	private Class<? extends Association> newStereotype;
	
	public BinOverAction(BinOverOccurrence ap) 
	{
		super(ap);
	}

	public enum Action { SET_REFLEXIVITY, SET_SYMMETRY, SET_TRANSITIVITY, SET_CYCLICITY, SET_DISJOINTNESS, CHANGE_STEREOTYPE }
	
	public void setReflexivity(BinaryPropertyValue value){
		code = Action.SET_REFLEXIVITY;
		if(value==BinaryPropertyValue.REFLEXIVE || value==BinaryPropertyValue.IRREFLEXIVE || value==BinaryPropertyValue.NON_REFLEXIVE)
			reflexivityValue = value;
		else
			reflexivityValue = BinaryPropertyValue.NONE;
	}
	
	public void setSymmetry(BinaryPropertyValue value){
		code = Action.SET_SYMMETRY;
		if(value==BinaryPropertyValue.SYMMETRIC || value==BinaryPropertyValue.ASYMMETRIC || value==BinaryPropertyValue.NON_SYMMETRIC)
			symmetryValue = value;
		else
			symmetryValue = BinaryPropertyValue.NONE;
	}
	
	public void setTransitivity(BinaryPropertyValue value){
		code = Action.SET_TRANSITIVITY;
		
		if(value==BinaryPropertyValue.TRANSITIVE || value==BinaryPropertyValue.INTRANSITIVE || value==BinaryPropertyValue.NON_TRANSITIVE)
			transitivityValue = value;
		else
			transitivityValue = BinaryPropertyValue.NONE;
		
	}
	
	public void setCyclicity(BinaryPropertyValue value){
		code = Action.SET_CYCLICITY;
		
		if(value==BinaryPropertyValue.CYCLIC || value==BinaryPropertyValue.ACYCLIC || value==BinaryPropertyValue.NON_CYCLIC)
			cyclicityValue = value;
		else
			cyclicityValue = BinaryPropertyValue.NONE;
	}
	
	public void setDisjointness(){
		code = Action.SET_DISJOINTNESS;
	}
	
	public void setChangeStereortype(Class<? extends Association> newStereotype){
		code = Action.CHANGE_STEREOTYPE;
		this.newStereotype = newStereotype;
	}
	
	@Override
	public void run() 
	{
		
		if(code==Action.SET_DISJOINTNESS)
			ap.makeEndsDisjoint();
		if(code==Action.SET_REFLEXIVITY)
			ap.generateReflexivityOCL(reflexivityValue);
	}
	
	@Override
	public String toString(){
		String result = "";
		if(code==Action.SET_REFLEXIVITY)
			result = "Create OCL constraint: Enforce "+reflexivityValue;
		else if(code==Action.SET_SYMMETRY)
			result = "Create OCL constraint: Enforce "+symmetryValue;
		else if(code==Action.SET_TRANSITIVITY)
			result = "Create OCL constraint: Enforce "+transitivityValue;
		else if(code==Action.SET_CYCLICITY)
			result = "Create OCL constraint: Enforce "+cyclicityValue;
		else if(code==Action.SET_DISJOINTNESS)
			result = "Modify Model: Enforce disjointness between "+ap.getSource().getName()+" and "+ap.getTarget().getName();
		else if(code==Action.CHANGE_STEREOTYPE)
			result = "Modify Association : Change "+ap.getAssociation().getName()+" stereotype from "+BinOverWizard.getStereotypeName(ap.getAssociation().getClass())+" to "+
					 BinOverWizard.getStereotypeName(newStereotype);
		
		return result;
	}

	public BinaryPropertyValue getReflexivityValue() {
		return reflexivityValue;
	}

	public BinaryPropertyValue getSymmetryValue() {
		return symmetryValue;
	}

	public BinaryPropertyValue getTransitivityValue() {
		return transitivityValue;
	}

	public BinaryPropertyValue getCyclcityValue() {
		return cyclicityValue;
	}

	public Class<? extends Association> getNewStereotype() {
		return newStereotype;
	}

}
