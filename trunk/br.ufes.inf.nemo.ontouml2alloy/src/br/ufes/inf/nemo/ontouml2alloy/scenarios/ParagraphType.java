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
	
	public String getPhrase(){
		
		if (this==PRED)
			return "I want to see";
		if (this==ASSERT)
			return "I believe my model requires";
		if (this==FACT)
			return "It is always true that";//TODO: fix this phrase
		
		return "";
	}
	
	public String getKeyword() {
		
		if (this==PRED)
			return "pred";
		if (this==ASSERT)
			return "assert";
		if (this==FACT)
			return "fact";
		
		return "";
	}
}