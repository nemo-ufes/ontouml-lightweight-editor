package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum ParagraphType {
	PRED ("a constraint you want to explore"), 
	ASSERT ("a belief you want to check"), 
	FACT ("a necessary truth");
	
	private String name;
	
	private ParagraphType(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}