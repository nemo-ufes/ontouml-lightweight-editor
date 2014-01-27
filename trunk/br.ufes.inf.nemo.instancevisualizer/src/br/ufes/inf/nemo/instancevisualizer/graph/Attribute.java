package br.ufes.inf.nemo.instancevisualizer.graph;

import java.util.ArrayList;

public class Attribute {
	private String name;
	private String value;
	
	public Attribute(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean hasName(String name) {
		if(this.name.equals(name)) {
			return true;
		}else{
			return false;
		}
	}
}
