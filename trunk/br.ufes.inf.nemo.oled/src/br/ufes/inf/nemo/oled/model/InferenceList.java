package br.ufes.inf.nemo.oled.model;

import java.util.ArrayList;

import RefOntoUML.componentOf;

public class InferenceList {

	private ArrayList<componentOf> inferredComponentOf;
	
	public InferenceList(ArrayList<componentOf> inferredComponentOf){
		this.inferredComponentOf = inferredComponentOf;
	}
	
	public InferenceList(){
		this.inferredComponentOf = new ArrayList<>();
	}

	public ArrayList<componentOf> getInferredComponentOf() {
		return inferredComponentOf;
	}

	public void setDerivedCompositions(ArrayList<componentOf> inferredComponentOf) {
		this.inferredComponentOf = inferredComponentOf;
	}
	
	public void addAll (ArrayList<componentOf> inferredComponentOf){
		this.inferredComponentOf.addAll(inferredComponentOf);
	}
}
