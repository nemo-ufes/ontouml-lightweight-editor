package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;

public abstract class AssociationScenario extends ContentScenario {

	private Association a;
	private boolean reverse;


	public AssociationScenario(OntoUMLParser parser, Association a, boolean isReversed) {
		super(parser);
		
		this.a=a;
		this.reverse = isReversed;
		
	}

	public Association getA() {
		return a;
	}

	public void setA(Association a) {
		this.a = a;
	}

	public void reverse(boolean reverse) {
		this.reverse = reverse;
	}
	
	public boolean isReverse() {
		return reverse;
	}

	
	
	public String getEnd() {
		if(reverse)
			return parser.getAlias(a.getMemberEnd().get(0));
		return parser.getAlias(a.getMemberEnd().get(1));
	}

	public String getDomain() {
		
		if(reverse)
			return parser.getAlias(a.getMemberEnd().get(1).getType()); 
		return parser.getAlias(a.getMemberEnd().get(0).getType());
	}
}