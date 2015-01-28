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
	private String outputMessages = "";
	
	public String getOutputMessages() {
		return outputMessages;
	}
	
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
			propertyName = generatePropertyName(property, null);
		}
		
		return propertyName;
	}

	private String generatePropertyName(NamedElement property, MappedProperty superMappedProperty) {
		String propertyName = property.getName();
		String initialName = propertyName;

		String propertyAlias = ontoParser.getAlias(property);
		propertyAlias = propertyAlias.replace(" ", "_");
		
		if(propertyName == null)
			propertyName = propertyAlias;
		else
			propertyName = propertyName.replace(" ", "_");
		
		String source = null;
		String target = null;
		String stereotype = "";
		
		if(property instanceof Association){
			EList<Property> memberEnds = ((Association) property).getMemberEnd();
//			source = memberEnds.get(0).getName();
//			target = memberEnds.get(1).getName();
			source = ((Association) property).getMemberEnd().get(0).getType().getName();
			target = ((Association) property).getMemberEnd().get(1).getType().getName();
			stereotype = ontoParser.getStereotype(property);
		}else{
			source = ((PropertyImpl)property).getClass_().getName();
			target = ((PropertyImpl)property).getType().getName();
			System.out.println();
		}
		
		source = source.replace(" ", "_");
		target = target.replace(" ", "_");
		stereotype = stereotype.replace(" ", "_");
		
		if(stereotype.length() > 0){
			String aux = stereotype.substring(0, 1);
			aux = aux.toLowerCase();
			
			stereotype = aux + stereotype.substring(1);
		}
		
		if(property instanceof Association && (propertyName == null || propertyName.equals(""))){
			propertyName = stereotype + "." + source + "." + target;
			outputMessages += "Warning: An unnamed Association from <"+source+"> (source class) to <"+target+"> (target class) was mapped to OWL <"+propertyName+">;\n";
		}else if(property instanceof PropertyImpl){
			propertyName = source + "." + propertyName;
		}
		
		MappedProperty secondAbstract = null, thirdAbstract = null;
		if(propertyByName.containsKey(propertyName)){
			secondAbstract = refactorProperty(propertyName);
			
			//concatena source e target no NOME da nova propriedade
			if(property instanceof Association){
				propertyName = propertyName + "." + source + "." + target;
			}else{
				
			}
		}
		
		if(propertyByName.containsKey(propertyName)){
			thirdAbstract = refactorProperty(propertyName);
			
			//concatena source e target no ALIAS da nova propriedade
			if(property instanceof Association){
				propertyName = propertyAlias + "." + source + "." + target;
			}else{
				
			}
		}
		
		MappedProperty newMappedProperty = new MappedProperty(property, propertyName);
		if(secondAbstract != null){
			newMappedProperty.setMappedAsSubPropertyOf(secondAbstract);
		}else if(superMappedProperty != null){
			newMappedProperty.setMappedAsSubPropertyOf(superMappedProperty);
		}else if(thirdAbstract != null){
			newMappedProperty.setMappedAsSubPropertyOf(thirdAbstract);
		}
		
		if(newMappedProperty.isMappedAsSubPropertyOf()){
			String superName = newMappedProperty.getMappedAsSubPropertyOf().getGeneratedName();
			outputMessages += "Warning: The association <"+initialName+"> with repeted name was mapped as subPropertyOf <"+superName+"> with the name <"+propertyName+">;\n";
		}
		
		propertyByName.put(propertyName, newMappedProperty);
		propertyByAlias.put(propertyAlias, newMappedProperty);
		
		//System.out.println("new relation -> " + propertyName);
		
		return propertyName;
	}
	
	public MappedProperty refactorProperty(String propertyName){
		//pega a propriedade que ja possui esse nome e gera outro nome para ela
		MappedProperty existentMappedProperty = propertyByName.get(propertyName);
		MappedProperty abstractMappedProperty = existentMappedProperty;
		if(existentMappedProperty.isAbstract() == false){
			//System.out.print(propertyName + " -> changed to -> ");
			abstractMappedProperty = new MappedProperty(null, propertyName, true);
			//existentMappedProperty.setMappedAsSubPropertyOf(abstractMappedProperty);
			propertyByName.put(propertyName, abstractMappedProperty);
			generatePropertyName(existentMappedProperty.getProperty(), abstractMappedProperty);
		}		
		return abstractMappedProperty;
	}
	
	public void generateAllPropertyNames(){
		System.out.println("Generating all property names");
		Set<Association> allAssociations = ontoParser.getAllInstances(RefOntoUML.Association.class);
		
		for (Association association : allAssociations) {
			//System.out.println(association.getName());
			generatePropertyName(association, null);
		}
		
		Set<Class> lstOntClass = ontoParser.getAllInstances(RefOntoUML.Class.class);
		for (Class ontCls : lstOntClass) {
			EList<Property> allAttributes = ontCls.getAttribute();
			for (Property attribute : allAttributes) {
				generatePropertyName(attribute, null);
			}
		}
		
		System.out.println(outputMessages);
	}
	
	public boolean isMappedAsSubRelationOf(NamedElement property){
		String alias = ontoParser.getAlias(property);
		MappedProperty mappedProperty = propertyByAlias.get(alias);
		
		if(mappedProperty.isMappedAsSubPropertyOf()){
			return true;
		}
		
		return false;
	}
	
	public String getSuperPropertyName(NamedElement property){
		String alias = ontoParser.getAlias(property);
		MappedProperty mappedProperty = propertyByAlias.get(alias);
		
		MappedProperty superMappedProperty = mappedProperty.getMappedAsSubPropertyOf();
		
		return superMappedProperty.getGeneratedName();
	}
}
