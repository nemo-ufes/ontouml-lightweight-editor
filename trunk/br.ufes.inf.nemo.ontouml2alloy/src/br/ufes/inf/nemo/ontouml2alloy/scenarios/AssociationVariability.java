package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum AssociationVariability {
	DIF("Different"), 
	EQUAL("Equal"), 
	DISJ("Disjoint");
	
	String name;
	
	private AssociationVariability(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}