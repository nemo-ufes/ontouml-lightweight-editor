package br.ufes.inf.nemo.oled.model;

import java.util.ArrayList;

import RefOntoUML.Element;

public class InferenceList {

	private ArrayList<Element> inferredElement;
	
	public InferenceList(ArrayList<Element> inferredElements){
		this.inferredElement = inferredElements;
	}
	
	public InferenceList(){
		this.inferredElement = new ArrayList<>();
	}

	public ArrayList<Element> getInferredElements() {
		return inferredElement;
	}

	public void setInferredElements(ArrayList<Element> inferredElements) {
		this.inferredElement = inferredElements;
	}
	
	public void addAll (ArrayList<Element> inferredElements){
		this.inferredElement.addAll(inferredElements);
	}
}
