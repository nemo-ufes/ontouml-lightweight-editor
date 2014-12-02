package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum BinaryOperator {
	EQUAL{
		public String toString() {
	        return "=";
	    }
	},  
	LESSER{
		public String toString() {
	        return "<";
	    }
	},  
	LESSER_EQ{
		public String toString() {
	        return "<=";
	    }
	},  
	GREATER{
		public String toString() {
	        return ">";
	    }
	},  
	GREATER_EQ{
		public String toString() {
	        return ">=";
	    }
	},  
	DIF{
		public String toString() {
	        return "!=";
	    }
	};
	
	public String getExpression(String leftExpression, String rightExpression){
		return leftExpression+toString()+rightExpression;
	}
	
	
}