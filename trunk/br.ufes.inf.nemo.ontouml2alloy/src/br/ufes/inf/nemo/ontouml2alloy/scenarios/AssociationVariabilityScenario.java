package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;

public class AssociationVariabilityScenario extends AssociationScenario {

	private AssociationVariability variability;
	
	public AssociationVariabilityScenario (OntoUMLParser parser){
		super(parser, null, false);
	}
	
	public AssociationVariabilityScenario (OntoUMLParser parser, Association a, boolean isReversed){
		super(parser, a, isReversed);
	}
	
	private String getSuffix() {
		if(variability==AssociationVariability.DISJ)
			return "]";
		return "";
	}

	private String getPrefix() {
		if(variability==AssociationVariability.DISJ)
			return "disj[";
		return "";
	}

	private String getOperator() {
		switch (variability) {
		case DIF:
			return "!=";
		case DISJ:
			return ",";
		case EQUAL:
			return "=";
		default:
			return "";
		}
	}

	public void setIsChangeable(){
		variability=AssociationVariability.DIF;
	}
	
	public void setIsConstant(){
		variability=AssociationVariability.EQUAL;
	}
	
	public void setIsDisjoint(){
		variability=AssociationVariability.DISJ;
	}
	
	public boolean isChangeable(){
		return variability==AssociationVariability.DIF;
	}
	
	public boolean isConstant(){
		return variability==AssociationVariability.EQUAL;
	}
	
	public boolean isDisjoint(){
		return variability==AssociationVariability.DISJ;
	}
	
	@Override
	public String getAlloy() {
		String domain = getDomain();
		String end = getEnd();
		String op = getOperator();
		String prefix = getPrefix();
		String suffix = getSuffix();
		
		return "all w1,w2: World | w1!=w2 implies (all x:w1."+domain+" | x in w2."+domain+" implies "+prefix+"x."+end+"[w1]"+op+"x."+end+"[w2]"+suffix+")";
	}
	
	@Override
	public String getPhrase() {
		return "a story in which the instances of "+OntoUMLNameHelper.getTypeAndName(getSource(), true, true)+
				" "+variabilityPhrase()+" instances of "+OntoUMLNameHelper.getTypeAndName(getTarget(), true, true)+
				", through association "+OntoUMLNameHelper.getTypeAndName(getAssociation(), true, true)+", in every world";
	}
	
	private String variabilityPhrase() {
		if(variability==AssociationVariability.EQUAL)
			return "are always connected to the same";
		if(variability==AssociationVariability.DIF)
			return "are never connected to the same";
		if(variability==AssociationVariability.DISJ)
			return "are always connected to completely different";
		
		return "";
	}

	@Override
	public String getScenarioName() {
		return "AssociationVariability";
	}

	public AssociationVariability getVariability() {
		return variability;
	}
	
	public void setVariability(AssociationVariability variability){
		this.variability = variability;
	}
	
	
}
