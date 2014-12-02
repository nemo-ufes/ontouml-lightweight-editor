package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;

public class AssociationDepthScenario extends AssociationScenario {

	public enum BoundType {Upper, Lower}
	private BoundType bound;
	
	private int depth;
	private CustomQuantification worldQ, classQ;
	
	public AssociationDepthScenario (OntoUMLParser parser, Association a){
		super(parser,a,false);
		
		worldQ = CustomQuantification.createWorldQuantification(1);
		
		classQ = new CustomQuantification();
		classQ.setDisj(true);
		classQ.addQuantificationData("x", getDomain(), getLimit());
	
	}
	
	public AssociationDepthScenario(OntoUMLParser parser) {
		this(parser, null);
	}

	@Override 
	public String getDomain(){
		return "w."+super.getDomain();
	}
	
	public int getDepth(){
		return depth;
	}
	
	public BoundType getBound(){
		return bound;
	}
	
	public void setBound(BoundType bound){
		this.bound = bound;
	}
	
	public void setDepth(int depth){
		this.depth = depth;
		updateQuantificationData();
	}
	
	public boolean isLowerBound(){
		return bound==BoundType.Lower;
	}
	
	public boolean isUpperBound(){
		return bound==BoundType.Upper;
	}
	
	public void setAsLowerBound(int depth){
		bound = BoundType.Lower;
		this.depth = depth;
		updateQuantificationData();
	}
	
	public void setAsUpperBound(int depth){
		bound = BoundType.Upper;
		this.depth = depth;
		updateQuantificationData();
	}
	
	public int getLimit(){
		if(bound==BoundType.Upper)
			return depth+1;
		return depth;
	}

	public void setAssociation(Association a) {
		super.setAssociation(a);
		updateQuantificationData();
	}

	private void updateQuantificationData() {
		classQ.getQuantificationData(0).setDomain(getDomain());
		classQ.getQuantificationData(0).setNumberOfVariables(getLimit());
	}

	public CustomQuantification getWorldQuantification(){
		return worldQ;
	}
	
	public CustomQuantification getClassQuantification(){
		return classQ;
	}
	
	
	@Override
	public String getAlloy() {
		String expr = getRestriction();
		expr = classQ.addQuantification(expr);
		expr = worldQ.addQuantification(expr);
		
		return expr;
	}

	private String getRestriction() {
		String expr="";
		String worldVariable = worldQ.getVariable(0,1);
		
		for (int i = 1; i < getLimit(); i++) {
			String var1 = classQ.getVariable(0,i);
			String var2 = classQ.getVariable(0,i+1);
			
			expr += var2+" in "+getEnd()+"["+var1+","+worldVariable+"]";
			
			if(i<getLimit()-1)
				expr+=" and ";
		}
		
		return expr;
	}
	
	@Override
	public String getPhrase() {
		return "a story with "+getBoundPhrase()+" instances of "+OntoUMLNameHelper.getTypeAndName(getBaseClass(), true, true)+
				"consecutively connected through association "+OntoUMLNameHelper.getTypeAndName(getAssociation(), true, true);
	}
	
	private EObject getBaseClass() {
		return getSource();//TODO: fix if accept associations different from Self-Type Relationship
	}

	private String getBoundPhrase() {
		
		if(isLowerBound())
			return "at least "+depth;
		if(isUpperBound())
			return "at mmost"+depth;
		
		return "";	
	}

	@Override
	public String getScenarioName() {
		return "AssociationDepth";
	}


	
	

	
	
	
	
}
