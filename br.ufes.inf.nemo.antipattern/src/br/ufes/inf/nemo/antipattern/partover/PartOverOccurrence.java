package br.ufes.inf.nemo.antipattern.partover;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class PartOverOccurrence extends AntipatternOccurrence{

	Classifier part;
	ArrayList<Property> wholeEnds;
	
	/*TODO: Adapt methods from RelOver to get supertypes...*/
	
	public PartOverOccurrence(Classifier part, ArrayList<Property> wholeEnds, OntoUMLParser parser) throws Exception {
		super(parser);
		
		if(part == null)
			throw new Exception(PartOverAntipattern.getAntipatternInfo().acronym+": provided input class is null. Can't create occurrence!");
		if(wholeEnds == null)
			throw new Exception(PartOverAntipattern.getAntipatternInfo().acronym+": provided input properties list is null. Can't create occurrence!");
		if(wholeEnds.size()<2)
			throw new Exception(PartOverAntipattern.getAntipatternInfo().acronym+": there must be at least two whole ends. Can't create occurrence!");
		
		this.part = part;
		this.wholeEnds = new ArrayList<Property>();
		
		for (Property wholeEnd : wholeEnds) {
			if(wholeEnd.getType().equals(part) || part.allParents().contains(wholeEnd.getType()))
				this.wholeEnds.add(wholeEnd);
		}
		
		if(this.wholeEnds.size()<2)
			throw new Exception(PartOverAntipattern.getAntipatternInfo().acronym+": there must be at least two whole ends. Can't create occurrence!");
		
		
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		for (Property p : this.wholeEnds){
			selection.add(p.getType());
			selection.add(p.getOpposite().getType());
			selection.add(p.getAssociation());
		}
		
		selection.add(part);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.ALL_ANCESTORS, false);
		return parser;
	}
	
	@Override
	public String toString() {
		String result;
		
		result = "Part: "+super.parser.getStringRepresentation(this.part)+"\n"+
				 "Wholes: ";
				
		for (Property p : this.wholeEnds)
			result+="\n\t"+super.parser.getStringRepresentation(p.getType());
		/*
		result+="\nSupertypes: ";
		
		int i = 1;
		for (Classifier supertype : this.commonSupertypes.keySet()) {
			result += "\n\tSupertype #"+i+": "+super.parser.getStringRepresentation(supertype);
			for (Property p : this.commonSupertypes.get(supertype)) {
				result += "\n\t\t"+super.parser.getStringRepresentation(p);
			}
			
			i++;
		}*/
		return result;
	}
	

}
