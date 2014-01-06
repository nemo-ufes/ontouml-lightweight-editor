package br.ufes.inf.nemo.antipattern.binover;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;

public abstract class  BinOverOccurrence extends AntipatternOccurrence {

	Association association;
	Classifier source,target;
	
	
	
	public BinOverOccurrence(Association a, Antipattern<?> ap) {
		super(ap);
		
		this.association = a;
		this.source = (Classifier) a.getMemberEnd().get(0).getType();
		this.target= (Classifier) a.getMemberEnd().get(1).getType();
	}

	public Association getAssociation() {
		return association;
	}

	public Classifier getSource() {
		return source;
	}

	public Classifier getTarget() {
		return target;
	}

	@Override
	public String toString(){
		return 	"Association: "+parser.getStringRepresentation(association)+
				"\nSource: "+parser.getStringRepresentation(source)+
				"\nTarget: "+parser.getStringRepresentation(target);
	}
	
	@Override
	public String getShortName() {
		return parser.getStringRepresentation(association);
	}

}
