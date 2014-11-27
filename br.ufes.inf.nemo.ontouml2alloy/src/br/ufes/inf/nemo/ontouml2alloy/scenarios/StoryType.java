package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum StoryType {
	
	LINEAR {
		public String toString() {
	        return "Linear";
	    }
	}, 
	FUTURES{
		public String toString() {
	        return "Alternative Futures";
	    }
	}, COUNTER{
		public String toString() {
	        return "Counterfactual Worlds";
	    }
	}, UNDEF{
		public String toString() {
	        return "Undefined";
	    }
	};
	
	
	
}