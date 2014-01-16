package br.ufes.inf.nemo.antipattern.partover;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingTypesVariation;

public class PartOverOccurrence extends OverlappingOccurrence{

	public PartOverOccurrence(Classifier part, ArrayList<Property> wholeEnds, PartOverAntipattern ap) throws Exception {
		super(ap, part, wholeEnds);
		
		for (Property p : getAllWholeEnds()) {
			if(!(p.getAssociation() instanceof Meronymic))
				throw new Exception(PartOverAntipattern.getAntipatternInfo().getAcronym()+": All provided properties must belong to a meronymc.");
		}
	}
	
	public Classifier getPart(){
		return getMainType();
	}
	
	public ArrayList<Property> getAllWholeEnds(){
		return getAllProperties();
	}
	
	@Override
	public String toString() {
		String result;
		
		result = "Part: "+getParser().getStringRepresentation(getPart())+"\n"+
				 "All Wholes: ";
				
		for (Property p : getAllWholeEnds())
			result+="\n\t"+getParser().getStringRepresentation(p);
				
		for (OverlappingTypesVariation variation : getVariations()) {
			result+="\n\n"+variation.toString();
		}
		
		return result;
	}

	@Override
	public String getShortName() {
		return "Part: "+parser.getStringRepresentation(getPart());
	}
	

}
