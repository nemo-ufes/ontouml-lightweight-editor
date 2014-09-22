package br.ufes.inf.nemo.antipattern.wholeover;

import java.util.HashSet;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;

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
		
		result ="Whole: "+OntoUMLNameHelper.getTypeAndName(getWhole(), true, false)+"\n"+
				"All Parts: ";
				
		for (Property p : getAllPartEnds()){
			result+="\n\t"+OntoUMLNameHelper.getNameAndType(p);
			if(p.getOpposite()!=null && p.getOpposite().getType().equals(getMainType()))
				result+=" (direct)";
			else
				result+=" (from: "+OntoUMLNameHelper.getTypeAndName(p.getOpposite().getType(), true, false)+")";
		}
		for (OverlappingGroup variation : getGroups()) {
			result+="\n\n"+variation.toString();
		}
		return result;
	}

	@Override
	public String getShortName() {
		return "Whole: "+OntoUMLNameHelper.getTypeAndName(getWhole(), true, false);
	}

	@Override
	public String getPropertyTypeString() {
		return "part types of";
	}

	@Override
	public String getExclusiveExample() {
		return 	"A scientific event organization committee (the whole base) is composed by people with different assignments and responsibilities. " +
				"There’s the Event Chairman, the Local Organization Chairs, the Technical Program Chairs, amongst others (which define the association set to the part types). "+
				"\r\n\r\n"+
				"If it is possible for the same person to perform all roles in the same conference, the memberships from the whole committee to the different " +
				"members  are non-exclusive. If a person can only have at most one position in a given committee, the relations set is said to exclusive.";
		 
	}

	@Override
	public String getBaseClassType() {
		return "Whole";
	}

	@Override
	public String getGroupTypeLine() {
		return "The current overlapping group of whole "+OntoUMLNameHelper.getTypeAndName(getWhole(),true, true)+" is composed by the following parts:";
	}
	
		
}
