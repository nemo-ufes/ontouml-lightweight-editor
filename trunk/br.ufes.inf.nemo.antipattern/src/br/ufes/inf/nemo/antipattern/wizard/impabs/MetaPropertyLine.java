package br.ufes.inf.nemo.antipattern.wizard.impabs;

import java.util.HashMap;

import RefOntoUML.Classifier;

public class MetaPropertyLine extends ImpAbsLine {

	private HashMap<String,Boolean> metaPropertiesValues;
	
	public MetaPropertyLine (Classifier source, Classifier target, HashMap<String,Boolean> metaPropertiesValues)	throws Exception {
		
		super(source,target);
		
		if(metaPropertiesValues!=null)
			this.metaPropertiesValues = metaPropertiesValues;
	}
	
	public boolean getValue(String metaPropertyName){
		if(metaPropertiesValues.get(metaPropertyName)==null)
			return false;
		else
			return metaPropertiesValues.get(metaPropertyName);
	}


}
