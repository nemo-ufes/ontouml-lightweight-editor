package br.ufes.inf.nemo.antipattern.wholeover;

import java.util.HashSet;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;

public class WholeOverOccurrence extends OverlappingOccurrence {

	public WholeOverOccurrence(Classifier whole, HashSet<Property> partEnds, WholeOverAntipattern ap) throws Exception {
		super(ap, whole, partEnds);
		
		for (Property p : getAllPartEnds()) {
			if(!(p.getAssociation() instanceof Meronymic))
				throw new Exception(WholeOverAntipattern.getAntipatternInfo().getAcronym()+": All provided properties must belong to a meronymc.");
		}
		
		if(!verifyMinimumUpperCardinalitySum(3))
			throw new Exception(WholeOverAntipattern.getAntipatternInfo().getAcronym()+": The sum of the upper cardinality is lower than 3.");
		
	}
	
	public Classifier getWhole() {
		return getMainType();
	}

	public HashSet<Property> getAllPartEnds() {
		return getAllProperties();
	}
	
	@Override
	public String toString() {
		String result;
		
		result = "Whole: "+getParser().getStringRepresentation(getWhole())+"\n"+
				"All Parts: ";
				
		for (Property p : getAllPartEnds())
			result+="\n\t"+getParser().getStringRepresentation(p);
				
		for (OverlappingGroup variation : getVariations()) {
			result+="\n\n"+variation.toString();
		}
		return result;
	}

	@Override
	public String getShortName() {
		return "Whole: "+ parser.getStringRepresentation(getWhole());
	}	
}
