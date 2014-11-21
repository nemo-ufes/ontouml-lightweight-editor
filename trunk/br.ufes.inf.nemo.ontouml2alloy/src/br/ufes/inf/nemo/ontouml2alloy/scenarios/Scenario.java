package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public abstract class Scenario {
	
	enum MODE {PRED, ASSERT, FACT}
	private MODE mode;
	
	public abstract String getString();
	public abstract String getAlloy();
	public abstract String getScenarioName();
	
	public MODE getMode(){
		return mode;
	}
	
	public void setMode(MODE mode){
		this.mode = mode;
	}
	
	public String getModeString(){
		
		if (mode==MODE.PRED)
			return "Is want to see";
		if (mode==MODE.ASSERT)
			return "The model only allows";
		if (mode==MODE.FACT)
			return "I want to enforce";
		
		return "";
	}
	
	private String getModeKeyword() {
		
		if (mode==MODE.PRED)
			return "pred";
		if (mode==MODE.ASSERT)
			return "assert";
		if (mode==MODE.FACT)
			return "fact";
		
		return "";
	}
	
	public String getNaturalLanguageTranslation(){
		return getModeString()+" "+getString()+".";
	}
		
	public String getExpression(){
		return 	getModeKeyword()+" "+getScenarioName()+" {"+
				"\n\t"+getAlloy()+
				"}";
	}
		
}
