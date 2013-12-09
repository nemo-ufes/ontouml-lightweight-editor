package br.ufes.inf.nemo.antipattern.undefformal;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.FormalAssociation;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class UndefFormalOccurrence extends AntipatternOccurrence {

	
	FormalAssociation formal;
	Classifier source, target;
	
	public UndefFormalOccurrence(FormalAssociation formal, OntoUMLParser parser) throws Exception {
		super(parser);

		if (formal==null)
			throw new NullPointerException("UndefFormal: null formal association provided.");
		if (!(formal instanceof FormalAssociation))
			throw new Exception("UndefFormal: a formal association instance is required.");
		if (formal.getMemberEnd().size()!=2 )
			throw new Exception("UndefFormal: a formal association must own exactly 2 ends.");
		if (formal.getMemberEnd().get(0).getType()==null || formal.getMemberEnd().get(1).getType()==null)
			throw new Exception("UndefFormal: one of the end types is null");
		
		this.formal = formal;
		this.source = (Classifier) formal.getMemberEnd().get(0).getType();
		this.target = (Classifier) formal.getMemberEnd().get(1).getType();
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(formal);
		selection.add(source);
		selection.add(target);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}
	
	@Override
	public String toString(){
		String result = "Source: "+super.parser.getStringRepresentation(this.source)+"\n"+
						"Target: "+super.parser.getStringRepresentation(this.target)+"\n"+
						"Formal: "+super.parser.getStringRepresentation(this.formal);
		
		return result;
	}

}
