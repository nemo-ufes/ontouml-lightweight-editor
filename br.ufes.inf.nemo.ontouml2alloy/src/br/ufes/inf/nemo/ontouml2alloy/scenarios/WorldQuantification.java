package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public class WorldQuantification {
	
	int n;
	WorldQuantificationType type;
	
	public WorldQuantification() {
		super();
	}
	
	public void setAsStory() {
		type=WorldQuantificationType.STORY;
	}
	
	public void setAsEvery() {
		type=WorldQuantificationType.EVERY;
	}
	
	public void setAsNo() {
		type=WorldQuantificationType.NO;
	}
	
	public void setAsSome() {
		type=WorldQuantificationType.SOME;
	}
	
	public void setAsLeast(int n){
		type=WorldQuantificationType.ATLEAST;
		this.n = n;
	}
	
	public void setAsMost(int n){
		type=WorldQuantificationType.ATMOST;
		this.n = n;
	}
	
	public void setAsExactly(int n){
		type=WorldQuantificationType.EXACTLY;
		this.n = n;
	}

	public String addQuantification(String expression){
		
		if (type==WorldQuantificationType.EVERY)
			return "all w:World | "+expression;
		if (type==WorldQuantificationType.NO)
			return "no w:World | "+expression;
		if (type==WorldQuantificationType.SOME)
			return "some w:World | "+expression;
		if (isNumeric()){
			String s = "#{ w:World | "+expression+"}";
			
			if(type==WorldQuantificationType.ATLEAST)
				return s+">="+n;
			if(type==WorldQuantificationType.ATMOST)
				return s+"<="+n;
			if(type==WorldQuantificationType.EXACTLY)
				return s+"="+n;
		}

		return expression;
	}

	public boolean isNumeric() {
		return type==WorldQuantificationType.ATLEAST || type==WorldQuantificationType.ATMOST || type==WorldQuantificationType.EXACTLY;
	}
	
	public String getWorldVariable(){
		if(type==WorldQuantificationType.STORY)
			return "World";
		
		return "w";
	}

	public WorldQuantificationType getType() {
		return type;
	}

	public int getValue() {
		return n;
	}
	
	public void setValue(int n){
		this.n = n;
	}

	public void setType(WorldQuantificationType type) {
		this.type = type;
	}

	
}
