package br.ufes.inf.nemo.antipattern.binover;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class  BinOverOccurrence extends AntipatternOccurrence {

	Association association;
	Classifier source,target;
	
	
	
	public BinOverOccurrence(Association a, OntoUMLParser parser) {
		super(parser);
		
		this.association = a;
		this.source = (Classifier) a.getMemberEnd().get(0).getType();
		this.target= (Classifier) a.getMemberEnd().get(1).getType();
	}

	@Override
	public String toString(){
		return 	"Association: "+parser.getStringRepresentation(association)+
				"\nSource: "+parser.getStringRepresentation(source)+
				"\nTarget: "+parser.getStringRepresentation(target);
	}

}
