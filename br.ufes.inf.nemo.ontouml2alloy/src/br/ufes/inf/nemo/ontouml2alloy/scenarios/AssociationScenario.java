package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Association;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLParser;

public abstract class AssociationScenario extends ContentScenario {

	private Association a;
	private boolean reverse;


	public AssociationScenario(OntoUMLParser parser, Association a, boolean isReversed) {
		super(parser);
		
		this.a=a;
		this.reverse = isReversed;
		
	}

	public Association getAssociation() {
		return a;
	}

	public void setAssociation(Association a) {
		this.a = a;
	}

	public void reverse(boolean reverse) {
		this.reverse = reverse;
	}
	
	public boolean isReverse() {
		return reverse;
	}

	public String getEnd() {
		
		if(a==null)
			return "";
		
		if(reverse)
			return parser.getAlias(a.getMemberEnd().get(0));
		return parser.getAlias(a.getMemberEnd().get(1));
	}

	public String getDomain() {
		if(a==null)
			return "";
		
		if(reverse)
			return parser.getAlias(a.getMemberEnd().get(1).getType()); 
		return parser.getAlias(a.getMemberEnd().get(0).getType());
	}
	
	public Type getSource() {
		if(a==null)
			return null;
		
		if(reverse)
			return a.getMemberEnd().get(1).getType(); 
		return a.getMemberEnd().get(0).getType();
	}
	
	public Type getTarget() {
		if(a==null)
			return null;
		
		if(reverse)
			return a.getMemberEnd().get(0).getType(); 
		return a.getMemberEnd().get(1).getType();
	}
}