package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.HashMap;
import java.util.HashSet;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.Antipattern;

public abstract class OverlappingAntipattern<T extends OverlappingOccurrence> extends Antipattern<T> {

	public OverlappingAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public OverlappingAntipattern(Package pack) throws NullPointerException {
		super(pack);
	}
	
	public <B extends Association> HashMap<Classifier,HashSet<Property>> buildMainTypeAndPropertiesHash(Class<B> associationType ){
		HashMap<Classifier,HashSet<Property>> hash = new HashMap<Classifier,HashSet<Property>>();
		parser.buildChildrenHashes();
		
		//builds initial hash, with meronymics that are directly connected to the types
		for (Association m : parser.getAllInstances(associationType)) {
			
			try{
				Property property = getProperty(m);
				Classifier mainType = getMainType(m);
				
				if (hash.keySet().contains(mainType))
					hash.get(mainType).add(property);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(property);
					hash.put(mainType, properties);
				}
			}
			catch(Exception e){ }
		}
		
		//adds supertypes' parts
		for (Classifier mainType : hash.keySet()) 
			for (Classifier parent : mainType.allParents()) 
				if(hash.keySet().contains(parent))
					hash.get(mainType).addAll(hash.get(parent));
			
		return hash;
	}
	
	public abstract Property getProperty(Association a);
	public abstract Classifier getMainType(Association a);

}
