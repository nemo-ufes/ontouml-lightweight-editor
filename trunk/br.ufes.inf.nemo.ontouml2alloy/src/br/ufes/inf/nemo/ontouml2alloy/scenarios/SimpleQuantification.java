package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public class SimpleQuantification {
	
	enum Value {STORY, EVERY, NO, SOME, ATLEAST, ATMOST, EXACTLY};
	int n;
	Value q;
	
	public SimpleQuantification() {
		super();
	}
	
	public void setAsStory() {
		q=Value.STORY;
	}
	
	public void setAsEvery() {
		q=Value.EVERY;
	}
	
	public void setAsNo() {
		q=Value.NO;
	}
	
	public void setAsSome() {
		q=Value.SOME;
	}
	
	public void setAsLeast(int n){
		q=Value.ATLEAST;
		this.n = n;
	}
	
	public void setAsMost(int n){
		q=Value.ATMOST;
		this.n = n;
	}
	
	public void setAsExactly(int n){
		q=Value.EXACTLY;
		this.n = n;
	}

	public String addQuantification(String expression){
		
		if (q==Value.EVERY)
			return "all w:World | "+expression;
		if (q==Value.NO)
			return "no w:World | "+expression;
		if (q==Value.SOME)
			return "some w:World | "+expression;
		if (q==Value.ATLEAST || q==Value.ATMOST || q==Value.EXACTLY){
			String s = "#{ w:World | "+expression+"}";
			
			if(q==Value.ATLEAST)
				return s+">="+n;
			if(q==Value.ATMOST)
				return s+"<="+n;
			if(q==Value.EXACTLY)
				return s+"="+n;
		}

		return expression;
	}
	
	public String getWorldVariable(){
		if(q==Value.STORY)
			return "World";
		
		return "w";
	}
}
