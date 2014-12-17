package br.ufes.inf.nemo.ootos.util;

import java.util.HashMap;
import java.util.Set;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.NamedElement;
import RefOntoUML.Property;
import RefOntoUML.impl.PropertyImpl;
import RefOntoUML.parser.OntoUMLParser;

public class MappingProperties {
	private OntoUMLParser ontoParser;
	private HashMap<String, MappedProperty> propertyByAlias = new HashMap<String, MappedProperty>();
	private HashMap<String, MappedProperty> propertyByName = new HashMap<String, MappedProperty>();
	
	public MappingProperties(OntoUMLParser _ontoParser) {
		ontoParser = _ontoParser;
	}
	
	@SuppressWarnings("unused")
	private MappingProperties() {}
	
	public String getPropertyName(NamedElement property){
		String propertyName = "";
		String propertyAlias = ontoParser.getAlias(property);
		
		if(propertyByAlias.containsKey(propertyAlias)){
			MappedProperty mappedProperty = propertyByAlias.get(propertyAlias);
			propertyName = mappedProperty.getGeneratedName();
		}else{
			propertyName = generatePropertyName(property);
		}
		
		return propertyName;
	}

	private String generatePropertyName(NamedElement property) {
		String propertyName = property.getName();
		propertyName = propertyName.replace(" ", "");
		String propertyAlias = ontoParser.getAlias(property);
		propertyAlias = propertyAlias.replace(" ", "");
		
		String source = null;
		String target = null;
		String stereotype = "";
		
		if(property instanceof Association){
			EList<Property> memberEnds = ((Association) property).getMemberEnd();
			source = memberEnds.get(0).getName();
			target = memberEnds.get(1).getName();
			stereotype = ontoParser.getStereotype(property);
		}else{
			source = ((PropertyImpl)property).getClass_().getName();
			target = ((PropertyImpl)property).getType().getName();
		}
		
		if(property instanceof Association && (propertyName == null || propertyName.equals(""))){
			propertyName = stereotype + "." + source + "." + target;
		}else if(property instanceof PropertyImpl){
			propertyName = source + "." + propertyName;
		}
		
		if(propertyByName.containsKey(propertyName)){
			refactorProperty(propertyName);
			
			//concatena source e target no NOME da nova propriedade
			if(property instanceof Association){
				propertyName = propertyName + "." + source + "." + target;
			}else{
				
			}
		}
		
		if(propertyByName.containsKey(propertyName)){
			refactorProperty(propertyName);
			
			//concatena source e target no ALIAS da nova propriedade
			if(property instanceof Association){
				propertyName = propertyAlias + "." + source + "." + target;
			}else{
				
			}
		}
		
		MappedProperty newMappedProperty = new MappedProperty(property, propertyName);
		propertyByName.put(propertyName, newMappedProperty);
		propertyByAlias.put(propertyAlias, newMappedProperty);
		
		System.out.println("new relation -> " + propertyName);
		
		return propertyName;
	}
	
	public void refactorProperty(String propertyName){
		//pega a propriedade que ja possui esse nome e gera outro nome para ela
		MappedProperty existentMappedProperty = propertyByName.get(propertyName);
		if(existentMappedProperty != null){
			System.out.print(propertyName + " -> changed to -> ");
			propertyByName.put(propertyName, null);
			generatePropertyName(existentMappedProperty.getProperty());
		}		
	}
	
	public void generateAllPropertyNames(){
		System.out.println("Generating all property names");
		Set<Association> allAssociations = ontoParser.getAllInstances(RefOntoUML.Association.class);
		
		for (Association association : allAssociations) {
			generatePropertyName(association);
		}
		
		Set<Class> lstOntClass = ontoParser.getAllInstances(RefOntoUML.Class.class);
		for (Class ontCls : lstOntClass) {
			EList<Property> allAttributes = ontCls.getAttribute();
			for (Property attribute : allAttributes) {
				generatePropertyName(attribute);
			}
		}
		System.out.println();
	}
}
