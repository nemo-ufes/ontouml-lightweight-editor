package br.ufes.inf.nemo.antipattern.imppart;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ImpPartOccurrence extends AntipatternOccurrence {

	Classifier whole, part;
	ArrayList<Classifier> partSubtypes;
	Meronymic meronymic;
	Property partEnd, wholeEnd;
	
	public ImpPartOccurrence(Property partEnd, ImpPartAntipattern ap) throws Exception {
		super(ap);
		
		this.partEnd = partEnd;
		this.wholeEnd = partEnd.getOpposite();
		
		this.whole = (Classifier) this.wholeEnd.getType();
		this.part = (Classifier) this.partEnd.getType();
		
		if (partEnd.getAssociation() instanceof Meronymic)
			this.meronymic = (Meronymic) partEnd.getAssociation();
		else 
			throw new Exception("ImpPart: "+parser.getStringRepresentation(meronymic)+" must be a part-whole relation");
		
		partSubtypes = new ArrayList<Classifier>();
		partSubtypes.addAll(part.allChildren());
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(meronymic);
		selection.add(whole);
		selection.add(part);
		selection.addAll(this.partSubtypes);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, false);
		return parser;
	}
	
	@Override
	public String toString() {
		
		String result = "Whole: "+parser.getStringRepresentation(whole)+
						"\nPart: "+parser.getStringRepresentation(part)+
						"\nMeronymic: "+parser.getStringRepresentation(meronymic)+
						"\nPart Subtypes:";
		
		for (Classifier subtype : partSubtypes) {
			result+="\n\t"+parser.getStringRepresentation(subtype);
		}
		
		return result;
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(meronymic);
	}

}
