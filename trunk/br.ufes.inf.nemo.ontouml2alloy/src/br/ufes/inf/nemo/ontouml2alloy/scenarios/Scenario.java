package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public abstract class Scenario {
	
	private ParagraphType paragraphType;
	
	public abstract String getPhrase();
	public abstract String getAlloy();
	public abstract String getScenarioName();
	
	public ParagraphType getParagraphType(){
		return paragraphType;
	}
	
	public void setParagraphType(ParagraphType paragraphType) {
		this.paragraphType = paragraphType;
	}
	
	public boolean isPredicate(){
		return paragraphType == ParagraphType.PRED;
	}
	
	public boolean isFact(){
		return paragraphType == ParagraphType.FACT;
	}
	
	public boolean isAssertion(){
		return paragraphType == ParagraphType.ASSERT;
	}
	
	public void setAsPredicate(){
		this.paragraphType = ParagraphType.PRED;
	}
	
	public void setAsFact(){
		this.paragraphType = ParagraphType.FACT;
	}
	
	public void setAsAssertion(){
		this.paragraphType = ParagraphType.ASSERT;
	}
	
	@Override
	public String toString(){
		return paragraphType.getPhrase()+" "+getPhrase()+".";
	}
		
	public String getParagraph(){
		return 	paragraphType.getKeyword()+" "+getScenarioName()+" {"+
				"\n\t"+getAlloy()+
				"\n}";
	}
	
		
}
