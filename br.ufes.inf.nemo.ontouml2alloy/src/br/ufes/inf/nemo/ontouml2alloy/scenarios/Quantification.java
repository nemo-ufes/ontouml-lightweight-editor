package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public class Quantification {
	
	enum VALUE {STORY, EVERY, NO, SOME, ATLEAST, ATMOST, EXACTLY};
	int n;
	VALUE q;
	
	public String addQuantification(String expression){
		
		if (q==VALUE.EVERY)
			return "all w:World | "+expression;
		if (q==VALUE.NO)
			return "no w:World | "+expression;
		if (q==VALUE.SOME)
			return "some w:World | "+expression;
		if (q==VALUE.ATLEAST || q==VALUE.ATMOST || q==VALUE.EXACTLY){
			String s = "#{ w:World | "+expression+"}";
			
			if(q==VALUE.ATLEAST)
				return s+">="+n;
			if(q==VALUE.ATMOST)
				return s+"<="+n;
			if(q==VALUE.EXACTLY)
				return s+"="+n;
		}

		return "";
	}
	
	public String getWorldVariable(){
		if(q==VALUE.EVERY)
			return "World";
		return "w";
	}
}
