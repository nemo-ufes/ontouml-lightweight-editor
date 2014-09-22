package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.list.Combination;

public abstract class OverlappingGroup {

	protected ArrayList<Property> overlappingProperties;
	protected ArrayList<Classifier> overlappingTypes;
	protected Antipattern<?> antipattern;
	boolean validGroup;
	
	
	public OverlappingGroup(ArrayList<Property> overlappingProperties, Antipattern<?> antipattern) throws Exception{
		this.antipattern = antipattern;
		this.overlappingProperties = overlappingProperties;
		this.overlappingTypes = new ArrayList<Classifier>();
		
		for (Property p : overlappingProperties) {
			overlappingTypes.add((Classifier) p.getType());
		}
		
		if(overlappingProperties.size()<2)
			throw new Exception("OVER_GROUP: More then one property is required.");
	}
	
	
	public boolean makeEndsExclusive(OverlappingOccurrence overOccurrence, ArrayList<Property> selectedProperties) {
		
		if(!this.overlappingProperties.containsAll(selectedProperties) || selectedProperties.size()<2)
			return false;
		
		for (Property p : selectedProperties)
			overOccurrence.getFix().addAll(overOccurrence.getFixer().fixPropertyName(p));
		
		String contextName, invName = "exclusiveTypes", invRule="";
		
		contextName = overOccurrence.getMainType().getName();
		
		Combination comb = new Combination(selectedProperties, 2);
		
		while(comb.hasNext()){
			ArrayList<Property> result = comb.next();
			String p1 = overOccurrence.addQuotes(result.get(0).getName());
			String p2 = overOccurrence.addQuotes(result.get(1).getName());
			
			invRule+= "self."+p1+"->asSet()->excludesAll(self."+p2+"->asSet())";
			if(comb.hasNext())
				invRule += " and ";
		}
		
		overOccurrence.getFix().addAll(overOccurrence.getFixer().generateOCLInvariant(contextName, invName, invRule));
		
		return true;
	}
	
	public ArrayList<Property> getOverlappingProperties() {
		return overlappingProperties;
	}

	public ArrayList<Classifier> getOverlappingTypes() {
		return overlappingTypes;
	}
	
	public String getOverlappingTypesString(){
		String result = new String();
		String typeName;
		for (int i = 0; i < overlappingTypes.size(); i++) {
			typeName = OntoUMLNameHelper.getTypeAndName(overlappingTypes.get(i), true, true);
			
			if(i==0){
				result += typeName;
			}
			else if(i<overlappingTypes.size()-1){
				result += ", "+typeName;
			}
			else{
				result += " and "+typeName;
			}
		}
		return result;
	}

	public boolean isValidVariation() {
		return validGroup;
	}
	
	public Antipattern<?> getAntipattern() {
		return antipattern;
	}
	
	public abstract String getType();
	public abstract Composite createComposite(Composite parent, int style);
	public abstract boolean makeEndsDisjoint(AntipatternOccurrence occurrence, ArrayList<Property> overlappingProperties);
	
}
