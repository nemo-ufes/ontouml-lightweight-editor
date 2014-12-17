package br.ufes.inf.nemo.ootos.util;

import RefOntoUML.NamedElement;

public class MappedProperty {
	private NamedElement property;
	private String generatedName;
	
	public MappedProperty(NamedElement _property, String _generatedName) {
		property = _property;
		generatedName = _generatedName;
	}
	
	@SuppressWarnings("unused")
	private MappedProperty() {}
	
	public String getGeneratedName() {
		return generatedName;
	}
	
	public NamedElement getProperty() {
		return property;
	}
	
	@Override
	public String toString() {
		return generatedName;
	}
}
