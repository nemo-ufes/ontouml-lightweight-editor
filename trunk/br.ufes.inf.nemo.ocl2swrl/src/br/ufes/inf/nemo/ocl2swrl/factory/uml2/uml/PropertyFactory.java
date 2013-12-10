package br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml;

import org.eclipse.uml2.uml.Property;

import br.ufes.inf.nemo.ocl2swrl.factory.Factory;

public class PropertyFactory extends Factory{
	Property property;
	
	public PropertyFactory(Property property) {
		this.property = property;
	}
}
