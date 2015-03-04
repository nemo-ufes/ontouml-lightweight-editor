package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.uml2.uml;

import org.eclipse.uml2.uml.Property;

import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.Factory;
import br.ufes.inf.nemo.ootos.util.MappingProperties;

public class PropertyFactory extends Factory{
	Property property;
	
	public PropertyFactory(MappingProperties mappingProperties, Property property) {
		super(mappingProperties);
		this.property = property;
	}
}
