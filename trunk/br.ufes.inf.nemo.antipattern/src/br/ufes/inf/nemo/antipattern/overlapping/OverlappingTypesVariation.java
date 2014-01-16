package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.list.Combination;

public abstract class OverlappingTypesVariation {
	OverlappingOccurrence occurrence;
	protected ArrayList<Property> overlappingProperties;
	protected ArrayList<Classifier> overlappingTypes;
	boolean validVariation;
	
	public OverlappingTypesVariation(OverlappingOccurrence occurrence, ArrayList<Property> overlappingProperties) throws Exception{
		this.occurrence = occurrence;
		this.overlappingProperties = overlappingProperties;
		this.overlappingTypes = new ArrayList<Classifier>();
		
		for (Property p : overlappingProperties) {
			overlappingTypes.add((Classifier) p.getType());
		}
		
		if(overlappingProperties.size()<2)
			throw new Exception("VAR: More then one part is required.");
	}
	
	public abstract boolean makeEndsDisjoint(ArrayList<Property> overlappingProperties);
	
	public boolean makeEndsExclusive(ArrayList<Property> selectedProperties) {
		if(!this.overlappingProperties.containsAll(selectedProperties) || selectedProperties.size()<2)
			return false;
		
		String contextName, invName = "exclusiveTypes", invRule="";
		
		contextName = occurrence.addQuotes(occurrence.getMainType().getName());
		
		Combination comb = new Combination(selectedProperties, 2);
		
		while(comb.hasNext()){
			ArrayList<Property> result = comb.next();
			String p1 = occurrence.addQuotes(result.get(0).getName());
			String p2 = occurrence.addQuotes(result.get(1).getName());
			
			invRule+= "self."+p1+"->asSet()->excludesAll(self."+p2+"->asSet())";
			if(comb.hasNext())
				invRule += " and ";
		}
		
		occurrence.getFix().addAll(occurrence.getFixer().generateOCLRule(contextName, invName, invRule));
		
		return true;
	}
}
