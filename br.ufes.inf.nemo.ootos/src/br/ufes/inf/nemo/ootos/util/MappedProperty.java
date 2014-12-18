package br.ufes.inf.nemo.ootos.util;

import RefOntoUML.NamedElement;

public class MappedProperty {
	private NamedElement property;
	private String generatedName;
	private MappedProperty mappedAsSubPropertyOf;
	private boolean isAbstract = false; 
	
	public MappedProperty(NamedElement _property, String _generatedName) {
		property = _property;
		generatedName = _generatedName;
	}
	
	public MappedProperty(NamedElement _property, String _generatedName, boolean _isAbstract) {
		this(_property, _generatedName);
		isAbstract = _isAbstract;
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

	public MappedProperty getMappedAsSubPropertyOf() {
		return mappedAsSubPropertyOf;
	}
	
	public void setMappedAsSubPropertyOf(MappedProperty mappedAsSubPropertyOf) {
		this.mappedAsSubPropertyOf = mappedAsSubPropertyOf;
	}
	
	public boolean isMappedAsSubPropertyOf() {
		if(mappedAsSubPropertyOf == null){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isAbstract() {
		return isAbstract;
	}
}
